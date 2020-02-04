import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    List<ListItem> list = new ArrayList<>();

    //UnorderedList(){ this.list = new ArrayList<>(); }

    UnorderedList addItem(ListItem item){
        this.list.add(item);
        return this;
    }


    void writeHTML(PrintStream out){
        out.println("<ul>");
        for(ListItem item: this.list)
            item.writeHTML(out);
        out.println("</ul>");
    }
}