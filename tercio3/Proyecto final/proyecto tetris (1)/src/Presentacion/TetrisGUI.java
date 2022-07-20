package Presentacion;

import Dominio.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class TetrisGUI
 *
 * @author  Archila-Otero
 */
public class TetrisGUI extends JFrame implements ActionListener, KeyListener, ItemListener, ChangeListener {

    //Int
    private int height;
    private int width;
    private final int boardheight = 20;
    private final int boardwidth = 10;
    private int Delay;
    private int Delay2;
    private int tiempojugado;
    private String nomb1 = "p1";
    private String nomb2 = "p2";

    //boolean
    private boolean Dosplayers = false;
    private boolean PlayervsMaquina = false;
    private boolean pausa = true;
    private boolean bufoactivo = false;
    private boolean bufoenpantalla = false;

    //Timer
    private Timer timer;
    private Timer tiempojuego;
    private Timer timer1;
    private Timer timer2;
    private Timer crono;
    private Timer tiempobufo;
    private Timer acciones;

    //Bufo
    private Buffo bufo = new Buffo();

    //Maquina
    private Maquina maquina;

    //String
    private String lastTablero;

    //JMenu
    private JMenuBar menuBar = new JMenuBar();
    private final JMenu menu = new JMenu("Menu");

    private final JMenuItem nuevo = new JMenuItem("Nuevo");
    private final JMenuItem abrir = new JMenuItem("Abrir");
    private final JMenuItem salvar = new JMenuItem("Salvar");
    private final JMenuItem salir = new JMenuItem("Salir");

    //JLabel
    private JLabel namePlayer;
    private JLabel name1;
    private JLabel name2;
    private JLabel namePersona;
    private JLabel nameMaquina;
    private JLabel puntuacion;
    private JLabel tiempo;
    private JLabel puntuacion1;
    private JLabel puntuacion2;
    private JLabel tiempo2;
    private JLabel puntuacionJugador;
    private JLabel puntuacionMaquina;
    private JLabel tiempo3;
    private JLabel puntaje1;
    private JLabel puntaje2;
    private JLabel puntaje3;
    private JLabel puntaje4;
    private JLabel puntaje5;

    //JPanel
    private JPanel home;
    private JPanel configjuego;
    private JPanel elegirModo;
    private JPanel eleccionTipoMaquina;
    private JPanel tablero1Jugador;
    private JPanel tablero2jugadores;
    private JPanel tableroContraMaquina;
    private JPanel config;
    private JPanel cambieColorMenu;
    private JPanel cambieColorPerfilMenu;
    private JPanel records;
    private JPanel bufosausar;

    //Tablero
    private Tablero board1Jugador;
    private Tablero boardMulti1;
    private Tablero boardMulti2;
    private Tablero boardJugador;
    private Tablero boardMaquina;

    //JButton
    private JButton homeJugar;
    private JButton homeRecords;
    private JButton homeSalir;
    private JButton confaceptar;
    private JButton modoUnJugador;
    private JButton modoDosJugadores;
    private JButton modoJugadorMaquina;
    private JButton volverModoMenu;
    private JButton eleccionNovato;
    private JButton eleccionExperto;
    private JButton eleccionVolver;
    private JButton volverRecordsMenu;
    private JButton pauseButton1J;
    private JButton pauseButton2J;
    private JButton pauseButtonMaquina;
    private JButton configVolverJuego;
    private JButton configCambiarColor;
    private JButton configCambiarNombre;
    private JButton configSalirMenu;
    private JButton volverConfigCambieColor;
    private JButton volverConfigCambiePerfil;
    private JButton cambiarColorPerfil;
    private JButton cambiarColorBackGround;
    private JButton cambiarColorPerfilNickname;
    private JButton cambiarColorPerfilBackground;

    //JComboBox
    private JComboBox confvelocidad;

    //JCheckBox
    private JCheckBox todos;
    private JCheckBox bufo1;
    private JCheckBox bufo2;
    private JCheckBox bufo3;
    private JCheckBox bufo4;

    /*
    Constructor de TetrisGUI
     */
    private TetrisGUI(){
        super("Tetris");
        prepareElementos();
        prepareAcciones();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    }

    /*
    Funcion main de TetrisGUI
     */
    public static void main(String[] args){
        TetrisGUI tetrisGUI = new TetrisGUI();
        tetrisGUI.setVisible(true);
    }

    /*
    Prepara todos los elementos necesarios para el juego
     */
    private void prepareElementos(){
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.height = (int)screen.getHeight();
        this.width = (int)screen.getWidth();
        setSize(width/2, height/2);
        setLocation(width/4, height/4);
        setFocusable(true);
        Image logo = Toolkit.getDefaultToolkit().getImage("./images/Iconos/tetrisLogo.png");
        setIconImage(logo);
        prepareElementosMenu();
        prepareElementosHome();
        prepareElementosconfigjuego();
        prepareElementosElegirModo();
        prepareElementosEleccionMaquina();
        prepareElementosTableroUnJugador();
        prepareElementosTableroDosJugadores();
        prepareElementosContraMaquina();
        prepareElementosConfig();
        prepareElementosCambieColor();
        prepareElementosMenuCambiePerfil();
        prepareElementosRecords();
        add(home);
    }

    /*
    Prepara y añade todos los botones al menu del juego
     */
    private void prepareElementosMenu(){
        setJMenuBar(menuBar);

        menuBar.add(menu);
        menu.add(nuevo);
        menu.add(abrir);
        menu.add(salvar);
        menu.add(salir);
    }

    /*
    Nueva clase que extiende a JPanel para poder poner una imagen como fondo
     */
    class FondoJpanel extends JPanel{
        private final String imageUrl;

        /*
        Constructor de la clase JPanel
         */
        public FondoJpanel(String imageUrl){
            this.imageUrl = imageUrl;
        }

        /*
        Se hace un Override de la funcion paint, dejando añadir una imagen
         */
        @Override
        public void paint(Graphics g) {
            Image imagen = new ImageIcon(imageUrl).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }

    /*
    Prepara los elementos (JButton, JPanel y Fondos) del menu principal
     */
    private void prepareElementosHome(){
        FondoJpanel fondo = new FondoJpanel("./images/Fondos/menuFondo.gif");
        fondo.setBounds(0, 0, width /2, height /2);

        home = new JPanel();
        home.setLayout(null);

        FondoJpanel tetrisLogo = new FondoJpanel("./images/Iconos/tetrisLogo.png");
        tetrisLogo.setBounds((width/4) - 150, 30, 300, 209);
        home = new JPanel();
        home.setLayout(null);

        homeJugar = new JButton("Jugar");
        homeJugar.setFont(new Font("Magneto", Font.BOLD, 20));
        homeJugar.setBackground(Color.BLACK);
        homeJugar.setForeground(Color.MAGENTA);
        homeJugar.setBounds((width/4) - 75, height/4, 150, 35);
        home.add(homeJugar);

        homeRecords = new JButton("Records");
        homeRecords.setFont(new Font("Magneto", Font.BOLD, 20));
        homeRecords.setBackground(Color.BLACK);
        homeRecords.setForeground(Color.MAGENTA);
        homeRecords.setBounds((width/4) - 75, (height/4)+50, 150, 35);
        home.add(homeRecords);

        homeSalir = new JButton("Salir");
        homeSalir.setFont(new Font("Magneto", Font.BOLD, 20));
        homeSalir.setBackground(Color.BLACK);
        homeSalir.setForeground(Color.MAGENTA);
        homeSalir.setBounds((width/4) - 75, (height/4)+100, 150, 35);
        home.add(homeSalir);

        home.setBackground(Color.BLACK);
        home.add(tetrisLogo);
        home.add(fondo);
        add(home);
    }

    private void prepareElementosconfigjuego(){
        configjuego = new JPanel();
        configjuego.setLayout(null);

        confvelocidad = new JComboBox<String>();
        confvelocidad.setBounds((width/4) -75 , (height/4)+7, 150, 35);
        confvelocidad.addItem("Seleccione velocidad");
        confvelocidad.addItem("Normal");
        confvelocidad.addItem("Acelerada");
        confvelocidad.addItemListener(this);
        confvelocidad.setBackground(Color.BLACK);
        confvelocidad.setForeground(Color.MAGENTA);
        configjuego.add(confvelocidad);

        bufosausar = new JPanel();
        bufosausar.setLayout(null);
        bufosausar.setBackground(Color.BLACK);
        bufosausar.setForeground(Color.MAGENTA);
        bufosausar.setBounds((width/4) -75 , (height/4)-160, 150, 155);

        todos = new JCheckBox("Todos");
        todos.setBounds(0 , 0, 150, 30);
        todos.addChangeListener(this);
        todos.setBackground(Color.BLACK);
        todos.setForeground(Color.MAGENTA);
        bufosausar.add(todos);

        bufo1 = new JCheckBox("StopTime");
        bufo1.setBounds(0 , 30, 150, 30);
        bufo1.addChangeListener(this);
        bufo1.setBackground(Color.BLACK);
        bufo1.setForeground(Color.MAGENTA);
        bufosausar.add(bufo1);

        bufo2 = new JCheckBox("StopDiece");
        bufo2.setBounds(0 , 60, 150, 30);
        bufo2.addChangeListener(this);
        bufo2.setBackground(Color.BLACK);
        bufo2.setForeground(Color.MAGENTA);
        bufosausar.add(bufo2);

        bufo3 = new JCheckBox("Slow");
        bufo3.setBounds(0 , 90, 150, 30);
        bufo3.addChangeListener(this);
        bufo3.setBackground(Color.BLACK);
        bufo3.setForeground(Color.MAGENTA);
        bufosausar.add(bufo3);

        bufo4 = new JCheckBox("2x");
        bufo4.setBounds(0 , 120, 150, 30);
        bufo4.addChangeListener(this);
        bufo4.setBackground(Color.BLACK);
        bufo4.setForeground(Color.MAGENTA);
        bufosausar.add(bufo4);

        configjuego.add(bufosausar);

        confaceptar = new JButton("Aceptar");
        confaceptar.setFont(new Font("Magneto", Font.BOLD, 16));
        confaceptar.setBackground(Color.BLACK);
        confaceptar.setForeground(Color.MAGENTA);
        confaceptar.setBounds((width/4) - 75, (height/4)+50, 150, 35);
        configjuego.add(confaceptar);

        FondoJpanel fondo = new FondoJpanel("./images/Fondos/elegirModoFondo.gif");
        fondo.setBounds(0, 0, width /2, height /2);
        configjuego.add(fondo);
        configjuego.setVisible(false);
    }

    /*
    Prepara los elementos (JButton, JPanel y Fondos) del menu en el que el usuario elige el modo que quiere jugar (Un
    Jugador, Dos Jugadores, Jugador VS CPU)
     */
    private void prepareElementosElegirModo(){
        elegirModo = new JPanel();
        elegirModo.setLayout(null);

        modoUnJugador = new JButton("Un Jugador");
        modoUnJugador.setFont(new Font("Magneto", Font.BOLD, 16));
        modoUnJugador.setBackground(Color.BLACK);
        modoUnJugador.setForeground(Color.MAGENTA);
        modoUnJugador.setBounds((width/4) - 75, height/4, 150, 35);
        elegirModo.add(modoUnJugador);

        modoDosJugadores = new JButton("Jugador VS Jugador");
        modoDosJugadores.setFont(new Font("Magneto", Font.BOLD, 16));
        modoDosJugadores.setBackground(Color.BLACK);
        modoDosJugadores.setForeground(Color.MAGENTA);
        modoDosJugadores.setBounds((width/4) - 110, (height/4) + 50, 220, 35);
        elegirModo.add(modoDosJugadores);

        modoJugadorMaquina = new JButton("Jugador VS CPU");
        modoJugadorMaquina.setFont(new Font("Magneto", Font.BOLD, 16));
        modoJugadorMaquina.setBackground(Color.BLACK);
        modoJugadorMaquina.setForeground(Color.MAGENTA);
        modoJugadorMaquina.setBounds((width/4) - 90, (height/4) + 100, 180, 35);
        elegirModo.add(modoJugadorMaquina);

        volverModoMenu = new JButton();
        volverModoMenu.setIcon(new ImageIcon("./images/Botones/blueArrow.png"));
        volverModoMenu.setBounds(0, 0, 50, 56);
        volverModoMenu.setBorderPainted(false);
        volverModoMenu.setContentAreaFilled(false);
        volverModoMenu.setFocusPainted(false);
        volverModoMenu.setOpaque(false);
        elegirModo.add(volverModoMenu);

        FondoJpanel fondo = new FondoJpanel("./images/Fondos/elegirModoFondo.gif");
        fondo.setBounds(0, 0, width /2, height /2);
        elegirModo.add(fondo);
        elegirModo.setVisible(false);
    }

    private void prepareElementosEleccionMaquina(){
        eleccionTipoMaquina = new JPanel();
        eleccionTipoMaquina.setLayout(null);

        eleccionNovato = new JButton("Novato");
        eleccionNovato.setFont(new Font("Magneto", Font.BOLD, 20));
        eleccionNovato.setBackground(Color.BLACK);
        eleccionNovato.setForeground(Color.MAGENTA);
        eleccionNovato.setBounds((this.getWidth()*2/6) - 75, this.getHeight()/2, 150, 35);
        eleccionTipoMaquina.add(eleccionNovato);

        eleccionExperto = new JButton("Experto");
        eleccionExperto.setFont(new Font("Magneto", Font.BOLD, 20));
        eleccionExperto.setBackground(Color.BLACK);
        eleccionExperto.setForeground(Color.MAGENTA);
        eleccionExperto.setBounds((this.getWidth()*4/6) - 75, this.getHeight()/2, 150, 35);
        eleccionTipoMaquina.add(eleccionExperto);

        eleccionVolver = new JButton("Volver");
        eleccionVolver.setFont(new Font("Magneto", Font.BOLD, 20));
        eleccionVolver.setBackground(Color.BLACK);
        eleccionVolver.setForeground(Color.MAGENTA);
        eleccionVolver.setBounds((width/4) - 75, (height/4)+100, 150, 35);
        eleccionTipoMaquina.add(eleccionVolver);

        FondoJpanel fondo = new FondoJpanel("./images/Fondos/elegirModoFondo.gif");
        fondo.setBounds(0, 0, width /2, height /2);
        eleccionTipoMaquina.add(fondo);
        elegirModo.setVisible(false);
    }

    /**
    Prepara los elementos (JButton, tablero, JPanel y Fondos) del modo Un Jugador
     */
    private void prepareElementosTableroUnJugador() {
        tablero1Jugador = new JPanel();
        tablero1Jugador.setLayout(null);

        namePlayer = new JLabel("Jugador1", SwingConstants.CENTER);
        namePlayer.setBounds((this.getWidth()/2)-50, 0, 100,35);
        namePlayer.setForeground(Color.BLUE);
        namePlayer.setBackground(Color.BLACK);
        namePlayer.setOpaque(true);
        namePlayer.setFont(new Font("Magneto", Font.BOLD, 15));
        tablero1Jugador.add(namePlayer);

        board1Jugador = new Tablero();
        board1Jugador.setName("P1");
        JPanel board = board1Jugador.createGame(boardheight, boardwidth, getWidth(), getHeight());
        tablero1Jugador.add(board);
        tablero1Jugador.setVisible(false);

        puntuacion = new JLabel("Puntuacion "+ nomb1 + ": 0", SwingConstants.CENTER);
        puntuacion.setBounds(0, (this.getHeight()/2)-50, 200,35);
        puntuacion.setForeground(Color.BLUE);
        puntuacion.setBackground(Color.BLACK);
        puntuacion.setOpaque(true);
        puntuacion.setFont(new Font("Magneto", Font.BOLD, 15));
        board1Jugador.setPuntuacion(puntuacion);
        tablero1Jugador.add(puntuacion);

        tiempo = new JLabel("timepo: 0", SwingConstants.CENTER);
        tiempo.setBounds(0, (this.getHeight()/2)-100, 200,35);
        tiempo.setForeground(Color.BLUE);
        tiempo.setBackground(Color.BLACK);
        tiempo.setOpaque(true);
        tiempo.setFont(new Font("Magneto", Font.BOLD, 15));
        tablero1Jugador.add(tiempo);

        pauseButton1J = new JButton();
        pauseButton1J.setIcon(new ImageIcon("./images/Botones/pause.png"));
        pauseButton1J.setBounds(this.getWidth()-75, 0, 50, 50);
        pauseButton1J.setBorderPainted(false);
        pauseButton1J.setContentAreaFilled(false);
        pauseButton1J.setFocusPainted(false);
        pauseButton1J.setOpaque(false);
        tablero1Jugador.add(pauseButton1J);

        FondoJpanel fondo = new FondoJpanel("./images/Fondos/unJugadorFondo.gif");
        fondo.setBounds(0, 0, width /2, height /2);
        tablero1Jugador.add(fondo);
        tablero1Jugador.setVisible(false);

        Delay = 400;
        timer = new Timer(Delay, this::juegue1);
        tiempojuego = new Timer(1000, this::cambietiempo);
        crono = new Timer(10000, this::aumenta);
    }

    /*
    Prepara los elementos (JButton, tableros, JPanel y Fondos) del modo Dos Jugador
     */
    private void prepareElementosTableroDosJugadores() {
        tablero2jugadores = new JPanel();
        tablero2jugadores.setLayout(null);

        name1 = new JLabel("Jugador1", SwingConstants.CENTER);
        name1.setForeground(Color.BLUE);
        name1.setBackground(Color.BLACK);
        name1.setOpaque(true);
        name1.setFont(new Font("Magneto", Font.BOLD, 15));
        name1.setBounds(((this.getWidth()/2)-(this.getWidth()/4)) - 50, 0, 100,35);

        name2 = new JLabel("Jugador2", SwingConstants.CENTER);
        name2.setForeground(Color.BLUE);
        name2.setBackground(Color.BLACK);
        name2.setOpaque(true);
        name2.setFont(new Font("Magneto", Font.BOLD, 15));
        name2.setBounds(((this.getWidth()/2)+(this.getWidth()/4)) - 50, 0, 100,35);
        tablero2jugadores.add(name1);
        tablero2jugadores.add(name2);

        boardMulti1 = new Tablero();
        boardMulti1.setName(nomb1);
        boardMulti2 = new Tablero();
        boardMulti2.setName(nomb2);
        JPanel board = boardMulti1.createGame(boardheight, boardwidth, getWidth(), getHeight());
        JPanel board2 = boardMulti2.createGame(boardheight, boardwidth, getWidth(), getHeight());
        board.setBounds(((this.getWidth()/2)-(this.getWidth()/4)) - getWidth()/8, 40, getWidth()/4,(getHeight()*3)/4);
        board2.setBounds(((this.getWidth()/2)+(this.getWidth()/4))- getWidth()/8, 40, getWidth()/4,(getHeight()*3)/4);
        tablero2jugadores.add(board);
        tablero2jugadores.add(board2);
        tablero2jugadores.setVisible(false);

        puntuacion1 = new JLabel("Puntuacion "+ nomb1 + ": 0", SwingConstants.CENTER);
        puntuacion1.setBounds((this.getWidth()/2)-100, (this.getHeight()/2)-50, 200,35);
        puntuacion1.setForeground(Color.BLUE);
        puntuacion1.setBackground(Color.BLACK);
        puntuacion1.setOpaque(true);
        puntuacion1.setFont(new Font("Magneto", Font.BOLD, 15));
        boardMulti1.setPuntuacion(puntuacion1);
        tablero2jugadores.add(puntuacion1);

        puntuacion2 = new JLabel("Puntuacion "+ nomb2 + ": 0", SwingConstants.CENTER);
        puntuacion2.setBounds((this.getWidth()/2)-100, (this.getHeight()/2), 200,35);
        puntuacion2.setForeground(Color.BLUE);
        puntuacion2.setBackground(Color.BLACK);
        puntuacion2.setOpaque(true);
        puntuacion2.setFont(new Font("Magneto", Font.BOLD, 15));
        boardMulti2.setPuntuacion(puntuacion2);
        tablero2jugadores.add(puntuacion2);

        tiempo2 = new JLabel("timepo: 0", SwingConstants.CENTER);
        tiempo2.setBounds((this.getWidth()/2)-100, (this.getHeight()/2)-100, 200,35);
        tiempo2.setForeground(Color.BLUE);
        tiempo2.setBackground(Color.BLACK);
        tiempo2.setOpaque(true);
        tiempo2.setFont(new Font("Magneto", Font.BOLD, 15));
        tablero2jugadores.add(tiempo2);

        pauseButton2J = new JButton();
        pauseButton2J.setIcon(new ImageIcon("./images/Botones/pause.png"));
        pauseButton2J.setBounds(this.getWidth()-75, 0, 50, 50);
        pauseButton2J.setBorderPainted(false);
        pauseButton2J.setContentAreaFilled(false);
        pauseButton2J.setFocusPainted(false);
        pauseButton2J.setOpaque(false);
        tablero2jugadores.add(pauseButton2J);

        FondoJpanel fondo = new FondoJpanel("./images/Fondos/unJugadorFondo.gif");
        fondo.setBounds(0, 0, width /2, height /2);
        tablero2jugadores.add(fondo);
        tablero2jugadores.setVisible(false);

        Delay2 = 700;
        timer1 = new Timer(700 , this:: juegue2);
    }

    private void prepareElementosContraMaquina(){
        tableroContraMaquina = new JPanel();
        tableroContraMaquina.setLayout(null);

        namePersona = new JLabel("Jugador1", SwingConstants.CENTER);
        namePersona.setForeground(Color.BLUE);
        namePersona.setBackground(Color.BLACK);
        namePersona.setOpaque(true);
        namePersona.setFont(new Font("Magneto", Font.BOLD, 15));
        namePersona.setBounds(((this.getWidth()/2)-(this.getWidth()/4)) - 50, 0, 100,35);

        nameMaquina = new JLabel("Jugador2", SwingConstants.CENTER);
        nameMaquina.setForeground(Color.BLUE);
        nameMaquina.setBackground(Color.BLACK);
        nameMaquina.setOpaque(true);
        nameMaquina.setFont(new Font("Magneto", Font.BOLD, 15));
        nameMaquina.setBounds(((this.getWidth()/2)+(this.getWidth()/4)) - 50, 0, 100,35);
        tableroContraMaquina.add(nameMaquina);
        tableroContraMaquina.add(nameMaquina);

        boardJugador = new Tablero();
        boardJugador.setName(nomb1);
        boardMaquina = new Tablero();
        boardMaquina.setName(nomb2);
        JPanel board = boardJugador.createGame(boardheight, boardwidth, getWidth(), getHeight());
        JPanel board2 = boardMaquina.createGame(boardheight, boardwidth, getWidth(), getHeight());
        board.setBounds(((this.getWidth()/2)-(this.getWidth()/4)) - getWidth()/8, 40, getWidth()/4,(getHeight()*3)/4);
        board2.setBounds(((this.getWidth()/2)+(this.getWidth()/4))- getWidth()/8, 40, getWidth()/4,(getHeight()*3)/4);
        tableroContraMaquina.add(board);
        tableroContraMaquina.add(board2);
        tableroContraMaquina.setVisible(false);

        puntuacionJugador = new JLabel("Puntuacion "+ nomb1 + ": 0", SwingConstants.CENTER);
        puntuacionJugador.setBounds((this.getWidth()/2)-100, (this.getHeight()/2)-50, 200,35);
        puntuacionJugador.setForeground(Color.BLUE);
        puntuacionJugador.setBackground(Color.BLACK);
        puntuacionJugador.setOpaque(true);
        puntuacionJugador.setFont(new Font("Magneto", Font.BOLD, 15));
        boardJugador.setPuntuacion(puntuacionJugador);
        tableroContraMaquina.add(puntuacionJugador);

        puntuacionMaquina = new JLabel("Puntuacion "+ nomb2 + ": 0", SwingConstants.CENTER);
        puntuacionMaquina.setBounds((this.getWidth()/2)-100, (this.getHeight()/2), 200,35);
        puntuacionMaquina.setForeground(Color.BLUE);
        puntuacionMaquina.setBackground(Color.BLACK);
        puntuacionMaquina.setOpaque(true);
        puntuacionMaquina.setFont(new Font("Magneto", Font.BOLD, 15));
        boardMaquina.setPuntuacion(puntuacionMaquina);
        tableroContraMaquina.add(puntuacionMaquina);

        tiempo3 = new JLabel("timepo: 0", SwingConstants.CENTER);
        tiempo3.setBounds((this.getWidth()/2)-100, (this.getHeight()/2)-100, 200,35);
        tiempo3.setForeground(Color.BLUE);
        tiempo3.setBackground(Color.BLACK);
        tiempo3.setOpaque(true);
        tiempo3.setFont(new Font("Magneto", Font.BOLD, 15));
        tableroContraMaquina.add(tiempo3);

        pauseButtonMaquina = new JButton();
        pauseButtonMaquina.setIcon(new ImageIcon("./images/Botones/pause.png"));
        pauseButtonMaquina.setBounds(this.getWidth()-75, 0, 50, 50);
        pauseButtonMaquina.setBorderPainted(false);
        pauseButtonMaquina.setContentAreaFilled(false);
        pauseButtonMaquina.setFocusPainted(false);
        pauseButtonMaquina.setOpaque(false);
        tableroContraMaquina.add(pauseButtonMaquina);

        FondoJpanel fondo = new FondoJpanel("./images/Fondos/unJugadorFondo.gif");
        fondo.setBounds(0, 0, width /2, height /2);
        tableroContraMaquina.add(fondo);
        tableroContraMaquina.setVisible(false);

        timer2 = new Timer(700 , this:: juegue3);
    }

    /*
    Prepara los elementos (JButton, JPanel y Fondos) del menu de configuracion
     */
    private void prepareElementosConfig(){
        config = new JPanel();
        config.setLayout(null);

        configVolverJuego = new JButton("Volver al juego");
        configVolverJuego.setFont(new Font("Magneto", Font.BOLD, 15));
        configVolverJuego.setBackground(Color.BLACK);
        configVolverJuego.setForeground(Color.MAGENTA);
        configVolverJuego.setBounds((this.getWidth()*2/10) - 100, this.getHeight()/10, 200, 35);
        config.add(configVolverJuego);

        configCambiarColor = new JButton("Cambiar colores");
        configCambiarColor.setFont(new Font("Magneto", Font.BOLD, 15));
        configCambiarColor.setBackground(Color.BLACK);
        configCambiarColor.setForeground(Color.MAGENTA);
        configCambiarColor.setBounds((this.getWidth()*5/10) - 100, this.getHeight()*2/10, 200, 35);
        config.add(configCambiarColor);

        configCambiarNombre = new JButton("Cambiar nombre");
        configCambiarNombre.setFont(new Font("Magneto", Font.BOLD, 15));
        configCambiarNombre.setBackground(Color.BLACK);
        configCambiarNombre.setForeground(Color.MAGENTA);
        configCambiarNombre.setBounds((this.getWidth()*8/10) - 90, this.getHeight()*3/10, 180, 35);
        config.add(configCambiarNombre);

        configSalirMenu = new JButton("Salir");
        configSalirMenu.setFont(new Font("Magneto", Font.BOLD, 15));
        configSalirMenu.setBackground(Color.BLACK);
        configSalirMenu.setForeground(Color.MAGENTA);
        configSalirMenu.setBounds((this.getWidth()*8/10) - 75, this.getHeight()-150, 150, 35);
        config.add(configSalirMenu);

        JLabel configTitulo = new JLabel("Configuraciones", SwingConstants.CENTER);
        configTitulo.setBounds(getWidth()*3/10 - 160, getHeight()*5/11 - 18, 320,36);
        configTitulo.setForeground(Color.MAGENTA);
        configTitulo.setFont(new Font("Matura MT Script Capitals", Font.BOLD, 42));
        config.add(configTitulo);

        FondoJpanel fondo = new FondoJpanel("./images/Fondos/configFondo.gif");
        fondo.setBounds(0, 0, width /2, height /2);
        config.add(fondo);
        config.setVisible(false);
    }

    /*
    Prepara los elementos (JButton, JPanel y Fondos) del menu de configuracion de los colores del juego
     */
    private void prepareElementosCambieColor(){
        cambieColorMenu = new JPanel();
        cambieColorMenu.setLayout(null);

        volverConfigCambieColor = new JButton("Volver");
        volverConfigCambieColor.setFont(new Font("Magneto", Font.BOLD, 15));
        volverConfigCambieColor.setBackground(Color.BLACK);
        volverConfigCambieColor.setForeground(Color.MAGENTA);
        volverConfigCambieColor.setBounds((this.getWidth()*2/10) - 50, this.getHeight()/10, 100, 35);
        cambieColorMenu.add(volverConfigCambieColor);

        cambiarColorPerfil = new JButton("Cambiar color del Perfil");
        cambiarColorPerfil.setFont(new Font("Magneto", Font.BOLD, 15));
        cambiarColorPerfil.setBackground(Color.BLACK);
        cambiarColorPerfil.setForeground(Color.MAGENTA);
        cambiarColorPerfil.setBounds((this.getWidth()*5/10) - 125, this.getHeight()*2/10, 250, 35);
        cambieColorMenu.add(cambiarColorPerfil);

        cambiarColorBackGround = new JButton("Cambiar color del fondo");
        cambiarColorBackGround.setFont(new Font("Magneto", Font.BOLD, 15));
        cambiarColorBackGround.setBackground(Color.BLACK);
        cambiarColorBackGround.setForeground(Color.MAGENTA);
        cambiarColorBackGround.setBounds((this.getWidth()*8/10) - 125, this.getHeight()*3/10, 250, 35);
        cambieColorMenu.add(cambiarColorBackGround);

        JLabel coloresTitulo = new JLabel("Colores", SwingConstants.CENTER);
        coloresTitulo.setBounds(getWidth()*3/10 - 160, getHeight()*5/11 - 18, 320,36);
        coloresTitulo.setForeground(Color.MAGENTA);
        coloresTitulo.setFont(new Font("Matura MT Script Capitals", Font.BOLD, 42));
        cambieColorMenu.add(coloresTitulo);

        FondoJpanel fondo = new FondoJpanel("./images/Fondos/configFondo.gif");
        fondo.setBounds(0, 0, width /2, height /2);
        cambieColorMenu.add(fondo);
        cambieColorMenu.setVisible(false);
    }

    /*
    Prepara los elementos (JButton, JPanel y Fondos) del menu de configuracion de los colores del perfil (Nombre y fondo
    del jugador)
     */
    private void prepareElementosMenuCambiePerfil(){
        cambieColorPerfilMenu = new JPanel();
        cambieColorPerfilMenu.setLayout(null);

        volverConfigCambiePerfil = new JButton("Volver");
        volverConfigCambiePerfil.setFont(new Font("Magneto", Font.BOLD, 15));
        volverConfigCambiePerfil.setBackground(Color.BLACK);
        volverConfigCambiePerfil.setForeground(Color.MAGENTA);
        volverConfigCambiePerfil.setBounds((this.getWidth()*2/10) - 50, this.getHeight()/10, 100, 35);
        cambieColorPerfilMenu.add(volverConfigCambiePerfil);

        cambiarColorPerfilNickname = new JButton("Cambiar color Nickname");
        cambiarColorPerfilNickname.setFont(new Font("Magneto", Font.BOLD, 15));
        cambiarColorPerfilNickname.setBackground(Color.BLACK);
        cambiarColorPerfilNickname.setForeground(Color.MAGENTA);
        cambiarColorPerfilNickname.setBounds((this.getWidth()*5/10) - 125, this.getHeight()*2/10, 250, 35);
        cambieColorPerfilMenu.add(cambiarColorPerfilNickname);

        cambiarColorPerfilBackground = new JButton("Cambiar color del fondo");
        cambiarColorPerfilBackground.setFont(new Font("Magneto", Font.BOLD, 15));
        cambiarColorPerfilBackground.setBackground(Color.BLACK);
        cambiarColorPerfilBackground.setForeground(Color.MAGENTA);
        cambiarColorPerfilBackground.setBounds((this.getWidth()*8/10) - 125, this.getHeight()*3/10, 250, 35);
        cambieColorPerfilMenu.add(cambiarColorPerfilBackground);

        JLabel perfilesTitulo = new JLabel("Cambio Perfil", SwingConstants.CENTER);
        perfilesTitulo.setBounds(getWidth()*3/10 - 160, getHeight()*5/11 - 18, 320,36);
        perfilesTitulo.setForeground(Color.MAGENTA);
        perfilesTitulo.setFont(new Font("Matura MT Script Capitals", Font.BOLD, 42));
        cambieColorPerfilMenu.add(perfilesTitulo);

        FondoJpanel fondo = new FondoJpanel("./images/Fondos/configFondo.gif");
        fondo.setBounds(0, 0, width /2, height /2);
        cambieColorPerfilMenu.add(fondo);
        cambieColorMenu.setVisible(false);
    }

    private void prepareElementosRecords(){
        records = new JPanel();
        records.setLayout(null);

        puntaje1 = new JLabel("puntaje1", SwingConstants.CENTER);
        puntaje1.setBounds(getWidth()/2 - 175, getHeight()*3/15 - 20, 350,30);
        puntaje1.setForeground(Color.MAGENTA);
        puntaje1.setBackground(Color.BLACK);
        puntaje1.setOpaque(true);
        puntaje1.setFont(new Font("Matura MT Script Capitals", Font.BOLD, 20));
        records.add(puntaje1);

        puntaje2 = new JLabel("puntaje2", SwingConstants.CENTER);
        puntaje2.setBounds(getWidth()/2 - 175, getHeight()*5/15 - 20, 350,30);
        puntaje2.setForeground(Color.MAGENTA);
        puntaje2.setBackground(Color.BLACK);
        puntaje2.setOpaque(true);
        puntaje2.setFont(new Font("Matura MT Script Capitals", Font.BOLD, 20));
        records.add(puntaje2);

        puntaje3 = new JLabel("puntaje3", SwingConstants.CENTER);
        puntaje3.setBounds(getWidth()/2 - 175, getHeight()*7/15 - 20, 350,30);
        puntaje3.setForeground(Color.MAGENTA);
        puntaje3.setBackground(Color.BLACK);
        puntaje3.setOpaque(true);
        puntaje3.setFont(new Font("Matura MT Script Capitals", Font.BOLD, 20));
        records.add(puntaje3);

        puntaje4 = new JLabel("puntaje4", SwingConstants.CENTER);
        puntaje4.setBounds(getWidth()/2 - 175, getHeight()*9/15 - 20, 350,30);
        puntaje4.setForeground(Color.MAGENTA);
        puntaje4.setBackground(Color.BLACK);
        puntaje4.setOpaque(true);
        puntaje4.setFont(new Font("Matura MT Script Capitals", Font.BOLD, 20));
        records.add(puntaje4);

        puntaje5 = new JLabel("puntaje5", SwingConstants.CENTER);
        puntaje5.setBounds(getWidth()/2 - 175, getHeight()*11/15 - 20, 350,30);
        puntaje5.setForeground(Color.MAGENTA);
        puntaje5.setBackground(Color.BLACK);
        puntaje5.setOpaque(true);
        puntaje5.setFont(new Font("Matura MT Script Capitals", Font.BOLD, 20));
        records.add(puntaje5);

        volverRecordsMenu = new JButton();
        volverRecordsMenu.setIcon(new ImageIcon("./images/Botones/blueArrow.png"));
        volverRecordsMenu.setBounds(0, 0, 50, 56);
        volverRecordsMenu.setBorderPainted(false);
        volverRecordsMenu.setContentAreaFilled(false);
        volverRecordsMenu.setFocusPainted(false);
        volverRecordsMenu.setOpaque(false);
        records.add(volverRecordsMenu);

        FondoJpanel fondo = new FondoJpanel("./images/Fondos/recordsFondo.gif");
        fondo.setBounds(0, 0, width /2, height /2);
        records.add(fondo);
        records.setVisible(false);
    }

    private void actualiceRecords() {
        try{
            ArrayList<String> lines = new ArrayList<>();
            int cont = 0;
            BufferedReader bIn = new BufferedReader(new FileReader("./archivos/records.txt"));
            String line = bIn.readLine();
            while (line != null) {
                lines.add(line);
                line = bIn.readLine();
                System.out.println(line);
            }
            puntaje1.setText(lines.get(0));
            puntaje2.setText(lines.get(1));
            puntaje3.setText(lines.get(2));
            puntaje4.setText(lines.get(3));
            puntaje5.setText(lines.get(4));
        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /*
    Añade los oyentes de todos los botones utilizados en el Juego
     */
    private void prepareAcciones(){
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent w) {
                salga();
            }
        });
        abrir.addActionListener(this);
        salvar.addActionListener(this);
        salir.addActionListener(this);
        homeJugar.addActionListener(this);
        confaceptar.addActionListener(this);
        modoUnJugador.addActionListener(this);
        modoDosJugadores.addActionListener(this);
        modoJugadorMaquina.addActionListener(this);
        eleccionNovato.addActionListener(this);
        eleccionExperto.addActionListener(this);
        homeRecords.addActionListener(this);
        homeSalir.addActionListener(this);
        volverModoMenu.addActionListener(this);
        volverRecordsMenu.addActionListener(this);
        pauseButton1J.addActionListener(this);
        pauseButton2J.addActionListener(this);
        pauseButtonMaquina.addActionListener(this);
        configVolverJuego.addActionListener(this);
        configCambiarColor.addActionListener(this);
        configSalirMenu.addActionListener(this);
        configCambiarNombre.addActionListener(this);
        cambiarColorBackGround.addActionListener(this);
        volverConfigCambieColor.addActionListener(this);
        volverConfigCambiePerfil.addActionListener(this);
        cambiarColorPerfil.addActionListener(this);
        cambiarColorPerfilNickname.addActionListener(this);
        cambiarColorPerfilBackground.addActionListener(this);
        this.addKeyListener(this);
    }

    /*
    Añade las acciones de todos los botones utilizados en el Juego
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==abrir) abra();
        if (e.getSource()==salvar) salve();
        if (e.getSource()==salir) salga();
        if (e.getSource()==homeJugar) jugar();
        if (e.getSource()==confaceptar) confaceptar();
        if (e.getSource()==modoUnJugador) unJugador();
        if (e.getSource()==homeRecords) abraRecords();
        if (e.getSource()==modoDosJugadores) dosjugadores();
        if (e.getSource()==modoJugadorMaquina) elegirMaquina();
        if (e.getSource()==eleccionNovato) jugarContraMaquina("Novato");
        if (e.getSource()==eleccionExperto) jugarContraMaquina("Experto");
        if (e.getSource()==homeSalir) salga();
        if (e.getSource()==volverModoMenu || e.getSource()==configSalirMenu || e.getSource()==volverRecordsMenu) vuelvaMenu();
        if (e.getSource()==pauseButton1J || e.getSource()==pauseButton2J || e.getSource()==pauseButtonMaquina || e.getSource()==volverConfigCambieColor || e.getSource()==volverConfigCambiePerfil) abraConfig();
        if (e.getSource()==configCambiarColor) abraCambieColorMenu();
        if (e.getSource()==configVolverJuego) vuelvaJuego();
        if (e.getSource()==cambiarColorBackGround) cambieColorFondo();
        if (e.getSource()==configCambiarNombre) cambieNombre();
        if (e.getSource()==cambiarColorPerfil) cambieColorPerfil();
        if (e.getSource()==cambiarColorPerfilNickname) cambieColorPerfilNickname();
        if (e.getSource()==cambiarColorPerfilBackground) cambieColorPerfilBackground();

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        ArrayList<Integer> bufos = new ArrayList<Integer>();;
        int rango = 0;
        if(todos.isSelected()) {
            bufos.add(6);
            bufos.add(7);
            bufos.add(8);
            bufos.add(9);
            rango = 4;
        }
        if(bufo1.isSelected()){
            bufos.add(6);
            rango+=1;
        }
        if(bufo2.isSelected()){
            bufos.add(7);
            rango+=1;
        }
        if(bufo3.isSelected()){
            bufos.add(8);
            rango+=1;
        }
        if(bufo4.isSelected()){
            bufos.add(9);
            rango+=1;
        }
        bufo.setbufosausar(bufos, rango);
    }

    @Override
    public void itemStateChanged(ItemEvent e){
        if(e.getSource() == confvelocidad){
            String vel = confvelocidad.getSelectedItem().toString();
            if(Objects.equals(vel, "Normal") || vel == null){
                Delay = 1000;

            }else{
                Delay = 400;
            }
            timer.setDelay(Delay);
            timer1.setDelay(Delay);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'w'){
            if(!Dosplayers && !PlayervsMaquina) {
                board1Jugador.Rote();
            }else if(!PlayervsMaquina){
                boardMulti1.Rote();
            }else{
                boardJugador.Rote();
            }
        }if(e.getKeyChar() == 'a'){
            if(!Dosplayers && !PlayervsMaquina) {
                board1Jugador.Izquierda();
            }else if(!PlayervsMaquina){
                boardMulti1.Izquierda();
            }else{
                boardJugador.Izquierda();
            }
        }if(e.getKeyChar() == 's'){
            timer.start();
            if(bufo.bufo == 7 && bufoactivo){
                bufoenpantalla= false;
                bufoactivo = false;
                bufo.bufo = 0;
            }
            if(!Dosplayers && !PlayervsMaquina) {
                board1Jugador.baje();
            }else if(!PlayervsMaquina){
                boardMulti1.baje();
            }else{
                boardJugador.baje();
            }
        }if(e.getKeyChar() == 'd') {
            if (!Dosplayers && !PlayervsMaquina) {
                board1Jugador.Derecha();
            } else if(!PlayervsMaquina){
                boardMulti1.Derecha();
            }else{
                boardJugador.Derecha();
            }
        }if(e.getKeyChar() == '.'){
            if(bufo.bufo == 6){
                timer.stop();
            }

            activarbufo();

        }if(Dosplayers || PlayervsMaquina) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if(!PlayervsMaquina) {
                    boardMulti2.Rote();
                }else{
                    boardMaquina.Rote();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if(!PlayervsMaquina) {
                    boardMulti2.Izquierda();
                }else{
                    boardMaquina.Izquierda();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if(!PlayervsMaquina) {
                    boardMulti2.Derecha();
                }else{
                    boardMaquina.Derecha();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if(!PlayervsMaquina) {
                    boardMulti2.baje();
                }else{
                    boardMaquina.baje();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    /*
    Accion del menu para abrir un archivo
     */
    private void abra() {
        try {
            if (tablero1Jugador.isVisible())throw new tetrisException(tetrisException.ABRIR_INGAME);
            JFileChooser filepath = new JFileChooser();
            filepath.setFileFilter(new FileNameExtensionFilter(null, "dat"));
            filepath.showDialog(null, "Importar");
            filepath.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            board1Jugador.importe(filepath.getSelectedFile());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, new tetrisException(tetrisException.NO_ABRIO));
        } catch (NullPointerException ex){
            JOptionPane.showMessageDialog(null, tetrisException.NO_SE_SELECCIONO_ARCHIVO);
        } catch (tetrisException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    /*
    Accion del menu para guardar un archivo
     */
    private void salve(){
        try {
            timer.stop();
            tiempojuego.stop();
            crono.stop();
            JFileChooser filepath = new JFileChooser();
            filepath.setFileFilter(new FileNameExtensionFilter(null, "dat"));
            filepath.showDialog(null, "Guardar");
            filepath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            timer.start();
            tiempojuego.start();
            crono.start();
            board1Jugador.exporte(filepath.getSelectedFile());
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, new tetrisException(tetrisException.NO_GUARDO));
        } catch (NullPointerException ex){
            JOptionPane.showMessageDialog(null, new tetrisException(tetrisException.NO_SE_SELECCIONO_ARCHIVO));
            timer.start();
            tiempojuego.start();
            crono.start();
        }
    }

    /*
    Accion del menu para salir del juego
     */
    private void salga(){
        if (JOptionPane.showConfirmDialog(rootPane, "¿Seguro que quiere salir de Tetris?", "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) System.exit(0);
    }

    /*
    Accion del menu principar para entrar al menu de modos de juego
     */
    private void jugar(){
        add(configjuego);
        home.setVisible(false);
        configjuego.setVisible(true);
    }

    private void confaceptar(){
        add(elegirModo);
        if (!todos.isSelected() && !bufo1.isSelected() && !bufo2.isSelected() && !bufo3.isSelected() && !bufo4.isSelected()) JOptionPane.showMessageDialog(null, tetrisException.NO_SELECCIONO_BUFO);
        else if (Objects.equals(confvelocidad.getSelectedItem().toString(), "Seleccione velocidad")) JOptionPane.showMessageDialog(null, tetrisException.NO_SELECCIONO_VELOCIDAD);
        else {
            configjuego.setVisible(false);
            elegirModo.setVisible(true);
        }
    }

    private void elegirMaquina(){
        add(eleccionTipoMaquina);
        elegirModo.setVisible(false);
        eleccionTipoMaquina.setVisible(true);
    }

    private void jugarContraMaquina(String dificultad){
        nomb1 = JOptionPane.showInputDialog("Jugador: ");
        PlayervsMaquina = true;
        if (Objects.equals(dificultad, "Novato")){
            boardJugador.setName(nomb1);
            namePersona.setText(nomb1);
            puntuacionJugador.setText("Puntuacion "+ nomb1 + ": 0");
            boardMaquina.setName("CPU");
            nameMaquina.setText("CPU");
            puntuacionMaquina.setText("Puntuacion "+ nomb2 + ": 0");
            add(tableroContraMaquina);
            eleccionTipoMaquina.setVisible(false);
            tableroContraMaquina.setVisible(true);
            boardJugador.inicie();
            boardMaquina.inicie();
            timer2.start();
        } else{
           JOptionPane.showMessageDialog(null, tetrisException.EN_CONSTRUCCION);
        }

    }

    /*
    Inicia el juego en modo Un Jugador
     */
    private void unJugador(){
        nomb1 = JOptionPane.showInputDialog("Jugador: ");
        if (!nomb1.isEmpty()){
            board1Jugador.setName(nomb1);
            namePlayer.setText(nomb1);
            puntuacion.setText("Puntuacion "+ nomb1 + ": 0");
        }
        add(tablero1Jugador);
        elegirModo.setVisible(false);
        tablero1Jugador.setVisible(true);
        board1Jugador.inicie();
        timer.start();
        tiempojuego.start();
        crono.start();
    }

    /*
    Inicia el juego en modo dos jugadores
     */
    private void dosjugadores(){
        Dosplayers = true;
        nomb1 = JOptionPane.showInputDialog("Jugador 1: ");
        nomb2 = JOptionPane.showInputDialog("Jugador 2: ");
        if (!nomb1.isEmpty()){
            boardMulti1.setName(nomb1);
            name1.setText(nomb1);
            puntuacion1.setText("Puntuacion "+ nomb1 + ": 0");

        }
        if (!nomb2.isEmpty()){
            boardMulti2.setName(nomb2);
            name2.setText(nomb2);
            puntuacion2.setText("Puntuacion "+ nomb2 + ": 0");
        }
        add(tablero2jugadores);
        elegirModo.setVisible(false);
        tablero2jugadores.setVisible(true);
        boardMulti1.inicie();
        boardMulti2.inicie();
        timer1.start();
    }

    /*
    Accion de volver al menu principal
     */
    private void vuelvaMenu(){
        if (JOptionPane.showConfirmDialog(rootPane, "Seguro que quiere volver al menu principal?",
                "Salir al menu", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (elegirModo.isVisible()) elegirModo.setVisible(false);
            else if (config.isVisible()) config.setVisible(false);
            else if (records.isVisible()) records.setVisible(false);
            if(Objects.equals(lastTablero, "1J")) board1Jugador.newTetris();
            else if (Objects.equals(lastTablero, "2J")){
                boardMulti1.newTetris();
                boardMulti2.newTetris();
            }
            home.setVisible(true);
        }
    }

    /*
    Accion para abrir las configuraciones del juego
     */
    private void abraConfig(){
        if (tablero1Jugador.isVisible()){
            lastTablero = "1J";
            tablero1Jugador.setVisible(false);
            pausa();
        }
        else if (tablero2jugadores.isVisible()){
            lastTablero = "2J";
            tablero2jugadores.setVisible(false);
        }
        else if (tableroContraMaquina.isVisible()){
            lastTablero = "Maquina";
            tableroContraMaquina.setVisible(false);
        }
        else if (cambieColorMenu.isVisible()) cambieColorMenu.setVisible(false);
        else if (cambieColorPerfilMenu.isVisible()) cambieColorPerfilMenu.setVisible(false);
        add(config);
        config.setVisible(true);
    }

    private void abraRecords(){
        add(records);
        actualiceRecords();
        home.setVisible(false);
        records.setVisible(true);
    }

    /*
    Accion para volver al juego
     */
    private void vuelvaJuego(){
        config.setVisible(false);
        if (lastTablero == "1J"){
            pausa();
            tablero1Jugador.setVisible(true);
        }else if (lastTablero == "2J") {
            pausa();
            tablero2jugadores.setVisible(true);
        }else if (lastTablero == "Maquina") {
            pausa();
            tableroContraMaquina.setVisible(true);
        }
    }

    /*
    Accion para iniciar el menu de cambio de color
     */
    private void abraCambieColorMenu(){
        config.setVisible(false);
        add(cambieColorMenu);
        cambieColorMenu.setVisible(true);
    }

    /*
    Accion para cambiar el nombre de el(los) jugador(es)
     */
    private void cambieNombre(){
        if (lastTablero == "1J"){
            nomb1 = JOptionPane.showInputDialog("Jugador: ");
            if (!nomb1.isEmpty()){
                namePlayer.setText(nomb1);
                puntuacion.setText("Puntuacion "+ nomb1 + ": 0");
            }
        }
        else if (lastTablero == "2J"){
            nomb1 = JOptionPane.showInputDialog("Jugador 1: ");
            nomb2 = JOptionPane.showInputDialog("Jugador 2: ");
            if (!nomb1.isEmpty()){
                name1.setText(nomb1);
                puntuacion1.setText("Puntuacion "+ nomb1 + ": 0");
            }
            if (!nomb2.isEmpty()){
                name2.setText(nomb2);
                puntuacion2.setText("Puntuacion "+ nomb2 + ": 0");
            }
        }
    }

    /*
    Accion para Cambiar el color del fondo del juego
     */
    private void cambieColorFondo(){
        Color color = JColorChooser.showDialog(null, "Seleccione un color para las fichas", board1Jugador.getColorFondo());
        if (lastTablero == "1J") board1Jugador.changeColorBackGround(color);
        if (lastTablero == "2J"){
            boardMulti1.changeColorBackGround(color);
            boardMulti2.changeColorBackGround(color);
        }
    }

    /*
    Accion para abrir el menu del menu para hacer el cambio de los colores del perfil del jugador
     */
    private void cambieColorPerfil(){
        cambieColorMenu.setVisible(false);
        add(cambieColorPerfilMenu);
        cambieColorPerfilMenu.setVisible(true);
    }

    /*
    Accion para cambiar el color del nombre de el(los) jugador(es)
     */
    private void cambieColorPerfilNickname(){
        Color color = JColorChooser.showDialog(null, "Seleccione un color para las fichas", board1Jugador.getColorFondo());
        if (lastTablero == "1J") namePlayer.setBackground(color);
        if (lastTablero == "2J"){
            name1.setBackground(color);
            name2.setBackground(color);
        }
    }

    /*
    Accion para cambiar el color del fondo del perfil de el(los) jugador(es)
     */
    private void cambieColorPerfilBackground() {
        Color color = JColorChooser.showDialog(null, "Seleccione un color para las fichas", board1Jugador.getColorFondo());
        if (lastTablero == "1J") namePlayer.setForeground(color);
        if (lastTablero == "2J") {
            name1.setForeground(color);
            name2.setForeground(color);
        }
    }

    public void cambietiempo(ActionEvent actionEvent){
        tiempojugado+=1;
        tiempo.setText("tiempo: " + tiempojugado);
    }

    private void juegue1(ActionEvent actionEvent){
        board1Jugador.juegue();
        if(board1Jugador.termino()){
            timer.stop();
            crono.stop();
        }
        if(!bufoenpantalla && bufo.bufo == 0){
            bufo.agregebufo(board1Jugador.tablero);
            bufoenpantalla = true;
        }
    }

    public void juegue2(ActionEvent actionEvent){
        boardMulti1.juegue();
        boardMulti2.juegue();
        if(boardMulti1.termino() || boardMulti2.termino()){
            timer1.stop();
        }
        if(!bufoenpantalla && bufo.bufo == 0) {
            bufo.agregebufo(boardMulti1.tablero);
            bufo.agregebufo(boardMulti2.tablero);
            bufoenpantalla = true;
        }
    }

    public void juegue3(ActionEvent actionEvent){
        boardJugador.juegue();
        boardMaquina.juegue();
        maquina = new Maquina(boardMaquina.logictetris);
        acciones = new Timer(2000, this::juegamaquina);
        acciones.start();
        if(boardJugador.termino()|| boardMaquina.termino()){
            timer2.stop();
        }
        if(!bufoenpantalla && bufo.bufo == 0) {
            bufo.agregebufo(boardJugador.tablero);
            bufoenpantalla = true;
        }
    }

    private void juegamaquina(ActionEvent actionEvent) {
        maquina.randomAction();
    }

    public void pausa(){
        this.pausa= !pausa;
        if(!pausa){
            timer.stop();
            tiempojuego.stop();
            crono.stop();
        }else{
            timer.start();
            tiempojuego.start();
            crono.start();
        }
    }

    public void aumenta(ActionEvent actionEvent){
        if(Delay > 300){
            Delay -=100;
        }else if(Delay >= 150){
            Delay -= 10;
        }
        timer.setDelay(Delay);
        timer1.setDelay(Delay);
    }

    public void activarbufo(){
        bufoactivo = true;
        if(bufo.bufo == 6){
            tiempobufo = new Timer(3000, this::abufo1);
            tiempobufo.start();
        }else if(bufo.bufo == 7){
            timer.stop();

        }else if(bufo.bufo == 8){
            timer.setDelay(Delay * 2);
            tiempobufo = new Timer(3000, this::abufo3);
            tiempobufo.start();

        }else if(bufo.bufo == 9){
            timer.setDelay(Delay / 2);
            tiempobufo = new Timer(3000, this::abufo4);
            tiempobufo.start();
        }
    }

    private void abufo4(ActionEvent actionEvent) {
        bufoactivo = false;
        timer.setDelay(Delay);
        tiempobufo.stop();
        bufoenpantalla= false;
        bufo.bufo = 0;
    }

    private void abufo3(ActionEvent actionEvent) {
        bufoactivo = false;
        timer.setDelay(Delay);
        tiempobufo.stop();
        bufoenpantalla= false;
        bufo.bufo = 0;
    }

    private void abufo1(ActionEvent actionEvent) {
        tiempobufo.stop();
        timer.start();
        bufoactivo = false;
        bufoenpantalla= false;
        bufo.bufo = 0;
    }
}