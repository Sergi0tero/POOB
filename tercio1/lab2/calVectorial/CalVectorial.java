import java.util.HashMap;

/** Calculadora.java
 * Representa una calculadora de vectores 
 * @author ESCUELA 2017-02 v01 2121-02 v02
 */
    
public class CalVectorial{
    private HashMap<String,Vector> operandos;
    private float productoPunto;
    private float proyeccionEscalar;
    private float proyeccionVectorial;
    private boolean okas;
    private String vectores[];
    private int vect;
    
    public CalVectorial(){
        operandos = new HashMap<String, Vector>();
        okas = true;
        vect = 0;
    }

    //Crea una nueva variable de memoria
    public void defina(String nombre){
        operandos.put(nombre, null);
        vectores[vect] = nombre;
        vect += 1;
        okas = true;
    }
     
    //Asigna una constante a una variable
    //a := P(longitud, grados)  La variable debe estar definida
    public void asigne(String a, float longitud, float grados ){
        Angulo newAngulo  =new Angulo(grados, 1);
        Vector newVector = new Vector(longitud, newAngulo);
        operandos.replace(a, newVector);
        okas = true;
    }
    
    //Asigna el resultado de una operacion unaria a una varible
    //Los caracteres de las operaciones posibles son: u (vector unitario) v (componente vertical), h (componente horizontal)
    //a := op b  Las variables deben estar definidas  
    public void asigne(String a, char op, String b){
        Vector resb = operandos.get(b);
        if (op == 'u'){
            operandos.replace(a, resb.unitario());
            okas = true;
        }
        else if(op == 'v'){
            float coordenada;
            coordenada = resb.coordenadaY();
            operandos.get(a).setY(coordenada);
            okas = true;
        }
        else if(op == 'h'){
            float coordenada;
            coordenada = resb.coordenadaX();
            operandos.get(a).setX(coordenada);
            okas = true;
        }
        else {
            okas = false;
        }
    }
    
    //Asigna el resultado de una operacion binaria a una varible
    //Los caracteres de las operaciones posibles son: + (suma), - (resta), . (producto punto), e (proyeccion escalar), v (proyeccion vectorial)
    //a := b op c  Las variables deben estar definidas  
    public void asigne(String a, String b, char op, String c){
        Vector resb = operandos.get(b);
        Vector resc = operandos.get(c);
        if (op == '+'){
            resb.sume(resc);
            Vector res = new Vector(resb.coordenadaX(), resb.coordenadaY());
            operandos.replace(a,res);
            okas = true;
        }
        else if (op == '-'){
            resb.reste(resc);
            Vector res = new Vector(resb.coordenadaX(), resb.coordenadaY());
            operandos.replace(a,res);
            okas = true;
        }
        else if (op == '.'){
            productoPunto = resb.productoPunto(resc);
            System.out.println(productoPunto);
            okas = true;
        }
        else if (op == 'e'){
            proyeccionEscalar = resb.proyeccionEscalar(resc);
            System.out.println(proyeccionEscalar);
            okas = true;
        }
        else if (op == 'v'){
            resb.proyeccionVectorial(resc);
            okas = true;
        }
        else {
            okas = false;
        }
    }
    
    //Retorna el valor de la variable a en coordenadas polares. Si no esta definida retorna 'INDEFINIDA'
    public String consulteEnPolares(String a){
        Vector vector = operandos.get(a);
        return vector.toString();
    }
    
    //retorna el nombre de todos los vectores creados
    public String[] getVectores(){
        return vectores;
    }
    
    //Retorna el valor de la variable a en coordenadas cartesianas. Si no esta definida retorna 'INDEFINIDA'
    public String consulteEnCartesianas(String a){
        Vector vector = operandos.get(a);
        String s0 = String.valueOf(vector.coordenadaX());
        String s1 = String.valueOf(vector.coordenadaY());
        return "(" + s0 + "," + s1 + ")";
    }
    
    //Si se logro hacer la ultima operacion
    public boolean ok(){
        return okas;
    }
}
    



