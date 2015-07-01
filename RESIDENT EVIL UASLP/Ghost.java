import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Enemigo Ghost
 * 
 * @author Marco Antonio Pescina Ruiz
 * @version 27/05/2015
 */
public class Ghost extends Enemigo
{
    /**
     * bandera para crear una sola municion
     */
    private boolean ban;
    /**
     * @param escala es la escala del enemigo dependiendo de donde se encuentre en el escenario.
     */
    public Ghost(int escala)
    {
        Im = new GreenfootImage("ghost.png");
        ban=false;
        ancho=escala;
        alto=escala/2;
        dir=1;
        x2=0;
        x3=0;
        velocidad=150;
        desp=5;
        poder=15;
        getImage().scale(ancho,alto);
        timerVel = new SimpleTimer();
        timerDisparo = new SimpleTimer();
        timerDisparo.mark();
        timerVel.mark();
    }

    /**
     * Act - Dispara despues de sierto tiempo y a cierta distancia de la base.
     * Si la vida es 0 llama al metodo muere y genera un objeto masVida.
     * Si se encuentra a mas de 500 pixeles de la base puede moverse.
     */
    public void act() 
    {
        if((timerDisparo.millisElapsed() > 1500 && getX() < 800 &&  vida>0) || (isTouching(bala.class) && timerDisparo.millisElapsed() > 1000 && vida>0))
        {
            disparo();
            Greenfoot.playSound("plasmaC.mp3");
        }
        teDisparo();
        if(vida > 0 && getX() > 500)//200 distancia hacia la base
            mov("ghost.png","ghost.png");
        tocoMc();
        if(vida <= 0)
        {
            if(ban==false)
            {
                ban=true;
                masVida mv = new masVida(escalaMasVida());
                getWorld().addObject(mv,getX(),getY());
            }
            muere("ghostMu.png");
        }
    }  
   
    /**
     * Escala el objeto masVida dependiendo del tamaño de la imagen del enemigo.
     * @return int - escala.
     */
    private int escalaMasVida()
    {
        int esc=0;
        if(ancho==75)
        {
            esc=20;
        }
        if(ancho==80)
        {
            esc=25;
        }
        if(ancho==85)
        {
            esc=28;
        }
        if(ancho==90)
        {
            esc=32;
        }
        if(ancho==95)
        {
            esc=37;
        }
        if(ancho==100)
        {
            esc=40;
        }
        return esc;
    }
}
