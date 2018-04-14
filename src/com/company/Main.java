package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.jar.JarFile;

public class Main {

    public static void main(String[] args) {
        JFrame calcFrame =  new JFrame("Calculator Pro");

        calcFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        calcFrame.add(new CalculatorPanel());

        Dimension minDimension = new Dimension(200, 200);
        calcFrame.setMinimumSize(minDimension);
        calcFrame.pack();
        calcFrame.setVisible(true);
    }
}
