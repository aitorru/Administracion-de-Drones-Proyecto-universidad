package com.AdministracionDrones;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.Timer;

public class TestAnimation {

    public static void main(String[] args) {
        new TestAnimation();
    }

    public TestAnimation() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                        ex.printStackTrace();
                    }

                    JFrame frame = new JFrame("Testing");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.add(new TestPane());
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public class TestPane extends JPanel {

        private BufferedImage img;

        private int xDelta, yDelta;
        private int xPos, yPos;

        public TestPane() throws IOException {
        	InputStream in = getClass().getResourceAsStream("foto dron.png");
        	img = ImageIO.read(in);
            Random rnd = new Random();
            do {
                xDelta = rnd.nextInt(4);
            } while (xDelta == 0);
            do {
                yDelta = rnd.nextInt(4);
            } while (yDelta == 0);
            if (rnd.nextBoolean()) {
                xDelta *= -1;
            }
            if (rnd.nextBoolean()) {
                yDelta *= -1;
            }

            xPos = (getPreferredSize().width - img.getWidth()) / 2;
            yPos = (getPreferredSize().height - img.getHeight()) / 2;

            Timer timer = new Timer(40, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    xPos += xDelta;
                    yPos += yDelta;
                    if (xPos + img.getWidth() > getWidth()) { 
                        xPos = getWidth() - img.getWidth();
                        xDelta *= -1;
                    } else if (xPos < 0) {
                        xPos = 0;
                        xDelta *= -1;
                    }
                    if (yPos + img.getHeight() > getHeight()) { 
                        yPos = getHeight() - img.getHeight();
                        yDelta *= -1;
                    } else if (yPos < 0) {
                        yPos = 0;
                        yDelta *= -1;
                    }

                    repaint();
                }
            });
            timer.start();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(400, 400);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (img != null) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.drawImage(img, xPos, yPos, this);
                g2d.dispose();
            }
        }

    }

}