import java.util.*;
import javax.swing.JOptionPane;
import javafx.util.*;
/**
 * Write a description of class World here.
 * 
 * @author Sergio Otero
 */
public class World
{
    private String color;
    private HashMap<String, Nation> nations = new HashMap<String, Nation>();
    private ArrayList<String> nationColor = new ArrayList<String>();
    private ArrayList<String> usedColor = new ArrayList<String>();
    private HashMap<String, Pair<Route, Integer>> routeList = new HashMap<String, Pair<Route, Integer>>();
    private ArrayList<String> actions;
    private String[] conquered;
    private int totalCost;
    private Rectangle board;
    private boolean okas;
    private Graph graph;
    private HashMap<Integer, Registro> previusStates = new HashMap<Integer, Registro>();
    private HashMap<Integer, Registro> nextStates = new HashMap<Integer, Registro>();
    private int numberaction;
    private int[][] routes;
    
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
            this.routes = routes;
            int totalArmy = 0;
            for (int i = 0; i < nation; i++){
                if(armies[i][0] < armies[i][1]){
                    totalArmy = armies[i][1] - armies[i][0];
                }
                else{
                    totalArmy = 0;
                }
                addNation(nationColor.get(i), nationPosX(i), nationPosY(i), totalArmy);
            }
            for (int i = 0; i < routes.length; i++){
                addRoute(nationColor.get(routes[i][0] - 1), nationColor.get(routes[i][1] - 1), routes[i][2]);
            }
            crearGrafo(nation, routes);
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
                Nation currentnation = new Nation(color, x, y, armies);
                nations.put(color, currentnation);
                nations.get(color).makeVisible();
                okas = true;
                usedColor.add(color);
                Registro newreg = new Registro("nacion","insert", color, x, y, armies, " ", " ", 0);
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
     * Elimina la nacion elejida
     * @param color String el color de la nacion que se quiere elejir
     */
    public void delNation(String color){
        if (nations.containsKey(color)){
            Registro newreg = new Registro("nacion","delete", color,nations.get(color).getX(), nations.get(color).getY(), nations.get(color).getArmy(), " ", " ", 0);
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
            Route current = new Route(nations.get(locationA), nations.get(locationB));
            String key = locationA + locationB;
            String key2 = locationB + locationA;
            routeList.put(key, new Pair(current, cost));
            routeList.put(key2, new Pair(current, cost));
            current.makeVisible();
            okas = true;
            Registro newreg = new Registro("line","insert", " " , 0, 0, 0, locationA, locationB, cost);
            addPreviusStates((getPreviusStates().size()), newreg);
            clearNextStates();
        }
        else{
            okas = false;
        }
    }
    
    /**
     * elimina una ruta
     * @param locationA String La primera locacion con la que se conecta la ruta
     * @param locationB String La segunda locacion con la que se conecta la ruta
     */
    public void delStreet(String locationA, String locationB){
        String key = locationA + locationB;
        String key2 = locationA + locationB;
        if(routeList.containsKey(key) || routeList.containsKey(key2)){
            Registro newreg = new Registro("line","delete", " ",0,0,0, locationA, locationB, routeList.get(key).getValue());
            addPreviusStates((getPreviusStates().size()), newreg);
            clearNextStates();
            routeList.get(key).getKey().makeInvisible();
            routeList.remove(key);
            routeList.remove(key2);
            okas = true;
        }   
        else{
            okas = false;
        }
    }
    
    /**
     * Añade una armada a una nacion
     * @param color String Color de la nacion a la que se quiere añadir la armada
     */
    public void putArmy(String color){
        okas = false;
        if (nations.containsKey(color)){
            nations.get(color).sumArmy();
            okas = true;
            Registro newreg = new Registro("army","insert", color, 0, 0, nations.get(color).getArmy(), " ", " ", 0);
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
            Registro newreg = new Registro("army","delete", color, 0, 0, nations.get(color).getArmy(), " ", " ", 0);
            addPreviusStates((getPreviusStates().size()), newreg);
            clearNextStates();
            nations.get(color).substractArmy();
            okas = true;
        }
    }
    
    /**
     * Mueve una armada de una nacion a otra
     * @param locationA String nacion desde donde se va a mover la armada
     * @param locationB String nacion a donde se va a mover la armada
     */
    public void moveArmyOneRoute(String locationA, String locationB){
        String[] camino = graph.dijkstra(locationA, locationB);
        for (int i = 1; i < camino.length - 1; i++){
            // System.out.println(camino[i]);
            String a = camino[i];
            String b = camino[i + 1];
            //System.out.println(a + b);
            move(a, b);
        }
    }
    
    /**
     * Usa el camino mínimo para mover una armada de una nacion a otra
     * @param locationA String nacion desde donde se va a mover la armada
     * @param locationB String nacion a donde se va a mover la armada
     */
    public void move(String locationA, String locationB){
        String key = locationA + locationB;
        if (routeList.containsKey(key)){
            if (nations.get(locationA).getArmy() > 0){
                if ((nations.get(locationA).isConquered() && nations.get(locationB).isConquered()) || (!nations.get(locationA).isConquered() && !nations.get(locationB).isConquered())){
                    nations.get(locationA).substractArmy();
                    nations.get(locationB).sumArmy();
                    okas = true;
                }
                else if (nations.get(locationA).isConquered() && !nations.get(locationB).isConquered()){
                    nations.get(locationA).substractArmy();
                    nations.get(locationB).substractArmy();
                    okas = true;
                }
                else{
                    okas = false;
                }
                totalCost += routeList.get(key).getValue();
            }
            else {
                okas = false;
            }
        }
    }
    
    /**
     * Devuelve un booleano si logra conquistar la nacion
     * @param String nacion que se intentara conquistar
     */
    public boolean tryToconquer(String location){
        boolean flag = false;
        boolean flag2 = true;
        int i =0;
        while(flag == false && flag2 == true){
            System.out.println(flag + " " + flag2); 
            if(usedColor.get(i) != location && nations.get(usedColor.get(i)).isConquered()){
                moveArmyOneRoute(usedColor.get(i), location);
                if(nations.get(location).isConquered()){
                    flag = true;
                }
            }
            i+=1;
            System.out.println(i + " " + usedColor.size());
            if( i > usedColor.size()-2){
                flag2 = false;
            }
        }
        return flag;
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
     * Hace visible el mundo y todos sus componentes
     */
    public void makeVisible(){
        Registro newreg = new Registro("mundo","Visible", " ", 0, 0, 0, " ", " ", 0);
        addPreviusStates((getPreviusStates().size()), newreg);
        clearNextStates();
        board.makeVisible();
        for(String i : nations.keySet()){
            nations.get(i).makeVisible();
        }
        
        for(String i : routeList.keySet()){
            routeList.get(i).getKey().makeVisible();
        }
        okas = true;
    }
    
    private void crearGrafo(int nation, int routes[][]){
        graph = new Graph(nation);
        for (int i = 0; i < nation; i++){
            graph.addNation(nationColor.get(i));
        }
        for (int j = 0; j < routes.length; j++){
            graph.addRoutes(routes[j][0], routes[j][1], routes[j][2]);
        }
        graph.dijkstra("blue", "green");
    }
    
    /**
     * Hace visible el mundo y todos sus componentes
     */
    public void makeInvisible(){
        Registro newreg = new Registro("mundo","Invisible", " ", 0, 0, 0, " ", " ", 0);
        addPreviusStates((getPreviusStates().size()), newreg);
        clearNextStates();
        
        for(String i : nations.keySet()){
            nations.get(i).makeInvisible();
        }
        
        for(String i : routeList.keySet()){
            routeList.get(i).getKey().makeInvisible();
        }
        okas = true;
        board.makeInvisible();
    }
    
    /**
     * Termina el juego
     */
    public void finish(){
        System.exit(0);
    }
    
    public void undo(){
        if(!getPreviusStates().isEmpty()){
            Registro lastreg = previusStates.get(getPreviusStates().size()-1);
            if(lastreg != null){
                if(lastreg.object == "nacion"){
                    if(lastreg.action == "insert"){
                        delNation(lastreg.color);
                        Registro oldreg = new Registro("nacion","insert", lastreg.color, lastreg.x, lastreg.y, lastreg.armies, " ", " ", 0);
                        addNextStates((getNextStates().size()+1), oldreg);
                        previusStates.remove(getPreviusStates().size());
                    }
                }
                else if(lastreg.object == "line"){
                    if(lastreg.action == "insert"){
                        delStreet(lastreg.locationA, lastreg.locationB);
                        Registro oldreg = new Registro("line","insert", " ", 0,0,0,lastreg.locationA, lastreg.locationB, lastreg.cost);
                        addNextStates((getNextStates().size()+1), oldreg);
                        previusStates.remove(getPreviusStates().size());
                    }
                }
                else if(lastreg.object == "army"){
                    if(lastreg.action == "insert"){
                        removeArmy(lastreg.color);
                        Registro oldreg = new Registro("line","insert", lastreg.color, 0,0,0," ", " ",0);
                        addNextStates((getNextStates().size()+1), oldreg);
                        previusStates.remove(getPreviusStates().size());
                    }
                    else if(lastreg.action == "delete"){
                        putArmy(lastreg.color);
                        Registro oldreg = new Registro("line","insert", lastreg.color, 0,0,0," ", " ",0);
                        addNextStates((getNextStates().size()+1), oldreg);
                        previusStates.remove(getPreviusStates().size());
                    }
                }
                else if(lastreg.object == "mundo"){
                    if(lastreg.action == "Visible"){
                        makeInvisible();
                        Registro oldreg = new Registro("mundo","Invisible", " ", 0, 0, 0, " ", " ", 0);
                        addNextStates((getNextStates().size()+1), oldreg);
                        previusStates.remove(getPreviusStates().size());
                    }
                    else if(lastreg.action == "Invisible"){
                        makeVisible();
                        Registro oldreg = new Registro("mundo","Visible", " ", 0, 0, 0, " ", " ", 0);
                        addNextStates((getNextStates().size()+1), oldreg);
                        previusStates.remove(getPreviusStates().size());
                    }
                }
            }
        }
        return;
    }
    
    public void redo(){
        if(!getNextStates().isEmpty()){
            Registro lastreg = previusStates.get(getNextStates().size()-1);
            if(lastreg != null){
                if(lastreg.object == "nacion"){
                    if(lastreg.action == "delete"){
                        addNation(lastreg.color, lastreg.x, lastreg.y, lastreg.armies);
                        Registro oldreg = new Registro("nacion","insert", lastreg.color, lastreg.x, lastreg.y, lastreg.armies," ", " ", 0);
                        addPreviusStates((getPreviusStates().size()+1), oldreg);
                        nextStates.remove(getNextStates().size());
                    }
                }
                else if(lastreg.object == "line"){
                    if(lastreg.action == "delete"){
                        addRoute(lastreg.locationA, lastreg.locationB, lastreg.cost);
                        Registro oldreg = new Registro("line","insert", " ",0,0,0,lastreg.locationA, lastreg.locationB, lastreg.cost);
                        addPreviusStates((getPreviusStates().size()+1), oldreg);
                        nextStates.remove(getNextStates().size());
                    }
                }
                else if(lastreg.object == "army"){
                    if(lastreg.action == "delete"){
                        putArmy(lastreg.color);
                        Registro oldreg = new Registro("army","insert", lastreg.color,0,0,0," ", " ",0);
                        addPreviusStates((getPreviusStates().size()+1), oldreg);
                        nextStates.remove(getNextStates().size());
                    }
                    else if(lastreg.action == "insert"){
                        removeArmy(lastreg.color);
                        Registro oldreg = new Registro("army","insert", lastreg.color,0,0,0," ", " ",0);
                        addPreviusStates((getPreviusStates().size()+1), oldreg);
                        nextStates.remove(getNextStates().size());
                    }
                }
                else if(lastreg.object == "mundo"){
                    if(lastreg.action == "Visible"){
                        makeInvisible();
                        Registro oldreg = new Registro("mundo","Invisible", " ", 0, 0, 0, " ", " ", 0);
                        addNextStates((getNextStates().size()+1), oldreg);
                        previusStates.remove(getPreviusStates().size());
                    }
                    else if(lastreg.action == "Invisible"){
                        makeVisible();
                        Registro oldreg = new Registro("mundo","Visible", " ", 0, 0, 0, " ", " ", 0);
                        addNextStates((getNextStates().size()+1), oldreg);
                        previusStates.remove(getPreviusStates().size());
                    }
                }
            }
        }
        return;
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
    
    private Collection<Registro> getPreviusStates(){
        return previusStates.values();
    }
    
    private Collection<Registro> getNextStates(){
        return nextStates.values();
    }
    
    private boolean addPreviusStates(int numeraction, Registro reg){
        Registro put = previusStates.put(numberaction, reg);
        return (put != null);
    }
    
    private boolean addNextStates(int numberaction, Registro reg){
        Registro put = nextStates.put(numberaction, reg);
        return (put != null);
    }
    
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
