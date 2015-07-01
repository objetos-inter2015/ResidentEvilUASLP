import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Nave de municiones
 * 
 * @author Marco Antonio Pescina Ruiz
 * @version 27/05/2015
 */
public class Pelican extends Personajes
{
    /**
     * timer de disparo
     */
    private SimpleTimer timer;
    /**
     * timer de velocidad
     */
    private SimpleTimer timerVel;
    /**
     * timer de entrega de municiones y vida
     */
    private SimpleTimer timerEntrega;
    /**
     * activacion del disparo
     */
    private boolean activacionDisp;
    
    /**
     * @param acti es la activacion de si el pelican dispara o no
     */
    public Pelican(boolean acti)
    {
        timerVel = new SimpleTimer();
        timer = new SimpleTimer();
        timerEntrega = new SimpleTimer();
        activacionDisp=acti;
        desp=10;
        velocidad=50;
        timerVel.mark();
        timer.mark();
        timerEntrega.mark();
        dir=0;
    }

    /**
     * dependiendo si el disparo esta activo o no se manda llamar al metodo disparo.
     * despues de sierto tiempo y en un area espesifica del escenario se general las entregas.
     */
    public void act() 
    {
        if(getX() + getImage().getWidth() < getWorld().getWidth() && activacionDisp==true)
            disparo();
            
        if(timerEntrega.millisElapsed() > 1000 && getX() < 400)
        {
            generaEntregas();
            timerEntrega.mark();
        }
        
        if(getX() < getWorld().getWidth()+ getImage().getWidth()/2)
            mov();
        else
            getWorld().removeObject(this);
    }    
    
    /**
     * genera las balas que dispara el pelican.
     */
    private void disparo()
    {
        if(timer.millisElapsed() > 100)
        {
            bala b = new bala(dir,30);
            getWorld().addObject(b, getX()+100, getY()+ getImage().getHeight()/2);

            bala b2 = new bala(4,30);
            getWorld().addObject(b2, getX()+50, getY()+ getImage().getHeight()/2);

            bala b3 = new bala(5,30);
            getWorld().addObject(b3, getX()+50, getY()- getImage().getHeight()/2);
            
            Greenfoot.playSound("bala.mp3");
            timer.mark();
        }
    }
    
    /**
     *Dependiendo de los tiempos se genera los objetos masVida y masMun para dejar caer del Pelican. 
     */
    protected void generaEntregas()
    {
        Random r = new Random();
        int i = r.nextInt(200) + 300;
        int j = r.nextInt(200) + 300;
        masVida1 mv1 = new masVida1(2,100);
        masMun mm = new masMun(40,100);
        
        if(i>=300 && i<=350)
            mv1 = new masVida1(14,i);
        if(i>=351 && i<=400)
            mv1 = new masVida1(17,i);
        if(i>=401 && i<=450)
            mv1 = new masVida1(20,i);
        if(i>=451 && i<=500)
            mv1 = new masVida1(24,i);/*
        if(i>=501 && i<=550)
            mv1 = new masVida1(27,i);
        if(i>=551 && i<=600)
            mv1 = new masVida1(30,i);*/
        getWorld().addObject(mv1,getX(),getY());
        
        if(j>=300 && j<=350)
            mm = new masMun(28,j);
        if(j>=351 && j<=400)
            mm = new masMun(30,j);
        if(j>=401 && j<=450)
            mm = new masMun(32,j);
        if(j>=451 && j<=500)
            mm = new masMun(34,j);/*
        if(j>=501 && j<=550)
            mm = new masMun(37,j);
        if(j>=551 && j<=600)
            mm = new masMun(40,j);*/
        getWorld().addObject(mm,getX(),getY());
    }
    
    /**
     * Movimiento del pelican.
     */
    private void mov()
    {
        if(timerVel.millisElapsed() > velocidad)
        {
            setLocation(getX()+ desp,getY());
            timerVel.mark();
        }
    }
}
