/*
    Superclass for all objects that interact with hero (shrubs and coins)
    Constructor programmed for shrubs; coins are self-contained
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

public class Obstacle{

    protected int _val, _y, _size;
    protected double _x;
    protected Rectangle borders;


    public Obstacle(double x, int y){
        //they look silly when they're not squares
        // size 30 - size 80
        _size = (int)(Math.random() * 51) + 30;
        _x = x;
        _y = y - _size;
        _val = (int)(Math.random() * 3);
        borders = new Rectangle();
        borders.setSize(_size,_size);
        borders.setLocation((int)_x,_y);
    }

    // draws shrub
    public void draw(Graphics g){

        BufferedImage img = null; // declare image
        try {
            // val determines what the shrub looks like
            // each one is just a different colored shrub, but it would be
            // quite simple for someone with design skills to make many
            // different villains
            img = ImageIO.read(new File(_val + ".png")); // here's the source
        }
        catch (IOException e) { // required
        }

        g.drawImage(img,(int)_x,_y,_size,_size,null);
    }

    // move x
    public void changeX(double change){
        _x += change;
        borders.setLocation((int)_x,_y);
    }

    public int getSize(){
        return _size;
    }

    // return true if offscreen; used to check if object should be deleted
    public boolean offScreen(){
        return _x < 0;
    }

    // used to check if a new object can be added; only called on
    // tail, so just check how much space left on screen. Hard-coded for
    // screen with width of 1000
    public boolean next(){
        // if a reasonable distance from end
        if (_x + _size + 400 < 1000) return true;
        return false;
    }

    // check if this object and other object (usually hero) are intersecting
    // uses borders of this object and Rectangle rhs to represent other border
    public boolean intersects(Rectangle rhs){
        // checks if any corner of this is inside rhs or vice versa
        return borders.intersects(rhs) || rhs.intersects(borders);
    }

    public Rectangle getBorders(){
        return borders;
    }

    public double getX(){
        return _x;
    }

}
