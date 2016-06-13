import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.Timer;

public class Runner2 extends JPanel implements ActionListener{

    private int pause, score, y;
    private double mv;
    private Hero hero;
    private CircleList<Obstacle> onScreen;
    private Timer timer;

    public Runner2(){
        // how long to pause
        pause = 100;
        // current score
        score = 0;
        // the floor
        y = 300;
        // how fast teverything moves
        mv = -40.0;
        // your hero
        hero = new Hero(200,y,50);
        // a list of all obstacles
        onScreen = new CircleList<Obstacle>();
        // one at end of screen
        onScreen.add(new Obstacle(1,1000,y,30));
        // one off screen
        onScreen.add(new Obstacle (1,1600, y, 30));
        timer = new Timer(100, this);
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        BufferedImage floor = null; // declare image
        try {
            floor = ImageIO.read(new File("Floor.png")); // here's the source
        }
        catch (IOException e) { // required
        }

        g.drawImage(floor,0,300,1000,200,null);

        BufferedImage sky = null;
        try{
            sky = ImageIO.read(new File("Sky.png"));
        }
        catch (IOException e){

        }

        g.drawImage(sky,0,0,1000,300,null);

        // draw hero
        hero.draw(g);
        // draw ground
        g.drawLine(0,y,this.getWidth(),y);

        // draw each villain
        for (int i = 0; i <= onScreen.size(); ++i){
            onScreen.getCursor().getValue().draw(g);
            onScreen.advance();
        }

    }

    //********KEYBOARD ACTIONS********

    public void keyTyped(KeyEvent e) {
    }


    public void gameOver(){
        System.out.println("END");
    }

    // allows for movement
    /** Handle the key-pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            hero.jump();
        }
    }

    // required to override KeyListener
    /** Handle the key-released event from the text field. */
    public void keyReleased(KeyEvent e) {
        return;
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == timer){
            for (int i = 0; i < onScreen.size(); ++i){
                Obstacle c = onScreen.getCursor().getNext().getValue();
                // move to the left
                c.changeX(mv);
                // curent obstacle
                //Obstacle c = onScreen.getCursor().getValue();
                // if the obstacle left screen
                if (c.offScreen()){
                    // remove from list
                    onScreen.remove();
                    // replace
                    onScreen.add(new Obstacle(1,999,y,30));
                }
            }
            score += 100;
            mv *= .999;
            //if (onScreen.getCursor().getValue().getY() < hero.getY()) gameOver();
            repaint();
        }
    }

}
