package Dominio;

import java.util.ArrayList;

public class Buffo {

    private ArrayList<Integer> bufos;
    public int bufo;
    public int rango;

    public int[][] agregebufo(int[][] tablero) {
        int flag = (int) (Math.random() * rango);
        bufo = bufos.get(flag);
        int i = (int) (Math.random() * 16) + 3;
        int j = (int) (Math.random() * 10);
        while (tablero[i][j] != 0){
            i = (int) (Math.random() * 16) + 3;
            j = (int) (Math.random() * 10);
        }
        tablero[i][j] = bufo;
        return tablero;
    }

    public void setbufosausar(ArrayList<Integer> bufos, int rango){
        this.bufos = bufos;
        this.rango = rango;
    }

    public int bufoactivo(){
        return bufo;
    }

}