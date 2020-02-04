
public class Main {
    public static void main(String[] args) {
        Document cv = new Document("Emilia Maczka - CV");
        cv.addPhoto("https://uwolnijnauke.pl/wp-content/uploads/sites/13/2014/02/chomik-200x200.png");
        cv.addSection("Wykształcenie")
                .addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w ...")
                .addParagraph("2006-2012 SP7 im Ronalda Regana w ...")
                .addParagraph("...");
        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList().setContent("Umiejętności")
                                .addItemToList("C")
                                .addItemToList("C++")
                                .addItemToList("Java"));
        cv.writeHTML(System.out);
    }
}
