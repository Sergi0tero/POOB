package Dominio;

import java.util.ArrayList;

public class Zeta extends Figura{

    public Zeta(int[][] tablero, boolean bolflag, int posy, char tipo){
        super('Z', tipo);
        this.tablero = tablero;
        this.bolflag = bolflag;
        this.posy = posy;
        setTablero();
    }

    int[][] estado1 = new int[][]{
            {3,3,0},
            {0,3,3},
            {0,0,0}
    };

    int[][] estado2 = new int[][]{
            {0,3,0},
            {3,3,0},
            {3,0,0}
    };

    protected int estados = 2;
    protected int[][] currentstate = estado1;
    protected int currentflag = 1;
    protected int numType = 3;

    private void setTablero(){
        for(int i=0; i< estado1.length; i++){
            for(int j=0; j< estado1.length; j++){
                if(tablero[i][j+4] == 0) {
                    tablero[i][j + 4] = currentstate[i][j];
                }
            }
        }
    }

    @Override
    protected int[][] getEstado() {
        return currentstate;
    }

    public boolean getflag(){
        return bolflag;
    }

    protected void llegoAbajo(){
        for (int i =19; i>=0; i-=1) {
            for (int j = 9; j>=0; j-=1) {
                if(tablero[i][j] == 3) tablero[i][j] = -3;

            }
        }
        bolflag = false;
    }

    @Override
    public int[][] abajo() {
        boolean bandera = true;
        for (int i = 19; i >= 0; i -= 1) {
            for (int j = 9; j >= 0; j -= 1) {
                if (tablero[19][j] == 3 && bandera) {
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
                }
                if (currentflag == 1 && bandera) {
                    if(tablero[i][j] == 3  && i < 19 && j>0){
                        if(tablero[i+1][j] == 6 || tablero[i+1][j-1] == 6 || tablero[i][j-2] == 6 || tablero[i+1][j] == 7 || tablero[i+1][j-1] == 7 || tablero[i][j-2] == 7 || tablero[i+1][j] == 8 || tablero[i+1][j-1] == 8 || tablero[i][j-2] == 8 || tablero[i+1][j] == 9 || tablero[i+1][j-1] == 9 || tablero[i][j-2] == 9) {
                            bufocapturado();
                        }
                    }
                    if (tablero[i][j] == 3 && (tablero[i + 1][j - 1] < 0 || tablero[i + 1][j] < 0 || tablero[i][j - 2] < 0 || tablero[i + 1][j - 1] == -2 || tablero[i + 1][j] == -2 || tablero[i][j - 2] == -2)) {
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
                    } else if (tablero[i][j] == 3 && i < 19) {
                        bandera = false;
                        tablero[i][j] = 0;
                        tablero[i - 1][j - 1] = 0;
                        tablero[i - 1][j - 2] = 0;
                        tablero[i + 1][j] = 3;
                        tablero[i + 1][j - 1] = 3;
                        tablero[i][j - 2] = 3;
                    }
                }
                if (currentflag == 2 && bandera) {
                    if(tablero[i][j] == 3  && i < 19 && j>0){
                        if(tablero[i+1][j] == 6 || tablero[i][j+1] == 6 || tablero[i+1][j] == 7 || tablero[i][j+1] == 7  || tablero[i+1][j] == 8 || tablero[i][j+1] == 8 || tablero[i+1][j] == 9 || tablero[i][j+1] == 9) {
                            bufocapturado();
                        }
                    }
                    if (tablero[i][j] == 3 && (tablero[i][j + 1] < 0 || tablero[i + 1][j] < 0 || tablero[i][j + 1] == -2 || tablero[i + 1][j] == -2)) {
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
                    } else if (tablero[i][j] == 3 && i < 19) {
                        bandera = false;
                        tablero[i - 1][j] = 0;
                        tablero[i - 2][j + 1] = 0;
                        tablero[i + 1][j] = 3;
                        tablero[i][j + 1] = 3;

                    }
                }
            }
        }
        return tablero;
    }

    @Override
    public int[][] mueveDerecha() {
        boolean bandera = true;
        for(int i = 19; i>=0; i-=1) {
            for (int j = 9; j >= 0; j -= 1) {
                if(tablero[i][j] == 3  && i < 19 ){
                    if(currentflag == 1 && j<8 && i > 0) {
                        if (tablero[i][j + 1] == 6 || tablero[i - 1][j] == 6 || tablero[i][j + 1] == 7 || tablero[i - 1][j] == 7 || tablero[i][j + 1] == 8 || tablero[i - 1][j] == 8 || tablero[i][j + 1] == 9 || tablero[i - 1][j] == 9) {
                            bufocapturado();
                        }
                    }
                    else if (currentflag == 2 && j<8 && i > 2) {
                        if (tablero[i][j + 1] == 6 || tablero[i - 1][j + 2] == 6 || tablero[i - 2][j + 2] == 6 || tablero[i][j + 1] == 7 || tablero[i - 1][j + 2] == 7 || tablero[i - 2][j + 2] == 7 || tablero[i][j + 1] == 8 || tablero[i - 1][j + 2] == 8 || tablero[i - 2][j + 2] == 8 || tablero[i][j + 1] == 9 || tablero[i - 1][j + 2] == 9 || tablero[i - 2][j + 2] == 9) {
                            bufocapturado();
                        }
                    }

                }
                if (currentflag == 1 && i > 0 && j > 1 && j < 9 && tablero[i][j] == 3 && tablero[i - 1][j-2] == 3 && tablero[i][j + 1] >= 0 && tablero[i - 1][j] >= 0 && tablero[i][j + 1] != -2 && tablero[i - 1][j] != -2 && bandera) {
                    bandera = false;
                    tablero[i][j - 1] = 0;
                    tablero[i - 1][j - 2] = 0;
                    tablero[i][j + 1] = 3;
                    tablero[i - 1][j] = 3;
                } else if(currentflag == 2 && j < 8 && i > 2 && tablero[i][j] == 3 && tablero[i - 2][j+1] == 3 && tablero[i][j + 1] >= 0 && tablero[i - 1][j + 2] >= 0 && tablero[i - 2][j + 2] >= 0 && tablero[i][j + 1] != -2 && tablero[i - 1][j + 2] != -2 && tablero[i - 2][j + 2] != -2 && bandera) {
                    bandera = false;
                    tablero[i][j] = 0;
                    tablero[i - 1][j] = 0;
                    tablero[i - 2][j + 1] = 0;
                    tablero[i][j + 1] = 3;
                    tablero[i - 1][j + 2] = 3;
                    tablero[i - 2][j + 2] = 3;
                }
            }
        }
        return tablero;
    }

    @Override
    public int[][] mueveIzquierda() {
        boolean bandera = true;
        for(int i = 19; i>=0; i-=1) {
            for (int j = 9; j >= 0; j -= 1) {
                if(tablero[i][j] == 3  && i < 19 && j>3 && i > 1){
                    if(currentflag == 1) {
                        if (tablero[i][j-2] == 6 || tablero[i-1][j-3] == 6 || tablero[i][j-2] == 7 || tablero[i-1][j-3] == 7 || tablero[i][j-2] == 8 || tablero[i-1][j-3] == 8 || tablero[i][j-2] == 9 || tablero[i-1][j-3] == 9) {
                            bufocapturado();
                        }
                    }else if (currentflag == 2) {
                        if (tablero[i][j - 1] == 6 || tablero[i - 1][j - 1] == 6 || tablero[i - 2][j] == 6 || tablero[i][j - 1] == 7 || tablero[i - 1][j - 1] == 7 || tablero[i - 2][j] == 7 || tablero[i][j - 1] == 8 || tablero[i - 1][j - 1] == 8 || tablero[i - 2][j] == 8 || tablero[i][j - 1] == 9 || tablero[i - 1][j - 1] == 9 || tablero[i - 2][j] == 9)
                        {
                            bufocapturado();
                        }
                    }
                }
                if (currentflag == 1 && i > 0 && j > 2 && tablero[i][j] == 3 && tablero[i - 1][j-2] == 3 && tablero[i][j - 2] >= 0 && tablero[i - 1][j - 3] >= 0 && tablero[i][j - 2] != -2 && tablero[i - 1][j - 3] != -2 && bandera) {
                    bandera = false;
                    tablero[i][j] = 0;
                    tablero[i - 1][j - 1] = 0;
                    tablero[i][j - 2] = 3;
                    tablero[i - 1][j - 3] = 3;
                } else if(currentflag == 2 && i > 2 && j < 9 && j>0 && tablero[i][j] == 3 && tablero[i - 2][j+1] == 3 && tablero[i][j - 1] >= 0 && tablero[i - 1][j - 1] >= 0 && tablero[i - 2][j] >= 0 && tablero[i][j - 1] != -2 && tablero[i - 1][j - 1] != -2 && tablero[i - 2][j] != -2 && bandera) {
                    bandera = false;
                    tablero[i][j] = 0;
                    tablero[i - 1][j + 1] = 0;
                    tablero[i - 2][j + 1] = 0;
                    tablero[i][j - 1] = 3;
                    tablero[i - 1][j - 1] = 3;
                    tablero[i - 2][j] = 3;
                }
            }
        }
        return tablero;
    }

    @Override
    public int[][] nextState(){
        boolean flag = true;
        for(int i=0; i<20; i++){
            for(int j=0; j<10; j++){
                if(tablero[i][j] == 3 && currentflag == 1 && flag){
                    currentflag =2;
                    currentstate = estado2;
                    tablero[i][j] = 0;
                    tablero[i+1][j+2] = 0;
                    tablero[i+1][j] = 3;
                    tablero[i+2][j] = 3;
                    flag = false;

                }else if (tablero[i][j] == 3 && currentflag == 2 && flag && j < 9){
                    currentflag = 1;
                    currentstate = estado1;
                    tablero[i+1][j-1] = 0;
                    tablero[i+2][j-1] = 0;
                    tablero[i][j-1] = 3;
                    tablero[i+1][j+1] = 3;
                    flag = false;
                }
            }
        }
        return tablero;
    }

    @Override
    public void setposy(int posy){
        this.posy = posy;
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
        if(currentflag == 1) {
            if (i < 19  && j > 3 && j < 9) {
                int xinicio = i + 1;
                int yinicio = j + 1;
                int xfinal = i - 2;
                int yfinal = j - 3;
                for (int k = xinicio; k >= xfinal; k -= 1) {
                    for (int l = yinicio; l >= yfinal; l -= 1) {
                        if(k == i - 2 && l == j+1) {
                            tablero[k][l] = tablero[k][l];
                        } if (k == i +1 && l == j -3 ){
                            tablero[k][l] = tablero[k][l];
                        }if(tablero[k][l] < 0 || tablero[k][l] == 3){
                            tablero[k][l] = 0;
                        }
                    }
                }
            } else if (i < 19 && j == 2) {
                int xinicio = i + 1;
                int yinicio = j + 1;
                int xfinal = i - 2;
                int yfinal = j - 2;
                for (int k = xinicio; k >= xfinal; k -= 1) {
                    for (int l = yinicio; l >= yfinal; l -= 1) {
                        if(k == i - 2 && l == j+1) {
                            tablero[k][l] = tablero[k][l];
                        } else {
                            tablero[k][l] = 0;
                        }
                    }
                }
            }else if (i == 19 && j == 2) {
                int xinicio = i;
                int yinicio = j + 1;
                int xfinal = i - 2;
                int yfinal = j - 2;
                for (int k = xinicio; k >= xfinal; k -= 1) {
                    for (int l = yinicio; l >= yfinal; l -= 1) {
                        if(k == i - 2 && l == j+1) {
                            tablero[k][l] = tablero[k][l];
                        }else{
                            tablero[k][l] = 0;
                        }
                    }
                }
            } else if (i == 19 &&  j < 9 && j > 2) {
                int xinicio = i;
                int yinicio = j + 1;
                int xfinal = i - 2;
                int yfinal = j - 3;
                for (int k = xinicio; k >= xfinal; k -= 1) {
                    for (int l = yinicio; l >= yfinal; l -= 1) {
                        if(k == i - 2 && l == j+1) {
                            tablero[k][l] = tablero[k][l];
                        }else{
                            tablero[k][l] = 0;
                        }
                    }
                }
            } else if (i < 19 && j == 9) {
                int xinicio = i + 1;
                int yinicio = j;
                int xfinal = i - 2;
                int yfinal = j - 3;
                for (int k = xinicio; k >= xfinal; k -= 1) {
                    for (int l = yinicio; l >= yfinal; l -= 1) {
                        if (k == i +1 && l == j -3 ){
                            tablero[k][l] = tablero[k][l];
                        }else{
                            tablero[k][l] = 0;
                        }
                    }
                }
            }else if (i == 19 && j == 9) {
                int xinicio = i;
                int yinicio = j;
                int xfinal = i - 2;
                int yfinal = j - 3;
                for (int k = xinicio; k >= xfinal; k -= 1) {
                    for (int l = yinicio; l >= yfinal; l -= 1) {
                        tablero[k][l] = 0;
                    }
                }
            }
        } else if(currentflag == 2) {
            if (i < 19 && j > 0 && j < 9) {
                int xinicio = i + 1;
                int yinicio = j + 2;
                int xfinal = i - 3;
                int yfinal = j - 1;
                for (int k = xinicio; k >= xfinal; k -= 1) {
                    for (int l = yinicio; l >= yfinal; l -= 1) {
                        if (k == i +1 && l == j + 2 ){
                            tablero[k][l] = tablero[k][l];
                        }else if (k == i - 3 && l == j - 1 ){
                            tablero[k][l] = tablero[k][l];
                        }else{
                            tablero[k][l] = 0;
                        }
                    }
                }
            } else if (i < 19 && j == 0) {
                int xinicio = i + 1;
                int yinicio = j + 2;
                int xfinal = i - 3;
                int yfinal = j;
                for (int k = xinicio; k >= xfinal; k -= 1) {
                    for (int l = yinicio; l >= yfinal; l -= 1) {
                        if (k == i +1 && l == j + 2 ){
                            tablero[k][l] = tablero[k][l];
                        } else {
                            tablero[k][l] = 0;
                        }
                    }
                }
            }else if (i == 19 && j == 0) {
                int xinicio = i;
                int yinicio = j+2;
                int xfinal = i - 3;
                int yfinal = j;
                for (int k = xinicio; k >= xfinal; k -= 1) {
                    for (int l = yinicio; l >= yfinal; l -= 1) {
                        tablero[k][l] = 0;
                    }
                }
            } else if (i == 19 && j > 1 && j < 8) {
                int xinicio = i;
                int yinicio = j + 2;
                int xfinal = i - 3;
                int yfinal = j - 1;
                for (int k = xinicio; k >= xfinal; k -= 1) {
                    for (int l = yinicio; l >= yfinal; l -= 1) {
                        if (k == i - 3 && l == j - 1 ){
                            tablero[k][l] = tablero[k][l];
                        }else{
                            tablero[k][l] = 0;
                        }
                    }
                }
            } else if (i < 19 && j == 8) {
                int xinicio = i + 1;
                int yinicio = j +1;
                int xfinal = i - 3;
                int yfinal = j - 1;
                for (int k = xinicio; k >= xfinal; k -= 1) {
                    for (int l = yinicio; l >= yfinal; l -= 1) {
                        if (k == i - 3 && l == j - 1 ){
                            tablero[k][l] = tablero[k][l];
                        }else{
                            tablero[k][l] = 0;
                        }
                    }
                }
            }else if (i == 19 && j == 8) {
                int xinicio = i;
                int yinicio = j +1;
                int xfinal = i - 3;
                int yfinal = j - 1;
                for (int k = xinicio; k >= xfinal; k -= 1) {
                    for (int l = yinicio; l >= yfinal; l -= 1) {
                        if (k == i - 3 && l == j - 1 ){
                            tablero[k][l] = tablero[k][l];
                        }else{
                            tablero[k][l] = 0;
                        }
                    }
                }
            }
        }
        bolflag =false;
        return tablero;
    }
}
