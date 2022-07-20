package world;
import java.util.Random;
import java.awt.Color;

/**
 * Write a description of class RGB here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RGB
{
    Random Rand = new Random();
    float r = Rand.nextFloat() / 2f + (1/2);
    float g = Rand.nextFloat() / 2f + (1/2);
    float b = Rand.nextFloat() / 2f + (1/2);
    /**
     * Constructor for objects of class RGB
     */
    public RGB()
    {
        Color randomColor = new Color(r, g, b);
    }

}
