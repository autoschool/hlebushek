package ru.yandex.school.hlebushek.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PostsApi {

    public int postId;
    public String postTitle;
    public String postBody;
    public String postCreateData;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        if (this.postTitle == null) {
            this.postTitle = "undefined";
        }
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostBody() {
        if (this.postBody == null) {
            this.postBody = "undefined";
        }
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public String getPostCreateData() {
        if (this.postCreateData == null) {
            this.postCreateData = "undefined";
        }
        return postCreateData;
    }

    public void setPostCreateData(String postCreateData) {
        this.postCreateData = postCreateData;
    }
}
