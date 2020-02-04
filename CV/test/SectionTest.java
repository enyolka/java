import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class SectionTest {
    ByteArrayOutputStream os;
    PrintStream ps;
    String result;

    @Before
    public void setUp() throws Exception {
        this.os = new ByteArrayOutputStream();
        this.ps = new PrintStream(os);
        this.result = null;
    }

    @After
    public void tearDown() throws Exception {
        os.close();
        ps.close();
    }

    @Test
    public void writeHTML() {
        Section s = new Section();
        String title = "Title";
        Paragraph p = new Paragraph("Content");
        s.setTitle(title);
        s.addParagraph(p);

        s.writeHTML(this.ps);

        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(result);

        assertTrue(result.contains("<h2>"));
        assertTrue(result.contains("</h2>"));
        assertTrue(result.contains(p.content));
        assertTrue(result.contains(title));
    }
}