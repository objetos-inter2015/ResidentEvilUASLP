import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Iterator;

/**
 * Write a description of class Torre here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Torre extends Base
{
    /**
     * Act - llama al metodo teDisparoTorre().
     */
    public void act() 
    {
        teDisparoTorre();
    }  
    
    /**
     * Verifica si a la torre le llego un disparo de plasma y resta vida a la Base.
     */
    private void teDisparoTorre()
    {
        List listaP;
        listaP=getObjectsInRange(100,plasma.class);
        Iterator itP = listaP.iterator();
        Escenario escenario = (Escenario)getWorld();
        Base b = escenario.getBase();
        
        while(itP.hasNext())
        {
            plasma p;
            p = (plasma)itP.next();
            b.vidaBase -= p.getPoder();
            getWorld().removeObject(p);
        }
        
        List listaP2;
        listaP2=getObjectsInRange(80,plasma2.class);
        Iterator itP2 = listaP2.iterator();
        
        
        while(itP2.hasNext())
        {
            plasma2 p2;
            p2 = (plasma2)itP2.next();
            b.vidaBase -= p2.getPoder();
            getWorld().removeObject(p2);
        }
    }
}
