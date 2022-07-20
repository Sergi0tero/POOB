package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Thread;
import java.io.*;

import dominio.*;
import Persistencia.*;

public class ArticoGUI extends JFrame{

    private JPanel botones;
    private JScrollPane contenedor;
    private JButton botonAccion;
    private JButton botonImprovisen;
    private JButton botonCorten;
    private JButton botonRapida;  

    private FotoArtico foto;

    private JMenuBar menuBar = new JMenuBar();
    private final JMenu menu = new JMenu("Menu");

    private final JMenuItem nuevo = new JMenuItem("Nuevo");
    private final JMenuItem abrir = new JMenuItem("Abrir");
    private final JMenuItem importar = new JMenuItem("Importar");
    private final JMenuItem guardarComo = new JMenuItem("Guardar como");
    private final JMenuItem exportarComo = new JMenuItem("Exportar como");
    private final JMenuItem salir = new JMenuItem("Salir");
    
    private ArticoGUI() {
        super("Artico Norte");
        try {
            Artico.demeArtico().algunosEnArtico();
            elementos();
            prepareElementosMenu();
            prepareAccionesMenu();
            acciones();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void elementos() throws Exception {
        setLayout(new BorderLayout());    
        contenedor = new JScrollPane();

        foto= new FotoArtico();
        contenedor.getViewport().add(foto);

        botones=new JPanel(new GridLayout(1,4));
        botonAccion=new JButton("Acci\u00f3n");
        botonImprovisen=new JButton("Improvisen"); 
        botonCorten=new JButton("Corten");
        botonRapida=new JButton("C\u00e1mara r\u00e1pida");
        
        botones.add(botonAccion);
        botones.add(botonImprovisen); 
        botones.add(botonCorten);
        botones.add(botonRapida);
        
        add(contenedor,BorderLayout.CENTER);
        add(botones,BorderLayout.SOUTH);

        pack();
        setSize(Artico.MAXIMO+100,Artico.MAXIMO+135);

        setResizable(false);
    }
    private void prepareElementosMenu(){
        setJMenuBar(menuBar);

        menuBar.add(menu);

        menu.add(nuevo);
        menu.add(abrir);
        menu.add(importar);
        menu.add(guardarComo);
        menu.add(exportarComo);
        menu.add(salir);
    }

    private void prepareAccionesMenu(){
        ActionListener oyenteBotonNuevo=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Artico.demeArtico().nuevo();
            }
        };
        nuevo.addActionListener(oyenteBotonNuevo);

        ActionListener oyenteBotonAbrir=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    Artico.demeArtico().abra00(new File(""));
                } catch (ArticoExcepcion ex) {
                    System.out.println(ex.getMessage());
                }
            }
        };
        abrir.addActionListener(oyenteBotonAbrir);

        ActionListener oyenteBotonImportar=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    Artico.demeArtico().importe(new File(""));
                } catch (ArticoExcepcion ex) {
                    System.out.println(ex.getMessage());
                }
            }
        };
        importar.addActionListener(oyenteBotonImportar);

        ActionListener oyenteBotonGuardarComo=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    Artico.demeArtico().guarde00(new File(""));
                } catch (ArticoExcepcion ex) {
                    System.out.println(ex.getMessage());
                }
            }
        };
        guardarComo.addActionListener(oyenteBotonGuardarComo);

        ActionListener oyenteBotonExportarComo=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    Artico.demeArtico().exporte(new File(""));
                } catch (ArticoExcepcion ex) {
                    System.out.println(ex.getMessage());
                }
            }
        };
        exportarComo.addActionListener(oyenteBotonExportarComo);

        ActionListener oyenteBotonSalir=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        };
        salir.addActionListener(oyenteBotonSalir);
    }

    private void acciones(){
        ActionListener oyenteBotonAccion=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                accion();
            }   
        };  
        botonAccion.addActionListener(oyenteBotonAccion);

        ActionListener oyenteBotonImprovisen=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                improvisen();
            }   
        };
        botonImprovisen.addActionListener(oyenteBotonImprovisen);  

        ActionListener oyenteBotonCorten=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    corten();
            }   
        };  
        botonCorten.addActionListener(oyenteBotonCorten);

        ActionListener oyenteBotonRapida=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                rapida();
            }   
        }; 
        botonRapida.addActionListener(oyenteBotonRapida);

        WindowListener w = new WindowAdapter() { 
            public void windowClosing(WindowEvent e) {
                fin();
            }
        };  
        this.addWindowListener(w);

    }



    private void accion(){
        Artico.demeArtico().accion();
        actualice();
    }

    private void corten(){       
        Artico.demeArtico().corten();
        actualice();
    }       

    private void improvisen(){       
        Artico.demeArtico().improvisen();
        actualice();
    }   


    private void rapida(){
        for(int i=0; i<999; i++){
            accion();
        }
    }  
    
    private void fin(){
        dispose();
        System.exit(0);
    }   

    
    private void actualice(){
        foto.actualice();
    }
    
    public static void main(String[] args) {
        ArticoGUI gui=new ArticoGUI();
        gui.setVisible(true);
    }   

    class FotoArtico extends JComponent {
        
        public void actualice(){
            repaint();
        }

        public void paintComponent(Graphics g){
            int x,y;
            g.setFont(new Font("TimesRoman", Font.PLAIN, 8)); 

            for (int i=1; i<=Artico.demeArtico().numeroEnArtico(); i++) {

                EnArtico e=Artico.demeArtico().demeEnArtico(i);
                x=e.getPosicionX();
                y=Artico.MAXIMO-e.getPosicionY();  

                g.setColor(e.getColor()); 
                g.drawString(e.mensaje(),x+20,y+10);   

                if (e.forma().equals("Persona")){
                    humano(g,(Persona)e,x,y);
                } else  if (e.forma().equals("Circulo")){
                    g.fillOval(x+10,y+0,20,20);
                } else  if (e.forma().equals("Cuadrado")){
                    g.fillRect(x,y,20,20);
                }
            }
            super.paintComponent(g);
        }
        
        public void humano(Graphics g, Persona e,int x, int y){
            int pos;
            g.setColor(Color.PINK);
            g.fillOval(x+10,y+0,10,10);/*cabeza*/
            g.setColor(e.getColor()); 
            g.drawOval(x+8,y-2,14,14);
            g.drawLine(x+10+5,y+10,x+10+5,y+10+20);

            pos=e.getPosicionBrazo('I');
            if (pos==Persona.ARRIBA){
                g.drawLine(x+10+5,y+10+5,x+10+15,y+10);/*brazo izq arriba*/
            } else if (pos==Persona.FRENTE){
                g.drawLine(x+10+5,y+10+5,x+10+15,y+10+5);/*brazo izq al frente*/
            } else {
                g.drawLine(x+10+5,y+10+5,x+10+15,y+10+10);/*brazo izq abajo*/
            }

            pos=e.getPosicionBrazo('D');
            if (pos==Persona.ARRIBA){
                g.drawLine(x+10+5,y+10+5,x+5,y+10);/*brazo der arriba*/
            } else if  (pos==Persona.FRENTE){
                g.drawLine(x+10+5,y+10+5,x+5,y+10+5);/*brazo der al frente*/
            } else{
                g.drawLine(x+10+5,y+10+5,x+5,y+10+10);/*brazo der abajo*/
            }

            g.drawLine(x+10+5,(y+15)+10+5,x+10+15,(y+15)+10+15);
            g.drawLine(x+10+5,(y+15)+10+5,x+5,(y+15)+10+15);

            pos=e.getPosicionPierna('D');
            if (pos==Persona.ARRIBA){
                g.drawLine(x+5,(y+15)+10+15,x+5+10,(y+15)+10+15);/*pierna der arriba*/
            } else if  (pos==Persona.FRENTE){
                g.drawLine(x+5,(y+15)+10+15,x+5-10,(y+15)+10+15+5);/*pierna der al frente*/
            } else{
                g.drawLine(x+5,(y+15)+10+15,x+5,(y+15)+10+15+10);/*pierna der abajo*/
            }

            pos=e.getPosicionPierna('I');
            if (pos==Persona.ARRIBA){
                g.drawLine(x+10+15,(y+15)+10+15,x+10+15-10,(y+15)+10+15);/*pierna izq arriba*/
            }else if  (pos==Persona.FRENTE){
                g.drawLine(x+10+15,(y+15)+10+15,x+10+15+10,(y+15)+10+15+5);/*pierna izq al frente*/
            }else {
                g.drawLine(x+10+15,(y+15)+10+15,x+10+15,(y+15)+10+15+10);/*piernaizqabajo*/
            }
        }
    }
}



