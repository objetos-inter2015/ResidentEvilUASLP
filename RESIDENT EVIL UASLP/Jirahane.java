import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Enemigo Jirahane
 * 
 * @author Marco Antonio Pescina Ruiz
 * @version 27/05/2015
 */
public class Jirahane extends Enemigo
{
     /**
     * @param escala es la escala del enemigo dependiendo de donde se encuentre en el escenario.
     */
    public Jirahane(int escala) 
    {
        Im = new GreenfootImage("jiralhane1.png");
        ancho=escala;
        alto=escala+10;
        dir=1;
        x2=0;
        x3=0;
        velocidad=350;
        desp=5;
        poder=6;
        getImage().scale(ancho,alto);
        timerVel = new SimpleTimer();
        timerDisparo = new SimpleTimer();
        timerDisparo.mark();
        timerVel.mark();
    }    
    
    /**
     * Act - Dispara despues de sierto tiempo y a cierta distancia de la base.
     * Si la vida es 0 llama al metodo muere.
     * Si se encuentra a mas de 600 pixeles de la base puede moverse.
     */
    public void act() 
    {
        if((timerDisparo.millisElapsed() > 2000 && getX() < 600 &&  vida>0) || (isTouching(bala.class) && timerDisparo.millisElapsed() > 200 && vida>0))
        {
            disparo();
            Greenfoot.playSound("plasmaA.mp3");
        }
        teDisparo();
        if(vida > 0 && getX() > 300)//200 distancia hacia la base
            mov("jiralhane1.png","jiralhane2.png");

        if(vida <= 0)
            muere("jiralhaneMu.png");
    }  
}
