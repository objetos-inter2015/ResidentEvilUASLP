import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Objeto que pone en 100 de cantidad de vida al jugador.
 * 
 * @author Marco Antonio Pescina Ruiz
 * @version 27/05/2015
 */
public class masVida1 extends Entregas
{
    /**
     * @param escala es el tamaño que se deve de ver el objeto dependiendo de donde se encuentre.
     * @param y es la posicion en y a la que debe llegar.
     */
    public masVida1(int escala, int y)
    {
        super();
        ancho=escala;
        alto=escala;
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
