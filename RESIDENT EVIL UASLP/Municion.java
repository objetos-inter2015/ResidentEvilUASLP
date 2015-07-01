import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase dela cual se heredan los metodos y variables que comparten las municiones.
 * 
 * @author Marco Antonio Pescina Ruiz
 * @version 27/05/2015
 */
public class Municion extends Actor
{
    /**
     * poder de disparo
     */
    protected int poder;
    /**
     * direccion del disparo
     */
    protected int dir;
    /**
     * desplasamiento del objeto
     */
    protected int desp;
    /**
     * velodicad de movimiento
     */
    protected int velocidad;
    /**
     * ancho de la imagen
     */
    protected int ancho;
    /**
     * alto de la imagen
     */
    protected int alto;
    /**
     * imagen
     */
    protected GreenfootImage B;
    /**
     * tiempo de movimiento
     */
    protected SimpleTimer timer;
    
    private boolean ban;
    public Municion()
    {
        ban=false;
    }
    
    /**
     * Act - do whatever the Municion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(timer.millisElapsed()>velocidad)
        {
            if(ban==false && dir==4)
            {
                turn(45);
                ban=true;
            }
            if(ban==false && dir==5)
            {
                turn(-45);
                ban=true;
            }
            mov();
            timer.mark();
        }
    }    

    /**
     * regresa el poder que tiene la municion.
     * @return int poder de la bala.
     */
    public int getPoder()
    {
        return poder;
    }

    /**
     * mivimiento de la municion dependiendo la direccion del objeto que la mande crear.
     */
    public void mov()
    {
        switch(dir)
        {
            case 0: 
            if(getX()+getImage().getWidth()/2 < getWorld().getWidth())
                setLocation(getX() + desp,getY());
            else
                getWorld().removeObject(this);
            break;

            case 1: 
            if(getX()-getImage().getWidth()/2 > 0)
                setLocation(getX() - desp,getY());
            else
                getWorld().removeObject(this);
            break;

            case 2: 
            if(getY() > getWorld().getHeight()/2  && alto > 1)
            {
                setLocation(getX(),getY()- desp);
            }
            else
                getWorld().removeObject(this);
            break;

            case 3: 
            if(getY()+ getImage().getHeight()/2 < getWorld().getHeight())
            {
                setLocation(getX(),getY()+ desp);
            }
            else
                getWorld().removeObject(this);
            break;

            case 4: //diagonal inferior
            if(getY()+ getImage().getHeight()/2 < getWorld().getHeight() || 
            getX()+getImage().getWidth()/2 < getWorld().getWidth())
            {
                setLocation(getX() + desp,getY()+ desp);
            }
            else
                getWorld().removeObject(this);
            break;
                
            case 5: //diagonal superior
            if(getY() > getWorld().getHeight()/2 || 
            getX()+getImage().getWidth()/2 < getWorld().getWidth())
            {
                setLocation(getX() + desp,getY()- desp);
            }
            else
                getWorld().removeObject(this);
            break;
        }
    }
}
