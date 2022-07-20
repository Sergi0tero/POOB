package dominio;

import java.awt.Color;


/**
 * Write a description of class Senku here.
 * 
 * @author Archila-otero
**/
public class Senku
{
    private int tamano;
    private int movimientos;
    private int capturados;
    
    private Color colorHuecos;
    private Color colorFichas;
    
    private Color[][] botones;
    
    private boolean creado;
    
    public Senku(int tamano, Color colorHuecos, Color colorFichas){
        this.tamano = tamano;
        this.colorHuecos = colorHuecos;
        this.colorFichas = colorFichas;
        botones = new Color[tamano][tamano];
        try{
            iniciarTablero();
            creado = true;
        }catch(SenkuException e){
            creado = false;
        }
    }
    
    public void iniciarTablero() throws SenkuException {
        if (tamano % 2 == 0) throw new SenkuException(SenkuException.TAMANO_INVALIDO);
        int cont = 0;
        int mitad = (tamano/2);
        for(int i = 0;i<mitad+1;i++){
            if (cont == 0){
                botones[i][mitad+1] = colorHuecos;
                botones[i][mitad-1] = colorHuecos;
                botones[botones.length-i-1][mitad+1] = colorHuecos;
                botones[botones.length-i-1][mitad-1] = colorHuecos;
                botones[mitad+1][i] = colorHuecos;
                botones[mitad-1][i] = colorHuecos;
                botones[mitad+1][botones.length-i-1] = colorHuecos;
                botones[mitad-1][botones.length-i-1] = colorHuecos;
                botones[mitad][mitad] = colorHuecos;
            }
            if (cont != mitad){
                botones[i][mitad] = colorFichas;
                botones[botones.length-i-1][mitad] = colorFichas;
            }
            for(int k = 1;k<cont+1;k++){
                botones[i][mitad+k] = colorFichas;
                botones[i][mitad-k] = colorFichas;
            }
            for(int k = 1;k<cont+1;k++){
                botones[botones.length - i-1][mitad+k] = colorFichas;
                botones[botones.length - i-1][mitad-k] = colorFichas;
            }
            cont++;
        }
        movimientos = 0;
        verificarGrices();
    }
    
    private void verificarGrices(){
        for (int i = 0; i < tamano; i ++){
            for (int j = 0; j < tamano; j ++){
                if (botones[i][j] == Color.gray){
                    botones[i][j] = null;
                }
            }
        }
    }
    
    public int[] verificarBotones(int inicioX, int inicioY, int objetivoX, int objetivoY) throws SenkuException{
        int res[] = new int[2];
        res[0] = -1;
        res[1] = -1;
        if ((botones[inicioX][inicioY] == colorFichas) && (botones[objetivoX][objetivoY] == colorHuecos)){
            if (inicioX == objetivoX + 2){
                res[0] = inicioX - 1;
                res[1] = inicioY;
            }
            else if (inicioX == objetivoX - 2){
                res[0] = inicioX + 1;
                res[1] = inicioY;
            }
            else if (inicioY == objetivoY + 2){
                res[0] = inicioX;
                res[1] = inicioY - 1;
            }
            else if (inicioY == objetivoY - 2){
                res[0] = inicioX;
                res[1] = inicioY + 1;
            }
        }
        if(res[0] == -1 && res[1] == -1)throw new SenkuException(SenkuException.MOVIMIENTO_INVALIDO); 
        return res;
    }
    
    public void actualiceColores(Color oldColor, Color newColor){
        for (int i = 0; i < tamano; i ++){
            for (int j = 0; j < tamano; j ++){
                if (botones[i][j] == oldColor){
                    botones[i][j] = newColor;
                }
            }
        }
    }
    
    public void cambieColorFichas(Color newcolor){
        this.colorFichas = newcolor;
    }
    
    public void cambieColorHuecos(Color newcolor){
        this.colorHuecos = newcolor;
    }
    
    public void move(int inicioX, int inicioY, int objetivoX, int objetivoY, int[] botonMedio){
        botones[inicioX][inicioY] = colorHuecos;
        botones[botonMedio[0]][botonMedio[1]] = colorHuecos;
        botones[objetivoX][objetivoY] = colorFichas;
        movimientos += 1;
        capturados += 1;
    }
    
    public Color[][] getTablero(){
        return botones;
    }

    public int movimientos(){
        return movimientos;
    }
    
    public int capturados(){
        return capturados;
    }
    
    public boolean creado(){
        return creado;
    }
    
    public Color getColorFichas(){
        return colorFichas;
    }
    
    public Color getColorHuecos(){
        return colorHuecos;
    }
}
