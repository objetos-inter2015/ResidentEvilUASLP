import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * El Nivel2 es el nivel intermedio del juego, aparecen todos los enemigos terrestres.
 * 
 * @author Marco Antonio Pescina Ruiz
 * @version 15/06/2015
 */
public class Nivel2 extends Escenario
{
    /**
     * Constructor for objects of class Nivel2.
     * Si los score estan disponibles guarda el score que tienes para sumarlo si tierminas el nivel.
     */
    public Nivel2()
    {    
        relojT = 200;//tiempo del nivel
        reloj.setValue(relojT);
        Nivel=2;
        texto tNivel = new texto("Level 2",25);
        addObject(tNivel,955,90);
        if (UserInfo.isStorageAvailable()) 
        {
            UserInfo myInfo = UserInfo.getMyInfo();
            newScore=myInfo.getScore();
        }
        else
            newScore=0;
    }

    /**
     * Act.
     * Dependiendo de los tiempos crea y agrega los enemigos y la nave Pelican.
     */
    public void act()
    {
        super.act();
        p = new Pelican(false);
        if(timerEvento.millisElapsed() > 6000)//jackals
        {
            if(i>=300 && i<=350)
                j = new Jackals(40);
            if(i>=351 && i<=400)
                j = new Jackals(45);
            if(i>=401 && i<=450)
                j = new Jackals(50);
            if(i>=451 && i<=500)
                j = new Jackals(55);
            if(i>=501 && i<=550)
                j = new Jackals(60);
            if(i>=551 && i<=600)
                j = new Jackals(65);
            addObject(j,1030,i);
            timerEvento.mark();
        }

        if(timerEvento2.millisElapsed() > 10000)//Jirahane
        {
            if(i>=300 && i<=350)
                ji = new Jirahane(45);
            if(i>=351 && i<=400)
                ji = new Jirahane(50);
            if(i>=401 && i<=450)
                ji = new Jirahane(55);
            if(i>=451 && i<=500)
                ji = new Jirahane(60);
            if(i>=501 && i<=550)
                ji = new Jirahane(65);
            if(i>=551 && i<=600)
                ji = new Jirahane(70);
            addObject(ji,1030,i);
            timerEvento2.mark();
        }

        if(timerEvento3.millisElapsed() > 20000)//Elite
        {
            if(i>=300 && i<=350)
                el = new Elite(55);
            if(i>=351 && i<=400)
                el = new Elite(60);
            if(i>=401 && i<=450)
                el = new Elite(65);
            if(i>=451 && i<=500)
                el = new Elite(70);
            if(i>=501 && i<=550)
                el = new Elite(75);
            if(i>=551 && i<=600)
                el = new Elite(80);
            addObject(el,1030,i);
            timerEvento3.mark();
        }

        if(timerEvento4.millisElapsed() > 25000)//Ghost
        {
            if(i>=300 && i<=350)
                g = new Ghost(75);
            if(i>=351 && i<=400)
                g = new Ghost(80);
            if(i>=401 && i<=450)
                g = new Ghost(85);
            if(i>=451 && i<=500)
                g = new Ghost(90);
            if(i>=501 && i<=550)
                g = new Ghost(95);
            if(i>=551 && i<=600)
                g = new Ghost(100);
            addObject(g,1030,i);
            timerEvento4.mark();
        }

        if(timerEvento6.millisElapsed() > 80000)//Pelican
        {
            addObject(p,-100,150);
            timerEvento6.mark();
        }
    }
}
