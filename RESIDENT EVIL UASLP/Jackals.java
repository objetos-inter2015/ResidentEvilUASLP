import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Enemigo Jackals
 * 
 * @author Marco Antonio Pescina Ruiz
 * @version 27/05/2015
 */
public class Jackals extends Enemigo
{
    /**
     * @param escala es la escala del enemigo dependiendo de donde se encuentre en el escenario.
     */
    public Jackals(int escala)
    {
        Im = new GreenfootImage("jackals1.png");
        ancho=escala;
        alto=escala;
        dir=1;
        x2=0;
        x3=0;
        velocidad=500;
        desp=5;
        poder=2;//poder de disparo
        getImage().scale(ancho,alto);
        timerVel = new SimpleTimer();
        timerDisparo = new SimpleTimer();
        timerDisparo.mark();
        timerVel.mark();
    }

    /**
     * Act - Dispara despues de sierto tiempo y a cierta distancia de la base.
     * Si la vida es 0 llama al metodo muere.
     * Si se encuentra a mas de 200 pixeles de la base puede moverse.
     */
    public void act() 
    {
        if((timerDisparo.millisElapsed() > 3000 && getX() < 500 &&  vida>0) || (isTouching(bala.class) && timerDisparo.millisElapsed() > 300 && vida>0))
        {
            disparo();
            Greenfoot.playSound("plasmaB.mp3");
        }
        teDisparo();
        if(vida > 0 && getX() > 250)//200 distancia hacia la base
            mov("jackals1.png","jackals2.png");

        if(vida <= 0)
            muere("jackalsMu.png");
    }  
}
