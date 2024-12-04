package typo;

import java.util.LinkedList;


public abstract class Group extends Box{
    
    protected final LinkedList<Box> list = new LinkedList<Box>();
    double ascent;
    double descent;
    double width;
    double stretchingCapacity;
    
    public void add(Box b) {list.add(b);}

    @Override
    public double getAscent() {return ascent;}

    @Override
    public double getDescent() {return descent;}

    @Override
    public double getWidth() {return width;}

    @Override
    public double getStretchingCapacity() {return stretchingCapacity;}

    @Override
    public String toString() {
        String r = super.toString() + "{";
        for (Box b : list) {r += (b.toString()); r += ","; r += "\n";}
        return r + "\n" + "}";
    }

}
