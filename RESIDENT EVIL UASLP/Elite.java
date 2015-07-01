import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Enemigo Elite
 * 
 * @author Marco Antonio Pescina Ruiz
 * @version 27/05/2015
 */
public class Elite extends Enemigo
{
    /**
     * bandera para crear una sola municion
     */
    private boolean ban;
    /**
     * @param escala es la escala del enemigo dependiendo de donde se encuentre en el escenario.
     */
    public Elite(int escala)
    {
        Im = new GreenfootImage("elite1.png");
        ban=false;
        ancho=escala;
        alto=escala;
        dir=1;
        x2=0;
        x3=0;
        velocidad=200;
        desp=5;
        poder=10;
        getImage().scale(ancho,alto);
        timerVel = new SimpleTimer();
        timerDisparo = new SimpleTimer();
        timerDisparo.mark();
        timerVel.mark();
    }

    /**
     * Act - Dispara despues de sierto tiempo y a cierta distancia de la base.
     * Si la vida es 0 llama al metodo muere y genera un objeto masMun.
     * Si se encuentra a mas de 200 pixeles de la base puede moverse.
     */
    public void act() 
    {
        if((timerDisparo.millisElapsed() > 1000 && getX() < 700 &&  vida>0) || (isTouching(bala.class) && timerDisparo.millisElapsed() > 100 && vida>0))
        {
            disparo();
            Greenfoot.playSound("plasmaC.mp3");
        }
        teDisparo();
        if(vida > 0 && getX() > 400)//200 distancia hacia la base
            mov("elite1.png","elite2.png");

        if(vida <= 0)
        {
            if(ban==false)
            {
                ban=true;
                masMun mm = new masMun(escalaMasMun(),getY());
                getWorld().addObject(mm,getX(),getY());
            }
            muere("eliteMu.png");
        }
    }    
    
    /**
     * Escala el objeto masMun dependiendo del tamaño de la imagen del enemigo.
     * @return int - escala.
     */
    private int escalaMasMun()
    {
        int esc=0;
        if(ancho==55)
        {
            esc=28;
        }
        if(ancho==60)
        {
            esc=30;
        }
        if(ancho==65)
        {
            esc=32;
        }
        if(ancho==70)
        {
            esc=34;
        }
        if(ancho==75)
        {
            esc=37;
        }
        if(ancho==80)
        {
            esc=40;
        }
        return esc;
    }
}
