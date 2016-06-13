/*
    Self-contained class. Creates and monitors Stars and reports on current
    stae of invincibility
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Stars{

    private int _y, invincibilityLeft;
    private Star star;
    private boolean onScreen;

    public Stars(int y){
        _y = y;
        // hard-code position of first star
        star = new Star(2000 + (int)(Math.random() * 2000),_y);
        onScreen = true;
        invincibilityLeft = 0;
    }

    public boolean move(Graphics g, Rectangle hero, double mv){
        if (onScreen){
            star.changeX(mv);
            star.draw(g);
            if (star.offScreen()){
                onScreen = false;
            }
            if (star.intersects(hero)) invincibilityLeft = 31;
        }
        // no current star on screen
        else if ((Math.random() * 101) > 99.5) {
            star.setX(1500);
            onScreen = true;
        }
        if (invincibilityLeft > 0) {
            --invincibilityLeft;
            star.drawTowardCenter(g);
            return true;
        }
        return false;
    }
}
