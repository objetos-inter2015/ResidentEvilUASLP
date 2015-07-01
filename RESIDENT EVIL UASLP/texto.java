import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Crea texto para poner mensajes.
 * 
 * @author CMGH
 * @version 27/05/2014
 */
public class texto extends Actor
{
    /**
     * crea la imagen con el texto el tamaño y en color blanco.
     * @param s recibe la cadena del que mostrara el objeto texto.
     * @param tam es el tamaño del texto.
     */
    public texto(String s,int tam)
    {
        setImage(new GreenfootImage(s,tam,Color.WHITE,null));
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}
