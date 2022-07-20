package presentacion;

import dominio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.ImageIcon;
import dominio.*;

/** *
 * @author Archila-Otero
 */
public class SenkuGUI extends JFrame implements ActionListener
{
    private int height;
    private int width;
    private int capturadas;
    private int tamano = 7;
    private int objetivoX;
    private int objetivoY;
    private int inicioX;
    private int inicioY;
    
    private boolean finalElegida = false;
    private boolean inicialElegida = false;
    
    private Color colorFichas = Color.black;
    private Color colorHuecos = Color.white;
    
    private JButton[][] botones = new JButton[tamano][tamano];
    
    private Senku senku;
    
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("Menu");
    private JMenu configuracion = new JMenu("Configuracion");
    
    private JLabel marcador1;
    private JLabel marcador2;
    
    private JMenuItem nuevo = new JMenuItem("Nuevo");
    private JMenuItem abrir = new JMenuItem("Abrir");
    private JMenuItem salvar = new JMenuItem("Salvar");
    private JMenuItem salir = new JMenuItem("Salir");
    private JMenuItem cambioColorFichas = new JMenuItem("Cambiar color de Fichas");
    private JMenuItem cambioColorHuecos = new JMenuItem("Cambiar color de Huecos");
    
    private JPanel tablero;
    private JPanel home;
    private JPanel game;
    
    private JButton homeJugar;
    private JButton homeSalir;
    private JButton reiniciar;
    private JButton volverInicio;
    private JButton hometamano;
    
    private SenkuGUI(){
        super("Senku");
        prepareElementos();
        senku = new Senku(tamano, colorHuecos, colorFichas);
        prepareAcciones();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args){
        SenkuGUI senkugui = new SenkuGUI();
        senkugui.setVisible(true);
    }
    
    private void prepareElementos(){
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.height = (int)screen.getHeight();
        this.width = (int)screen.getWidth();
        setSize(width/2, height/2);
        setLocation(width/4, height/4);
        prepareElementosMenu();
        prepareElementosHome();
        prepareElementosTablero();
    }
    
    private void prepareElementosMenu(){
        setJMenuBar(menuBar);
        
        menuBar.add(menu);
        menu.add(nuevo);
        menu.add(abrir);
        menu.add(salvar);
        menu.add(salir);
        
        menuBar.add(configuracion);
        configuracion.add(cambioColorFichas);
        configuracion.add(cambioColorHuecos);
    }
    
    private void prepareElementosHome(){
        home = new JPanel();
        home.setLayout(new BorderLayout(width/9, 50));
        add(home);
        
        ImageIcon img = new javax.swing.ImageIcon("imagenSenku.png");
        JLabel image = new javax.swing.JLabel(img);
        home.add(image, BorderLayout.NORTH);
        
        JPanel derechaHome = new JPanel();
        home.add(derechaHome, BorderLayout.EAST);
        
        JPanel izquierdaHome = new JPanel();
        home.add(izquierdaHome, BorderLayout.WEST);
        
        JPanel abajoHome = new JPanel();
        home.add(abajoHome, BorderLayout.SOUTH);
        
        JPanel homeButtons = new JPanel();
        homeButtons.setLayout(new GridLayout(4,1, 1, 20));
        
        homeJugar = new JButton("Jugar");
        homeButtons.add(homeJugar);
        
        homeSalir = new JButton("Salir");
        homeButtons.add(homeSalir);
        
        hometamano = new JButton("Digitar tamaño");
        homeButtons.add(hometamano);
        
        home.add(homeButtons, BorderLayout.CENTER);
    }
    
    private void prepareElementosTablero(){
        this.tablero = new JPanel();
        tablero.setLayout(new BorderLayout(250, 100));
        JPanel marcadores = new JPanel();
        marcadores.setLayout(new GridLayout(1, 4));
        JPanel blank1 = new JPanel();
        JPanel blank2 = new JPanel();
        marcador1 = new JLabel("Movimientos");
        marcador2 = new JLabel("Capturadas");
        marcador1.setForeground(Color.blue);
        marcador2.setForeground(Color.red);
        marcadores.add(blank1);
        marcadores.add(marcador1);
        marcadores.add(marcador2);
        marcadores.add(blank2);
        tablero.add(marcadores, BorderLayout.NORTH);
        JPanel derecha = new JPanel();
        tablero.add(derecha, BorderLayout.EAST);
        JPanel izquierda = new JPanel();
        tablero.add(izquierda, BorderLayout.WEST);
        JPanel abajo = new JPanel();
        abajo.setLayout(new GridLayout(1, 2, 50, 50));
        reiniciar = new JButton("Reiniciar");
        abajo.add(reiniciar);
        volverInicio = new JButton("Volver al menu principal");
        abajo.add(volverInicio);
        tablero.add(abajo, BorderLayout.SOUTH);
        createTablero();
    }
    
    private void createTablero(){
        tablero.setVisible(true);
        JPanel botonesTablero = new JPanel();
        botonesTablero.setLayout(new GridLayout(tamano, tamano, 10 ,10));
        this.botones = new JButton[tamano][tamano];
        for (int i = 0 ; i < tamano; i++){
            for (int j = 0 ; j < tamano; j++){
                JButton boton = new JButton();
                boton.setBackground(Color.gray);
                botones[i][j] = boton;
                botonesTablero.add(boton);
            }
        }
        tablero.add(botonesTablero, BorderLayout.CENTER);
        iniciarTablero();
    }
    
    private void iniciarTablero(){
        senku = new Senku(tamano, colorHuecos, colorFichas);
        int cont = 0;
        int mitad = (tamano/2);
        for(int i = 0;i<mitad+1;i++){
            if (cont == 0){
                botones[i][mitad+1].setBackground(colorHuecos);
                botones[i][mitad-1].setBackground(colorHuecos);
                botones[botones.length-i-1][mitad+1].setBackground(colorHuecos);
                botones[botones.length-i-1][mitad-1].setBackground(colorHuecos);
                botones[mitad+1][i].setBackground(colorHuecos);
                botones[mitad-1][i].setBackground(colorHuecos);
                botones[mitad+1][botones.length-i-1].setBackground(colorHuecos);
                botones[mitad-1][botones.length-i-1].setBackground(colorHuecos);
                botones[mitad][mitad].setBackground(colorHuecos);
            }
            if (cont != mitad){
                botones[i][mitad].setBackground(colorFichas);
                botones[botones.length-i-1][mitad].setBackground(colorFichas);
            }
            for(int k = 1;k<cont+1;k++){
                botones[i][mitad+k].setBackground(colorFichas);
                botones[i][mitad-k].setBackground(colorFichas);
            }
            for(int k = 1;k<cont+1;k++){
                botones[botones.length - i-1][mitad+k].setBackground(colorFichas);
                botones[botones.length - i-1][mitad-k].setBackground(colorFichas);
            }
            cont++;
        }
        marcador1.setText("Movimientos" + 0);
        marcador2.setText("Capturadas" + 0);
        verificarGrices();
    }
    
    private void verificarGrices(){
        for (int i = 0; i < tamano; i ++){
            for (int j = 0; j < tamano; j ++){
                if (botones[i][j].getBackground() == Color.gray){
                    botones[i][j].setVisible(false);
                }
            }
        }
    }
    
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
        homeSalir.addActionListener(this);
        hometamano.addActionListener(this);
        reiniciar.addActionListener(this);
        volverInicio.addActionListener(this);
        cambioColorFichas.addActionListener(this);
        cambioColorHuecos.addActionListener(this);
        for(int i = 0; i < tamano; i++){
            for (int j = 0; j < tamano; j++){
                botones[i][j].addActionListener(this);
            }
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==abrir) abra();
        if (e.getSource()==salvar) salve();
        if (e.getSource()==salir) salga();
        if (e.getSource()==homeJugar) jugar();
        if (e.getSource()==homeSalir) salga();
        if (e.getSource()==hometamano) cambietamano();
        if (e.getSource()==reiniciar) reiniciar(); 
        if (e.getSource()==volverInicio) volverMenu(); 
        if (e.getSource()==cambioColorFichas) cambieColorFichas(); 
        if (e.getSource()==cambioColorHuecos) cambieColorHuecos();
        for(int i = 0; i < tamano; i++){
            for (int j = 0; j < tamano; j++){
                if (e.getSource() == botones[i][j]) moverFicha(i, j);
            }
        }
    }
    
    private void moverFicha(int i, int j){
        if (!inicialElegida){
            inicioX = i;
            inicioY = j;
            inicialElegida = true;
        }
        else if (!finalElegida){
            objetivoX = i;
            objetivoY = j;
            finalElegida = true;
            if (inicialElegida && finalElegida){
                try
                {
                    int botonMedio[] = senku.verificarBotones(inicioX, inicioY, objetivoX, objetivoY);
                    if (botonMedio[0] != -1){
                    if (botones[botonMedio[0]][botonMedio[1]].getBackground() != Color.white){
                        senku.move(inicioX, inicioY, objetivoX, objetivoY, botonMedio);
                    }
                }
                }
                catch (SenkuException se)
                {
                    JOptionPane.showMessageDialog(null, SenkuException.MOVIMIENTO_INVALIDO);
                }
            }
        }
        if (inicialElegida && finalElegida){
            finalElegida = false;
            inicialElegida = false;
            refrescar();
        }
    }
    
    private void cambietamano(){
        this.tamano = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tamaño que desea"));
        this.botones = new JButton[tamano][tamano];
        this.senku = new Senku(tamano, colorHuecos, colorFichas);
        createTablero();
        jugar();
    }
    
    private void cambieColorFichas(){
        Color color = JColorChooser.showDialog(null, "Seleccione un color para las fichas", colorFichas);
        Color oldColor = colorFichas;
        this.colorFichas = color;
        senku.cambieColorFichas(colorFichas); 
        senku.actualiceColores(oldColor, colorFichas);
        refrescar();
    }
    
    private void cambieColorHuecos(){
        Color color = JColorChooser.showDialog(null, "Seleccione un color para los huecos", colorHuecos);
        Color oldColor = colorHuecos;
        this.colorHuecos = color;
        senku.cambieColorHuecos(colorHuecos);
        senku.actualiceColores(oldColor, colorHuecos);
        refrescar();
    }
    
    private void volverMenu(){
        tablero.setVisible(false);
        home.setVisible(true);
    }
    
    private void refrescar(){
        marcador1.setText("Movimientos" + senku.movimientos());
        marcador2.setText("Capturadas" + senku.movimientos());
        Color[][] colorTab = senku.getTablero();
        for (int i = 0; i < tamano; i ++){
            for (int j = 0; j < tamano; j ++){
                if(colorTab[i][j] != null){
                    botones[i][j].setBackground(colorTab[i][j]);
                }
            }
        }
    }
    
    private void reiniciar(){
        senku = new Senku(tamano, colorHuecos, colorFichas);
        iniciarTablero();
        jugar();
    }
    
    private void jugar(){
        home.setVisible(false);
        add(tablero);
        tablero.setVisible(true);
    }
    
    private void abra(){
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION){
            JOptionPane.showMessageDialog(this, "Esta funcionalidad está en construcción, no se puede abrir el archivo " + fileChooser.getSelectedFile().getName());
        }
    }
    
    private void salve(){
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION){
            JOptionPane.showMessageDialog(this, "Esta funcionalidad está en construcción, no se puede salvar el archivo " + fileChooser.getSelectedFile().getName());
        }
    }
    
    private void salga(){
        if (JOptionPane.showConfirmDialog(rootPane, "¿Seguro que quiere salir de Senku?", "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) System.exit(0);
    }
}
