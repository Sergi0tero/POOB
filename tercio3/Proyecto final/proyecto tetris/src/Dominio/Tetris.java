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

    /*
    @Autor Archila - Otero
    constructor de la clase tetris
     */
    public  Tetris(){
        for(int i=0; i<heigth; i++){
            for(int j=0; j<width; j++){
                tablero[i][j] = 0;
            }
        }
    }

    /*
    @Autor Archila - Otero
    segundo constructor de la clase tetris
    @param int[][] tablero, matriz logica del tablero
     */
    public Tetris(int[][] tablero){
        for(int i=0; i<heigth; i++){
            for(int j=0; j<width; j++){
                if (tablero[i][j] > 0 && tablero[i][j] != 10) tablero[i][j] = 0;
            }
        }
        this.tablero = tablero;
    }

    /*
    @Autor Archila - Otero
    genera una nueva figura apartir de un numero random y un tipo random
     */
    public void nuevafigura() {
        int flag2 = (int) (Math.random() * 3);
        char tipo = tipos[flag2];
        int flag = (int) (Math.random() * 5);
        switch (flag) {
            case 1 -> figura = new Cuadro(tablero, bolflag, posy, tipo);
            case 2 -> figura = new Te(tablero, bolflag, posy, tipo);
            case 3 -> figura = new Zeta(tablero, bolflag, posy, tipo);
            case 4 -> figura = new Ele(tablero, bolflag, posy, tipo);
            case 5 -> figura = new Recta(tablero, bolflag, posy, tipo);
        }
    }

    /*
    @Autor Archila - Otero
    pregunta a la ficha si ya termino, es decir si ya llego abajo o colisiono con otra ficha
     */
    public boolean terminoficha(){
        try {
            return figura.getflag();
        }catch (NullPointerException e){
            return false;
        }
    }

    /*
    @Autor Archila - Otero
    cambia la bandera de la ficha
     */
    public  void setflag(boolean flag){this.bolflag = flag;}

    /*
    @Autor Archila - Otero
    verifica si en la primera fila de la matriz hay una ficha que ya termino de jugar
     */
    public boolean terminojuego(){
        try {
            boolean bandera = false;
            for (int k = 0; k < 9; k++) {
                if (tablero[1][k] < 0) {
                    bandera = true;
                }
            }
            return bandera;
        }catch (NullPointerException e){
            return false;
        }
    }

    /*
    @Autor Archila - Otero
    baja la cantidad de filas que fueron eliminadas
     */
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

    /*
    @Autor Archila - Otero
    retorna la amtriz logica de tetris
     */
    public int[][] getTablero() {
        return tablero;
    }

    /*
    @Autor Archila - Otero
    retoruna el tipo de figura que esta en juego
     */
    public char tipofigura(){
        try {
            return figura.tipo;
        }catch (NullPointerException e){
            return ' ';
        }
    }

    /*
    @Autor Archila - Otero
    le indica a la figura que debe bajar
     */
    public void abajo(){
        try{
        figura.abajo();
        }catch (NullPointerException e){
            figura = figura;
        }
    }

    /*
    @Autor Archila - Otero
    le indica a la figura que debe moverse a la derecha
     */
    public void Derecha(){
        try{
            figura.mueveDerecha();
        }catch (NullPointerException e){
            figura = figura;
        }
    }

    /*
    @Autor Archila - Otero
    le indica a la figura que debe moverse a la izquierda
     */
    public void Izquierda(){
        try{
            figura.mueveIzquierda();
        }catch (NullPointerException e){
            figura = figura;
        }
    }

    /*
    @Autor Archila - Otero
    le indica a la figura que debe rotar
     */
    public void rote(){
        try{
            figura.nextState();
        }catch (NullPointerException e){
            figura = figura;
        }
    }

    /*
    @Autor Archila - Otero
    retorna si fue capturado un bufo
     */
    public boolean bufocapturadoF(){
        try {
            return figura.bufoatrapadoF;
        }catch (NullPointerException e){
            return false;
        }
    }
}
