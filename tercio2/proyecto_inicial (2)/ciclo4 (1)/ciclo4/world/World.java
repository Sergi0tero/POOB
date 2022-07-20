package world;
import shapes.*;
import java.util.*;
import javax.swing.JOptionPane;
import javafx.util.*;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Write a description of class World here.
 * 
 * @author Archila - Otero
 */
public class World
{
    private String color;
    private HashMap<String, Nation> nations = new HashMap<String, Nation>();
    private ArrayList<String> nationColor = new ArrayList<String>();
    private ArrayList<String> usedColor = new ArrayList<String>();
    private ArrayList<Route> routeList = new ArrayList<Route>();
    private ArrayList<String> actions;
    private String[] conquered;
    private int totalCost;
    private Rectangle board;
    private boolean okas;
    private Graph graph;
    private HashMap<Integer, Registro> previusStates = new HashMap<Integer, Registro>();
    private HashMap<Integer, Registro> nextStates = new HashMap<Integer, Registro>();
    private int numberaction;
    private int index = 0;
    public boolean visible;
    
    /**
     * Constructor for objects of class World
     * @param lenght int longitud del mundo
     * @param widht int ancho del mundo
     */
    public World(int lenght, int widht){
        createBoard(lenght, widht);
        totalCost = 0;
        graph = new Graph(0);
    }
    
    /**
     * Segundo constructor de la clase World
     * @param nation int cantidad de naciones
     * @param routes int las rutas con sus respectivos costos
     * @param armies int las armadas que va a tener cada nacion
     */
    
    public World(int nation, int routes[][], int armies[][]){
        if (nation <= 5){
            usedColor = new ArrayList<String>();
            nationColor.add("red");
            nationColor.add("blue");
            nationColor.add("yellow");
            nationColor.add("green");
            nationColor.add("magenta");
            createBoard(500, 500);
            totalCost = 0;
            for (int i = 0; i < nation; i++){
                addNation(nationColor.get(i), nationPosX(i), nationPosY(i), armies[i][0]);
                for (int j = 0; j < armies[i][1]; j++){
                    nations.get(nationColor.get(i)).sumAttack();
                }
            }
            for (int i = 0; i < routes.length; i++){
                addRoute(nationColor.get(routes[i][0] - 1), nationColor.get(routes[i][1] - 1), routes[i][2]);
            }
        }
    }
    
    /**
     * Crea una nueva nacion
     * @param color String El color que va a tener la nueva nacion
     * @param x int la coordenada x donde se va a ubicar la nueva nacion
     * @param y int la coordenada y donde se va a ubicar la nueva nacion
     * @param armies int cantidad de armadas que va a tener las naciones
     */
    public void addNation(String color, int x, int y, int armies){
        if (!usedColor.contains(color)){
            if (color != "black" && color != "white"){
                Nation currentnation = new NationNormal(color, x, y, armies, index, new ArrayList());
                index += 1;
                nations.put(color, currentnation);
                nations.get(color).makeVisible();
                okas = true;
                usedColor.add(color);
                nationColor.add(color);
                Registro newreg = new Registro("nacion","normal","insert", color, x, y, armies, " ", " ", 0);
                addPreviusStates((getPreviusStates().size()), newreg);
                clearNextStates();
            }
            else{
                okas = false;
            }
        }
        else {
            okas = false;
        }
    }
    
    /**
     * Crea una nueva nacion
     * @param type int tipo de la nacion
     * @param color String El color que va a tener la nueva nacion
     * @param x int la coordenada x donde se va a ubicar la nueva nacion
     * @param y int la coordenada y donde se va a ubicar la nueva nacion
     * @param armies int cantidad de armadas que va a tener las naciones
     */
    public void addNation(String type, String color, int x, int y, int armies){
        okas = false;
        if (type == "walled"){
            NationWalled currentNation = new NationWalled(color, x, y, armies, index, new ArrayList());
            nations.put(color, currentNation);
            okas = true;
        }
        else if(type == "aggressive"){
            NationAggressive currentNation = new NationAggressive(color, x, y, armies, index, new ArrayList());
            nations.put(color, currentNation);
            okas = true;
        }
        else if(type == "normal"){
            Nation currentNation = new NationNormal(color, x, y, armies, index, new ArrayList());
            nations.put(color, currentNation);
            okas = true;
        }
        if (okas == true){
            index += 1;
            nations.get(color).makeVisible();
            usedColor.add(color);
            nationColor.add(color);
        }
        Registro newreg = new Registro("nacion",type,"insert", color, x, y, armies, " ", " ", 0);
        addPreviusStates((getPreviusStates().size()), newreg);
        clearNextStates();
    }
    
    /**
     * Elimina la nacion elejida
     * @param color String el color de la nacion que se quiere elejir
     */
    public void delNation(String color){
        if (nations.containsKey(color)){
            Registro newreg = new Registro("nacion",nations.get(color).getNationtype(),"delete", color,nations.get(color).getX(), nations.get(color).getY(), nations.get(color).getArmy(), " ", " ", 0);
            addPreviusStates((getPreviusStates().size()), newreg);
            clearNextStates();
            nations.get(color).makeInvisible();
            nations.remove(color);
            usedColor.remove(color);
            okas = true;
        }
        else{
            okas = false;
        }
    }
    
    /**
     * Crea una nueva ruta
     * @param locationA String la nacion en la que se quiere crear la ruta
     * @param locationB String la otra nacion en la que se quiere conectar la ruta
     * @param cost int el costo de la ruta
     */
    public void addRoute(String locationA, String locationB, int cost){
        if (nations.containsKey(locationA) && nations.containsKey(locationB)){
            Route current = new RouteNormal(nations.get(locationA), nations.get(locationB), cost);
            routeList.add(current);
            current.makeVisible();
            okas = true;
            Registro newreg = new Registro("line","normal","insert", " " , 0, 0, 0, locationA, locationB, cost);
            addPreviusStates((getPreviusStates().size()), newreg);
            clearNextStates();
        }
        else{
            okas = false;
        }
    }
    
    /**
     * Crea una nueva ruta
     * @param type int tipo de la ruta
     * @param locationA String la nacion en la que se quiere crear la ruta
     * @param locationB String la otra nacion en la que se quiere conectar la ruta
     * @param cost int el costo de la ruta
     */
    public void addRoute(String type, String locationA, String locationB, int cost){
        if (nations.containsKey(locationA) && nations.containsKey(locationB)){
            okas = true;
            if (type == "normal") {
                Route current = new RouteNormal(nations.get(locationA), nations.get(locationB), cost);
                routeList.add(current);
                current.makeVisible();
            }
            else if (type == "weak") {
                Route current = new RouteWeak(nations.get(locationA), nations.get(locationB), cost);
                routeList.add(current);
                current.makeVisible();
            }
            else if (type == "dealer") {
                Route current = new RouteDealer(nations.get(locationA), nations.get(locationB), cost);
                routeList.add(current);
                current.makeVisible();
            }
            else okas = false;
            Registro newreg = new Registro("line",type,"insert", " " , 0, 0, 0, locationA, locationB, cost);
            addPreviusStates((getPreviusStates().size()), newreg);
            clearNextStates();
        }
        else okas = false;
    }
    
    /**
     * elimina una ruta
     * @param locationA String La primera locacion con la que se conecta la ruta
     * @param locationB String La segunda locacion con la que se conecta la ruta
     */
    public void delStreet(String locationA, String locationB){
        for (int i=0 ; i< routeList.size();i++){
            Route r= routeList.get(i);
            if ((r.getNation1().getColor() == locationA && r.getNation2().getColor() == locationB) || (r.getNation1().getColor() == locationB && r.getNation2().getColor() == locationA)){
               Route currentRoute = r;
               Registro newreg = new Registro("line",r.getType(),"delete", " ",0,0,0, locationA, locationB, r.getCost());
               addPreviusStates((getPreviusStates().size()), newreg);
               clearNextStates();
               currentRoute.makeInvisible();
               routeList.remove(currentRoute);
               okas = true;
            }
            else{
                okas = false;
            }
        }
    }
    
    /**
     * Añade una armada a una nacion
     * @param color String Color de la nacion a la que se quiere añadir la armada
     */
    public void putArmy(String color){
        okas = false;
        ArrayList lista = nations.get(color).getlista();
        if (nations.containsKey(color)){
            if (!nations.get(color).isConquered()){
                nations.get(color).setArmyType("normal", lista);
                nations.get(color).sumArmy();
            }
            nations.get(color).sumAttack();
            okas = true;
            Registro newreg = new Registro("army","normal","insert", color, 0, 0, nations.get(color).getArmy(), " ", " ", 0);
            addPreviusStates((getPreviusStates().size()), newreg);
            clearNextStates();
        }
    }
    
    /**
     * Añade una armada a una nacion
     * @param color String Color de la nacion a la que se quiere añadir la armada
     */
    public void putArmy(String color, String type){
        okas = false;
        if (nations.containsKey(color)){
            if (!nations.get(color).isConquered()){
                ArrayList lista = nations.get(color).getlista();
                if(type == "normal"){
                   nations.get(color).setArmyType("normal", lista);
                }
                else if(type == "friendly"){
                    nations.get(color).setArmyType("friendly", lista);
                }
                else if(type == "fearful"){
                    nations.get(color).setArmyType("fearful", lista);
                }
                nations.get(color).sumArmy();
            }
            nations.get(color).sumAttack();
            okas = true;
            Registro newreg = new Registro("army", type, "insert", color, 0, 0, nations.get(color).getArmy(), " ", " ", 0);
            addPreviusStates((getPreviusStates().size()), newreg);
            clearNextStates();
        }
    }
    
    /**
     * Elimina una armada de una nacion
     * @param color String La nacion de la que se elimina la armada
     */
    public void removeArmy(String color){
        okas = false;
        if (nations.containsKey(color)){
            if (!nations.get(color).isConquered()){
                nations.get(color).substractArmy();
            }
            nations.get(color).substractAttack();
            okas = true;
            Registro newreg = new Registro("army",nations.get(color).gettipo(),"delete", color, 0, 0, nations.get(color).getArmy(), " ", " ", 0);
            addPreviusStates((getPreviusStates().size()), newreg);
            clearNextStates();
        }
    }
    
    /**
     * Mueve una armada de una nacion a otra
     * @param locationA String nacion desde donde se va a mover la armada
     * @param locationB String nacion a donde se va a mover la armada
     */
    public void moveArmyOneRoute(String locationA, String locationB){
        crearGrafo(nations.size());
        String[] camino = graph.dijkstra(locationA, locationB);
        okas = true;
        for (int i = 1; i < camino.length - 1; i++){
            String a = camino[i];
            String b = camino[i + 1];
            Nation nation1 = nations.get(a), nation2 = nations.get(b);
            ArrayList <Rectangle> lista = nation2.getlista();
            Rectangle ult1 = nation1.getlast();
            if(ult1.getColor() == "magenta"){
                nation2.setArmyType("normal", lista);
                move(nation1, nation2);
            }
            else if(ult1.getColor() == "red" && nation1.getAttack() >= nation2.getAttack()){
                nation2.setArmyType("fearful", lista);
                move(nation1, nation2);
            }
            else if(nation1.getAttack() == 1){
                nation2.setArmyType("friendly", lista);
                move(nation1, nation2);
            }
            else{
                okas = false;
            }
        }
    }
    
    /**
     * Usa el camino mínimo para mover una armada de una nacion a otra
     * @param locationA String nacion desde donde se va a mover la armada
     * @param locationB String nacion a donde se va a mover la armada
     */
    private void move(Nation nation1, Nation nation2){
            for (Route r : routeList){
                if (r.isRoute(nation1, nation2)){
                    if (nation1.getAttack() > 0 && nation1.getType() != "walled"){
                        if ((nation1.isConquered() && nation2.isConquered()) || (!nation1.isConquered() && !nation2.isConquered())){
                            nation1.substractAttack();
                            nation2.sumAttack();
                            okas = true;
                            totalCost += r.getCost();
                            if (r instanceof RouteWeak){
                                r.makeInvisible();
                                routeList.remove(r);
                            }
                            r.crossed();
                            break;
                        }else if (nation1.isConquered() && !nation2.isConquered()){
                            nation1.substractAttack();
                            nation2.substractArmy();
                            okas = true;
                            totalCost += r.getCost();
                            if (r instanceof RouteWeak){
                                r.makeInvisible();
                                routeList.remove(r);
                            }
                            r.crossed();
                            break;
                        }
                        Registro newreg = new Registro("line",r.getType(),"move", " ",0,0,0, nation1.getColor(), nation2.getColor(), r.getCost());
                        addPreviusStates((getPreviusStates().size()), newreg);
                        clearNextStates();
                    }
                }
                okas = false;
            }
    }
    
    /**
     * Devuelve una lista con las naciones conquistadas
     * @return conquered String[] Lista de las naciones conquistadas
     */
    public String[] conqueredNations(){
        okas = false;
        conquered = new String[nations.size()];
        int j = 0;
        for(String i : nations.keySet()){
            if (nations.get(i).isConquered()){
                conquered[j] =i;
                j += 1;
                okas = true;
            }
        }
        return conquered;
    }
    
    /**
     * Devuelve el costo total de los pagos realizados durante el juego
     * @return totalCost int Costo total
     */
    public int payments(){
        return totalCost;
    }
    
    /**
     * Devuelve un booleano, el cual indica si ya todo el mundo fue conquistado
     * @return boolean True: El mundo ya fue conquistado, False: Aún no se ha conquistado todo el mundo
     */
    public boolean conquer(){
        boolean res = true;
        for(String i : nations.keySet()){
            if(!nations.get(i).isConquered()){
                res = false;
            }
        }
        return res;
    }
    
    /**
     * Devuelve un booleano si logra conquistar la nacion
     * @param String nacion que se intentara conquistar
     */
    public boolean tryToconquer(String location){
        boolean flag = false;
        boolean flag2 = true;
        int i =0;
        int tam = 0;
        while(flag == false && flag2 == true){
            if(usedColor.get(i) != location && nations.get(usedColor.get(i)).isConquered()){
                tam = nations.get(usedColor.get(i)).getAttack();
                for(int j=0; j<tam;j++){
                        moveArmyOneRoute(usedColor.get(i), location);
                }
                if(nations.get(location).isConquered()){
                    flag = true;
                }
            }
            i+=1;
            if( i > usedColor.size()-1){
                flag2 = false;
            }
        }
        return flag;
    }
    
    /**
     * Hace visible el mundo y todos sus componentes
     */
    public void makeVisible(){
        Registro newreg = new Registro("mundo", " " ,"Visible", " ", 0, 0, 0, " ", " ", 0);
        addPreviusStates((getPreviusStates().size()), newreg);
        clearNextStates();
        board.makeVisible();
        for(String i : nations.keySet()){
            nations.get(i).makeVisible();
        }
        for(Route i : routeList){
            i.makeVisible();
        }
        okas = true;
        visible = true;
    }
    
    /**
     * llama a la clase Graph para generar el grafo del problema 
     * @param cantidad de naciones que hay en el mundo
     */
    private void crearGrafo(int nation){
        graph = new Graph(nation);
        int locationA = 0, locationB = 0;
        for (int i = 0; i < nation; i++){
            graph.addNation(nationColor.get(i));
        }
        for (Route r : routeList){
            locationA = r.getNation1().getIndex();
            locationB = r.getNation2().getIndex();
            graph.addRoutes(locationA + 1, locationB + 1, r.getCost());
        }
    }
    
    /**
     * Hace visible el mundo y todos sus componentes
     */
    public void makeInvisible(){
        Registro newreg = new Registro("mundo"," " ,"Invisible", " ", 0, 0, 0, " ", " ", 0);
        addPreviusStates((getPreviusStates().size()), newreg);
        clearNextStates();
        
        for(String i : nations.keySet()){
            nations.get(i).makeInvisible();
        }
        
        for(Route i : routeList){
            i.makeInvisible();
        }
        okas = true;
        visible = false;
        board.makeInvisible();
    }
    
    /**
     * Termina el juego
     */
    public void finish(){
        System.exit(0);
    }
    
    /**
     * Deshace una accion realizada en el mundo
     */
    public void undo(){
        if(!getPreviusStates().isEmpty()){
            Registro lastreg = previusStates.get(getPreviusStates().size()-1);
            if(lastreg != null){
                if(lastreg.object == "nacion"){
                    if(lastreg.action == "insert"){
                        delNation(lastreg.color);
                        Registro oldreg = new Registro("nacion",lastreg.type,"delete", lastreg.color, lastreg.x, lastreg.y, lastreg.armies, " ", " ", 0);
                        addNextStates((getNextStates().size()+1), oldreg);
                        previusStates.remove(getPreviusStates().size());
                    }
                }
                else if(lastreg.object == "line"){
                    if(lastreg.action == "insert"){
                        delStreet(lastreg.locationA, lastreg.locationB);
                        Registro oldreg = new Registro("line",lastreg.type,"insert", " ", 0,0,0,lastreg.locationA, lastreg.locationB, lastreg.cost);
                        addNextStates((getNextStates().size()+1), oldreg);
                        previusStates.remove(getPreviusStates().size());
                    }
                }
                else if(lastreg.object == "army"){
                    if(lastreg.action == "insert"){
                        removeArmy(lastreg.color);
                        Registro oldreg = new Registro("line",lastreg.type,"delete", lastreg.color, 0,0,0," ", " ",0);
                        addNextStates((getNextStates().size()+1), oldreg);
                        previusStates.remove(getPreviusStates().size());
                    }
                    else if(lastreg.action == "delete"){
                        putArmy(lastreg.color, lastreg.type);
                        Registro oldreg = new Registro("line",lastreg.type,"insert", lastreg.color, 0,0,0," ", " ",0);
                        addNextStates((getNextStates().size()+1), oldreg);
                        previusStates.remove(getPreviusStates().size());
                    }
                }
                else if(lastreg.object == "mundo"){
                    if(lastreg.action == "Visible"){
                        makeInvisible();
                        Registro oldreg = new Registro("mundo",lastreg.type,"Invisible", " ", 0, 0, 0, " ", " ", 0);
                        addNextStates((getNextStates().size()+1), oldreg);
                        previusStates.remove(getPreviusStates().size());
                    }
                    else if(lastreg.action == "Invisible"){
                        makeVisible();
                        Registro oldreg = new Registro("mundo",lastreg.type,"Visible", " ", 0, 0, 0, " ", " ", 0);
                        addNextStates((getNextStates().size()+1), oldreg);
                        previusStates.remove(getPreviusStates().size());
                    }
                }
            }
        }
        return;
    }
    
    /**
     * Rehace una accion deshecha en el mundo 
     */
    public void redo(){
        if(!getNextStates().isEmpty()){
            Registro lastreg = previusStates.get(getNextStates().size()-1);
            if(lastreg != null){
                if(lastreg.object == "nacion"){
                    if(lastreg.action == "delete"){
                        addNation(lastreg.type,lastreg.color, lastreg.x, lastreg.y, lastreg.armies);
                        Registro oldreg = new Registro("nacion",lastreg.type,"insert", lastreg.color, lastreg.x, lastreg.y, lastreg.armies," ", " ", 0);
                        addPreviusStates((getPreviusStates().size()+1), oldreg);
                        nextStates.remove(getNextStates().size());
                    }
                }
                else if(lastreg.object == "line"){
                    if(lastreg.action == "delete"){
                        addRoute(lastreg.type, lastreg.locationA, lastreg.locationB, lastreg.cost);
                        Registro oldreg = new Registro("line",lastreg.type,"insert", " ",0,0,0,lastreg.locationA, lastreg.locationB, lastreg.cost);
                        addPreviusStates((getPreviusStates().size()+1), oldreg);
                        nextStates.remove(getNextStates().size());
                    }
                }
                else if(lastreg.object == "army"){
                    if(lastreg.action == "delete"){
                        putArmy(lastreg.color, lastreg.type);
                        Registro oldreg = new Registro("army",lastreg.type,"insert", lastreg.color,0,0,0," ", " ",0);
                        addPreviusStates((getPreviusStates().size()+1), oldreg);
                        nextStates.remove(getNextStates().size());
                    }
                    else if(lastreg.action == "insert"){
                        removeArmy(lastreg.color);
                        Registro oldreg = new Registro("army",lastreg.type,"delete", lastreg.color,0,0,0," ", " ",0);
                        addPreviusStates((getPreviusStates().size()+1), oldreg);
                        nextStates.remove(getNextStates().size());
                    }
                }
                else if(lastreg.object == "mundo"){
                    if(lastreg.action == "Visible"){
                        makeInvisible();
                        Registro oldreg = new Registro("mundo",lastreg.type,"Invisible", " ", 0, 0, 0, " ", " ", 0);
                        addNextStates((getNextStates().size()+1), oldreg);
                        previusStates.remove(getPreviusStates().size());
                    }
                    else if(lastreg.action == "Invisible"){
                        makeVisible();
                        Registro oldreg = new Registro("mundo",lastreg.type,"Visible", " ", 0, 0, 0, " ", " ", 0);
                        addNextStates((getNextStates().size()+1), oldreg);
                        previusStates.remove(getPreviusStates().size());
                    }
                }
            }
        }
    }
    
    /**
     * La accion anterior se pudo hacer
     * @return okas boolean True: la accion se realizó, False: la accion no se realizó
     */
    public boolean ok(){
        if (okas){
            JOptionPane.showMessageDialog(null, "La accion se realizo correctamente.");
        }
        else{
            JOptionPane.showMessageDialog(null, "La accion no se pudo realizar correctamente.");
        }
        return okas;
    }
    
    /**
     * Las posiciones x en las que se crean las naciones
     * @param i int la posicion x en la que se va a crear la nacion
     */
    private int nationPosX(int i){
        int x = 0;
        if (i == 0){
            x = 225;
        }
        else if (i == 1){
            x = 10;
        }
        else if (i == 2){
            x = 440;
        }
        else if (i == 3){
            x = 100;
        }
        else if (i == 4){
            x = 350;
        }
        return x;
    }
    
    /**
     * Las posiciones y en las que se crean las naciones
     * @param i int la posicion y en la que se va a crear la nacion
     */
    private int nationPosY(int i){
        int x = 0;
        if (i == 0){
            x = 10;
        }
        else if (i == 1 || i == 2){
            x = 200;
        }
        else if (i == 3 || i == 4){
            x = 440;
        }
        return x;
    }
    
    /**
     * Devuelve los valores de los siguientes estados
     * @return Collection valores de los estados anteriores
     */
    private Collection<Registro> getPreviusStates(){
        return previusStates.values();
    }
    
    /**
     * Devuelve los valores de los anteriores estados
     * @return Collection valores de los estados siguientes
     */
    private Collection<Registro> getNextStates(){
        return nextStates.values();
    }
    
    /**
     * añade un reguistro anterior de estado
     * @return boolean existe el estado anterior
     */
    private boolean addPreviusStates(int numeraction, Registro reg){
        Registro put = previusStates.put(numberaction, reg);
        return (put != null);
    }
    
    /**
     * añade un siguiente reguistro de estado
     * @return boolean existe el estado siguiente
     */
    private boolean addNextStates(int numberaction, Registro reg){
        Registro put = nextStates.put(numberaction, reg);
        return (put != null);
    }
    
    /**
     * limpia los siguientes estados del reguistro
     */
    private void clearNextStates(){
        nextStates.clear();
    }
    
    /**
     * Crea el tablero
     * @param x int longitud x del mundo
     * @param y int longitud y del mundo
     */
    private void createBoard(int x, int y){
        board = new Rectangle();
        board.changeSize(x, y);
        board.changeColor("black");
        board.makeVisible();
    }
}
