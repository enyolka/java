import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

public class CSVReaderTest {

    @Test
    public void testAsString() throws IOException {
        CSVReader reader = new CSVReader("with-header.csv", ";",true);
        List<String> s = new LinkedList<>();
        while(reader.next()){
            String id = reader.get(0);
            String name = reader.get(1);
            String surname = reader.get(2);
            String street = reader.get(3);
            String s_number = reader.get(4);
            String f_number = reader.get(5);

            s.add(id);
            System.out.println(id+". "+name+" "+ surname+";  "+street+" "+s_number+"/"+f_number);
        }
        assertTrue(s.get(2) instanceof String);
    }

    @Test
    public void testGetInt() throws IOException {
        CSVReader reader = new CSVReader("with-header.csv", ";",true);
        reader.next();

        assertEquals(1,reader.getInt("id"));
        assertEquals("Jan", reader.get("imie"));
    }

    @Test
    public void testIsMissing() throws IOException {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        reader.next();
        reader.next();
        assertTrue(reader.isMissing(4));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testMissingColumn() throws IOException {
        CSVReader reader = new CSVReader("missing-values.csv",";",true);
        reader.next();
        String a = reader.get(15);
    }

    @Test
    public void testReader() throws IOException {
        String text = "a,b,c\n123.4,567.8,91011.12";
        CSVReader reader = new CSVReader(new StringReader(text),",",true);
        reader.next();
        assertEquals(123.4, reader.getDouble(0), 0.000001);
        assertEquals(567.8, reader.getDouble("b"), 0.000001);
        assertEquals(",",reader.delimiter);
    }
}