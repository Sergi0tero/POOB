package world;
import shapes.*;
import java.util.*;

/**
 * Write a description of class Army here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Army
{
    private int xPosition;
    private int yPosition;
    private String color;
    private String nation, type;
    
    protected int attack;
    protected int columna = 0;
    protected int attackX, origenx;
    protected int attackY, origeny;
    protected ArrayList<Rectangle> attacklist = new ArrayList<Rectangle>();
    
    public abstract void sumAttack();
    
    public Army(int xPosition, int yPosition, String nation, String type){
        this.color = color;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.nation = nation;
        this.type = type;
        this.attack = 0;
        origenx = xPosition;
        origeny = yPosition + 50;
    }
    
    public void substractAttack(){
        if(attacklist.size() != 0){
            attacklist.get(attacklist.size()-1).makeInvisible();
            attacklist.remove(attacklist.size()-1);
        }
    }
    
    public int getAttack(){
        return attack;
    }
    
    public String getNation(){
        return nation;
    }
    
    /**
     * Hace visible las armadas de la nacion
     */
    public void makeVisible(){
        for (Rectangle r: attacklist){
            r.makeVisible();
        }
    }
    
    /**
     * Hace invisible las armadas de la nacion
     */
    public void makeInvisible(){
        for (Rectangle r: attacklist){
            r.makeInvisible();
        }
    }
    
    /**
     * organiza las armadas de ataque de cada nacion, primero las friendly, luego las fearful y por ultimo las normales
     */
    public void organice(){
        int columna = 0;
        attackY = origeny;
        attackX = origenx;
        this.attacklist = organiceLista();
        for (int i = 0; i < 5; i ++){
            for (int j = 0; j < 5; j ++){
                if (columna < attacklist.size()){
                    attacklist.get(columna).moveTo(attackX, attackY);
                    attacklist.get(columna).makeVisible();
                    attackX += 11;
                    columna += 1;
                }
            }
            attackY += 10;
            attackX = origenx;
        }
    }
    
    /**
     * organiza la lista de las armadas de attaque segun el color, primero las verdes, luego rojas y por ultimo magentas
     */
    private ArrayList organiceLista(){
        ArrayList<Rectangle> newLista = new ArrayList<Rectangle>();
        ArrayList<String> colors = new ArrayList<String>();
        colors.add("green");
        colors.add("red");
        colors.add("magenta");
        int actColor = 0;
        while(attacklist.size() > 0){
            for(int i = 0; i < attacklist.size(); i++){
                if (attacklist.get(i).getColor() == colors.get(actColor)){
                    newLista.add(attacklist.get(i));
                    attacklist.remove(attacklist.get(i));
                    i-=1;
                }
            }
            if (actColor == 2){
                actColor = 0;
            }
            else{
                actColor += 1;
            }
        }
        makeInvisible();
        return newLista;
    }
    
    /**
     * Devuelve la lista de las armadas de ataque de la nacion
     * @return ArrayList armadas de ataque
     */
    public ArrayList<Rectangle> getlista(){
        return attacklist;
    }
    
    /**
     * Devuelve el ultimo army de ataque
     * @return Rectangle ultimo army de ataque
     */
    public Rectangle getultimo(){
        Rectangle ultimo;
        if(!attacklist.isEmpty()){
            return attacklist.get(attacklist.size()-1);
        }
        else{
            return new Rectangle();
        }
    }
    
    /**
     * Devuelve el tipo de army
     * @return String tipo de army
     */
    public String gettype(){
        return type;
    }
}