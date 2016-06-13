/*
    Main class to actually play game. Responsible for interaction between Runner
    and gameOver screen. Allows for replay after gameOver
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main implements KeyListener{

    private Runner runner;
    private JFrame mainframe;
    private JButton exit;

    public Main(){
        mainframe = new JFrame();
        mainframe.setVisible(true);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // play function
        play();

    }

    // precondition: mainframe initalized, replay button clicked
    // postcondition: clears mainframe, calls play function (below)
    public void replay(){
        // clears mainframe
        mainframe.getContentPane().removeAll();
        play();
    }

    // set up Runner JPanel and JFrame for gameplay, begin gameplay
    public void play(){

        // exit button
        JButton exit = new JButton("Exit"); // create button w/word "Exit"
        exit.setActionCommand("Exit"); // when exit is pressed, send signal "Exit"
        // class within class at bottom
        exit.addActionListener(new ExitListener());
        // game coded for 1000 x 500 JFrame
        mainframe.setSize(1000,500);

        // create runner, begin game
        runner = new Runner(this);
        runner.add(exit);
        mainframe.add(runner);

        // gives runner control of keyboard
        runner.requestFocus();
        runner.addKeyListener(this);

        // mainframe updated
        mainframe.revalidate();
        mainframe.repaint();
    }

    // precondition: you died
    // postcondition: displays gameOver screen with option to replay
    public void gameOver(int score){

       // pause for 1 second (that's what happens in Mario)
        try {
            Thread.sleep(1000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        // delete game
        mainframe.remove(runner);

        // exit button
        exit = new JButton("Exit"); // create button w/word "Exit"
        exit.setActionCommand("Exit"); // when exit is pressed, send signal "Exit"
        exit.addActionListener(new ExitListener());

        String s = toComma(score + "");
        // score JLabel
        String message = "Your score is : " + s;
        JLabel label = new JLabel(message, JLabel.CENTER);

        // replay button
        JButton replay = new JButton("Play again?");
        replay.setActionCommand("replay");
        // class within this class
        replay.addActionListener(new Replay());

        // replay button on bottom
        mainframe.add(replay, BorderLayout.PAGE_END);
        // your score in center
        mainframe.add(label, BorderLayout.CENTER);
        // exit button on top
        mainframe.add(exit, BorderLayout.PAGE_START);
        // update mainframe
        mainframe.revalidate();
        mainframe.repaint();

    }

    //********KEYBOARD ACTIONS********

    // required to override KeyListener
    public void keyTyped(KeyEvent e) {
        return;
    }


    // allows for movement
    public void keyPressed(KeyEvent e) {
        // call function in runner
        runner.keyPressed(e);
    }

    // required to override KeyListener
    public void keyReleased(KeyEvent e) {
        return;
    }

    public String toComma(String n){
        if (n.length() <=3) return n;
        return toComma(n.substring(0,n.length() -3)) + "," + n.substring(n.length() - 3);
    }

    // if exit button pressed
    private class ExitListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand();
            if (action.equals("Exit")) System.exit(0); // if clicked, exit
            // 0 just means we exited on purpose
            // if exit's parameter != 0, it's a code for what went wrong
            // that made the program exit
        }
    }

    // if replay button pressed
    public class Replay implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String action = e.getActionCommand();
            if (action.equals("replay")){
                replay();
            }
        }
    }

    public static void main(String[] args){
        Main main = new Main();
    }
}
