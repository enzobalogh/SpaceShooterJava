package meujogo.modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    private int x, y;
    private int dx, dy;
    private Image imagem;
    private int altura, largura;

    public Player() {
        this.x = x;
        this.y = y;
    }
    public void load(){

        ImageIcon referencia = new ImageIcon("C:\\Users\\Ícaro\\Downloads\\survivalgame\\SpaceShooterJava\\componentes-graficos\\PNG\\playerShip1_blue.png");
        imagem = referencia.getImage();

        altura = imagem.getHeight(null);
        altura = imagem.getWidth(null);
    }
    public void update(){
        // FAZ A NAVE SE MOVER
        x += dx;
        y += dy;
    }
    public void keyPressed(KeyEvent tecla){
        int codigo = tecla.getKeyCode();

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
}
