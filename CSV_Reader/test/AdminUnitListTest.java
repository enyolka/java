import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class AdminUnitListTest {

    @Test
    public void testNeighbors() throws IOException {
        AdminUnitList aul = new AdminUnitList();
        aul.read("admin-units.csv");
        BoundingBox b = new BoundingBox(20.8824402935597,49.4009925756112,21.4217419879187,49.7817396885343);
        AdminUnit gorl = new AdminUnit("powiat gorlicki" , 6 , 109201.0 , 966.456 , 112.991, b);

        double t1 = System.nanoTime()/1e6;
        AdminUnitList nb = aul.getNeighbors(gorl,10);
        double t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);
        nb.list(System.out);

        assertTrue(nb.units.stream().anyMatch(item->"powiat jasielski".equals(item.name)));
        assertTrue(nb.units.stream().anyMatch(item->"powiat tarnowski".equals(item.name)));
        assertTrue(nb.units.stream().anyMatch(item->"powiat nowosądecki".equals(item.name)));
    }

    @Test
    public void testSortedArea() throws IOException {
        AdminUnitList aul = new AdminUnitList();
        aul.read("admin-units.csv");
        AdminUnitList res = aul.sortInplaceByArea();
        for(int i=1; i<res.units.size()-1; i++)
            assertTrue(res.units.get(i).area >= res.units.get(i-1).area );
    }

    @Test
    public void testFilter() throws IOException{
        AdminUnitList aul = new AdminUnitList();
        aul.read("admin-units.csv");
        AdminUnitList res = aul.filter(a->a.name.startsWith("Ż"));
        for(AdminUnit u: res.units)
            assertTrue(u.name.matches("^Ż.*"));
    }




}