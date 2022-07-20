package presentacion;
 
import dominio.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
 * @version ECI 2016
 */
public class ComboOfertasGUI extends JFrame{

    private static final int ANCHO_PREFERIDO = 450;
    private static final int ALTO_PREFERIDO= 450;
    private static final Dimension DIMENSION_PREFERIDA =
                         new Dimension(ANCHO_PREFERIDO,ALTO_PREFERIDO);

    private ComboOfertas ofertas;

    /*listar*/
    private JButton botonListar;
    private JButton botonReiniciarListar;
    private JTextArea textoDetalles;
    
    /*adicionar*/
    private JTextField nombre;   
    private JTextField descuento;
    private JTextArea  productos;
    private JButton botonAdicionar;
    private JButton botonReiniciarAdicionar;
    
    /*buscar*/
    private JTextField busquedaTexto;
    private JTextArea resultadosTexto;
    
    /**
     * Create un marco para el catalogo de ofertas de arte
     */
    
    
    private ComboOfertasGUI(){
        ofertas=new ComboOfertas();
        prepareElementos();
        prepareAcciones();
    }


    private void prepareElementos(){
        setTitle("ComboOfertas.");
        nombre = new JTextField(50);
        descuento = new JTextField(50);
        productos = new JTextArea(10, 50);
        productos.setLineWrap(true);
        productos.setWrapStyleWord(true);
        
        JTabbedPane etiquetas = new JTabbedPane();
        etiquetas.add("Listar",   prepareAreaListar());
        etiquetas.add("Adicionar",  prepareAreaAdicionar());
        etiquetas.add("Buscar", prepareAreaBuscar());
        getContentPane().add(etiquetas);
        setSize(DIMENSION_PREFERIDA);
        
    }


    /**
     * Prepara el panel para listar ofertas
     * @return el panel para listar ofertas
     */
    private JPanel prepareAreaListar(){

        textoDetalles = new JTextArea(10, 50);
        textoDetalles.setEditable(false);
        textoDetalles.setLineWrap(true);
        textoDetalles.setWrapStyleWord(true);
        JScrollPane scrollArea =
                new JScrollPane(textoDetalles,
                                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                                
        JPanel  botones = new JPanel();
        botonListar = new JButton("Listar");
        botonReiniciarListar = new JButton("Limpiar");
        botones.add(botonListar);
        botones.add(botonReiniciarListar);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollArea, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);

        return panel;
     }
     
    /**
     * Prepara el area de adición
     * @return El area de adición
     */
    private JPanel prepareAreaAdicionar(){
        
        Box nombreH = Box.createHorizontalBox();
        nombreH.add(new JLabel("Nombre", JLabel.LEFT));
        nombreH.add(Box.createGlue());
        Box nombreV = Box.createVerticalBox();
        nombreV.add(nombreH);
        nombreV.add(nombre);
        
        Box descuentoH = Box.createHorizontalBox();
        descuentoH.add(new JLabel("Descuento", JLabel.LEFT));
        descuentoH.add(Box.createGlue());
        Box descuentoV = Box.createVerticalBox();
        descuentoV.add(descuentoH);
        descuentoV.add(descuento);
        
        Box productosH = Box.createHorizontalBox();
        productosH.add(new JLabel("Productos", JLabel.LEFT));
        productosH.add(Box.createGlue());
        Box productosV = Box.createVerticalBox();
        productosV.add(productosH);
        productosV.add(productos);
 
        Box singleLineFields = Box.createVerticalBox();
        singleLineFields.add(nombreV);
        singleLineFields.add(descuentoV);       

        JPanel textoDetallesPanel = new JPanel();
        textoDetallesPanel.setLayout(new BorderLayout());
        textoDetallesPanel.add(singleLineFields, BorderLayout.NORTH);
        textoDetallesPanel.add(productosV, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        botonAdicionar = new JButton("Adicionar");
        botonReiniciarAdicionar = new JButton("Limpiar");

        botones.add(botonAdicionar);
        botones.add(botonReiniciarAdicionar);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(textoDetallesPanel, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);
        return panel;
    }

    



   /**
     * Prepara el area de liatar
     * @return El panel para buscar ofertass
     */
    private JPanel prepareAreaBuscar(){

        Box busquedaEtiquetaArea = Box.createHorizontalBox();
        busquedaEtiquetaArea.add(new JLabel("Buscar", JLabel.LEFT));
        busquedaEtiquetaArea.add(Box.createGlue());
        busquedaTexto = new JTextField(50);
        Box busquedaArea = Box.createHorizontalBox();
        busquedaArea.add(busquedaEtiquetaArea);
        busquedaArea.add(busquedaTexto);
        
        resultadosTexto = new JTextArea(10,50);
        resultadosTexto.setEditable(false);
        resultadosTexto.setLineWrap(true);
        resultadosTexto.setWrapStyleWord(true);
        JScrollPane scrollArea = new JScrollPane(resultadosTexto,
                                     JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                     JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel botonListarArea = new JPanel();
        botonListarArea.setLayout(new BorderLayout());
        botonListarArea.add(busquedaArea, BorderLayout.NORTH);
        botonListarArea.add(scrollArea, BorderLayout.CENTER);

        return botonListarArea;
    }


    private void prepareAcciones(){
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev){
                setVisible(false);
                System.exit(0);
            }
        });
        
        /*Listar*/
        botonListar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                accionListar();
            }
        });

        botonReiniciarListar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                textoDetalles.setText("");
            }
        });
        
        /*Adicionar*/
        botonAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                accionAdicionar();                    
            }
        });
        
        botonReiniciarAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                nombre.setText("");
                descuento.setText("");
                productos.setText("");
            }
        });
        
        /*Buscarr*/
        busquedaTexto.getDocument().addDocumentListener(new DocumentListener(){
            public void changedUpdate(DocumentEvent ev){
                accionBuscar();
            }
           
            public void insertUpdate(DocumentEvent ev){
                accionBuscar();
            }
            
            public void removeUpdate(DocumentEvent ev){
                accionBuscar();
            }
            

        });
    }    

    
    private void accionListar(){
        textoDetalles.setText(ofertas.toString());
    }
    
    private void  accionAdicionar(){
        ofertas.adicione(nombre.getText(),descuento.getText(),productos.getText());
    }

    private void accionBuscar(){
        String patronBusqueda=busquedaTexto.getText();
        StringBuffer buffer = new StringBuffer();
        if(patronBusqueda.length() > 0) {
            ArrayList <Combo> results = ofertas.busque(patronBusqueda);
            for(int i = 0; i < results.size(); i++) {
                buffer.append(results.get(i).toString());
                buffer.append('\n');
                buffer.append('\n');
             }
        }
        resultadosTexto.setText(buffer.toString());
    } 
    
   public static void main(String args[]){
       ComboOfertasGUI gui=new ComboOfertasGUI();
       gui.setVisible(true);
   }    
}
