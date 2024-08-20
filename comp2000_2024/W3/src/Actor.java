import java.awt.Color;
import java.awt.Graphics;

public abstract class Actor {
    protected Cell cell;
    protected Color color;

    public Actor(Cell cell, Color color) {
        this.cell = cell;
        this.color = color;
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect((int)cell.getX(), (int)cell.getY(), Cell.size, Cell.size);
    }
}
