package typo;

import java.awt.Graphics;

public class Vbox extends Group {

    double lineSkip;

    public Vbox(double lineSkip) {this.lineSkip = lineSkip;}

    public void add(Box b) {
        super.add(b);
        if (!list.isEmpty()) {ascent += lineSkip;}
        ascent += b.getAscent() + this.getDescent();
        descent = b.getDescent();
        width = Math.max(b.getWidth(), super.getWidth());
        stretchingCapacity = Math.max(b.getStretchingCapacity(), super.getStretchingCapacity());
    }

    @Override
    public String toString() {return "Vbox" + super.toString();}

    @Override
    public boolean doDraw(Graphics graph, double x, double y, double w) {
        boolean r = true;
        if (w < getWidth()) {r = false; w = getWidth();}
        for (Box b : list) {
            b.draw(graph, x, y, w);
            y += b.getAscent();
            y += b.getDescent();
            y += lineSkip;
        }
        return r;
    }
}
