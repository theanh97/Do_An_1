/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Draw;

import Model.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author DELL
 */
public class DrawPoint extends JPanel {

    Graphics2D mG2d;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        mG2d = (Graphics2D) g;
        ImageIcon img = new ImageIcon(getClass().getResource("/Images/point.png"));
        g2d.drawImage(img.getImage(), 20, 20, this);
//        new JLabel(img); 

    }

    public static void main(String[] args) {
//        DrawPoint points = new DrawPoint();
//        points.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                System.out.println("Clicked");
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//            }
//        });
        JFrame frame = new JFrame("Points");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        DrawArrow line = new DrawArrow(50,50,250,250);
        DrawArrow line1 = new DrawArrow(200,50,250,250);
        DrawPoint point = new DrawPoint();
        frame.getContentPane().add(line);
        frame.getContentPane().add(line1);
        frame.getContentPane().repaint();

        Timer t = new Timer(3000,
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Remove line ");
                frame.getContentPane().remove(line);
                frame.getContentPane().repaint();
            }
        });
        t.start();
    }
}
