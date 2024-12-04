package typo;

import java.awt.Graphics;

public class Hbox extends Group {
    
    @Override
    public void add(Box b) {
        super.add(b);
        ascent = Math.max(ascent, b.getAscent());
        descent = Math.max(descent, b.getDescent());
        width += b.getWidth();
        stretchingCapacity += b.getStretchingCapacity();
    }

    @Override
    public String toString() {return "Hbox" + super.toString();}

    @Override
    public boolean doDraw(Graphics graph, double x, double y, double w) {
        boolean r = true;
        double e = w - getWidth();
        double width;
        if (e < 0) {
            r = false;
            e = 0;
        }
        for (Box b : list) {
            width = b.getWidth() + e * (b.getStretchingCapacity()/this.getStretchingCapacity());
            b.draw(graph, x, y + this.getAscent()-b.getAscent(), width);
            x += width;
        }
        return r;
    }
}
