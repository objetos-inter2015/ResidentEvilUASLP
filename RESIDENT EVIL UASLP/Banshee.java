import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Enemigo Banshee
 * 
 * @author Marco Antonio Pescina Ruiz 
 * @version 27/05/2015
 */
public class Banshee extends Enemigo
{
    /**
     * Variable de destino al ser derrivada.
     */
    private int yDestino;
    /**
     * bandera de caida de la nave
     */
    private boolean ban;
    /**
     * tiempo para movimientod e caida.
     */
    private SimpleTimer timerCaida;
    
     /**
     * @param escala es la escala del enemigo dependiendo de donde se encuentre en el escenario.
     */
    public Banshee(int escala)
    {
        Im = new GreenfootImage("banshee.png");
        ancho=escala;
        alto=escala/2;
        dir=1;
        x2=0;
        x3=0;
        ban=false;
        velocidad=100;
        desp=5;
        poder=20;
        getImage().scale(ancho,alto);
        timerCaida = new SimpleTimer();
        timerVel = new SimpleTimer();
        timerDisparo = new SimpleTimer();
        timerDisparo.mark();
        timerVel.mark();
        timerCaida.mark();
        
    }
    
    
    public void act() 
    {
        if((timerDisparo.millisElapsed() > 1500 && getX() < 800 &&  vida>0) || (isTouching(bala.class) && timerDisparo.millisElapsed() > 1000 && vida>0))
        {
            disparo();
            Greenfoot.playSound("plasma2.mp3");
        }
        teDisparo();
        teDisparoArma();
        if(vida > 0 && getX() > 500)//distancia hacia la base
        {
            mov("banshee.png","banshee.png");
        }

        if(vida <= 0)
        {
            if(ban==false)
            {
                ban=true;
                yDestino = getY() + 280;
                Im = new GreenfootImage("bansheeMu.png");
                setImage(Im);
                getImage().scale(ancho,alto);
            }
            if(getY() < yDestino && timerCaida.millisElapsed() > 50)
            {
                setLocation(getX(),getY()+10);
                timerCaida.mark();
            }
            if(getY() >= yDestino)
            {
                tocoMc();
                muere("bansheeMu.png");
            }
        }
    } 
    
    /**
     * Verifica si lo esta tocando un plasma del arma tierra-aire y resta vida..
     */
    private void teDisparoArma()
    {
        if(isTouching(plasma3.class) && vida>0)
        {
            plasma3 p;
            p = (plasma3) getOneIntersectingObject(plasma3.class);
            vida= vida - (p.getPoder());
            getWorld().removeObject(p);
        }
    }
    
    /**
     * genera el plasma a disparar. 
     */
    protected void disparo()
    {
        escalaDisparo();
        plasma2 p2 = new plasma2(poder,escalaDispAn,escalaDispAl);
        getWorld().addObject(p2, getX()-32, getY()- getImage().getHeight()/8);
        timerDisparo.mark();
    }
}
