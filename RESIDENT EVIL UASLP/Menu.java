import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Es el menu del juego
 * 
 * @author Marco Antonio Pescina Ruiz
 * @version 
 */
public class Menu extends World
{
    private Boton boton1;//boton play
    private Boton boton2;//boton score
    private Boton boton3;//boton help
    private Boton boton5;//boton return
    private ScoreBoard score;//cuadro de score
    private Help h;//menu de ayuda
    private GreenfootSound soundtrack;//musica de fondo
    private int ban;//bandera de referencia para saber si regresa del menu de ayuda o es el inicio del juego
    /**
     * Constructor for objects of class Menu.
     * Se construlle con un tamaño de 1000 x 600.
     * Manda llamar el metodo prepare() para incluir los botones.
     */
    public Menu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1); 
        soundtrack = new GreenfootSound("Soundtrack.mp3");
        ban=0;
        texto t = new texto("Marco Antonio Pescina Ruiz \n Programacion orientada a objetos \n    semestre 2015",20);
        addObject(t, 870, 550);
        prepare();
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     * crea y agrega los botones del menu.
     */
    private void prepare()
    {
        boton1 = new Boton(1);
        addObject(boton1, 750, 270);

        boton2 = new Boton(2);
        addObject(boton2, 750, 380);

        boton3 = new Boton(3);
        addObject(boton3, 750, 480);

        //Greenfoot.start();
    }

    /**
     * Verifica que boton se preciono y realiza las acciones correspondientes
     */
    public void act()
    {
        soundtrack.playLoop();
        if(Greenfoot.mouseClicked(boton1))
        {
            int auxScore=0;
            World newWorld = new Nivel1();
            if (UserInfo.isStorageAvailable()) {
                UserInfo myInfo = UserInfo.getMyInfo();
                auxScore = myInfo.getScore();
            }
            if(auxScore<4000)
            {
                newWorld = new Nivel1();//crrea el nivel uno y lo aparece
            }
            if(auxScore>4000 && auxScore<7000)
            {
                 newWorld = new Nivel2();//crrea el nivel uno y lo aparece
            }
            if(auxScore>7000)
            {
                newWorld = new Nivel3();//crrea el nivel uno y lo aparece
            }
            soundtrack.stop();
            Greenfoot.setWorld(newWorld);
        }

        if(Greenfoot.mouseClicked(boton2))
        {
            if (UserInfo.isStorageAvailable()) 
            {
                UserInfo myInfo = UserInfo.getMyInfo();
                score= new ScoreBoard(500,500);
                removeObject(boton1);
                removeObject(boton2);
                removeObject(boton3);

                addObject(score,350,300);

                boton5 = new Boton(5);
                addObject(boton5, 820, 550);
            }
            else
            {
                texto t = new texto("Not Available",100); 
                addObject(t,500,300);
                Greenfoot.delay(100);
                removeObject(t);
            }
        }
        
        if(Greenfoot.mouseClicked(boton3))
        {
            removeObject(boton1);
            removeObject(boton2);
            removeObject(boton3);
            h = new Help();
            ban=1;
            addObject(h,500, 300);
            boton5 = new Boton(5);
            addObject(boton5, 820, 550);
        }
        
        if(Greenfoot.mouseClicked(boton5))
        {
            if(ban==1)
            {
                removeObject(h);
                ban=0;
            }
            removeObject(score);
            removeObject(boton5);
            prepare();
        }
    }
}
