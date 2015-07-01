import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Iterator;

/**
 * La Base es el objeto que el jugador tiene que defender.
 * 
 * @author Cristian Miguel Garcia Huerta
 * @version 27/05/2014
 */
public class Base extends Actor
{
    /**
     * Cantidad de Vida que tiene la Base
     */
    public int vidaBase;
    /**
     * Tiempo para cambio de imagen.
     */
    private SimpleTimer timerImagen;
    /**
     * Bandera para seleccionar cambio de imagen al estarse quemando la base.
     */
    private int ban;
    public Base()
    {
        vidaBase=5000;
        ban = 1;
        timerImagen = new SimpleTimer();
        timerImagen.mark();
    }

    /**
     * Act - manda llamar los metodos:
     * teDisparo();
     * setVidaBase();
     * imagenBase();
     * Verifica si la vida de la base es 0 envia el mensage "Game Over!!" y regresa al menu principal.
     */
    public void act() 
    {
        teDisparo();
        setVidaBase();
        imagenBase();
        if(vidaBase<=0)
        {
            texto tx = new texto("Game Over!!",100);
            getWorld().addObject(tx,500,300);
            Greenfoot.delay(150);
            World newWorld = new Menu();
            Greenfoot.setWorld(newWorld);
        }
    }    

    /**
     * Este metodo cambia de imagen de la base cuando esta se desta quemando para simular el movimiento del fuego.
     */
    private void imagenBase()
    {
        if(vidaBase < 2000 && vidaBase > 1000 && timerImagen.millisElapsed() > 100)
        {
            if(ban==1)
            {
                setImage("baseFinalF1-1.png");
                ban=2;
                timerImagen.mark();
            }
            else
            {
                setImage("baseFinalF1-2.png");
                ban=1;
                timerImagen.mark();
            }
        }
        if(vidaBase< 1000 && timerImagen.millisElapsed() > 100)
        {
            if(ban==1)
            {
                setImage("baseFinalF2-1.png");
                ban=2;
                timerImagen.mark();
            }
            else
            {
                setImage("baseFinalF2-2.png");
                ban=1;
                timerImagen.mark();
            }
        }
    }

    /**
     * Regresa la cantidad de vida de la base
     * @return int
     */
    public int getVidaBase()
    {
        return vidaBase;
    }
    
    /**
     * Marca en el counter la vida de la base 
     */
    private void setVidaBase()
    {
        if(vidaBase<0)
            vidaBase=0;
        Escenario escenario = (Escenario)getWorld();
        Counter c = escenario.getContadorVidaBase();
        if(vidaBase < 1000)
            c.setImagen("barraVidaR.png");
        c.setValue(vidaBase);
    }

    /**
     * Verifica si te la base recibe un disparo de plasma y resta vida.
     */
    private void teDisparo()
    {
        List listaP;
        listaP=getObjectsInRange(100,plasma.class);
        Iterator itP = listaP.iterator();

        while(itP.hasNext())
        {
            plasma p;
            p = (plasma)itP.next();
            vidaBase -= p.getPoder();
            getWorld().removeObject(p);
        }

        List listaP2;
        listaP2=getObjectsInRange(100,plasma2.class);
        Iterator itP2 = listaP2.iterator();
        while(itP2.hasNext())
        {
            plasma2 p2;
            p2 = (plasma2)itP2.next();
            vidaBase -= p2.getPoder();
            getWorld().removeObject(p2);
        }
    }
}
