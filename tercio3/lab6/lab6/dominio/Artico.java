package dominio;
import javax.swing.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Artico implements Serializable {
    public static final int MAXIMO = 500;
    private static Artico polo = null;
    private static ArrayList<Esquimal> esquimales;

    public static Artico demeArtico() {
        if (polo==null){
            polo=new Artico();
        }
        return polo;
    }

    private static void nuevoArtico() {
        polo=new Artico();
        esquimales = new ArrayList<Esquimal>();
        elementos = new ArrayList<EnArtico>();
    }   

    public static void cambieArtico(Artico d) {
        polo=d;
    }       

    private static ArrayList<EnArtico> elementos;
    private int poloNorteX;
    private int poloNorteY;
    private boolean enPoloNorte;

    private Artico() {
        elementos = new ArrayList<EnArtico>();
        poloNorteX = (int)(Math.random() * MAXIMO);
        poloNorteY = (int)(Math.random() * MAXIMO);
        enPoloNorte=false;
    }
    
    private int randomubi(){
        return (int)(Math.random() * MAXIMO);
    }
    
    public int getPoloX(){
        return poloNorteX;
    }
    
    public int getPoloY(){
        return poloNorteY;
    }

    public void algunosEnArtico(){
         esquimales = new ArrayList<Esquimal>();
         Esquimal aaju = new Esquimal(polo, "aaju", randomubi(), randomubi());
         Esquimal alek = new Esquimal(polo, "alek", randomubi(),randomubi());
         EsquimalSordo aguu = new EsquimalSordo(polo, "aguu", randomubi(), randomubi());
         EsquimalSordo ivanna = new EsquimalSordo(polo, "ivanna", randomubi(), randomubi());
         elementos.add(aaju);
         elementos.add(alek);
         elementos.add(aguu);
         elementos.add(ivanna);
         EsquimalExperto abdul = new EsquimalExperto(polo, "abdul", randomubi(), randomubi());
         elementos.add(abdul);
         esquimales.add(aaju);
         esquimales.add(alek);
         esquimales.add(aguu);
         esquimales.add(ivanna);
         esquimales.add(abdul);
         EsquimalExplorador nanuk = new EsquimalExplorador(polo, "nanuk", randomubi(), randomubi(), esquimales);
         esquimales.add(nanuk);
         elementos.add(nanuk);
         EsquimalExplorador sialuk = new EsquimalExplorador(polo, "sialuk", randomubi(), randomubi(), esquimales);
         esquimales.add(sialuk);
         elementos.add(sialuk);
         HuecoPesca pescaArriba = new HuecoPesca(polo, "pescaArriba", 250, 500);
         HuecoPesca pescaAbajo = new HuecoPesca(polo, "pescaAbajo", 250, 0);
         elementos.add(pescaArriba);
         elementos.add(pescaAbajo);
         Iglu superiorDerecha = new Iglu(polo, "superiorDerecha", 500, 500);
         Iglu superiorIzquierda = new Iglu(polo, "superiorIzquierda", 0, 500);
         Iglu inferiorIzquierda = new Iglu(polo, "inferiorIzquierda", 0, 0);
         Iglu inferiorDerecha = new Iglu(polo, "inferiorDerecha", 500, 0);
         elementos.add(superiorDerecha);
         elementos.add(superiorIzquierda);
         elementos.add(inferiorIzquierda);
         elementos.add(inferiorDerecha);
    }
    
    public EnArtico demeEnArtico(int n){
        EnArtico h=null;
        if (1<=n && n<=elementos.size()){
            h=elementos.get(n-1);
        }    
        return h; 
    }
    
    public void adicione(EnArtico e){
        elementos.add(e);
    }

    public int numeroEnArtico(){
        return elementos.size();
    }

    public boolean enPoloNorte(EnArtico e){
        boolean ok=(poloNorteX==e.getPosicionX() && poloNorteY==e.getPosicionY());
        enPoloNorte = enPoloNorte || ok;
        return enPoloNorte;
    }

    public void nuevo(){
        nuevoArtico();
    }

    public Artico abra01(File archivo)throws ArticoExcepcion {
        try {
            ObjectInputStream archivoArtico = new ObjectInputStream(new FileInputStream(archivo));
            Artico articoCargado = (Artico) archivoArtico.readObject();
            archivoArtico.close();
            cambieArtico(articoCargado);
            return articoCargado;
        } catch (IOException | ClassNotFoundException e) {
            throw new ArticoExcepcion(ArticoExcepcion.NO_ABRIO);
        }
    }

    public void guarde01(File archivo)throws ArticoExcepcion {
        ObjectOutputStream salida = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(archivo);
            salida = new ObjectOutputStream(fos);
            salida.writeObject(this);
        } catch (IOException ex) {
            throw new ArticoExcepcion(ArticoExcepcion.NO_GUARDO);
        }
    }

    public Artico importe03(File archivo)throws ArticoExcepcion {
        try {
            ArrayList<String> lines = new ArrayList<>();
            int cont = 0;
            nuevoArtico();
            BufferedReader bIn = new BufferedReader(new FileReader(archivo));
            String line = bIn.readLine();
            while (line != null) {
                lines.add(line);
                line = bIn.readLine();
            }
            verificaErrores(lines);
            Artico a = new Artico();
            for(String ln: lines){
                String[] lineclean;
                lineclean = ln.trim().split(" ");
                int fila = Integer.parseInt(lineclean[2]);
                int columna = Integer.parseInt(lineclean[3]);
                if(!Objects.equals(lineclean[0], "EsquimalExplorador")) {
                    Class cl = Class.forName("dominio." + lineclean[0]);
                    Class[] parameterType = {Artico.class, String.class, int.class, int.class};
                    polo.adicione((EnArtico) cl.getDeclaredConstructor(parameterType).newInstance(a, "Hola", fila, columna));
                } else{
                    Class cl = Class.forName("dominio." + lineclean[0]);
                    Class[] parameterType = {Artico.class, String.class, int.class, int.class, ArrayList.class};
                    polo.adicione((EnArtico) cl.getDeclaredConstructor(parameterType).newInstance(a, "Hola", fila, columna, esquimales));
                }
            }
            return this;
        } catch (ArticoExcepcion e) {
            throw new ArticoExcepcion(ArticoExcepcion.CLASE_NO_ENCONTRADA);
        } catch (Exception e) {
            throw new ArticoExcepcion(ArticoExcepcion.NO_IMPORTO);
        }
    }

    public Artico importe02(File archivo)throws ArticoExcepcion{
        try {
            nuevoArtico();
            BufferedReader bIn = new BufferedReader(new FileReader(archivo));
            String line = bIn.readLine();
            while (line != null) {
                line = line.trim();
                if (line.equals("\n")) continue;
                String[] elements = line.split(" ");
                if (elements[0].equals("Esquimal")) {
                    Esquimal esquimal = new Esquimal(demeArtico(), elements[1], Integer.parseInt(elements[2]), Integer.parseInt(elements[3]));
                    esquimales.add(esquimal);
                    adicione(esquimal);
                } else if (elements[0].equals("EsquimalSordo")) {
                    EsquimalSordo esquimalSordo = new EsquimalSordo(demeArtico(), elements[1], Integer.parseInt(elements[2]), Integer.parseInt(elements[3]));
                    esquimales.add(esquimalSordo);
                    adicione(esquimalSordo);
                } else if (elements[0].equals("EsquimalExperto")) {
                    EsquimalExperto esquimalExperto = new EsquimalExperto(demeArtico(), elements[1], Integer.parseInt(elements[2]), Integer.parseInt(elements[3]));
                    esquimales.add(esquimalExperto);
                    adicione(esquimalExperto);
                } else if (elements[0].equals("EsquimalExplorador")) {
                    EsquimalExplorador esquimalExplorador = new EsquimalExplorador(demeArtico(), elements[1], Integer.parseInt(elements[2]), Integer.parseInt(elements[3]), esquimales);
                    esquimales.add(esquimalExplorador);
                    adicione(esquimalExplorador);
                } else if (elements[0].equals("Iglu")) {
                    Iglu iglu = new Iglu(demeArtico(), "", Integer.parseInt(elements[2]), Integer.parseInt(elements[3]));
                    adicione(iglu);
                } else if (elements[0].equals("HuecoPesca")) {
                    HuecoPesca huecoPesca = new HuecoPesca(demeArtico(), "", Integer.parseInt(elements[2]), Integer.parseInt(elements[3]));
                    adicione(huecoPesca);
                } else{
                    throw new ArticoExcepcion(ArticoExcepcion.CLASE_NO_ENCONTRADA);
                }
                line = bIn.readLine();
            }
            return this;
        } catch (ArticoExcepcion e) {
            throw new ArticoExcepcion(ArticoExcepcion.CLASE_NO_ENCONTRADA);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ArticoExcepcion(ArticoExcepcion.NO_IMPORTO);
        }
    }

    public Artico importe01(File archivo)throws ArticoExcepcion{
        try{
            nuevoArtico();
            BufferedReader bIn = new BufferedReader(new FileReader(archivo));
            String line = bIn.readLine();
            while (line != null) {
                line = line.trim();
                if (line.equals("\n")) continue;
                String[] elements = line.split(" ");
                if(elements[0].equals("Esquimal")) {
                    Esquimal esquimal  = new Esquimal(demeArtico(), elements[1], Integer.parseInt(elements[2]), Integer.parseInt(elements[3]));
                    esquimales.add(esquimal);
                    adicione(esquimal);
                }
                else if(elements[0].equals("EsquimalSordo")) {
                    EsquimalSordo esquimalSordo = new EsquimalSordo(demeArtico(), elements[1], Integer.parseInt(elements[2]), Integer.parseInt(elements[3]));
                    esquimales.add(esquimalSordo);
                    adicione(esquimalSordo);
                }
                else if(elements[0].equals("EsquimalExperto")) {
                    EsquimalExperto esquimalExperto = new EsquimalExperto(demeArtico(),elements[1],Integer.parseInt(elements[2]), Integer.parseInt(elements[3]));
                    esquimales.add(esquimalExperto);
                    adicione(esquimalExperto);
                }
                else if(elements[0].equals("EsquimalExplorador")) {
                    EsquimalExplorador esquimalExplorador = new EsquimalExplorador(demeArtico(), elements[1], Integer.parseInt(elements[2]), Integer.parseInt(elements[3]), esquimales);
                    esquimales.add(esquimalExplorador);
                    adicione(esquimalExplorador);
                }
                else if(elements[0].equals("Iglu")) {
                    Iglu iglu = new Iglu(demeArtico(), "", Integer.parseInt(elements[2]), Integer.parseInt(elements[3]));
                    adicione(iglu);
                }
                else if(elements[0].equals("HuecoPesca")) {
                    HuecoPesca huecoPesca = new HuecoPesca(demeArtico(), "", Integer.parseInt(elements[2]), Integer.parseInt(elements[3]));
                    adicione(huecoPesca);
                }
                line = bIn.readLine();
            }
            return this;
        } catch (IOException e) {
            throw new ArticoExcepcion(ArticoExcepcion.NO_IMPORTO);
        }
    }
    public void exporte03(File archivo)throws ArticoExcepcion{
        try{
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(archivo));
            for(EnArtico elemento : elementos){
                printWriter.println(elemento.getClass().getSimpleName() + " " + elemento.getName() + " " + elemento.getPosicionX() + " " + elemento.getPosicionY() + "\n");
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new ArticoExcepcion(ArticoExcepcion.ARCHIVO_NO_ENCONTRADO);
        } catch (Exception e){
            throw new ArticoExcepcion(ArticoExcepcion.NO_EXPORTO);
        }
    }

    public void exporte02(File archivo)throws ArticoExcepcion{
        try{
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(archivo));
            for(EnArtico elemento : elementos){
                printWriter.println(elemento.getClass().getSimpleName() + " " + elemento.getName() + " " + elemento.getPosicionX() + " " + elemento.getPosicionY() + "\n");
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new ArticoExcepcion(ArticoExcepcion.ARCHIVO_NO_ENCONTRADO);
        } catch (Exception e){
            throw new ArticoExcepcion(ArticoExcepcion.NO_EXPORTO);
        }
    }


    public void exporte01(File archivo)throws ArticoExcepcion{
        try{
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(archivo));
            for(EnArtico elemento : elementos){
                printWriter.println(elemento.getClass().getSimpleName() + " " + elemento.getName() + " " + elemento.getPosicionX() + " " + elemento.getPosicionY() + "\n");
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new ArticoExcepcion(ArticoExcepcion.NO_EXPORTO);
        }
    }

    public Artico abra00(File archivo)throws ArticoExcepcion {
        throw new ArticoExcepcion(ArticoExcepcion.EN_CONSTRUCCION);
    }

    public void guarde00(File archivo)throws ArticoExcepcion {
        throw new ArticoExcepcion(ArticoExcepcion.EN_CONSTRUCCION);
    }

    public Artico importe00(File archivo)throws ArticoExcepcion{
        throw new ArticoExcepcion(ArticoExcepcion.EN_CONSTRUCCION);
    }

    public void exporte00(File archivo)throws ArticoExcepcion{
        throw new ArticoExcepcion(ArticoExcepcion.EN_CONSTRUCCION);
    }

    public Artico abra(File archivo)throws ArticoExcepcion {
        try {
            ObjectInputStream archivoArtico = new ObjectInputStream(new FileInputStream(archivo));
            Artico articoCargado = (Artico) archivoArtico.readObject();
            archivoArtico.close();
            cambieArtico(articoCargado);
            return articoCargado;
        } catch (FileNotFoundException e) {
            throw new ArticoExcepcion( ArticoExcepcion.ARCHIVO_NO_ENCONTRADO);
        } catch (StreamCorruptedException e) {
            throw new ArticoExcepcion( ArticoExcepcion.ARCHIVO_CORRUPTO);
        } catch (ClassNotFoundException e) {
            throw new ArticoExcepcion( ArticoExcepcion.CLASE_NO_ENCONTRADA);
        } catch (InvalidClassException e) {
            throw new ArticoExcepcion( ArticoExcepcion.CLASE_NO_PERMITIDA);
        } catch (IOException e) {
            throw new ArticoExcepcion( ArticoExcepcion.ENTRADA_SALIDA);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ArticoExcepcion( ArticoExcepcion.NO_ABRIO);
        }
    }

    public void guarde(File archivo)throws ArticoExcepcion{
        ObjectOutputStream salida = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(archivo);
            salida = new ObjectOutputStream(fos);
            salida.writeObject(this);
        } catch (FileNotFoundException e) {
            throw new ArticoExcepcion( ArticoExcepcion.ARCHIVO_NO_ENCONTRADO);
        } catch (InvalidClassException e) {
            throw new ArticoExcepcion( ArticoExcepcion.CLASE_NO_PERMITIDA);
        }catch (IOException e) {
            throw new ArticoExcepcion( ArticoExcepcion.ENTRADA_SALIDA);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ArticoExcepcion( ArticoExcepcion.NO_GUARDO);
        }
    }

    public Artico importe(File archivo)throws ArticoExcepcion {
        try {
            ArrayList<String> lines = new ArrayList<>();
            int cont = 0;
            nuevoArtico();
            BufferedReader bIn = new BufferedReader(new FileReader(archivo));
            String line = bIn.readLine();
            while (line != null) {
                lines.add(line);
                line = bIn.readLine();
            }
            verificaErrores(lines);
            Artico a = new Artico();
            for(String ln: lines){
                String[] lineclean;
                lineclean = ln.trim().split(" ");
                int fila = Integer.parseInt(lineclean[2]);
                int columna = Integer.parseInt(lineclean[3]);
                if(!Objects.equals(lineclean[0], "EsquimalExplorador")) {
                    System.out.println("entro a primer class for name");
                    Class cl = Class.forName("dominio." + lineclean[0]);
                    Class[] parameterType = {Artico.class, String.class, int.class, int.class};
                    polo.adicione((EnArtico) cl.getDeclaredConstructor(parameterType).newInstance(a, "Hola", fila, columna));
                } else{
                    System.out.println("entro a segundo class for name");
                    Class cl = Class.forName("dominio." + lineclean[0]);
                    Class[] parameterType = {Artico.class, String.class, int.class, int.class, ArrayList.class};
                    polo.adicione((EnArtico) cl.getDeclaredConstructor(parameterType).newInstance(a, "Hola", fila, columna, esquimales));
                }
            }
            return this;
        } catch (ArticoExcepcion e) {
            throw new ArticoExcepcion(ArticoExcepcion.CLASE_NO_ENCONTRADA);
        } catch (Exception e) {
            throw new ArticoExcepcion(ArticoExcepcion.NO_IMPORTO);
        }
    }

    public void exporte(File archivo)throws ArticoExcepcion{
        try{
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(archivo));
            for(EnArtico elemento : elementos){
                printWriter.println(elemento.getClass().getSimpleName() + " " + elemento.getName() + " " + elemento.getPosicionX() + " " + elemento.getPosicionY());
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new ArticoExcepcion(ArticoExcepcion.ARCHIVO_NO_ENCONTRADO);
        } catch (Exception e){
            throw new ArticoExcepcion(ArticoExcepcion.NO_EXPORTO);
        }
    }

    public void accion(){
        for (EnArtico elemento : elementos){
            elemento.accion();
        }
    }

    public void improvisen(){
        for (EnArtico elemento : elementos){
            elemento.improvise();
        }
    }    

    public void corten(){
        for (EnArtico elemento : elementos){
            elemento.corte();
        }
    }


    private static void escribirError(String error, int linea, int posicion, File file) throws IOException{
        FileWriter fw = new FileWriter(file, true);
        fw.write("Se encontro que " + error + " en la linea " + linea +" en la palabra " + posicion + "\n");
        fw.close();
    }

    public static void verificaErrores(ArrayList<String> lines) throws ArticoExcepcion, IOException {
        boolean hayErrores = false;
        File file = new File("salida/articoErr.txt");
        file.delete();
        for (int i = 0; i < lines.size(); i++) {
            String[] lineclean;
            lineclean = lines.get(i).trim().split(" ");
            try {
                Class cls = Class.forName("dominio." + lineclean[0]);
                if (!EnArtico.class.isAssignableFrom(cls)) {
                    throw new ClassCastException();
                }
            } catch (ClassNotFoundException e) {
                hayErrores = true;
                escribirError("No se encontro la clase", i + 1, 1, file);
            } catch (ClassCastException e) {
                hayErrores = true;
                escribirError("La clase no es de tipo Elemento", i + 1, 1, file);
            } catch (Exception e) {
                hayErrores = true;
                escribirError("No se conoce excepcion", i + 1, 1, file);
            }
            if (revisandoPosicion(lineclean[2], lineclean[3], i + 1, 3, file)) {
                hayErrores = true;
            }
        }
        if (hayErrores) {
            throw new ArticoExcepcion(ArticoExcepcion.IMPORTAR_DETALLES);
        }
    }

    private static boolean revisandoPosicion(String numeroX, String numeroY, int linea, int posicion, File file) throws IOException{
       try {
           int x = Integer.parseInt(numeroX);
           int y = Integer.parseInt(numeroY);
           if (x < 0 || x > 500 || y < 0 || y > 500) {
               throw new IndexOutOfBoundsException();
           }
           return false;
       }catch (NumberFormatException e){
           escribirError("Las posiciones no son enteros", linea, posicion, file);
           return true;
       }catch (IndexOutOfBoundsException e){
           escribirError("La posicion esta fuera de rango", linea, posicion, file);
           return true;
       }catch (Exception e){
           escribirError("No se conoce excepcion", linea, posicion, file);
           return true;
       }
    }
}
