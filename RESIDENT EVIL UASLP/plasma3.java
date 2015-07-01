import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Plasma3
 * 
 * @author Marco Antonio Pescina Ruiz
 * @version 27/05/2015
 */
public class plasma3 extends Municion
{
    /**
     * timer de velocidad
     */
    private SimpleTimer timer;
    /**
     * direccion en la que apunta el arma tierra-aire-
     */
    private int ModoDisp;
    
    /**
     * @param modo direccion en la que apunta el arma tierra-aire.
     * dependiendeo del modo se cambia la imagen.
     */
    public plasma3(int modo)
    {
        timer = new SimpleTimer();
        timer.mark();
        poder = 20;
        velocidad=5;
        ModoDisp = modo;
        if(modo==2)
            setImage("plasma3-2.png");
        if(modo==3)
            setImage("plasma3-3.png");
    }
    
   
    public void act() 
    {
        if(timer.millisElapsed()>velocidad)
        {
            mov();
            timer.mark();
        }
    }    
    
    /**
     * movimiento del plasma dependiendeo su direccion.
     */
    public void mov()
    {
        if(getY()-getImage().getHeight()/2 > 0)
        {
            switch(ModoDisp)
            {
                case 1:
                setLocation(getX()+10,getY()-10);
                break;
                
                case 2:
                setLocation(getX()+5,getY()-10);
                break;
                
                case 3:
                setLocation(getX()+1,getY()-10);
                break;
            }
        }
        else
            getWorld().removeObject(this);
    }
}
