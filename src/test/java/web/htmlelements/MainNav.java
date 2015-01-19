package web.htmlelements;


import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;

/**
 * Created by ksenie on 17.01.15.
 */
public class MainNav extends HtmlElement {

    @FindBy(id = "nav_addPost")
    private Link goToAddPostNav;

    @FindBy(linkText = "ALL POSTS")
    private Link goToAllPostsNav;

    public Link getGoToAddPostNav(){ return this.goToAddPostNav; }
    public Link getGoToAllPostsNav(){ return this.goToAllPostsNav; }

    public void openAllPostPage(){
        getGoToAddPostNav().click();
    }

}
