package meujogo.modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private int x, y;
    private int dx, dy;
    private Image imagem;
    private int altura, largura;
    private boolean isVisivel;
    private int vidaPLayer;

    private List <Tiro> tiros;

    public Player() {
        this.x = x;
        this.y = y;
        isVisivel = true;
        this.vidaPLayer = 3;

        tiros = new ArrayList<Tiro>();
    }
    public void load(){

        ImageIcon referencia = new ImageIcon("componentes-graficos/PNG/playerShip1_blue.png");
        imagem = referencia.getImage();

        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
    }
    public void update(){
        // FAZ A NAVE SE MOVER
        x += dx;
        y += dy;
    }

    public void tiroSimples(){
        Tiro tiro = new Tiro(x + largura, y + (altura / 4));
        tiro.load();
        tiros.add(tiro);
    }
    public Rectangle getBounds(){
        return new Rectangle(x, y, largura, altura);
    }

    public void keyPressed(KeyEvent tecla){
        int codigo = tecla.getKeyCode();

        if(codigo == KeyEvent.VK_A){
            tiroSimples();
        }
        if(codigo == KeyEvent.VK_UP){
            dy = -3;
        }

        if(codigo == KeyEvent.VK_DOWN){
            dy = 3;
        }
        if(codigo == KeyEvent.VK_LEFT){
            dx = -3;
        }
        if(codigo == KeyEvent.VK_RIGHT){
            dx = 3;
        }
    }
    public void keyRelease(KeyEvent tecla){
        int codigo = tecla.getKeyCode();

        if(codigo == KeyEvent.VK_UP){
            dy = 0;
        }
        if(codigo == KeyEvent.VK_DOWN){
            dy = 0;
        }
        if(codigo == KeyEvent.VK_LEFT){
            dx = 0;
        }
        if(codigo == KeyEvent.VK_RIGHT){
            dx = 0;
        }
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }

    public List<Tiro> getTiros() {
        return tiros;
    }

    public boolean isVisivel() {
        return isVisivel;
    }

    public void setVisivel(boolean visivel) {
        isVisivel = visivel;
    }

    public void perderVida() {
        if (vidaPLayer > 0) {
            vidaPLayer--;
        }
    }

    public int getVidaPlayer() {
        return vidaPLayer;
    }
}
