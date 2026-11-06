package meujogo.modelo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

public class Fase extends JPanel implements ActionListener {
    private Image fundo;
    private Player player;
    private Timer timer;

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);
        ImageIcon referencia = new ImageIcon("C:\\Users\\Ícaro\\Downloads\\survivalgame\\SpaceShooterJava\\componentes-graficos\\Backgrounds\\fundopreto.png");
        fundo = referencia.getImage();

        player = new Player();
        player.load();

        addKeyListener(new TecladoAdapter());

        timer = new Timer(5, this);

        timer.start();
    }


    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);
        graficos.rotate(
                Math.toRadians(90),
                player.getX() + player.getImagem().getWidth(null) / 2.0,
                player.getY() + player.getImagem().getHeight(null) / 2.0
        );
        graficos.drawImage(
                player.getImagem(),
                player.getX(),
                player.getY(),
                this
        );
        graficos.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        repaint();
    }

    private class TecladoAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            player.keyPressed(e);
        }
        @Override
        public void keyReleased(KeyEvent e){
            player.keyRelease(e);
        }
}
}


