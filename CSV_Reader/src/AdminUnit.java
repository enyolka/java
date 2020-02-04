import java.util.List;

public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox bbox = new BoundingBox();
    List<AdminUnit> children;

    AdminUnit(String name, int adminLevel, double popoulation, double area,double density){
        this(name,adminLevel,popoulation,area,density,null );
    }

    AdminUnit(String name, int adminLevel, double popoulation, double area,double density, BoundingBox bbox){
        this.name = name;
        this.adminLevel = adminLevel;
        this.population = popoulation;
        this.area = area;
        this.density = density;
        this.bbox = bbox;
    }

    void fixMissingValues(){
        if(this.density == 0)
            this.density = parent.density;
        if(this.population == 0)
            this.population = parent.population;
    }

    public String toString(){
        StringBuilder b = new StringBuilder();
        b.append(this.name  + " : \nadmin lvl " + this.adminLevel+ " ; population " + this.population+ " ; area " + this.area + " ; density " + this.density + " ; coordinates "
                + this.bbox /* + "....." + this.children*/);
        //if(this.parent != null)
        //    b.append("      "+ this.parent);
        return b.toString();
    }
}
