package Dominio;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class Tetris {

    private final int heigth = 20;
    private final int width = 10;

    private int[][] tablero = new int[heigth][width];
    private final char[] figuras = {'R','L','Z','T','C' };
    private final char[] tipos = {'N', 'B', 'U'};
    protected boolean bolflag = true;
    protected int posy = 1;
    public Figura figura;

    public  Tetris(){
        for(int i=0; i<heigth; i++){
            for(int j=0; j<width; j++){
                tablero[i][j] = 0;
            }
        }
    }

    public Tetris(int[][] tablero){
        for(int i=0; i<heigth; i++){
            for(int j=0; j<width; j++){
                if (tablero[i][j] > 0 && tablero[i][j] != 10) tablero[i][j] = 0;
            }
        }
        this.tablero = tablero;
    }

    public void nuevafigura() {
        int flag = (int) (Math.random() * 5);
        char fig = figuras[flag];
        int flag2 = (int) (Math.random() * 3);
        char tipo = tipos[flag2];
//        char fig = 'Z';
//        char tipo = 'N';
        // switchcase
        if (fig == 'C') {
            figura = new Cuadro(tablero, bolflag, posy, tipo);
        }
        else if (fig == 'T') {
            figura = new Te(tablero, bolflag, posy, tipo);
        }
        else if (fig == 'Z') {
            figura = new Zeta(tablero, bolflag, posy, tipo);
        }
        else if (fig == 'L') {
            figura = new Ele(tablero, bolflag, posy, tipo);
        }
        else if (fig == 'R') {
            figura = new Recta(tablero, bolflag, posy, tipo);
        }
    }

    public boolean terminoficha(){
        return figura.getflag();
    }

    public  void setflag(boolean flag){this.bolflag = flag;}

    public boolean terminojuego(){
        boolean bandera = false;
        for(int k=0; k < 9; k++){
            if(tablero[1][k] < 0){
                bandera = true;
            }
        }
        return bandera;
    }

    public void bajelineas(int k, int l){
        for(int i = k; i>0; i-=1){
            for(int j = l; j>=0; j-=1){
                for (int m = 0; m < 10; m++) {
                    if (tablero[i][m] < 0){
                        if (tablero[i - 1][m] < 0) tablero[i][m] = tablero[i - 1][m];
                        else if (tablero[i - 1][m] == 0) tablero[i][m] = 0;
                    }
                }
            }
        }
    }

    public int[][] getTablero() {
        return tablero;
    }
}
