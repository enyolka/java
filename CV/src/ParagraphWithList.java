import java.io.PrintStream;

public class ParagraphWithList extends Paragraph {
    UnorderedList list = new UnorderedList();

    ParagraphWithList(){
        super();
    }

    ParagraphWithList(String content){
        super(content);
    }

    ParagraphWithList setContent(String content){
        super.setContent(content);
        return this;
    }

    ParagraphWithList addItemToList(ListItem item){
        this.list.addItem(item);
        return this;
    }

    @Override
    void writeHTML(PrintStream out) {
        super.writeHTML(out);
        list.writeHTML(out);
    }
}
