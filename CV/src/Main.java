public class Main {
    public static void main(String[] args) {
        Document cv = new Document("Emilia Maczka - CV");
        cv.addPhoto("https://66.media.tumblr.com/ae5a0f908843eb40c59f4df7b2c15e48/tumblr_p0cfpdmcC11sqhy0go1_500.jpg");
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