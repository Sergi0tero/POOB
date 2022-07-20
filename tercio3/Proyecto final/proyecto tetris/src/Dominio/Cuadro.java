package Dominio;

import java.util.Arrays;

public class Cuadro extends  Figura{

    protected int estados = 1;
    protected int numType = 5;

    public Cuadro(int[][] tablero, boolean bolflag, int posy, char tipo ){
        super('C', tipo);
        this.tablero = tablero;
        this.bolflag = bolflag;
        this.posy = posy;
        setTablero();
    }

    int[][] estado1 = new int[][]{
            {5,5},
            {5,5}
    };

    private void setTablero(){
        for(int i=0; i< estado1.length; i++){
            for(int j=0; j< estado1.length; j++){
                if(tablero[i][j+4] == 0) {
                    tablero[i][j + 4] = estado1[i][j];
                }
            }
        }
    }


    @Override
    protected int[][] getEstado() {return tablero;}

    public boolean getflag(){
        return bolflag;
    }

    protected void llegoAbajo(){
        for (int i =19; i>=0; i-=1) {
            for (int j = 9; j>=0; j-=1) {
                if(tablero[i][j] == 5)tablero[i][j] = -5;
            }
        }
        bolflag = false;
    }

    @Override
    public int[][] abajo() {
        boolean bandera = true;
        for (int i = 19; i >= 0; i -= 1) {
            for (int j = 9; j >= 0; j -= 1) {
                if(tablero[i][j] == 5  && i < 19 && j>0){
                    if(tablero[i+1][j] == 6 || tablero[i+1][j-1] == 6 || tablero[i+1][j] == 7 || tablero[i+1][j-1] == 7 || tablero[i+1][j] == 8 || tablero[i+1][j-1] == 8 || tablero[i+1][j] == 9 || tablero[i+1][j-1] == 9) {
                        bufocapturado();
                    }
                }
                if (tablero[19][j] == 5 && bandera) {
                    bandera = false;
                    if(tipo == 'N') {
                        llegoAbajo();
                    }else if(tipo == 'U'){
                        Useless(numType);
                    }else if(tipo == 'W'){
                        Winner();
                    }else if(tipo == 'B'){
                        Bomb(i,j);
                    }
                } else if (j > 0 && tablero[i][j] == 5 && (tablero[i + 1][j] < 0 || tablero[i + 1][j - 1] < 0 || tablero[i + 1][j] == -2 || tablero[i + 1][j - 1] == -2)&& bandera) {
                    bandera = false;
                    if(tipo == 'N') {
                        llegoAbajo();
                    }else if(tipo == 'U'){
                        Useless(numType);
                    }else if(tipo == 'W'){
                        Winner();
                    }else if(tipo == 'B'){
                        Bomb(i,j);
                    }

                } else if (tablero[i][j] == 5 && i < 19 && bandera) {
                    bandera = false;
                    tablero[i - 1][j] = 0;
                    tablero[i - 1][j-1] = 0;
                    tablero[i + 1][j] = 5;
                    tablero[i + 1][j-1] = 5;
                }

            }
        }
        return tablero;
    }

    @Override
    public int[][] nextState() {
        return tablero;
    }

    @Override
    public int[][] mueveDerecha() {
        boolean bandera = true;
        for (int i = 19; i >= 0; i -= 1) {
            for (int j = 9; j >= 0; j -= 1) {
                if(tablero[i][j] == 5  && i < 19 && j<9 && i > 0){
                    if(tablero[i][j+1] == 6 || tablero[i-1][j+1] == 6 || tablero[i][j+1] == 7 || tablero[i-1][j+1] == 7 || tablero[i][j+1] == 8 || tablero[i-1][j+1] == 8 || tablero[i][j+1] == 9 || tablero[i-1][j+1] == 9) {
                        bufocapturado();
                    }
                }
                if (j > 0 && j < 9 && i > 0 && tablero[i][j] == 5 && tablero[i - 1][j] == 5 && tablero[i][j - 1] == 5 && tablero[i][j + 1] >= 0 && tablero[i - 1][j + 1] >= 0 &&  tablero[i][j + 1] != -2 && tablero[i - 1][j + 1] != -2 && bandera) {
                    bandera = false;
                    tablero[i][j - 1] = 0;
                    tablero[i - 1][j - 1] = 0;
                    tablero[i][j + 1] = 5;
                    tablero[i - 1][j + 1] = 5;
                }
            }
        }
        return tablero;
    }

    @Override
    public int[][] mueveIzquierda() {
        boolean bandera = true;
        for (int i = 19; i >= 0; i -= 1) {
            for (int j = 9; j >= 0; j -= 1) {
                if(tablero[i][j] == 5  && i < 19 && j>1 && i > 0){
                    if(tablero[i][j-2] == 6 || tablero[i-1][j-2] == 6 || tablero[i][j-2] == 7 || tablero[i-1][j-2] == 7 || tablero[i][j-2] == 8 || tablero[i-1][j-2] == 8 || tablero[i][j-2] == 9 || tablero[i-1][j-2] == 9) {
                        bufocapturado();
                    }
                }
                if (j > 1 && i > 0 && tablero[i][j] == 5 && tablero[i - 1][j] == 5 && tablero[i][j - 1] == 5 && tablero[i][j - 2] >= 0 && tablero[i - 1][j - 2] >= 0 && tablero[i][j - 2] != -2 && tablero[i - 1][j - 2] != -2 && bandera) {
                    bandera = false;
                    tablero[i][j] = 0;
                    tablero[i - 1][j] = 0;
                    tablero[i][j - 2] = 5;
                    tablero[i - 1][j -2] = 5;
                }
            }
        }
        return tablero;
    }

//    @Override
//    public int[][] Useless() {
//        return tablero;
//    }

    @Override
    public int[][] Winner() {
        return tablero;
    }

    @Override
    public int[][] Bomb(int i, int j) {
        if(i<19 && j > 1 && j < 9){
            int xinicio = i+1;
            int yinicio = j+1;
            int xfinal = i-2;
            int yfinal = j-2;
            for(int k = xinicio; k>=xfinal; k-=1){
                for(int l = yinicio;l>= yfinal; l-=1){
                    tablero[k][l] = 0;
                }
            }
        }else if(i<19 && j == 1) {
            int xinicio = i + 1;
            int yinicio = j + 1;
            int xfinal = i - 2;
            int yfinal = j - 1;
            for (int k = xinicio; k >= xfinal; k -= 1) {
                for (int l = yinicio; l >= yfinal; l -= 1) {
                    tablero[k][l] = 0;
                }
            }
        } else if(i==19 && j == 1) {
            int xinicio = i;
            int yinicio = j + 1;
            int xfinal = i - 2;
            int yfinal = j - 1;
            for (int k = xinicio; k >= xfinal; k -= 1) {
                for (int l = yinicio; l >= yfinal; l -= 1) {
                    tablero[k][l] = 0;
                }
            }
        } else if(i == 19 && j > 1 && j < 9){
            int xinicio = i;
            int yinicio = j+1;
            int xfinal = i-2;
            int yfinal = j-2;
            for(int k = xinicio; k>=xfinal; k-=1){
                for(int l = yinicio;l>= yfinal; l-=1){
                    tablero[k][l] = 0;
                }
            }
        }else if(i<19 && j == 9) {
            int xinicio = i + 1;
            int yinicio = j;
            int xfinal = i - 2;
            int yfinal = j - 2;
            for (int k = xinicio; k >= xfinal; k -= 1) {
                for (int l = yinicio; l >= yfinal; l -= 1) {
                    tablero[k][l] = 0;
                }
            }
        }else if(i==19 && j == 9) {
            int xinicio = i;
            int yinicio = j;
            int xfinal = i - 2;
            int yfinal = j - 2;
            for (int k = xinicio; k >= xfinal; k -= 1) {
                for (int l = yinicio; l >= yfinal; l -= 1) {
                    tablero[k][l] = 0;
                }
            }
        }
        bolflag =false;
        return tablero;
    }

    @Override
    public void setposy(int posy){
        this.posy = posy;
    }

}
