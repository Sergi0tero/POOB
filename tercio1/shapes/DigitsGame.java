import java.util.*;
/**
 * Write a description of class DigitsGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DigitsGame
{
    private Rectangle background;
    private int backgroundSize;
    private Rectangle rectan[];
    private Digit value[];
    private Digit hole[];
    private String color;
    private int N;
    private int M;
    private int B;
    
    /**
     * Creates the game
     */
    public DigitsGame(int N, int M, int B){
        this.N = N;
        this.M = M;
        this.B = B;
        background = new Rectangle();
        backgroundSize = (N*60);
        rectan = new Rectangle[N*N];
        value = new Digit[M];
        hole = new Digit[M];
        color = "green";
        List inavailable = new ArrayList();
        List barriers = new ArrayList();
        for (int i = 0; i < (N*N); i++){
                rectan[i] = new Rectangle();
                rectan[i].changeSize(50,50);
                rectan[i].changeColor("white");
            }
        board();
        numberPlacement(inavailable, barriers);
        makeVisible();
    }
    
    public void makeVisible(){
        background.makeVisible();
        for (int i = 0; i < (N*N); i++){
            rectan[i].makeVisible();
        }
        
        for (int i = 0; i < M; i++){
            value[i].makeVisible();
            hole[i].makeVisible();
        }
    }
    
    public void changeColor(String color){
        for (int i = 0; i < M; i++){
            value[i].changeColor(color);
        }
    }
    
    private void moveBoardN(List inavailable, List barriers){
        for (int i = N - 1; i >= 0; i--){
            if (((i + N) < N*N) && inavailable.contains(i)){
                if (!barriers.contains(i + N)){
                    
                }
            }
        }
    }
    
    private void numberPlacement(List inavailable, List barriers){
        for (int i = 0; i < M; i++){
            Random rand = new Random();
            byte numRan = (byte) rand.nextInt(10);
            value[i] = new Digit(numRan);
            hole[i] = new Digit(numRan);
        }
        
        for (int i = 0; i < B; i++){
            Random num = new Random();
            int newNum = num.nextInt(N*N);
            while (inavailable.contains(newNum) || barriers.contains(newNum)){
                newNum = num.nextInt(N*N);
            }
            barriers.add(newNum);
            rectan[newNum].changeColor("black");
        }
        
        for (int i = 0; i < M; i++){
            Random num = new Random();
            int xRectan, yRectan, newNum = num.nextInt(N*N);
            while (inavailable.contains(newNum) || barriers.contains(newNum)){
                newNum = num.nextInt(N*N);
            }
            inavailable.add(newNum);
            xRectan = rectan[newNum].getX();
            yRectan = rectan[newNum].getY();
            value[i].moveTo(xRectan + 15, yRectan + 5);
        }
        
        for (int i = 0; i < M; i++){
            Random num = new Random();
            int xRectan, yRectan, newNum = num.nextInt(N*N);
            while (inavailable.contains(newNum) || barriers.contains(newNum)){
                newNum = num.nextInt(N*N);
            }
            inavailable.add(newNum);
            xRectan = rectan[newNum].getX();
            yRectan = rectan[newNum].getY();
            hole[i].moveTo(xRectan + 15, yRectan + 5);
            hole[i].changeColor("black");
        }
    }
    
    private void board(){
        background.changeSize(backgroundSize, backgroundSize);
        background.changeColor("black");
        int k = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                rectan[k].moveHorizontal((60*j)+5);
                rectan[k].moveVertical((60*i)+5);
                k += 1;
            }
        }
    }
}
