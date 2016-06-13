/*
    Self-contained class. Creates and monitors the flying objects, called
    flyers, represent by a picture of a minion
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Flyers{

    private int baseY,_y;
    private boolean onScreen, ret;
    private Flyer minion;

    public Flyers(int y, int heroSize){
        baseY = y;
        // account for hero's size and space in between
        _y = baseY - heroSize - 20;
        //first flyer into position
        minion = new Flyer(1000 + (int)(Math.random() * 1000 ), _y);
        onScreen = true;
    }

    public boolean move(Graphics g, Rectangle hero, double mv){
        // deals with a lag issue
        //if (ret == true) return ret;
        if (onScreen){
            minion.changeX(mv*2);
            minion.draw(g);
            if (minion.intersects(hero)) return true;
            if (minion.offScreen()){
                onScreen = false;
                return false;
            }
        }
        // no current minion
        else if ((Math.random() * 101) > 98) {
            minion.setX(1500);
            onScreen = true;
        }
        return false;
    }

}
