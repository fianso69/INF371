package typo;

import java.awt.Graphics;


public class Space extends Box {
    double width;
    private double stretchingCapacity;

    public Space(double w, double sC) {width = w; stretchingCapacity = sC;}

    @Override
    public boolean doDraw(Graphics graph, double x, double y, double w) {return true;}

    @Override
    public double getAscent() {return 0;}

    @Override
    public double getDescent() {return 0;}

    @Override
    public String toString() {return "Space" + super.toString();}

    @Override
    public double getWidth() {return width;}

    @Override
    public double getStretchingCapacity() {return stretchingCapacity;}

}
