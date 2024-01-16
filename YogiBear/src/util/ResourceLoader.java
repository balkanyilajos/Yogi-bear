package util;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import model.Figure;

/**
 * It helps to read data
 * @author Balkányi Lajos
 */
public class ResourceLoader {
    private ResourceLoader() {} 
    
    public static Image loadImage(String path) {
        try {
            URL url = Figure.class.getClassLoader().getResource(path);
            return ImageIO.read(url);
        }
        catch(IOException e) {
            System.out.println(e);
            return null;
        }
        
    }
    
    public static BufferedReader loadFile(String path) {
        try {
            return new BufferedReader(new FileReader(path));
        }
        catch(IOException e) {
            System.out.println(e);
            return null;
        }
    }
    
}
