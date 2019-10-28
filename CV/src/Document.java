import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();

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


    void writeHTML(PrintStream out){
        out.printf("<!DOCTYPE HTML> \n <html lang = \"pl\"> \n" +
                    "<head>\n" +
	                    "<meta charset = \"utf-8\" /> \n <title>Filmy</title> \n" +
                        "<meta name = \"decription\" content = \"CV\" /> \n" +
                        "<meta name = \"keywords\" content = \"film, kino\" /> \n" +
                        "<meta http-equiv = \"X-UA-Compatible\" content = \"IE=edge,chrome=1\"/\">\n" +
                    "</head> \n <body> \n");
        // dodaj tytuł i obrazek
        // dla każdej sekcji wywołaj section.writeHTML(out)
        for(Section s: sections)
            s.writeHTML(out);

    }
}
