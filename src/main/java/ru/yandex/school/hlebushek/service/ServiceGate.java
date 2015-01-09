package ru.yandex.school.hlebushek.service;

import com.google.gson.JsonElement;
import ru.yandex.school.hlebushek.common.CookiesService;
import ru.yandex.school.hlebushek.common.GitRepositoryState;
import ru.yandex.school.hlebushek.exceptions.ServiceGateException;
import ru.yandex.school.hlebushek.models.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Properties;

@Path("/")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ServiceGate extends ServiceResult {

    private GitRepositoryState gitState;

    /**
     * Method return json response users model
     * @param userId int { /service/users?user_id=num }
     * @param login String { /service/users?login=string }
     * @return JsonObject by String
     */
    @GET
    @Path("users")
    public String getUser(
            @QueryParam("user_id") int userId,
            @DefaultValue("") @QueryParam("login") String login) {
        ServiceGateException exception = null;
        JsonElement json = null;
        try {
            json = new UsersData().getUser(userId, login);
        }catch (ServiceGateException e) {
            exception = e;
        }
        return create(json, exception).toString();
    }

    /**
     * Method return json response new post model
     * @param login String { /service/users?login=string }
     * @param firstName String { /service/users?first_name=string }
     * @param lastName String { /service/users?last_name=string }
     * @param password String { /service/users?password=string }
     */
    @POST
    @Path("users")
    public void setUser(
            @DefaultValue("") @FormParam("login") String login,
            @DefaultValue("") @FormParam("first_name") String firstName,
            @DefaultValue("") @FormParam("last_name") String lastName,
            @DefaultValue("") @FormParam("password") String password,
            @Context HttpServletRequest request,
            @Context HttpServletResponse response) throws IOException {
        String referer = request.getHeader("referer");
        try {
            if (!login.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() && !password.isEmpty()) {
                Users user = new UsersData().setUser(login, firstName, lastName, password);
                if (user != null) {
                    response.addCookie(CookiesService.setCookieWithUserId(user.getUserId()));
                    response.sendRedirect(String.format("%s#/add-new_post",referer));
                }
            }
        }catch (ServiceGateException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method return json response posts model
     * without parameter method return all posts
     * @param postId int { /service/posts?post_id=num }
     * @param authorId int { /service/posts?author_id=num }
     * @return JsonObject by String
     */
    @GET
    @Path("posts")
    public String getPosts(
            @QueryParam("post_id") int postId,
            @QueryParam("author_id") int authorId) {
        ServiceGateException exception = null;
        JsonElement json = null;
        try {
            json = new PostsData().getPosts(postId, authorId);
        }catch (ServiceGateException e) {
            exception = e;
        }
        return create(json, exception).toString();
    }

    /**
     * Method return json response new post model
     * @param authorId int { /service/posts?author_id=num }
     * @param title String { /service/posts?title=string }
     * @param message String { /service/posts?message=string }
     */
    @POST
    @Path("posts")
    public void setPost(
            @FormParam("author_id") int authorId,
            @DefaultValue("") @FormParam("title") String title,
            @DefaultValue("") @FormParam("message") String message,
            @Context HttpServletRequest request,
            @Context HttpServletResponse response) throws IOException {
        String referer = request.getHeader("referer");
        try {
            if (authorId != 0 && !title.isEmpty() && !message.isEmpty()) {
                new PostsData().setPost(authorId, title, message);
                response.sendRedirect(String.format("%s#/all_posts/user%s",referer, authorId));
            }
        }catch (ServiceGateException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method return json response comments model
     * @param postId int { /service/comments?post_id=num }
     * @param authorId int { /service/comments?author_id=num }
     * @return JsonObject by String
     */
    @GET
    @Path("comments")
    public String getComments(
            @QueryParam("post_id") int postId,
            @QueryParam("author_id") int authorId) {
        ServiceGateException exception = null;
        JsonElement json = null;
        try {
            json = new CommentsData().getComments(postId, authorId);
        } catch (ServiceGateException e) {
            exception = e;
        }
        return ServiceResult.create(json, exception).toString();
    }

    /**
     * Method return json response new comment model
     * @param userId int { /service/comments?user_id=num }
     * @param postId int { /service/comments?post_id=num }
     * @param authorId int { /service/comments?author_id=num }
     * @param message String { /service/comments?message=string }
     */
    @POST
    @Path("comments")
    public void setComment(
            @FormParam("user_id") int userId,
            @FormParam("post_id") int postId,
            @FormParam("author_id") int authorId,
            @DefaultValue("") @FormParam("message") String message,
            @Context HttpServletRequest request,
            @Context HttpServletResponse response) {
        String referer = request.getHeader("referer");
        try {
            if (postId != 0 && authorId != 0 && !message.isEmpty()) {
                new CommentsData().setComment(postId, authorId, message);
                response.sendRedirect(String.format("%s#/user%s/post%s",referer, userId, postId));
            }
        }catch (ServiceGateException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method return git info
     * @return GitRepositoryState
     * @throws IOException
     */
    @GET
    @Path("git_info")
    public GitRepositoryState getGitState() throws IOException {
        if (gitState == null) {
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("git.properties"));
            gitState = new GitRepositoryState(properties);
        }
        return gitState;
    }
}
