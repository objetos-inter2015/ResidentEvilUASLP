import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Plasma
 * 
 * @author Marco Antonio Pescina Ruiz
 * @version 27/05/2015
 */
public class plasma extends Municion
{
    /**
     * @param pow poder del plasma.
     * @param an ancho de la imagen.
     * @param al alto de la imagen.
     */
    
    public plasma(int pow,int an, int al)
    {
        timer = new SimpleTimer();
        ancho=an;
        alto=al;
        dir=1;
        desp=10;
        poder=pow;
        velocidad=10;
        getImage().scale(ancho,alto);
    }
    
    /**
     * super.act();
     */
    public void act() 
    {
        super.act();
    }    
}
