/*
    The coin object; obstacle that draws picture of coin
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Coin extends Obstacle{

    public Coin(double x, int y){
        super(x,y);
        _size = 30;
        _y = y-_size;
        borders.setSize(_size,_size);

    }

    // override draw(Graphics g) of obstacle to draw coin
    public void draw(Graphics g){
        BufferedImage coin = null;
        try {
            coin = ImageIO.read(new File("Coin.png")); // here's the source
        }
        catch (IOException e) { // required
        }
        g.drawImage(coin,(int) _x,_y,_size,_size,null);
    }
}
