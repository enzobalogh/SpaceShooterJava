package meujogo.modelo;

import javax.swing.*;
import java.awt.*;

public class Tiro {
    private Image imagem;
    private int x, y;
    private int largura, altura;
    private boolean isVisivel;

    private static final int LARGURA = 1350;
    private static final int VELOCIDADE = 3;

    public Tiro(int x , int y) {
        this.x = x;
        this.y = y;
        isVisivel = true;
    }
    public void load(){
        ImageIcon referencia = new ImageIcon("componentes-graficos/PNG/Lasers/laserBlue01.png");
        imagem = referencia.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }
    public void update(){
        this.x += VELOCIDADE;
            if (this.x > LARGURA){
                isVisivel = false;
            }
    }
    public Rectangle getBounds(){
        return new Rectangle(x, y, largura, altura);
    }

    public static int getVelocidade(){
        return VELOCIDADE;
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

    public boolean isVisivel() {
        return isVisivel;
    }

    public void setVisivel(boolean visivel) {
        isVisivel = visivel;
    }
}
