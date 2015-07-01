import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * MasterCheaf es el personaje que estara manejando el jugador
 * 
 * @author Marco Antonio Pescina Ruiz
 * @version 26/05/2015
 */
public class MasterCheaf extends Personajes
{
    private GreenfootImage Mc;//imagen
    private SimpleTimer timer;//timer de disparo
    private SimpleTimer timer2;//tiempo de movimiento
    private SimpleTimer timerRecVida;//tiempo de recuperacion de vida
    private SimpleTimer timerRecVida2;//tiempo para comenzar a recuperacion de vida
    private SimpleTimer timerSoundVida;// tiempo de sonido de pierde vida
    private int x;//variable de referencia para movimientos
    private int x2;//variable de movimiento numero de imagen
    private int pelicanOp;//veces que puedes acceder al pelican
    public int numVidas;//numero de vidas
    
    /**
     * este constructor es para inicio de juego con condiciones iniciales.
     * @param vidas  el parametro vidas llega con su valor inicial que es 3
     */
    public MasterCheaf(int vidas)
    {
        timer = new SimpleTimer();
        timer2 = new SimpleTimer();
        timerRecVida = new SimpleTimer();
        timerRecVida2 = new SimpleTimer();
        timerSoundVida = new SimpleTimer();
        Mc = new GreenfootImage("McRight.png");
        pelicanOp=3;
        x=5;
        x2=0;
        ancho=50;
        alto=60;
        dir=0;
        desp=10;
        poder=10;
        numVidas=vidas;
        setImage(Mc);
        getImage().scale(ancho,alto);
        timer.mark();
        timer2.mark();
        timerRecVida.mark();
        timerRecVida2.mark();
        timerSoundVida.mark();
    }

    /**
     * Este constructor es cuando se quiere crear el objeto con variables espesificas. 
     * Se utiliza cuando el jugador baja del arma del nivel 3.
     * @param munAnterior recibe el numero de municiones disponibles.
     * @param vidaAnt recibe la cantidad de vida disponible.
     * @param anchoAnt recibe el ancho de la imagen del jugador.
     * @param altoAnt recibe el alto de la imagen del jugador.
     * @param vidas recibe el numero de vidas disponibles.
     */
    public MasterCheaf(int munAnterior,int vidaAnt,int anchoAnt,int altoAnt,int vidas)
    {
        municion= munAnterior;
        ancho = anchoAnt;
        alto = altoAnt;
        vida = vidaAnt;

        timer = new SimpleTimer();
        timer2 = new SimpleTimer();
        timerRecVida = new SimpleTimer();
        timerRecVida2 = new SimpleTimer();
        timerSoundVida = new SimpleTimer();
        Mc = new GreenfootImage("McRight.png");
        pelicanOp=3;
        x=5;
        x2=0;
        dir=0;
        desp=10;
        poder=10;
        numVidas=vidas;
        setImage(Mc);
        getImage().scale(ancho,alto);
        timer.mark();
        timer2.mark();
        timerRecVida.mark();
        timerRecVida2.mark();
        timerSoundVida.mark();
    }

    /**
     * Act - do whatever the MasterCheaf wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    { 
        if(x==5)
            checTecla();
        if(x==1)//dir = 1 izquierda
        {
            dir=1;
            movimiento();
        }
        if(x==0)//dir = 0 derecha
        {
            dir=0;
            movimiento();
        }
        if(x==2)//dir = 2 arriba
        {
            dir=2;
            movimiento();
        }
        if(x==3)//dir = 3 abajo
        {
            dir=3;
            movimiento();
        }
        if(Greenfoot.isKeyDown("space") && timer.millisElapsed() > 50 && municion>0)
        {
            disparo();
            Greenfoot.playSound("bala.mp3");
        }
        setMun();
        if(timerRecVida2.millisElapsed() > 3000)
            recuperaVida();
        teDisparo();
        setVida();
        setVidas();
        recogioAlgo();
        if(vida <= 0)
        {
            numVidas--;
            muere();
        }
    }
    
     /**
      * recogioAlgo - En este metodo se verifica si se esta tocando algun objeto que pueda ya sea darle mas vida al jugador o mas municiones.
      */
    private void recogioAlgo()
    {
      
        if(isTouching(masVida.class) && vida<200)
        {
            Greenfoot.playSound("masVida.mp3");
            masVida mv;
            mv = (masVida)getOneIntersectingObject(masVida.class);
            getWorld().removeObject(mv);
            vida=200;
        }
        if(isTouching(masMun.class))
        {
            Greenfoot.playSound("municion.mp3");
            masMun mm;
            mm = (masMun)getOneIntersectingObject(masMun.class);
            getWorld().removeObject(mm);
            municion+=80;
        }
        if(isTouching(masVida1.class) && vida<100)
        {
            Greenfoot.playSound("vida.mp3");
            masVida1 mv1;
            mv1 = (masVida1)getOneIntersectingObject(masVida1.class);
            getWorld().removeObject(mv1);
            vida=100;
        }
    }

    /**
     * muere - en este metodo se checa si el jugador aun tiene vidas disponibles y lo elimina de donde murio y crea en posicion de inicio. si no tiene vidas envia el mensage Game Over y regresa a menu.
     */
    private void muere()
    {
        Mc = new GreenfootImage("McMu.png");
        setImage(Mc);
        getImage().scale(alto,ancho);
        Greenfoot.playSound("mcMuere.mp3");
        
        if(numVidas>0)
        {
            Greenfoot.delay(100);
            MasterCheaf m = new MasterCheaf(numVidas);
            getWorld().addObject(m,200,450);
            getWorld().removeObject(this);
        }
        else
        {
            texto tx = new texto("Game Over!!",100);
            getWorld().addObject(tx,500,300);
            setVidas();
            Greenfoot.delay(200);
            World newWorld = new Menu();
            Greenfoot.setWorld(newWorld);
        }
    }

    /**
     * este metodo se basa en un tiempo para devolver la cantidad de vida del jugador.
     */
    private void recuperaVida()
    {
        if(timerRecVida.millisElapsed() > 100 && vida<100)
        {
            vida++;
            timerRecVida.mark();
        }
    }

    /**
     * este metodo busca si algun plasma esta tocando al jugador para eliminarla y disminuir la cantidad de vida.
     */
    private void teDisparo()
    {
        if(isTouching(plasma.class))
        {
            plasma p;
            p = (plasma) getOneIntersectingObject(plasma.class);
            vida = vida - p.getPoder();
            getWorld().removeObject(p);
            timerRecVida2.mark();
        }
        if(isTouching(plasma2.class))
        {
            plasma2 p2;
            p2 = (plasma2) getOneIntersectingObject(plasma2.class);
            vida = vida - p2.getPoder();
            getWorld().removeObject(p2);
            timerRecVida2.mark();
        }
    }

    /**
     * este metodo setea en el contador de vidas el numero de vidas del jugador y cambia la imagen del contador.
     */
    private void setVidas()//vidas disponibles
    {
        Escenario escenario = (Escenario)getWorld();
        Counter c = escenario.getContNumVidas();
        switch(numVidas)
        {
            case 3:
            c.setImagen("3vidas.png");
            c.setValue(3);
            break;
            
            case 2:
            c.setImagen("2vidas.png");
            c.setValue(2);
            break;
            
            case 1:
            c.setImagen("1vidas.png");
            c.setValue(1);
            break;
            
            case 0:
            c.setImagen("0vidas.png");
            c.setValue(0);
            break;
        }
    }

    /**
     * este metodo setea la cantidad de vida que tiene el jugador, ademas cambia la imagen de color.
     */
    private void setVida()//marca en el counter la cantidad de vida
    {
        if(vida<0)
            vida=0;
        Escenario escenario = (Escenario)getWorld();
        Counter c = escenario.getContadorVida();
        if(vida < 40)
        {
            c.setImagen("barraVidaR.png");
            if(timerSoundVida.millisElapsed() > 360)
            {
                Greenfoot.playSound("peligroVida1.mp3");
                timerSoundVida.mark();
            }
        }
        if(vida >=40 && vida <=100)
            c.setImagen("barraVida.png");
        if(vida > 100)
            c.setImagen("barraVidaMV.png");
        c.setValue(vida);
    }

    /**
     * este metodo setea el numero de municiones disponibles y cambia la imagen del contador si hay pocas municiones
     */
    private void setMun()//marca en el counter las municiones
    {
        Escenario escenario = (Escenario)getWorld();
        Counter c = escenario.getContadorMun();
        if(municion < 25)
            c.setImagen("barraMunR.png");
        else
            c.setImagen("barraMun.png");
        c.setValue(municion);
    }

    /**
     * este metodo modifica la variable x para indicar la direccion del jugador, ademas verifica si el jugador ya no toco ninguna tecla para llamar el metodo sinMov().
     * tambien verifica si el jugador toca la tecla "p" que es una tecla secreta para mandar llamar la nave de municiones.
     */
    private void checTecla()
    {
        if(Greenfoot.isKeyDown("left"))
        {
            x=1;
            return;
        }
        if(Greenfoot.isKeyDown("right"))
        {
            x=0;
            return;
        }
        if(Greenfoot.isKeyDown("up"))
        {
            x=2;
            return;
        }
        if(Greenfoot.isKeyDown("down"))
        {
            x=3;
            return;
        }
        if(Greenfoot.getKey() == null)
        {
            sinMov();
        }
        if(Greenfoot.isKeyDown("p") && pelicanOp>0 && timer.millisElapsed() > 50)
        {
            Pelican p;
            Escenario escenario = (Escenario)getWorld();
            if(escenario.getNivel() == 1 || escenario.getNivel() == 2)
                p = new Pelican(false);
            else
                p = new Pelican(true);
            getWorld().addObject(p,-100,150);
            pelicanOp--;
            timer.mark();
        }
    }

    /**
     * este metodo genera las balas dependiendo de la direccion del jugador la cual se da por la variable dir
     */
    private void disparo()
    {
        bala b = new bala(dir,poder);
        switch(dir)
        {
            case 0:
            getWorld().addObject(b, getX()+32, getY()- getImage().getHeight()/3);
            break;

            case 1:
            getWorld().addObject(b, getX()-32, getY()- getImage().getHeight()/3);
            break;

            case 2:
            getWorld().addObject(b, getX(), getY()- getImage().getHeight()/3);
            break;

            case 3:
            getWorld().addObject(b, getX()-10, getY()- getImage().getHeight()/3);
            break;
        }
        municion--;
        timer.mark();
    }

    /**
     * este metodo verifica que el jugador no traspase el area de la base esta para efectos visuales
     * @return true si se encuentra en el area permitida si no refresa false y pones las variables de movimiento en su estado inicial.
     */
    private boolean rangoBase()
    {
        if((getX()>=200 && getY()>=450) || (getX()<=200 && getY()<= 450) || (getX()>200 && getY()<450))
            return true;
        else
        {
            x=5;
            x2=0;
            return false;
        }
    }

    /**
     * este es el metodo principal de los mivimientos del jugador en el se realizan los cambios de imagen para simular el movimiento de caminar.
     */
    private void movimiento()
    {
        switch(dir)
        {
            case 0://right
            if(getX()+getImage().getWidth()/2 < getWorld().getWidth())
            {
                if(timer2.millisElapsed() > 50 && x2==0)
                {
                    Mc = new GreenfootImage("McRightM1.png");
                    setImage(Mc);
                    getImage().scale(ancho,alto);
                    setLocation(getX()+desp,getY());
                    x2=1;
                    timer2.mark();
                }
                if(timer2.millisElapsed() > 50 && x2==1)
                {
                    //Greenfoot.delay(4);
                    Mc = new GreenfootImage("McRightM2.png");
                    setImage(Mc);
                    getImage().scale(ancho,alto);
                    setLocation(getX()+desp,getY());
                    x2=2;
                    timer2.mark();
                }
                if(timer2.millisElapsed() > 50 && x2==2)
                {
                    Mc = new GreenfootImage("McRightM3.png");
                    setImage(Mc);
                    getImage().scale(ancho,alto);
                    setLocation(getX()+desp,getY());
                    x2=3;
                    timer2.mark();
                }
                if(timer2.millisElapsed() > 50 && x2==3)
                {
                    x=5;
                    x2=0;
                    timer2.mark();
                }
            }
            else
                x=5;
            break;

            case 1://left
            if(getX()-getImage().getWidth()/2 > 0 && rangoBase())
            {
                if(timer2.millisElapsed() > 50 && x2==0)
                {
                    Mc = new GreenfootImage("McLeftM1.png");
                    setImage(Mc);
                    getImage().scale(ancho,alto);
                    setLocation(getX()-desp,getY());
                    x2=1;
                    timer2.mark();
                }
                if(timer2.millisElapsed() > 50 && x2==1)
                {
                    Mc = new GreenfootImage("McLeftM2.png");
                    setImage(Mc);
                    getImage().scale(ancho,alto);
                    setLocation(getX()-desp,getY());
                    x2=2;
                    timer2.mark();
                }
                if(timer2.millisElapsed() > 50 && x2==2)
                {
                    Mc = new GreenfootImage("McLeftM3.png");
                    setImage(Mc);
                    getImage().scale(ancho,alto);
                    setLocation(getX()-desp,getY());
                    x2=3;
                    timer2.mark();
                    //Greenfoot.delay(4);
                }
                if(timer2.millisElapsed() > 50 && x2==3)
                {
                    x=5;
                    x2=0;
                    timer2.mark();
                }
            }
            else
                x=5;

            break;

            case 2://up
            if(ancho>15 && alto>15 && getY() > getWorld().getHeight()/2)//si no ha llegado a los limites
            {
                if(timer2.millisElapsed() > 50 && x2==0)
                {
                    Mc = new GreenfootImage("McBackM1.png");
                    setImage(Mc);
                    getImage().scale(ancho=ancho-1,alto=alto-1);
                    setLocation(getX(),getY()-desp);
                    x2=1;
                    timer2.mark();
                }
                if(timer2.millisElapsed() > 50 && x2==1)
                {
                    Mc = new GreenfootImage("McBackM2.png");
                    setImage(Mc);
                    getImage().scale(ancho=ancho-1,alto=alto-1);
                    setLocation(getX(),getY()-desp);
                    x2=2;
                    timer2.mark();
                }
                if(timer2.millisElapsed() > 50 && x2==2)
                {
                    x=5;
                    x2=0;
                    timer2.mark();
                }
            }
            else
                x=5;
            break;

            case 3://down
            if(getY()+ getImage().getHeight()/2 < getWorld().getHeight() && rangoBase())
            {
                if(timer2.millisElapsed() > 50 && x2==0)
                {
                    Mc = new GreenfootImage("McFrontM1.png");
                    setImage(Mc);
                    getImage().scale(ancho=ancho+1,alto=alto+1);
                    setLocation(getX(),getY()+ desp);
                    x2=1;
                    timer2.mark();
                }
                if(timer2.millisElapsed() > 50 && x2==1)
                {
                    Mc = new GreenfootImage("McFrontM2.png");
                    setImage(Mc);
                    getImage().scale(ancho=ancho+1,alto=alto+1);
                    setLocation(getX(),getY()+ desp);
                    x2=2;
                    timer2.mark();
                }
                if(timer2.millisElapsed() > 50 && x2==2)
                {
                    x=5;
                    x2=0;
                    timer2.mark();
                }
            }
            else
                x=5;
            break;
        }
        getImage().scale(ancho,alto);
    }

    /**
     * este metodo cambia la imagen del jugador a una imagen donde esta parado sin movimiento esta se llama cuando el jugador deja de precionar alguna tecal de direccion
     */
    private void sinMov()
    {
        switch(dir)
        {
            case 0://right
            Mc = new GreenfootImage("McRight.png");
            setImage(Mc);
            break;
            case 1://left
            Mc = new GreenfootImage("McLeft.png");
            setImage(Mc);
            break;
            case 2://up
            Mc = new GreenfootImage("McBack.png");
            setImage(Mc);
            break;
            case 3://down
            Mc = new GreenfootImage("McFront.png");
            setImage(Mc);
            break;
        }
        getImage().scale(ancho,alto);
    }
}
