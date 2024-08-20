import java.awt.Graphics;

public class Stage {
    private Grid grid;
    private Actor[] actors;

    public Stage(Grid grid, Actor[] actors) {
        this.grid = grid;
        this.actors = actors;
    }

    public void paint(Graphics g) {
        grid.paint(g, null);  
        for (Actor actor : actors) {
            actor.paint(g);
        }
    }
}

