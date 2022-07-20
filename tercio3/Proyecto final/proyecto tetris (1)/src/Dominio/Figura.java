package Dominio;

import java.util.Arrays;

public abstract class Figura {


    protected int[][] tablero;
    protected boolean bolflag;
    protected int posy = 1;
    protected char type;
    public char tipo;
    protected int[][] f;
    protected boolean flag =true;
    protected int numType;
    public boolean bufoatrapadoF;

    public Figura(char type , char tipo ){
        this.type = type;
        this.tipo = tipo;
    }


    protected abstract int[][] getEstado();


    public abstract int[][] abajo();
    public abstract int[][] nextState();
    public abstract int[][] mueveDerecha();
    public abstract  boolean getflag();
    public abstract  void setposy(int posy);
    public abstract int[][] mueveIzquierda();
//    public abstract int[][] Useless();
    public abstract int[][] Winner();
    public abstract int[][] Bomb(int i, int j);
    protected abstract void llegoAbajo();


    protected void bufocapturado(){
        bufoatrapadoF = true;
    }

    protected void Useless(int tipo){
        for (int i =19; i>=0; i-=1) {
            for (int j = 9; j>=0; j-=1) {
                if(tablero[i][j] != 0 ){
                    if(tablero[i][j] > 0 && tablero[i][j] != 6 && tablero[i][j] != 7 && tablero[i][j] != 8 && tablero[i][j] != 9)
                        tablero[i][j] = -10 - tipo;
                }
            }
        }
        bolflag = false;
    }


    protected void imprime(){
        for(int n=0; n< 20; n++){
            System.out.println(Arrays.toString(tablero[n]));
        }
        System.out.println("");
    }
}
