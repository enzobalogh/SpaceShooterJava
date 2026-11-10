package meujogo.modelo;
import java.awt.Rectangle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

public class Fase extends JPanel implements ActionListener {
    private Image fundo;
    private Player player;
    private Timer timer;
    private List <Inimigo1> inimigo1List;
    private boolean emJogo;
    private boolean jogoGanho;

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);
        ImageIcon referencia = new ImageIcon("componentes-graficos/Backgrounds/fundopreto.png");
        fundo = referencia.getImage();

        player = new Player();
        player.load();

        addKeyListener(new TecladoAdapter());

        timer = new Timer(5, this);

        timer.start();
        inicializaInimigos();
        emJogo = true;
        jogoGanho = false;
    }

    public void inicializaInimigos(){
        int coordenadas [] = new int [10];
        inimigo1List = new ArrayList<Inimigo1>();

        for (int i = 0; i < coordenadas.length; i++) {
            int x = (int)(Math.random()*8000+1920);
            int y = (int)(Math.random()*650+50);

            Inimigo1 inimigo = new Inimigo1(x, y);
            inimigo.load();

            inimigo1List.add(inimigo);
        }

    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graficos = (Graphics2D) g.create();
        if(emJogo){
            // Desenha o fundo
            graficos.drawImage(fundo, 0, 0, getWidth(), getHeight(), this);
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
            for (Inimigo1 inimigo1 : inimigo1List){
                if (inimigo1.getImagem() == null) {
                    continue;
                }

                Graphics2D gInimigo1 = (Graphics2D) graficos.create();

                gInimigo1.rotate(
                        Math.toRadians(90),
                        inimigo1.getX() + inimigo1.getImagem().getWidth(null) / 2.0,
                        inimigo1.getY() + inimigo1.getImagem().getHeight(null) / 2.0
                );

                gInimigo1.drawImage(
                        inimigo1.getImagem(),
                        inimigo1.getX(),
                        inimigo1.getY(),
                        this
                );

                gInimigo1.dispose();
            }
            if(jogoGanho){
            ImageIcon triunfo = new ImageIcon("componentes-graficos/Backgrounds/jogoGanho.jpeg");
            graficos.drawImage(triunfo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        } else{
            ImageIcon fimJogo = new ImageIcon("componentes-graficos/Backgrounds/fimdejogo.jpeg");
            graficos.drawImage(fimJogo.getImage(), 0, 0, getWidth(), getHeight(), this);
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

        for (int o = 0; o < inimigo1List.size(); o++) {
            Inimigo1 in = inimigo1List.get(o);
                if(in.isVisivel()){
                    in.update();
                } else{
                    inimigo1List.remove(o);
                }

        }
        checaColisoes();
        if(inimigo1List.isEmpty()){
            jogoGanho = true;
        }

        repaint();
    }
    public void checaColisoes(){
        Rectangle formaPlayer = player.getBounds();
        Rectangle formaInimigo1;
        Rectangle formaTiro;

        for (int i = 0; i < inimigo1List.size() ; i++) {
            Inimigo1 tempInimigo1 = inimigo1List.get(i);
            formaInimigo1 = tempInimigo1.getBounds();
            if(formaPlayer.intersects(formaInimigo1)) {
                player.perderVida();
                tempInimigo1.setVisivel(false);

            }
        }
        if (player.getVidaPlayer() == 0){
            player.setVisivel(false);
            emJogo = false;
        }

        List<Tiro> tiros = player.getTiros();
        for (int j = 0; j < tiros.size(); j++) {
            Tiro tempTiro = tiros.get(j);
            formaTiro = tempTiro.getBounds();
            for (int o = 0; o < inimigo1List.size() ; o++) {
                Inimigo1 tempInimigo1 = inimigo1List.get(o);
                formaInimigo1 = tempInimigo1.getBounds();
                if(formaTiro.intersects(formaInimigo1)){
                    tempInimigo1.setVisivel(false);
                    tempTiro.setVisivel(false);
                }
            }
        }

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


