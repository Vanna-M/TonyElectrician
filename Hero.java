/*

    Class for Hero, Tony the Electrician

*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Hero{

    private int _y, _size, _gravity, mv, dwn;
    private double _x;
    private boolean jumped, mid;
    private Rectangle borders;

    public Hero(double x, int y, int size){
        _x = x;
        // y refers to floor, so y - size is head
        _y = y - size;
        _size = size;
        // not jumping
        _gravity = 0;
        // how much you fall each time; factor of mv
        dwn = 35;
        // how high you jump up
        mv = dwn*4;
        // you have not double jumped
        jumped = false;
        // boolean to check which pic to draw
        mid = false;
        // to check for intersection
        borders = new Rectangle();
        borders.setSize(size,size);
        borders.setLocation((int)_x,_y);
    }

    public void draw(Graphics g){

        // if you've jumped
        if (_gravity > 0){
            // move down
            _y += dwn;
            // move your borders
            borders.setLocation((int)_x, _y);
            // tell that you've dropped
            --_gravity;
        }

        // picture 1
        if (mid) {
            BufferedImage hero1 = null;

            try {
                hero1 = ImageIO.read(new File("Hero1.png")); // here's the source
            }
            catch (IOException e) { // required
            }
            g.drawImage(hero1,(int) _x,_y,_size,_size,null);

            mid = false;
        }

        // picture 2
        else{
            BufferedImage hero2 = null;
            try {
                hero2 = ImageIO.read(new File("Hero2.png")); // here's the source
            }
            catch (IOException e) { // required
            }
            g.drawImage(hero2,(int) _x,_y,_size,_size,null);
            mid = true;
        }

    }

    // called from Runner
    public void jump(){
        // you're back on ground
        if (_gravity == 0) jumped = false;
        // triple jump disallowed
        if (jumped) return;
        // double jump
        if (_gravity > 0) jumped = true;
        // jumping
        _gravity += (mv / dwn);
        _y -= mv;
        borders.setLocation((int)_x, _y);
    }

    // bottomost (biggest) y
    public int getY(){
        return _y + _size;
    }

    // lefttmost x
    public double getX(){
        return _x;
    }

    public int getSize(){
        return _size;
    }

    public Rectangle getBorders(){
        return borders;
    }

}
