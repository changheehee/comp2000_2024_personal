import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
  public static void main(String[] args) throws Exception {
    Main window = new Main();
    window.run();
  }

  class Canvas extends JPanel {
    Grid grid = new Grid();
    Actor[] actors = {
        new Cat(grid.cellAtColRow(0, 0)),
        new Dog(grid.cellAtColRow(5, 5)),
        new Bird(grid.cellAtColRow(10, 10))
    };
    Stage stage = new Stage(grid, actors);  

    public Canvas() {
      setPreferredSize(new Dimension(720, 720));
    }

    @Override
    public void paint(Graphics g) {
      stage.paint(g);
    }
  }

  private Main() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Canvas canvas = new Canvas();
    this.setContentPane(canvas);
    this.pack();
    this.setVisible(true);
  }

  public void run() {
    while(true) {
      repaint();
    }
  }
}
