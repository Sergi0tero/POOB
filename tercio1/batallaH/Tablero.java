import java.util.ArrayList;

public class Tablero {
    private Circle table;
    private ArrayList<Flota> flotas;
    public Tablero(){
        table = new Circle();
        table.makeVisible();
    }
}
