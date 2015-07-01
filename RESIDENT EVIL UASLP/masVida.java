import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Objeto que pone en 200 de cantidad de vida al jugador.
 * 
 * @author Marco Antonio Pescina Ruiz
 * @version 27/05/2015
 */
public class masVida extends Entregas
{
    /**
     * @param escala es el tamaño que se deve de ver el objeto dependiendo de donde se encuentre.
     */
    public masVida(int escala)
    {
        ancho=escala;
        alto=escala;
        getImage().scale(ancho,alto);
    }
   
    public void act() 
    {
        // Add your action code here.
    }    
}
