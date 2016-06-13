/*
    Draws star object
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

public class Star extends Obstacle{
    public Star(double x, int y){
        super(x,y);
        // standard size
        _size = 30;
        _y = y-_size;
        borders.setSize(_size,_size);
    }

    public void draw(Graphics g){
        BufferedImage img = null; // declare image
        try {
            img = ImageIO.read(new File("Star.png")); // here's the source
        }
        catch (IOException e) { // required
        }

        g.drawImage(img,(int)_x,_y,_size,_size,null);
    }

    public void drawTowardCenter(Graphics g){
        BufferedImage img = null; // declare image
        try {
            img = ImageIO.read(new File("Star.png")); // here's the source
        }
        catch (IOException e) { // required
        }

        g.drawImage(img,300,200,_size,_size,null);
    }

    public void setX(int x){
        _x = x;
    }

}
