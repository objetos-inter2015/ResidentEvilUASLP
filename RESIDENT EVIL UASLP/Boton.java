import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Objeto boton
 * 
 * @author Marco Antonio Pescina Ruiz
 * @version 27/05/2015
 */
public class Boton extends Actor
{
    /**
     * Dependiendo del num se pone la imagen correspondiente.
     * @param num Numero de reperencia del boton o tipo de boton.
     */
    public Boton(int num)
    {
        switch(num)
        {
            case 1:setImage("BotonPlay.png");break;
            case 2:setImage("BotonScore.png");break;
            case 3:setImage("BotonHelp.png");break;
            case 4:setImage("BotonMenuCh.png");break;
            case 5:setImage("BotonReturn.png");break;
        }
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}
