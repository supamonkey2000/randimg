package com.milada.randimg;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;

import static com.milada.randimg.Core.VariableSliders.max_b_slider;
import static com.milada.randimg.Core.VariableSliders.max_g_slider;
import static com.milada.randimg.Core.VariableSliders.max_r_slider;
import static com.milada.randimg.Core.VariableSliders.min_b_slider;
import static com.milada.randimg.Core.VariableSliders.min_g_slider;
import static com.milada.randimg.Core.VariableSliders.min_r_slider;

public class Core extends JFrame {
    JLabel label;
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 400;
    private static int TIME_STEP = 1;
    private static int MIN_R = 0;
    private static int MIN_G = 0;
    private static int MIN_B = 0;
    private static int MAX_R = 255;
    private static int MAX_G = 255;
    private static int MAX_B = 255;
    public static void main(String[] args) {
        new Core();
    }

    private Core() {
        super("Random Image Generator");
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        label = new JLabel(new ImageIcon(getImage()));
        add(label);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Looper looper = new Looper();
        looper.start();
        new VariableSliders();
    }

    private static int getRandomIntegerForColor(int min, int max) {
        try {
            return ThreadLocalRandom.current().nextInt(min, max);
        } catch (Exception ex) {
            return 128;
        }
    }

    private BufferedImage getImage() {
        MIN_R = min_r_slider.getValue();
        MIN_G = min_g_slider.getValue();
        MIN_B = min_b_slider.getValue();
        MAX_R = max_r_slider.getValue();
        MAX_G = max_g_slider.getValue();
        MAX_B = max_b_slider.getValue();
        BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                image.setRGB(x,y,new Color(getRandomIntegerForColor(MIN_R, MAX_R),getRandomIntegerForColor(MIN_G, MAX_G),getRandomIntegerForColor(MIN_B, MAX_B)).getRGB());
            }
        }
        return image;
    }

    private class Looper extends Thread {
        public void run() {
            while (true) {
                TIME_STEP = VariableSliders.time_step_slider.getValue();
                try {
                    Thread.sleep(TIME_STEP);
                } catch (Exception ex) {
                    System.err.println("Failed to sleep!");
                    return;
                }
                min_r_slider.setMaximum(max_r_slider.getValue());
                min_g_slider.setMaximum(max_g_slider.getValue());
                min_b_slider.setMaximum(max_b_slider.getValue());
                max_r_slider.setMinimum(min_r_slider.getValue());
                max_g_slider.setMinimum(min_g_slider.getValue());
                max_b_slider.setMinimum(min_b_slider.getValue());
                label.setIcon(new ImageIcon(getImage()));
            }
        }
    }

    static class VariableSliders extends JFrame {
        static JSlider min_r_slider = new JSlider(JSlider.HORIZONTAL, 0,255,0);
        static JSlider min_g_slider = new JSlider(JSlider.HORIZONTAL, 0,255,0);
        static JSlider min_b_slider = new JSlider(JSlider.HORIZONTAL, 0,255,0);
        static JSlider max_r_slider = new JSlider(JSlider.HORIZONTAL, 0,255,255);
        static JSlider max_g_slider = new JSlider(JSlider.HORIZONTAL, 0,255,255);
        static JSlider max_b_slider = new JSlider(JSlider.HORIZONTAL, 0,255,255);
        static JSlider time_step_slider = new JSlider(JSlider.HORIZONTAL,1,1000,100);

        VariableSliders() {
            super("Control Panel");
            setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            add(min_r_slider);
            add(min_g_slider);
            add(min_b_slider);
            add(max_r_slider);
            add(max_g_slider);
            add(max_b_slider);
            add(time_step_slider);
            setLayout(new GridLayout(0,1));
            pack();
            setVisible(true);
        }
    }
}
