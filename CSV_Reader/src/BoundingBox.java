public class BoundingBox {
    double xmin;
    double ymin;
    double xmax;
    double ymax;
    boolean empty = true;

    BoundingBox(){
        this(0,0,0,0);
        this.empty = true;
    }

    BoundingBox(double xmin, double ymin, double xmax, double ymax){
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
        this.empty = false;
    }

    void addPoint(double x, double y){
        if(x < xmin)
            x = xmin;
        if(y < ymin)
            y = ymin;
        if(x > xmax)
            x = xmax;
        if(y > ymax)
            y = ymax;
    }

    boolean contains(double x, double y){
        return ((x >= xmin && y >= ymin) && (x <= xmax && y <= ymax));
    }
    boolean contains(BoundingBox bb){
        return (bb.contains(bb.xmin, bb.ymin) && bb.contains(bb.xmax, bb.ymax));
    }

    double width(){
        return xmax-xmin;
    }
    double height(){
        return ymax-ymin;
    }
    boolean intersects(BoundingBox bb){
        return (!this.isEmpty() && !bb.isEmpty() && (xmin <= bb.xmin + bb.width()) && (xmin + width() >= bb.xmin) && (ymin <= bb.ymin + bb.height()) && (ymin + height() >= bb.ymin));
    }

    BoundingBox add(BoundingBox bb){
        if(this.intersects(bb)){
            addPoint(bb.xmin, bb.ymin);
            addPoint(bb.xmax, bb.ymax);
        }
        return this;
    }

    boolean isEmpty(){
        return empty;
    }

    double getCenterX(){
        if(!isEmpty())
            return (xmax + xmin)/2 ;
        else throw new IllegalStateException("Not implemented");
    }
    double getCenterY(){
        if(!isEmpty())
            return (ymax + ymin)/2 ;
        throw new IllegalStateException("Not implemented");
    }

    double distanceTo(BoundingBox bb){
        if(!isEmpty() && !bb.isEmpty()){
            return Math.sqrt(Math.pow((this.getCenterX() - bb.getCenterX()),2) + Math.pow((this.getCenterY() - bb.getCenterY()),2));
        }

        else throw new RuntimeException("Not implemented");
    }

    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("min(" + this.xmin + "," + this.ymin + ")\nmax(" + this.xmax + "," + this.ymax + ")\n");
        return b.toString();
    }


}
