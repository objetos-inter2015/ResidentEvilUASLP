import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Clase en la que se encuentran las variables principales de cada personaje del juego.
 * 
 * @author CMGH
 * @version 27/05/2014
 */
public class Personajes extends Actor
{
    /**
     * velocidad de movimiento
     */
    protected int velocidad;
    /**
     * ancho de la imagen
     */
    public int ancho;
    /**
     * alto de la imagen
     */
    public int alto;
    /**
     * direccion del movimiento o hacia a donde apunta
     */
    protected int dir;
    /**
     *  num de desplasamiento del objeto
     */
    protected int desp;
    /**
     * cantidad de vida disponible.
     */
    public int vida;
    /**
     * Cantidad de municiones.
     */
    public int municion;
    /**
     * poder que le dara al disparo que genere. poder de ataque
     */
    public int poder;
    
    public Personajes()
    {
        vida=100;
        municion=100;
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}
