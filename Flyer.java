/*
    Flyer object; draws minion launching at you in sky. If you touch minion,
    you die.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.geom.*;

public class Flyer extends Obstacle{
    public Flyer(double x, int y){
        super(x,y);
        // standard size
        _size = 40;
        _y = y-_size;
        borders.setSize(_size,_size);
    }

    public void draw(Graphics g){
        BufferedImage img = null; // declare image
        try {
            img = ImageIO.read(new File("Minion.png")); // here's the source
        }
        catch (IOException e) { // required
        }

        g.drawImage(img,(int)_x,_y,_size,_size,null);

    }

    public void setX(int x){
        _x = x;
    }

}
