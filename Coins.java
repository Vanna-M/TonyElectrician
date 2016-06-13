/*
    Self-contained class; create Coins, tell it where the floor is, calle it
    when it's time to redraw, and let it do its thing
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Coins{

    private int _quantity, baseY, _y, _size, _val;
    private Node<Coin> head, tail;
    private boolean newCoin;

    // precondition: quantity > 0, size > 0
    // postcondition: creates quantity number of Node<coin> that
    //                 with head as the first node
    public Coins(int y){
        // size of coins
        _size = 30;
        // floor
        baseY = y;
        // up to 60 spaces above ground
        _y = baseY - (int)(Math.random() * 61);
        _quantity = 0;
        moreCoins();
    }

    // creates more coins when space after tail
    public void moreCoins(){
        if (_quantity < 0) _quantity = 0;
        // up to 15 new coins, at least 5
        _quantity += (int)(Math.random() * 11) + 5;
        int need = _quantity;
        // up to 60 spaces above ground
        _y = baseY - (int)(Math.random() * 61);
        if (head == null || tail == null) {
            double pos = 1000 + (int)(Math.random() * 500);
            head = new Node<Coin>(new Coin(pos,_y), null);
            tail = new Node<Coin>(new Coin(pos + _size, _y), null);
            head.setNext(tail);
            need -= 2;
        }
        double pos = 1100;
        // head of new coins
        Node<Coin> next = new Node<Coin>(new Coin(pos,_y),null);
        tail.setNext(next);
        for (int i = 1; i < need; ++i){
            // create new coin
            Node<Coin> newNext = new Node<Coin>(new Coin(pos + (_size * i),_y),null);
            // add it to list
            next.setNext(newNext);
            // continue on
            next = next.getNext();
        }
        tail = next;
    }


    public boolean isEmpty(){
        return _quantity == 0;
    }

    // called every time you redraw in Runner
    // postcondition: all Coins redrawn, updated; returns number of points
    //                hero adds to score from picking up coins
    public int coinMv(Graphics g, Rectangle rhs, double mv){
        int ret = 0;
        if (head != null){
            // if head is offscreen, delete it
            if (head.getValue().offScreen()) {
                head = head.getNext();
                --_quantity;
            }
        }
        // if tail is empty, more coins needed; else, check for space
        if (tail == null) newCoin = true;
        else newCoin = tail.getValue().next();
        if (head != null){
            // head special case; hard coded outside of loop
            head.getValue().changeX(mv);
            head.getValue().draw(g);
            if (head.getValue().getBorders().intersects(rhs)) {
                ret += _val;
                head = head.getNext();
                --_quantity;
            }
        }
        // next = head
        Node<Coin> next = head;
        // because head complete (hard-coded), function for whichever coin
        // is after head
        while (next != null && next.getNext() != null){
            //coin you're working on
            Node<Coin> newNext = next.getNext();
            newNext.getValue().changeX(mv);
            newNext.getValue().draw(g);
            // if intersects with hero
            if (newNext.getValue().getBorders().intersects(rhs)){
                //remove
                next.setNext(newNext.getNext());
                --_quantity;
                // add _val of coin to score
                ret += _val;
            }
            next = next.getNext();
        }

        if (_quantity == 0 || head == null || tail == null) newCoin = true;

        // if we have space for more coins, create them 20% of time
        if (newCoin && (Math.random() * 101) > 80){
            moreCoins();
        }
        // return how much you add to score
        return ret;
    }

}
