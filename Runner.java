import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.Timer;

public class Runner extends JPanel implements ActionListener{

    private int pause, score, y;
    private double mv;
    private Hero hero;
    private SLinkedList<Obstacle> obstacles;
    private Coins coins;
    private Flyers minions;
    private Stars stars;
    private Timer timer;
    private Main main;
    private boolean next, invincible, gameOver;
    private JLabel scoreChart;

    public Runner(Main _main){

        main = _main;
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
        invincible = false;
        // a list of all obstacles
        obstacles = new SLinkedList<Obstacle>();
        // one at end of screen
        obstacles.addFirst(new Obstacle(1000 + (int)(Math.random() * 500),y));
        // one off screen
        obstacles.addLast(new Obstacle (1900 + (int)(Math.random() * 700), y));
        next = false;

        coins = new Coins(y);
        minions = new Flyers(y, hero.getSize());
        stars = new Stars(y);

        scoreChart = new JLabel("0");
        add(scoreChart);

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

        // draw ground
        g.drawLine(0,y,this.getWidth(),y);

        hero.draw(g);
        score += coins.coinMv(g, hero.getBorders(), mv);
        invincible = stars.move(g,hero.getBorders(),mv);
        if (minions.move(g,hero.getBorders(), mv) && !invincible) gameOver = true;;

        // draw each shrub
        Node<Obstacle> h = obstacles.getFirst();
        while (h != null){
            // move first
            h.getValue().changeX(mv);
            // draw in new position
            h.getValue().draw(g);
            // next one
            h = h.getNext();
        }

    }

    //********KEYBOARD ACTIONS********

    public void keyTyped(KeyEvent e) {
    }


    public void gameOver(){
        repaint();
        main.gameOver(score);
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
            if (gameOver == true) {
                timer.stop();
                gameOver();
            }
            if (!obstacles.isEmpty()){
                if (obstacles.getFirst().getValue().offScreen()){
                    // remove from list
                    obstacles.removeFirst();
                }
            }
            // check again, in case removed last
            if (!obstacles.isEmpty()){
                // if intersect enemy
                // (not very precise, but relatively efficent)
                if(obstacles.getFirst().getValue().intersects(hero.getBorders())){
                    if (!invincible){
                        timer.stop();
                        gameOver();
                    }
                }
                if (obstacles.getLast().getValue().next()){
                    next = true;
                }
            }
        }

        score += (- 1 * mv) * 100;
        scoreChart.setText(score + "");
        // just a TEEENSY bit faster
        mv *= 1.00001;
        if (next && (Math.random() * 100) > 80){
            // replace
            obstacles.addLast(new Obstacle(999,y));
            next = false;
        }
        repaint();
    }
}
