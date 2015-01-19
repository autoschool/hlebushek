package web.htmlelements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;


/**
 * Created by ksenie on 16.01.15.
 */

public class PostWrapper extends HtmlElement {

    @FindBy(className = "post_link")
    private Link postTitleLink;

    public Link getPostTitleLink(){ return this.postTitleLink; }

}
