import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Escenario es el mundo principal que prepara y que tiene las variables necesarias para cada nivel.
 * 
 * @author Marco Antonio Pescina Ruiz
 * @version 27 de junio del 2015
 */
public class Escenario extends World
{
    /**
     * timer de evento de aparicion Jackals
     */
    protected SimpleTimer timerEvento;
    /**
     * timer de evento de aparicion Jirahane
     */
    protected SimpleTimer timerEvento2;
    /**
     * timer de evento de aparicion Elite
     */
    protected SimpleTimer timerEvento3;
    /**
     * timer de evento de aparicion Ghost
     */
    protected SimpleTimer timerEvento4;
    /**
     * timer de evento de aparicion Banshee
     */
    protected SimpleTimer timerEvento5;
    /**
     * timer de evento de aparicion Pelican
     */
    protected SimpleTimer timerEvento6;
    /**
     * tiempo para el reloj
     */
    private SimpleTimer timerReloj;

    /**
     * contador de cantidad de vida
     */
    public Counter contVida;
    /**
     * contador de cantidad de municiones
     */
    public Counter contMunicion;
    /**
     * contador de cantidad e vida de la base
     */
    public Counter contVidaBase;
    /**
     * contador de cronometro de cuenta regresiva del nivel
     */
    public Counter reloj;
    /**
     * contador de vidas disponibles del jugador
     */
    public Counter contNumVidas;
    /**
     * Es el objeto base el cual el jugador tendra que defender.
     */
    public Base base;
    /**
     * tiempo en segundos restantes para ganar nivel
     */
    protected int relojT;
    /**
     * variable random para darle posicion en "y" a cada enemigo que se encuentra en el suelo.
     */
    protected int i;
    /**
     * variable random para darle posicion en "y" a cada enemigo que se encuentra en el aire.
     */
    protected int a;
    /**
     *  Nivel en el que se encuetnra el jugador.
     */
    protected int Nivel;
    
    
    //personajes del juego
    /**
     * Enemigo Jackals
     */
    protected Jackals j = new Jackals(2);
    /**
     * Enemigo Jirahane
     */
    protected Jirahane ji = new Jirahane(2);
    /**
     * Enemigo Elite
     */
    protected Elite el = new Elite(2);
    /**
     * Enemigo Ghost
     */
    protected Ghost g = new Ghost(2);
    /**
     * Enemigo Banshee
     */
    protected Banshee b = new Banshee(2);
    /**
     * Nave de municiones Pelincan
     */
    protected Pelican p = new Pelican(false);
    /**
     * Boton para regresar al menu principal
     */
    protected Boton bMenu = new Boton(4);
    /**
     * score del jugador
     */
    protected int newScore;
    
    /**
     * Constructor for objects of class Escenario.
     * Con un tamaño de 1000 x 600.
     * Crea e inicialisa las variables y objetos.
     * inicializa tiempos.
     */
    public Escenario()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1,false); 
        setPaintOrder(Counter.class,arma.class,plasma.class,plasma2.class,Base.class,Pelican.class);
        

        //creacion de timers
        timerEvento = new SimpleTimer();
        timerEvento2 = new SimpleTimer();
        timerEvento3 = new SimpleTimer();
        timerEvento4 = new SimpleTimer();
        timerEvento5 = new SimpleTimer();
        timerEvento6 = new SimpleTimer();
        timerReloj = new SimpleTimer();

        //partes principales del escenario
        MasterCheaf m = new MasterCheaf(3);
        addObject(m,200,450);
        base = new Base();
        addObject(base,86,485);
        Torre torre = new Torre();
        addObject(torre,26,285);

        //Contadores
        contVida = new Counter();
        addObject(contVida,50,20);
        contVida.setImagen("barraVida.png");
        contMunicion = new Counter();
        addObject(contMunicion,150,20);
        contMunicion.setImagen("barraMun.png");
        contVidaBase = new Counter();
        addObject(contVidaBase,250,20);
        contVidaBase.setImagen("barraVidaBase.png");
        contNumVidas = new Counter();
        addObject(contNumVidas,350,20);
        contNumVidas.setImagen("3vidas.png");
        reloj = new Counter();
        addObject(reloj,950,40);
        reloj.setImagen("reloj.png");

        //boton
        addObject(bMenu,800,30);

        //nivel
        Nivel=1;
        newScore=0;
        
        //inicio de tiempos
        timerEvento.mark();
        timerEvento2.mark();
        timerEvento3.mark();
        timerEvento4.mark();
        timerEvento5.mark();
        timerEvento6.mark();
        timerReloj.mark();
    }

    /**
     * Regresa el contador de numero de vidas disponibles
     * @return Counter
     */
    public Counter getContNumVidas()
    {
        return contNumVidas;
    }

    /**
     * Regresa el nivel en el que se encuentra el jugador
     * @return int
     */
    public int getNivel()
    {
        return Nivel;
    }

    /**
     * Regresa el contador de vida disponible
     * @return Counter
     */
    public Counter getContadorVida()
    {
        return contVida;
    }

    /**
     * Regresa el contador de Municiones
     * @return Counter
     */
    public Counter getContadorMun()
    {
        return contMunicion;
    }

    /**
     * Regresa el contador de vida disponible de la base
     * @return Counter
     */
    public Counter getContadorVidaBase()
    {
        return contVidaBase;
    }

    /**
     * Regresa la base del Nivel
     * @return Base
     */
    public Base getBase()
    {
        return base;
    }

    /**
     * Verifica si el tiempo del nivel se ha terminado y envia el mensaje de "Level Completed".
     * Dependiendo el nivel en el que se encuentre envia al jugador al siguiente nivel o al menu principal si ya termino el ultimo nivel.
     * Ademas guarda el score si el nivel se termino.
     */
    protected void tiempoRestante()
    {
        if(timerReloj.millisElapsed() > 1000 && relojT >0)//resta tiempo
        {
            relojT--;
            reloj.setValue(relojT);
            timerReloj.mark();
        }

        if(relojT == 0)
        {
            texto tx = new texto("Level Completed",100);
            addObject(tx,500,300);
            Greenfoot.delay(150);
            World newWorld;

            switch(Nivel)
            {
                case 1:
                newScore=base.getVidaBase();
                if (UserInfo.isStorageAvailable()) {
                    UserInfo myInfo = UserInfo.getMyInfo();
                    if (newScore > myInfo.getScore()) {
                        myInfo.setScore(newScore);
                        myInfo.store();  // write back to server
                    }
                }
                newWorld = new Nivel2();
                Greenfoot.setWorld(newWorld);
                Nivel=2;
                break;

                case 2:
                newScore+=base.getVidaBase();
                if (UserInfo.isStorageAvailable()) {
                    UserInfo myInfo = UserInfo.getMyInfo();
                    if (newScore > myInfo.getScore()) {
                        myInfo.setScore(newScore);
                        myInfo.store();  // write back to server
                    }
                }
                newWorld = new Nivel3();
                Greenfoot.setWorld(newWorld);
                Nivel=3;
                break;

                case 3:
                newScore+=base.getVidaBase();
                if (UserInfo.isStorageAvailable()) {
                    UserInfo myInfo = UserInfo.getMyInfo();
                    if (newScore > myInfo.getScore()) {
                        myInfo.setScore(newScore);
                        myInfo.store();  // write back to server
                    }
                }
                texto txf = new texto("Levels Completed",100);
                addObject(txf,500,450);
                Greenfoot.delay(200);
                newWorld = new Menu();
                Greenfoot.setWorld(newWorld);
                break;
            }
        }
    }

    /**
     * Verifica si se ha dado click en el boton de menu y envia al jugador al menu principal.
     */
    private void botMenu()
    {
        if(Greenfoot.mouseClicked(bMenu))
        {
            World newWorld = new Menu();
            Greenfoot.setWorld(newWorld);
        }
    }

    /**
     * Act.
     * Genera los random para las posiciones de los enemigos.
     * Crea los enemigos.
     * Llama al metodo botMenu() y tiempoRestante().
     */
    public void act()
    {
        Random r = new Random();
        i = r.nextInt(270) + 300; 
        a = r.nextInt(260) + 20;
        j = new Jackals(2);
        ji = new Jirahane(2);
        el = new Elite(2);
        g = new Ghost(2);
        b = new Banshee(2);

        botMenu();
        tiempoRestante();
    }
}
