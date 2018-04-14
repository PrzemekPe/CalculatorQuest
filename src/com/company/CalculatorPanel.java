package com.company;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CalculatorPanel extends JPanel implements ActionListener {
    private JTextField display;
    private final String CLEAR = "C";
    private String operation = "";
    private Logger logger =Logger.getLogger(CalculatorPanel.class.getSimpleName());

    public CalculatorPanel() {
        initLayout();
        initComponents();
    }

    private void initLayout() {
        setLayout(new BorderLayout());

    }

    private void initComponents() {
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.CENTER);
        add(display, BorderLayout.NORTH);

        JPanel panelForButtons = new JPanel();
        panelForButtons.setLayout(new GridLayout(4, 4, 5,5));

        String operations = "789/"
                + "456*"
                + "123-"
                + "C0.+";

        for (int it = 0; it < operations.length(); it++) {
            JButton button = new JButton(String.valueOf(operations.charAt(it))); //lub ("" + it)
            button.setPreferredSize(new Dimension(60, 60));
            button.addActionListener(this);
            button.setBorder(new LineBorder(Color.LIGHT_GRAY,3, true));
            panelForButtons.add(button);
        }

        add(panelForButtons, BorderLayout.CENTER);

        JButton summarize = new JButton("=");
        summarize.addActionListener(this);
        add(summarize, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String prev = display.getText();
        String sign = e.getActionCommand();

        logger.log(Level.INFO, String.format("PREV: %s, SIGN: %s", prev, sign, operation));

        if (CLEAR.equals(sign)) {
            display.setText("");
            prev = "";

        } else if (Character.isDigit(sign.charAt(0)) || ".".equals(sign)) {
            display.setText(prev.concat(sign)); // concat tzn polacz i dziala jak +
        } else if ("=".equals(sign)) {
            double result = getResultForCurrentArguments(prev);
            display.setText(String.format("%.10f", result));
            prev = "";
            operation = "";
        } else {
            if ("".equals(operation)) {
                operation = sign;
                display.setText((prev + " " + sign + " "));

            } else {
                double result = getResultForCurrentArguments(prev);
                operation = sign;
                display.setText(String.format("%.10f %s ", result, operation));
            }
        }
        logger.log(Level.INFO, String.format("OUT DISP: %s, SIGN: %s, OPER: %s",display.getText(), prev, sign, operation));
    }

    private double getResultForCurrentArguments(String prev) {
        String regx = "\\" + operation;
        String [] args = prev.split(regx);
        Double arg1 = Double.parseDouble(args[0]);
        Double arg2 = Double.parseDouble(args[1]);
        return performOperation(arg1, arg2, operation);
    }

    private double performOperation(double arg1, double arg2, String operation) {
    switch (operation){

        case "+" : return arg1 + arg2;
        case "-" : return arg1 - arg2;
        case "/" : return arg1 / arg2;
        case "*" : return arg1 * arg2;
    }
        return  0.0;
    }
}
