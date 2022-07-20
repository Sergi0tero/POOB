package world;
import java.util.*;
import shapes.*;

/**
 * resuelve el juego 
 *
 * @author Archila - Otero
 */
public class WorldConTest
{
    private World world;
    public boolean visible;
    private boolean okas = true;
    
    /**
     * resuelve el problema del mundo
     */
    public WorldConTest(){
        
    }
    
    /**
     * soluciona el mundo
     */
    public int solve(int nations, int[][] routes, int[][]armies){
        world = new World(nations, routes, armies);
        int flag = 0;
        boolean flag2 = false;
        String [] color = {"yellow", "blue", "red", "green", "magenta"};
        while(!flag2){
            for(int i=0; i<nations;i++){
                world.tryToconquer(color[i]);
                flag = world.payments();
                flag2= world.conquer();
            }
        }
        okas = true;
        return flag;
    }
    
    /**
     * Devuelve si la accion se pudo hacer
     * @return boolean si se pudo hacer/ nos epudo hacer
     */
    public boolean ok(){
        return okas;
    }
}
