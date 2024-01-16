package yogibear;

import gui.GameWindow;
import java.sql.SQLException;
import java.util.Random;

/**
 *
 * @author balka
 */
public class YogiBear {    
    public static void main(String[] args) {
        try {
            GameWindow game = new GameWindow(3, 800, 800);
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        
        /*
        Random rnd = new Random();
        System.out.println("Player 0 0 10 100");
        for(int i = 0; i < 7; i++) {
            System.out.println("Tree " + rnd.nextInt(0, 750) + " " + rnd.nextInt(0, 750) + " " + rnd.nextInt(50, 150));
        }
        System.out.println("Ranger Up " + rnd.nextInt(0, 750) + " " + rnd.nextInt(0, 750) + " " + rnd.nextInt(5, 20) + " " + rnd.nextInt(30, 150));
        System.out.println("Ranger Down " + rnd.nextInt(0, 750) + " " + rnd.nextInt(0, 750) + " " + rnd.nextInt(5, 20) + " " + rnd.nextInt(30, 150));
        System.out.println("Ranger Left " + rnd.nextInt(0, 750) + " " + rnd.nextInt(0, 750) + " " + rnd.nextInt(5, 20) + " " + rnd.nextInt(30, 150));
        System.out.println("Ranger Right " + rnd.nextInt(0, 750) + " " + rnd.nextInt(0, 750) + " " + rnd.nextInt(5, 20) + " " + rnd.nextInt(30, 150));
        for(int i = 0; i < 3; i++) {
            System.out.println("Basket " + rnd.nextInt(0, 750) + " " + rnd.nextInt(0, 750) + " " + rnd.nextInt(60, 90));
        }
        */
    }
    
}
