import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Objeto que agrega 90 municiones al jugador.
 * 
 * @author CMGH
 * @version 27/05/2014
 */
public class masMun extends Entregas
{
    /**
     * @param escala es el tamaño que se deve de ver el objeto dependiendo de donde se encuentre.
     * @param y es la posicion en y a la que debe llegar.
     */
    public masMun(int escala,int y)
    {
        super();
        ancho=escala;
        alto=escala-22;
        getImage().scale(ancho,alto);
        yDestino=y;
        timer.mark();
    }
    
    /**
     * Act - llama al metodo mueve().
     */
    public void act() 
    {
        mueve();
    }    
}
