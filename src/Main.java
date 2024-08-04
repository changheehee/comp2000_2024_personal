import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Main { 
    public static void main(String[] args) throws Exception {
        System.out.println("Red vs. Blue");

        // Task 3
        JFrame frame = new JFrame("Interactive Grid");
        InteractiveGrid interactiveGrid = new InteractiveGrid();
        frame.add(interactiveGrid);
        frame.setSize(720, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

// Task 4
class Cell {
    private int x, y;
    private static final int SIZE = 35;
    private boolean highlighted;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.highlighted = false;
    }

    public void paint(Graphics g) {
        if (highlighted) {
            g.setColor(Color.GRAY);
            g.fillRect(x, y, SIZE, SIZE);
            g.setColor(Color.BLACK);
        }
        g.drawRect(x, y, SIZE, SIZE);
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }
}

class Grid {
    private Cell[][] cells;
    private static final int GRID_SIZE = 20;
    private static final int OFFSET = 10;

    public Grid() {
        cells = new Cell[GRID_SIZE][GRID_SIZE];
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                int x = OFFSET + col * Cell.getSize;
                int y = OFFSET + row * Cell.getSize;
                cells[row][col] = new Cell(x, y);
            }
        }
    }

    public void paint(Graphics g) {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.paint(g);
            }
        }
    }

    public void highlightCell(int mouseX, int mouseY) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Cell cell = cells[row][col];
                int cellX = OFFSET + col * Cell.getSize;
                int cellY = OFFSET + row * Cell.getSize;
                cell.setHighlighted(mouseX >= cellX && mouseX < cellX + Cell.getSize && mouseY >= cellY && mouseY < cellY + Cell.getSize);
            }
        }
    }
}

// Task 5
class InteractiveGrid extends JPanel {
    private Grid grid;

    public InteractiveGrid() {
        grid = new Grid();
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                grid.highlightCell(e.getX(), e.getY());
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        grid.paint(g);
    }
}
