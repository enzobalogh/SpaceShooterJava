package meujogo.modelo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.util.List;

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



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graficos = (Graphics2D) g.create();

        // Desenha o fundo
        graficos.drawImage(fundo, 0, 0, this);

        // Desenha a nave com rotação independente
        Graphics2D gPlayer = (Graphics2D) graficos.create();

        gPlayer.rotate(
                Math.toRadians(90),
                player.getX() + player.getImagem().getWidth(null) / 2.0,
                player.getY() + player.getImagem().getHeight(null) / 2.0
        );

        gPlayer.drawImage(
                player.getImagem(),
                player.getX(),
                player.getY(),
                this
        );

        gPlayer.dispose();

        // Desenha os tiros
        List<Tiro> tiros = player.getTiros();

        for (Tiro m : tiros) {

            if (m.getImagem() == null) {
                continue;
            }

            Graphics2D gTiro = (Graphics2D) graficos.create();

            gTiro.rotate(
                    Math.toRadians(90),
                    m.getX() + m.getImagem().getWidth(null) / 2.0,
                    m.getY() + m.getImagem().getHeight(null) / 2.0
            );

            gTiro.drawImage(
                    m.getImagem(),
                    m.getX(),
                    m.getY(),
                    this
            );

            gTiro.dispose();
        }

        graficos.dispose();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        List<Tiro> tiros = player.getTiros();

        for(int i = 0;i<tiros.size();i++){
            Tiro m = tiros.get(i);
                if(m.isVisivel()){
                    m.update();
                } else {
                    tiros.remove(i);
                }
        }

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


