package typo;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;


public class Glyph extends Box {

  final private static FontRenderContext frc = new FontRenderContext(null, false, false);
  final private Font font;
  final private char[] chars;
  final private Rectangle2D bounds;

    public Glyph(Font f, char c) {
        chars = new char[1];
        chars[0] = c;
        font = f;

        TextLayout layout = new TextLayout("" + chars[0], font, frc);
        bounds = layout.getBounds();
    }


    @Override
    public double getStretchingCapacity() {return 0;};

    @Override
    public double getWidth() {return bounds.getWidth();}

    @Override
    public double getAscent() {return -bounds.getY();}

    @Override
    public double getDescent() {return bounds.getHeight() + bounds.getY();}

    @Override
    public boolean doDraw(Graphics graph, double x, double y, double w) {
      graph.setFont(font);
      int X = (int) Math.round(x-bounds.getX());
      int Y = (int) Math.round(y-bounds.getY());
      graph.drawChars(chars, 0, 1, X, Y);
      return true;
    }

    @Override
    public String toString() {
        return String.format("Glyph(%s)", chars[0]) + super.toString();
}
}