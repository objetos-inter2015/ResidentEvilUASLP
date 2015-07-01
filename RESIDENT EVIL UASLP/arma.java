import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Arma tierra-aire con la cual se elimina a las naves enemigas.
 * 
 * @author Marco Antonio Pescina Ruiz
 * @version 27/06/2015
 */
public class arma extends Actor
{
    private int munMc; //municiones de MasterCheaf
    private int anchoMc;//ancho de MasterCheaf
    private int altoMc;//alto de MasterCheaf
    private int vidaMc;//vida al momento de subirse al arma
    private int numVidasMc;//numero de vidas
    private int dirDisp;//direccion del disparo
    private boolean McDentro;//variable para saber si el jugador esta dentro del arma
    private SimpleTimer timer;//timer deretardo para que no se hagan funciones multiples
    private SimpleTimer timerDisparo;//tiempo de iteracion entre disparo
    private SimpleTimer timerRecVida;
    private SimpleTimer timerSoundVida;
    private SimpleTimer timerPos;
    
    public arma()
    {
        McDentro = false;
        timer = new SimpleTimer();
        timerDisparo = new SimpleTimer();
        timerRecVida = new SimpleTimer();
        timerSoundVida = new SimpleTimer();
        timerPos = new SimpleTimer();
        
        dirDisp = 1;
        
        timerRecVida.mark();
        timerDisparo.mark();
        timer.mark();
        timerSoundVida.mark();
        timerPos.mark();
    }

    /**
     * Act - llama al metodo estaMc().
     * si el jugador se convierte en el arma llama a los metodos:
     * bajaDeArma();
     * disparo();
     * recuperaVida();
     * setVida();
     * muevePos();
     */
    public void act() 
    {
        estaMc();
        
        if(McDentro == true)//si esta en el arma
        {
            bajaDeArma();
            disparo();
            recuperaVida();
            setVida();
            muevePos();
        }   
    }    
    
    /**
     * Mueve el angulo de disparo del arma
     */
    private void muevePos()
    {
        if(Greenfoot.isKeyDown("left") && dirDisp < 3 && timerPos.millisElapsed() > 100)
        {
            dirDisp++;
            setImagen();
            timerPos.mark();
        }
        if(Greenfoot.isKeyDown("right") && dirDisp > 1 && timerPos.millisElapsed() > 100) 
        {
            dirDisp--;
            setImagen();
            timerPos.mark();
        }
    }
    
    /**
     * Cambia la imagen del arma dependiendo su posicion
     */
    private void setImagen()
    {
        switch(dirDisp)
        {
            case 1: setImage("arma1.png");
            break;
            
            case 2: setImage("arma2.png");
            break;
            
            case 3: setImage("arma3.png");
            break;
        }
    }

    /**
     * Al bajar del arma se crea un nuebo objeto MasterCheaf con los parametros que tenia antes de subir al arma.
     */
    private void bajaDeArma()
    {
        if(Greenfoot.isKeyDown("e") && timer.millisElapsed() > 500)//bajar del arma
        {
            McDentro = false;
            MasterCheaf m = new MasterCheaf(munMc,vidaMc,anchoMc,altoMc,numVidasMc);
            getWorld().addObject(m,getX(),getY());
            setImage("arma.png");
            setLocation(getX()+10,getY()+20);
            resetMun();
            timer.mark();
        }
    }
    
    /**
     * Metodo para crear el objeto plasma que se disparara.
     */
    private void disparo()
    {
        if(Greenfoot.isKeyDown("space") && timerDisparo.millisElapsed() > 500)
        {
            plasma3 p3 = new plasma3(dirDisp);
            getWorld().addObject(p3,getX()+10,getY()-25);
            Greenfoot.playSound("plasma2.mp3");
            timerDisparo.mark();
        }
    }

    /**
     * Si el jugador se encuentra serca, puede subir al arma.
     * Guarda los valores que tenia el jugador para que al bajarse del arma los recupere.
     */
    protected void estaMc()//si se encuentra serca, puede subir al arma
    {
        List list;
        list=getObjectsInRange(40,MasterCheaf.class); 

        if(!list.isEmpty())
        {
            texto t = new texto("Press e",25);
            getWorld().addObject(t,getX()-10,getY()+getImage().getHeight()/2);

            if(Greenfoot.isKeyDown("e") && timer.millisElapsed() > 500)//subir al arma
            {
                MasterCheaf m;
                m = (MasterCheaf) list.get(0);
                munMc = m.municion;
                anchoMc = m.ancho;
                altoMc = m.alto;
                vidaMc = m.vida;
                numVidasMc = m.numVidas;
                McDentro = true;
                getWorld().removeObject(m);
                dirDisp = 1;
                setMun();//municiones infinitas
                setImage("arma1.png");
                setLocation(getX()-10,getY()-20);
                timer.mark();
            }
        }
        else//desaparece letrero
        {
            list=getObjectsInRange(100,texto.class); 
            if(!list.isEmpty())
            {
                texto te = new texto("Press e",25);
                te = (texto) list.get(0);
                getWorld().removeObject(te);
            }
        }
    }

    /**
     * Marca en el contador de municiones el numero de municiones disponibles.
     */
    private void setMun()//marca en el counter las municiones
    {
        Escenario escenario = (Escenario)getWorld();
        Counter c = escenario.getContadorMun();
        c.setImagen("barraMun.png");
        c.setValue(0);
        c.setPrefix("INF ");
    }

    /**
     * Regresa el contador de municiones a su estado antes de que el jugador subiera al arma.
     */
    private void resetMun()
    {
        Escenario escenario = (Escenario)getWorld();
        Counter c = escenario.getContadorMun();
        c.setPrefix("");
    }

    /**
     * Dependiendo del tiempo se recupera la cantidad de vida disponible del jugador.
     */
    private void recuperaVida()
    {
        if(timerRecVida.millisElapsed() > 100 && vidaMc<100)
        {
            vidaMc++;
            timerRecVida.mark();
        }
    }

    /**
     * Marca en el counter las vidas
     */
    private void setVida()
    {
        if(vidaMc<0)
            vidaMc=0;
        Escenario escenario = (Escenario)getWorld();
        Counter c = escenario.getContadorVida();
        if(vidaMc < 40)
        {
            c.setImagen("barraVidaR.png");
            if(timerSoundVida.millisElapsed() > 360)
            {
                Greenfoot.playSound("peligroVida1.mp3");
                timerSoundVida.mark();
            }
        }
        if(vidaMc >=40 && vidaMc <=100)
            c.setImagen("barraVida.png");
        if(vidaMc > 100)
            c.setImagen("barraVidaMV.png");
        c.setValue(vidaMc);
    }
}

