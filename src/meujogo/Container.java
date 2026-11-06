package meujogo;

import meujogo.modelo.Fase;

import javax.swing.*;

public class Container extends JFrame {
    public Container() {
        add(new Fase());
        setTitle("Survival Game");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Container();
    }
}
