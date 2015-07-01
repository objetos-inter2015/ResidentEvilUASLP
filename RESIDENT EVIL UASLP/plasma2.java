import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Plasma2
 * 
 * @author Marco Antonio Pescina Ruiz
 * @version 27/05/2015
 */
public class plasma2 extends Municion
{
    /**
     * numero de pasos de la caida.
     */
    private int caida;
    /**
     * desplasamiento vertical.
     */
    private int despVer;
    /**
     * desplasamiento horizontal.
     */
    private int despHor;
    /**
     * contador de pasos para modificar valores de dir y girar la imagen.
     */
    private int cont;
    public plasma2(int pow,int an, int al)
    {
        timer = new SimpleTimer();
        ancho=an;
        alto=al;
        dir=1;
        desp=10;
        despVer=1;
        despHor=10;
        cont=0;
        poder=pow;
        velocidad=5;
        caida=0;
        getImage().scale(ancho,alto);
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
     * movimiento del plasma2.
     */
    public void mov()
    {
        if(getX()-getImage().getWidth()/2 > 0 && caida<80 || getY() > getWorld().getHeight()- getImage().getHeight())
        {
            setLocation(getX() - despHor,getY() + despVer);
            caida++;
            cont++;
            if(cont==10)
            {
                turn(-9);
                despHor--;
                despVer++;
                cont=0;
            }
        }
        else
            getWorld().removeObject(this);
    }
}
