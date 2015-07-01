import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Bala
 * 
 * @author Marco Antonio Pescina Ruiz
 * @version 27/05/2015
 */
public class bala extends Municion
{
    /**
     * @param dire direccion de la bala.
     * @param pod poder de la bala.
     * Dependiendo de la direccion se cambia de imagen.
     */
    public bala(int dire,int pod)
    {
        timer = new SimpleTimer();
        dir=dire;
        desp=10;
        ancho=10;
        alto=4;
        poder=pod;
        velocidad=10;
        getImage().scale(ancho,alto);
        if(dir==1)
            getImage().mirrorHorizontally();
        if(dir==2)
        {
            B = new GreenfootImage("bala2.png");
            setImage(B);
            getImage().scale(alto,ancho);
        }
        if(dir==3)
        {
            B = new GreenfootImage("bala2.png");
            setImage(B);
            getImage().mirrorVertically();
            getImage().scale(alto,ancho);
        } 
        timer.mark();
    }
    
    /**
     * super.act();
     */
    public void act() 
    {
        super.act();
    } 
}
