package web.htmlelements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * Created by ksenie on 17.01.15.
 */
public class PostForm extends HtmlElement {

    @FindBy(id = "post-title-input")
    private TextInput postTitleInput;

    @FindBy(id = "post-text-input")
    private TextInput postBodyInput;

    @FindBy (id = "submit_post_btn")
    private Button submitPostBtn;

    public TextInput getPostTitleInput(){ return this.postTitleInput; }
    public TextInput getPostBodyInput(){ return this.postBodyInput; }
    public Button getSubmitPostBtn(){ return this.submitPostBtn; }

}
