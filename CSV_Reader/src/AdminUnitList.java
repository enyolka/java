import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    Map<Long,AdminUnit> id_unit = new HashMap<>();
    Map<AdminUnit,Long> unit_parentId = new HashMap<>();
    Map<Long,List<AdminUnit>> parentId_child = new HashMap<>();


    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename, ",", true);

        while(reader.next()) {
            String name = reader.get("name");
            int adminLevel = reader.getInt("admin_level");
            double population = reader.getDouble("population");
            double area = reader.getDouble("area");
            double density = reader.getDouble("density");
            long id = reader.getLong("id");
            long parentId = reader.getLong("parent");
            BoundingBox bbox = new BoundingBox(reader.getDouble("x1"), reader.getDouble("y1"),reader.getDouble("x3"), reader.getDouble("y3"));

            AdminUnit unit = new AdminUnit(name,adminLevel,population,area,density,bbox);

            this.units.add(unit);
            id_unit.put(id, unit);
            unit_parentId.put(unit, parentId);

            if(parentId != 0) {
                if (!parentId_child.containsKey(parentId)){
                    List<AdminUnit> children = new ArrayList<>();
                    children.add(unit);
                    parentId_child.put(parentId, children);
                }
                else parentId_child.get(parentId).add(unit);
            }

    }
        for(AdminUnit u: units){
            long parentId = unit_parentId.get(u);
            if(parentId == 0 ){
                u.parent = null;
                continue;
            } else u.parent = id_unit.get(parentId);

            if(u.parent == null)  u.parent.children = new ArrayList<>();
            else  u.parent.children = parentId_child.get(parentId);
            fixMissingValues(u);
        }

    }

    void list(PrintStream out){
        for(int i = 0; i<units.size(); i++)
            out.println(units.get(i));
    }

    void list(PrintStream out,int offset, int limit ){
        for(int i = offset; i<limit; i++)
            out.println(units.get(i));
    }

    private void fixMissingValues(AdminUnit au){
        au.fixMissingValues();
    }


    AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();
        for(AdminUnit unit: this.units)
            if(regex) {
                if (unit.name.matches(pattern))
                    ret.units.add(unit);
                else if (unit.name.contains(pattern))
                    ret.units.add(unit);
            }
        return ret;
    }

    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance){
        AdminUnitList ret = new AdminUnitList();
        for(AdminUnit u: units) {
            if (unit.name.equals(u.name))
                continue;
            if (u.adminLevel == unit.adminLevel && u.adminLevel >= 8) // czy osiedla i dzielnice traktujemy jak miejscowości?
                if (unit.bbox.distanceTo(u.bbox) <= maxdistance && unit.bbox.intersects(u.bbox))
                    ret.units.add(u);
            if (u.adminLevel == unit.adminLevel && u.adminLevel < 8)
                if (unit.bbox.intersects(u.bbox))
                    ret.units.add(u);
        }
        return ret;
    }


// do zmiany ?
/*   class NameComparator implements Comparator<AdminUnit>{
        @Override
        public int compare(AdminUnit t, AdminUnit t1) {
            return t.name.compareTo(t1.name);
        }
    }*/

    Comparator<AdminUnit> nameComparator(){
        class NameComparator implements Comparator<AdminUnit>{
            public int compare(AdminUnit t, AdminUnit t1) {
                return t.name.compareTo(t1.name);
            }
        }
        return new NameComparator();
    }

    Comparator<AdminUnit> areaComparator(){
       return new Comparator<AdminUnit>() {
           @Override
           public int compare(AdminUnit t, AdminUnit t1) {
                /*if(t.area > t1.area) return -1;
                if(t.area < t1.area) return 1;
                else return 0;*/
               return Double.compare(t.area,t1.area);
           }
       };
    }

    AdminUnitList sortInplaceByName(){
        units.sort(nameComparator());
        return this;
    }

    AdminUnitList sortInplaceByArea(){
        units.sort(areaComparator());
        return  this;
    }

    AdminUnitList sortInplaceByPopulation(){
        units.sort((t,t1)->Double.compare(t.population,t1.population));
        return this;
    }

    AdminUnitList sortInplace(Comparator<AdminUnit> cmp){
        units.sort(cmp);
        return this;
    }

    AdminUnitList sort(Comparator<AdminUnit> cmp){
        AdminUnitList ret = new AdminUnitList();
        for(AdminUnit u: this.units)
            ret.units.add(u);
        ret.sortInplace(cmp);
        return ret;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred){
        //return this.filter(pred,this.units.size());
        AdminUnitList result = new AdminUnitList();
        result.units = this.units.stream().filter(pred).collect(Collectors.toList());
        return result;
        //return this.copier(this.units.stream().filter(pred).collect(Collectors.toList()));
    }
    /*AdminUnitList copier(List<AdminUnit> list){
        AdminUnitList result = new AdminUnitList();
        result.id_unit = this.id_unit.entrySet().stream().filter(value->list.contains(value.getValue())).collect(Collectors.toMap(value->value.getKey(), value->value.getValue()));
        result.unit_parentId = this.unit_parentId.entrySet().stream().filter(value->list.contains(value.getKey())).collect(Collectors.toMap(value->value.getKey(), value->value.getValue()));

        return result;
    }*/

    AdminUnitList filter(Predicate<AdminUnit> pred, int limit){
        AdminUnitList result = new AdminUnitList();
        result.units = this.units.stream().filter(pred).limit(limit).collect(Collectors.toList());
        return result;
        //return this.filter(pred,0,limit);
    }

    AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit){
        AdminUnitList result = new AdminUnitList();
        result.units = this.units.stream().filter(pred).skip(offset).limit(limit).collect(Collectors.toList());
        return result;
    }

    public static void main(String[] args) throws IOException {
        AdminUnitList aul = new AdminUnitList();
        aul.read("admin-units.csv");
        //aul.selectByName("^gmina Bie.*",true).sortInplaceByName().list(System.out);
        Predicate<AdminUnit> p1 = a->a.adminLevel==6;
        Predicate<AdminUnit> p2 = a->a.parent.name.equals("województwo małopolskie");
        Predicate<AdminUnit> p3 = a->a.parent!=null;
        aul.sortInplaceByName().filter(p1.and(p2)).list(System.out);
    }

}
