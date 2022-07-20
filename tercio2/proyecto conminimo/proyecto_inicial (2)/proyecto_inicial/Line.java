import java.awt.*;

/**
 * Write a description of class line here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Line
{
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private String color;
    private boolean visible;
    
    /**
     * Constructor for objects of class line
     */
    public Line(int x1, int y1, int x2, int y2)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        color = "white";
        visible = false;
    }
    
    /**
     * Make this Line visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        visible = true;
        draw();
    }
    
    /**
     * Make this Line invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        visible = false;
    }
    
    public boolean isVisible(){
        return visible;
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    private void draw() {
        if(visible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, new java.awt.geom.Line2D.Double(x1, y1, x2, y2));
            canvas.wait(10);
        }
    }
    
    /*
     * Erase the Line on screen.
     */
    private void erase(){
        if(visible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}
