package Dominio.Test;

import Dominio.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;


public class TetrisTest {
    Tetris tetris= new Tetris();
    Figura figura;
    int[][] tablero = new int[20][10];



    @BeforeEach
    public void setUp() {

    }

    @Test
    public void deberiaAgregarFigura(){
        figura= new Cuadro(tablero, true, 0, 'N');
        assertEquals(tablero[0][4], 5);
    }

    @Test
    public void deberiaMoverseDerecha(){
        figura= new Te(tablero, true, 0, 'N');
        figura.mueveDerecha();
        assertEquals(tablero[0][4], 0);
        assertEquals(tablero[0][5], 4);
    }

    @Test
    public void deberiaMoverseIzquierda(){
        figura= new Te(tablero, true, 0, 'N');
        figura.mueveIzquierda();
        assertEquals(tablero[0][3], 4);
        assertEquals(tablero[0][2], 0);
    }

    @Test
    public void deberiaRotar(){
        figura= new Recta(tablero, true, 0, 'N');
        assertEquals(tablero[0][4], 1);
        assertEquals(tablero[0][5], 1);
        figura.nextState();
        assertEquals(tablero[0][4], 0);
        assertEquals(tablero[0][5], 1);
        assertEquals(tablero[0][6], 0);
    }

    @Test
    public void deberiaPerder(){
        while(!tetris.terminojuego()){
            tetris.nuevafigura();
            figura = tetris.figura;
            while(figura.getflag()){
                figura.abajo();
            }
        }
        assertTrue(tetris.terminojuego());
    }

}
