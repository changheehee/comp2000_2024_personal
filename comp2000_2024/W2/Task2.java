//Libraries
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Task2 extends JPanel {
    private static int pointSize = 100; // number of the point
    private Queue<Point> mousePosition = new LinkedList<>(); // save mouse traces
    private Point currentMousePosition = null; // current mouse coordinates

    public Task2() {
        setBackground(Color.WHITE); // set background color
        setPreferredSize(new Dimension(600, 600)); // starting window size >> 600 x 600

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (mousePosition.size() >= pointSize) { // If the number of points over the size of the mouse position.....
                    mousePosition.poll();  // remove Oldest point 
                    // System.out.println("Oldest point removed / [" + mousePosition.size() + "]"); // for debug
                }
                mousePosition.add(e.getPoint()); // add current mouse position to Queue
                currentMousePosition = e.getPoint();  // add current mouse position to currentMousePosition
                repaint();  // update the panel
            }
        });
    }

    
    private void drawGrid(Graphics g) {
        g.setColor(Color.BLACK);
        for (int i = 0; i <= getWidth(); i += 30) g.drawLine(i, 0, i, getHeight());

        for (int i = 0; i <= getHeight(); i += 30) g.drawLine(0, i, getWidth(), i);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Perform basic drawing action

        drawGrid(g); // drawing grid

        if (currentMousePosition != null) { 
            g.setColor(Color.LIGHT_GRAY); 
            int gridSize = 30;
            int x = (currentMousePosition.x / gridSize) * gridSize; // Calculate the current X-grid top left coordinates
            int y = (currentMousePosition.y / gridSize) * gridSize; // Calculate the current Y-grid top left coordinates
            g.fillRect(x, y, gridSize, gridSize); // draw sqaure in calculated coordinates
        }

        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g.setColor(Color.GRAY);

        for (Point point : mousePosition) g.fillOval(point.x - 5, point.y - 5, 10, 10); // draw point

        g.dispose();

        // System.out.println("mousePosition.size());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Assignment1_Milestone1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Task2());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);      
    }
}
