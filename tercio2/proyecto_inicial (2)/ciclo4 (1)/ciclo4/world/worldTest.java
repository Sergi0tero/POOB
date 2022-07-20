package world;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class worldTest
{
    private World world_one, world_two, world_thre, world_four, world_five, newfive, world_six, world_seven;
    private WorldConTest firstworld = new WorldConTest();
    private int pays_one,secondpays, pays_two, pays_thre, solution;
    private boolean ok_1, ok_2, ok_3;
    private boolean worldconquer1, worldconquer2, worldconquer3, conquer, nconquer;


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
        int [][] routes_two = {{1,2,1},{2,3,2},{3,4,3}};
        int [][] armies_two = {{1,2},{3,4},{5,6},{0,5}};
        int [][] routes_thre = {{1,2,1},{1,3,5},{2,3,2},{2,4,4},{3,4,3}};
        int [][] armies_thre = {{2,0},{2,1},{3,1},{2,0}};
        
        World world_one = new World(3, routes_one, armies_one);
        worldconquer1 = world_one.conquer();
        pays_one = world_one.payments();
        world_one.removeArmy("yellow");
        world_one.tryToconquer("red");
        world_one.tryToconquer("blue");
        conquer = world_one.conquer();
        secondpays = world_one.payments();
        ok_1 = world_one.ok();
        
        
        World world_two = new World(4, routes_two, armies_two);
        worldconquer2 = world_two.conquer();
        world_two.tryToconquer("yellow");
        world_two.tryToconquer("blue");
        world_two.tryToconquer("red");
        pays_two = world_two.payments();
        nconquer = world_two.conquer();
        ok_2 = world_two.ok();
        
        
        World world_thre = new World(4, routes_thre, armies_thre);
        worldconquer3 = world_thre.conquer();
        world_thre.moveArmyOneRoute("blue", "red");
        pays_thre = world_thre.payments();
        ok_3 = world_thre.ok();
        
        World world_four = new World(500 ,500);
        world_four.addNation("aggressive", "red", 50, 50, 1);
        world_four.putArmy("red");
        
        
        World world_five = new World(500 ,500);
        world_four.addNation("aggressive", "red", 50, 50, 1);
        
        World newfive = new World(500,500);
        world_four.addNation("aggressive", "red", 50, 50, 1);
        world_four.addNation("red", 150, 150, 1);
        
        solution = firstworld.solve(4,routes_two, armies_two);
        
        
        World world_six = new World(500,500);
        world_six.addNation("blue", 50, 50, 1);
        world_six.addNation("walled", "red", 150, 150, 1);
        world_six.addRoute("red", "blue", 2);
        world_six.putArmy("red");
        world_six.moveArmyOneRoute("blue", "red");
        
        World world_seven = new World(500,500);
        world_seven.addNation("blue", 50, 50, 1);
        world_six.addNation("walled", "red", 150, 150, 1);
        world_six.addRoute("red", "blue", 2);
        world_six.putArmy("red");
        
        
    }

    @Test
    public void nodeberiaañadirnacion(){
        assertEquals(world_five, newfive);
    }
    
    @Test
    public void deberiacompararconquista() {
        assertEquals(worldconquer1, false);
        assertEquals(worldconquer2, false);
        assertEquals(worldconquer3, false);
    }
    
    @Test
    public void nodeberiaañadirarmy(){
        assertEquals(world_four, world_five);
    }
    
    @Test
    public void nodeberiamoverarmy(){
        assertEquals(world_six, world_seven);
    }
    
    @Test
    public void deberiacompararloscostos(){
        assertEquals(pays_one, 0);
        assertEquals(pays_two, 41);
        assertEquals(pays_thre, 1);
    }
    
    @Test
    public void deberiasercorrecto(){
        assertTrue(ok_1);
        assertTrue(ok_2);
        assertTrue(ok_3);
    }
    
    @Test
    public void nodeberiaestarconquistado(){
        assertFalse(conquer);
    }
    
    @Test
    public void deberiaestarconquistado(){
        assertTrue(nconquer);
    }
    
    @Test
    public void deberiacomparardenuevoelcosto(){
        assertEquals(secondpays, 50);
    }
    
    @Test
    public void deberiasolucionarelmundo(){
        assertEquals(41,solution);
    }
}