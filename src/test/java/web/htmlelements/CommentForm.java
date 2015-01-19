package web.htmlelements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * Created by ksenie on 16.01.15.
 */

@Name("CommentForm")
public class CommentForm extends HtmlElement {
    @FindBy(id = "comment-input")
    private TextInput commentInput;

    @FindBy(id = "submit-comment-btn")
    private Button submitCommentBtn;

    public TextInput getCommentInput(){ return this.commentInput; }
    public Button getSubmitCommentBtn(){ return this.submitCommentBtn; }
}
