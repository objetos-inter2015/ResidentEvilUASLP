import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * objetos que el jugador puede recoger.
 * 
 * @author CMGH
 * @version 27/05/2014
 */
public class Entregas extends Actor
{
    /**
     * Variable de posicion en "y" a la cual tiene que caer el objeto
     */
    public int yDestino;
    /**
     * Variable de despasamiento del objeto al caer.
     */
    public int desp;
    /**
     * Velocidad de caida del objeto.
     */
    public int vel;
    /**
     * ancho de la imagen dependiendo de donde valla a caer.
     */
    public int ancho;
    /**
     * alto de la imagen dependiendo de donde valla a caer
     */
    public int alto;
    /**
     * Timer de velocidad
     */
    public SimpleTimer timer;
    
    public Entregas()
    {
        timer = new SimpleTimer();
        desp=10;
        vel=100;
    }
    
    public void act() 
    {
        // Add your action code here.
    }
    
    /**
     * Movimiento de caida del objeto.
     */
    public void mueve()
    {
        if(getY() < yDestino && timer.millisElapsed() > vel)
        {
            setLocation(getX(),getY()+desp);
            timer.mark();
        }
    }
}
