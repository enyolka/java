import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();

    public Document(String s) {
        this.title = s;
    }

    Document setTitle(String title){
        this.title = title;
        return this;
    }

    Document setPhoto(String photoUrl){
        this.photo = new Photo(photoUrl);
        return this;
    }

    Section addSection(String sectionTitle){
        Section section = new Section(sectionTitle);
        this.sections.add(section);
        return section;
    }
    Document addSection(Section s){
        this.sections.add(s);
        return this;
    }

    public void addPhoto(String s) {
        Photo new_photo = new Photo(s);
        this.photo = new_photo;
    }

    void writeHTML(PrintStream out){
        out.printf("<!DOCTYPE HTML> \n <html lang = \"pl\"> \n" +
                "<head>\n" +
                "<meta charset = \"utf-8\" /> \n <title>CV</title> \n" +
                "<meta name = \"decription\" content = \"CV\" /> \n" +
                "<meta name = \"keywords\" content = \"osoba, wyszkatlcenie\" /> \n" +
                "<meta http-equiv = \"X-UA-Compatible\" content = \"IE=edge,chrome=1\"/\">\n" +
                "</head> \n\n <body> \n");
        out.printf("<h1> %s </h1>\n", title);
        photo.writeHTML(out);
        for(Section s: sections)
            s.writeHTML(out);
        out.printf("\n</body> \n </html>");
    }


}
