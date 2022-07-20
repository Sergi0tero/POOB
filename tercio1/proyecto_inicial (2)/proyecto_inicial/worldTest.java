

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class worldTest
{
    private World world_one, world_two, world_thre;
    private int pays_one, pays_two, pays_thre;
    private boolean ok_1, ok_2, ok_3;
    private boolean worldconquer1, worldconquer2, worldconquer3;


    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        int [][] routes_one = {{1,2,5},{3,1,5}};
        int [][] armies_one = {{2,1},{5,0},{1,3}};
        int [][] routes_two = {{1,2,2},{1,3,5},{1,4,1},{2,5,5},{2,6,1}};
        int [][] armies_two = {{0,0},{1,0},{2,1},{2,1},{0,1},{0,1}};
        int [][] routes_thre = {{1,2,1},{1,3,5},{2,3,2},{2,4,4},{3,4,3}};
        int [][] armies_thre = {{2,0},{2,1},{3,1},{2,0}};
        
        World world_one = new World(3, routes_one, armies_one);
        worldconquer1 = world_one.conquer();
        pays_one = world_one.payments();
        ok_1 = world_one.ok();
        
        World world_two = new World(6, routes_two, armies_two);
        worldconquer2 = world_two.conquer();
        pays_one = world_one.payments();
        ok_1 = world_one.ok();
        
        World world_thre = new World(4, routes_thre, armies_thre);
        worldconquer3 = world_thre.conquer();
        pays_one = world_one.payments();
        ok_1 = world_one.ok();
        
        
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}
