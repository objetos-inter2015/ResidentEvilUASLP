import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Clase enemigo de donde se heredan variables y metodos que todos los enemigos utilizan.
 * 
 * @author CMGH
 * @version 27/05/2014
 */
public class Enemigo extends Personajes
{
    /**
     * timer de velocidad
     */
    protected SimpleTimer timerVel;
    /**
     * timer de disparo
     */
    protected SimpleTimer timerDisparo;
    /**
     * variable de imagen que se muestra
     */
    protected int x2;
    /**
     * variable para tiempo de muerte
     */
    protected int x3;
    /**
     * variable para escalar disparos ancho
     */
    protected int escalaDispAn;
    /**
     * variable para escalar disparos alto
     */
    protected int escalaDispAl;
    /**
     * imagen
     */
    protected GreenfootImage Im;


    public void act() 
    {

    }    

    /**
     * Genera los plasmas que se disparan dependiendo de la direccion y el poder de cada enemigo, ademas se escala dependiendo de la pos en la que se encuentre el enemigo.
     */
    protected void disparo()
    {
        escalaDisparo();
        plasma p = new plasma(poder,escalaDispAn,escalaDispAl);
        switch(dir)
        {
            case 0:
            getWorld().addObject(p, getX()+32, getY()- getImage().getHeight()/3);
            break;

            case 1:
            getWorld().addObject(p, getX()-32, getY()- getImage().getHeight()/8);
            break;

            case 2:
            getWorld().addObject(p, getX(), getY()- getImage().getHeight()/3);
            break;

            case 3:
            getWorld().addObject(p, getX()-10, getY()- getImage().getHeight()/3);
            break;
        }
        timerDisparo.mark();
    }

    /**
     * Dependiendo del tamaño del enemigo se escalan los plasmas que dispara.
     */
    protected void escalaDisparo()
    {
        if(ancho==40)
        {
            escalaDispAn=20;
            escalaDispAl=4;
        }
        if(ancho==45)
        {
            escalaDispAn=23;
            escalaDispAl=5;
        }
        if(ancho==50)
        {
            escalaDispAn=26;
            escalaDispAl=6;
        }
        if(ancho==55)
        {
            escalaDispAn=30;
            escalaDispAl=8;
        }
        if(ancho==60)
        {
            escalaDispAn=33;
            escalaDispAl=9;
        }
        if(ancho==65)
        {
            escalaDispAn=36;
            escalaDispAl=10;
        }
        if(ancho==70)
        {
            escalaDispAn=40;
            escalaDispAl=12;
        }
        if(ancho==75)
        {
            escalaDispAn=43;
            escalaDispAl=13;
        }
        if(ancho==80)
        {
            escalaDispAn=46;
            escalaDispAl=14;
        }
        if(ancho==85)
        {
            escalaDispAn=50;
            escalaDispAl=15;
        }
        if(ancho==90)
        {
            escalaDispAn=53;
            escalaDispAl=16;
        }
        if(ancho==95)
        {
            escalaDispAn=56;
            escalaDispAl=17;
        }
        if(ancho==100)
        {
            escalaDispAn=60;
            escalaDispAl=18;
        }
    }

    /**
     * Movimietno del enemigo cambiando de una imagen a otra para simular el movimiento.
     * @param imagen1 es la primera imagen que se mostrara
     * @param imagen2 es la segunda imagen que se mostrara.
     */
    public void mov(String imagen1,String imagen2)
    {
        if(x2==0 && timerVel.millisElapsed() > velocidad)
        {
            Im = new GreenfootImage(imagen2);
            setImage(Im);
            getImage().scale(ancho,alto);
            setLocation(getX()-desp,getY());
            x2=1;
            timerVel.mark();
        }
        if(x2==1 && timerVel.millisElapsed() > velocidad)
        {
            Im = new GreenfootImage(imagen1);
            setImage(Im);
            getImage().scale(ancho,alto);
            setLocation(getX()-desp,getY());
            x2=0;
            timerVel.mark();
        }
    }

    /**
     * Cambia la imagen cuando el enemigo muere, espera un tiempo y remueve el objeto del escenario.
     * @param imagen es la imagen que pondra al morir.
     */
    public void muere(String imagen)
    {
        if(x3==0)
        { 
            Im = new GreenfootImage(imagen);
            setImage(Im);
            getImage().scale(ancho,alto);
            x3=1;
            timerVel.mark();
        }
        if(x3==1 && timerVel.millisElapsed() > 2000)
        {
            getWorld().removeObject(this);
        }
    }

    /**
     * Verifica si alguna bala toca al enemigo y le resta vida.
     */
    protected void teDisparo()
    {
        if(isTouching(bala.class) && vida>0)
        {
            bala b;
            b = (bala) getOneIntersectingObject(bala.class);
            vida= vida - (b.getPoder()-5);
            getWorld().removeObject(b);
        }
    }

    /**
     * Verifica si esta tocando al jugador principal.
     */
    protected void tocoMc()
    {
        List list;
        list=getObjectsInRange(25,MasterCheaf.class); 

        if(!list.isEmpty())
        {
            MasterCheaf m;
            m = (MasterCheaf) list.get(0);
            m.vida=0;
        }
    }
}
