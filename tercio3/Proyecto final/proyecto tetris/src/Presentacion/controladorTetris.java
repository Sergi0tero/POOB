package Presentacion;

import Dominio.Te;
import Dominio.Tetris;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Class Tablero
 *
 * @author  Archila-Otero
 */
public class controladorTetris {

    //String
    private String name;

    //Boolean
    private boolean juega = true;
    public boolean bufocapturadoT = false;

    //JLabel
    private JLabel puntuacionLabel;
    private int puntuacion;

    //JPanel
    private JPanel[][] board;

    private String movimiento;

    //Color
    private Color colorInterno = Color.BLACK;
    private Color colorBorde = Color.BLUE;

    //int
    public int[][] tablero;
    private int boardHeight;
    private int boardWidth;

    //Tetris
    public Tetris logictetris = new Tetris();

    //Borde
    private Border borde;

    /*
    @Autor Archila - Otero
    Constructor del tablero
     */
    public controladorTetris(){
        tablero = logictetris.getTablero();
    }

    /*
    @Autor Archila - Otero
    Crea el tableror del juego
    @param boardHeight Int que representa la cantidad de JPanels que hay de alto en el tablero(sumando el borde del mismo)
    @param boardWidth Int que representa la cantidad de JPanels que hay de ancho en el tablero(sumando el borde del mismo)
    @param width Int que representa el ancho del JFrame en el que se pondra el tablero
    @param height Int que representa el alto del JFrame en el que se pondra el tablero
     */
    public JPanel createGame(int boardHeight , int boardWidth, int widht, int height) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        boardHeight += 2;
        boardWidth += 2;
        JPanel botonesTablero = new JPanel();
        botonesTablero.setLayout(new GridLayout(boardHeight, boardWidth,0, 0));
        board = new JPanel[boardHeight][boardWidth];
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                JPanel boton = new JPanel();
                if (i == 0 || i == boardHeight - 1 || j == 0 || j == boardWidth - 1) {
                    boton.setBackground(colorBorde);
                }
                else{
                    boton.setBackground(colorInterno);
                }
                board[i][j] = boton;
                botonesTablero.add(boton);
            }
        }
        botonesTablero.setBounds((widht/2)-widht/6, 40, widht/3,((height*3)/4));
        botonesTablero.setOpaque(false);
        return botonesTablero;
    }

    /*
    @Autor Archila - Otero
    Funcion que cambia el color del fondo (borde) del tablero
    @param newColor Color que representa el color que va a poner de fondo
     */
    public void changeColorBackGround(Color newColor){
        for (JPanel[] linea : board) {
            for(JPanel boton : linea) {
                if (boton.getBackground() == colorBorde) {
                    boton.setBackground(newColor);
                }
            }
        }
        this.colorBorde = newColor;
    }

    /*
    @Autor Archila - Otero
    actualiza el nombre del tablero
     */
    public void setName(String name){ this.name = name;}

    /*
    @Autor Archila - Otero
    actualiza los recods del tablero
     */
    public void actualiceRecords() {
        try {
            // Buscamos si la puntuacion es mayor a algun record
            ArrayList<String> lines = new ArrayList<>();
            BufferedReader bIn = new BufferedReader(new FileReader("./archivos/records.txt"));
            String line = bIn.readLine();
            while (line != null) {
                lines.add(line);
                line = bIn.readLine();
            }
            boolean añadido = false;
            for (int i = 0; i < 5; i ++){
                String[] record = lines.get(i).split("          ");
                if (this.puntuacion > Integer.parseInt(record[1]) && !añadido){
                    lines.add(i, this.name + "          " + this.puntuacion);
                    añadido = true;
                }
            }
            // En caso de que si sea una mejor puntuacion, se reemplaza
            PrintWriter printWriter = new PrintWriter(new FileOutputStream("./archivos/records.txt"));
            for (int i = 0; i < 5; i++){
                printWriter.println(lines.get(i));
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    @Autor Archila - Otero
    Devuelve el color del fondo(borde) del tablero
     */
    public Color getColorFondo(){
        return colorBorde;
    }

    public void changeColors(){
        for(int i=0; i<20; i++){
            for(int j=0; j<10; j++){
                board[i + 1][j + 1].revalidate();
                if(tablero[i][j] == 1 || tablero[i][j] == -1) changeBordes(i, j, Color.CYAN, -1);
                else if(tablero[i][j] == 2 || tablero[i][j] == -2) changeBordes(i, j, Color.ORANGE, -2);
                else if(tablero[i][j] == 3 || tablero[i][j] == -3) changeBordes(i, j, Color.GREEN, -3);
                else if(tablero[i][j] == 4 || tablero[i][j] == -4) changeBordes(i, j, Color.MAGENTA, -4);
                else if(tablero[i][j] == 5 || tablero[i][j] == -5) changeBordes(i, j, Color.YELLOW, -5);
                else if(tablero[i][j] == 16) board[i + 1][j + 1].setBackground(Color.BLUE);
                else if(tablero[i][j] == 6) board[i+1][j+1].setBackground(new Color(253, 253,150));
                else if(tablero[i][j] == 7) board[i + 1][j + 1].setBackground(new Color(87, 35, 100));
                else if(tablero[i][j] == 8) board[i+1][j+1].setBackground(new Color(189,236,182));
                else if(tablero[i][j] == 9) board[i+1][j+1].setBackground(new Color(255,164,32));
                else if(tablero[i][j] == 0){
                    board[i + 1][j + 1].setBackground(Color.black);
                    board[i + 1][j + 1].setBorder(null);
                } else if(tablero[i][j] < -10){
                    borde = BorderFactory.createLineBorder(new Color(138,149,151), 1);
                    board[i + 1][j + 1].setBorder(borde);
                    if (tablero[i][j] == -11) board[i + 1][j + 1].setBackground(Color.CYAN);
                    else if (tablero[i][j] == -12) board[i + 1][j + 1].setBackground(Color.ORANGE);
                    else if (tablero[i][j] == -13) board[i + 1][j + 1].setBackground(Color.GREEN);
                    else if (tablero[i][j] == -14) board[i + 1][j + 1].setBackground(Color.MAGENTA);
                    else if (tablero[i][j] == -15) board[i + 1][j + 1].setBackground(Color.YELLOW);
                }
            }
        }
        verifiqueLinea();
    }

    private void changeBordes(int i, int j, Color color, int negNum){
        board[i + 1][j + 1].setBackground(color);
        if(logictetris.tipofigura() == 'N' || tablero[i][j] == negNum) {
            borde = BorderFactory.createLineBorder(Color.BLACK, 1);
            board[i + 1][j + 1].setBorder(borde);
        }else if(logictetris.tipofigura() == 'U') {
            borde = BorderFactory.createLineBorder(new Color(138,149,151), 1);
            board[i + 1][j + 1].setBorder(borde);
        }else if(logictetris.tipofigura() == 'W') {
            borde = BorderFactory.createLineBorder(new Color(212,175,55), 1);
            board[i + 1][j + 1].setBorder(borde);
        }else if(logictetris.tipofigura() == 'B') {
            borde = BorderFactory.createLineBorder(Color.RED, 1);
            board[i + 1][j + 1].setBorder(borde);
        }
    }

    /*
    @Autor Archila - Otero
    verifica si alguna linea esta completa para borrarla
     */
    private void verifiqueLinea(){
        for(int i=19; i>=0; i-=1) {
            int contador = 0;
            for (int j = 9; j >= 0; j-=1) {
                if (tablero[i][j] < 0 && tablero[i][j] > -10) contador += 1;
                if (contador == 10){
                    puntuacion += 200;
                    puntuacionLabel.setText("Puntuacion " + this.name +": "+ puntuacion);
                    logictetris.bajelineas(i, j);
                }
            }
        }
    }

    /*
    @Autor Archila - Otero
    actualiza la puntuacion del tablero
     */
    public void setPuntuacion(JLabel puntuacionLabel){
        this.puntuacionLabel = puntuacionLabel;
    }

    /*
    @Autor Archila - Otero
    inicia el tablero del jugador, generando la primera ficha
     */
    public void inicie(){
        logictetris.nuevafigura();
        juega = logictetris.terminojuego();
        changeColors();
    }

    /*
    @Autor Archila - Otero
    este es el metodo que hace que las fichas bajen y si estan abajo saca una nueva
     */
    public void juegue() {
        logictetris.abajo();
        if(logictetris.bufocapturadoF()) bufocapturadoT = true;
        if (!logictetris.terminoficha()) {
            if (logictetris.tipofigura() == 'B'){
                puntuacion += 50;
                puntuacionLabel.setText("Puntuacion " + this.name +": "+ puntuacion);
            }
            logictetris.nuevafigura();
        }
        if (logictetris.terminojuego()) {
            actualiceRecords();
            JOptionPane.showMessageDialog(null, "Buen intento crack \nPuntuacion final: " + puntuacion, "TERMINO EL JUEGO", JOptionPane.INFORMATION_MESSAGE);
        }
        changeColors();
    }

    /*
    @Autor Archila - Otero
    indica al tablero logico que eljugador quiere moverse a la derecha
     */
    public void Derecha(){
        try {
            logictetris.Derecha();
            changeColors();
        }catch (NullPointerException e){
            logictetris = logictetris;
        }
    }

    /*
    @Autor Archila - Otero
    indica al tablero logico que eljugador quiere moverse a la izquierda
     */
    public void Izquierda(){
        try {
            logictetris.Izquierda();
            changeColors();
        }catch (NullPointerException e){
            logictetris = logictetris;
        }
    }

    /*
    @Autor Archila - Otero
    indica al tablero logico que eljugador quiere rotar
     */
    public void Rote(){
        try {
            logictetris.rote();
            changeColors();
        }catch (NullPointerException e){
            logictetris = logictetris;
        }
    }

    /*
    @Autor Archila - Otero
    indica al tablero logico que eljugador quiere moverse hacia abajo
     */
    public void baje(){
        try {
            logictetris.abajo();
            changeColors();
        }catch (NullPointerException e){
            logictetris = logictetris;
        }
    }

    /*
    @Autor Archila - Otero
    pregunta al tablero logico si el juego ya termino
     */
    public boolean termino() {
        return logictetris.terminojuego();
    }

    /*
    @Autor Archila - Otero
    metodo que importa un juego que esta guardado
     */
    public void importe(File archivo) throws IOException {
        BufferedReader bIn = new BufferedReader(new FileReader(archivo));
        String logicLine = bIn.readLine();
        logicLine = logicLine.trim();
        int fila = 0;
        int[][] newMatrizTetris = new int[boardHeight][boardWidth];
        while (logicLine != null && !Objects.equals(logicLine, " ")){
            logicLine = logicLine.trim();
            String[] logicNewLine = logicLine.split(" ");
            int[] logicIntLine = new int[boardWidth];
            for (int i = 0 ; i < boardWidth ; i++){
                if (!Objects.equals(logicNewLine[i], "")) logicIntLine[i] = Integer.parseInt(logicNewLine[i]);
            }
            newMatrizTetris[fila] = logicIntLine;
            fila += 1;
            logicLine = bIn.readLine();
        }
        logictetris = new Tetris(newMatrizTetris);
        tablero = logictetris.getTablero();
    }

    /*
    @Autor Archila - Otero
    exporta el juego actual a un archivo
     */
    public void exporte(File archivo) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(archivo));
        for(int[] fila : logictetris.getTablero()){
            String newFila = "";
            for (int num : fila){
                newFila += (num + " ");
            }
            printWriter.println(newFila);
        }
        printWriter.close();
    }

    /*
    @Autor Archila - Otero
    genera una nuevo Tetris con su tablero records y puntuacion
     */
    public void newTetris() {
        this.logictetris = new Tetris();
        tablero = logictetris.getTablero();
        actualiceRecords();
        this.puntuacion = 0;
        puntuacionLabel.setText("Puntuacion " + this.name +": "+ puntuacion);
    }
}
