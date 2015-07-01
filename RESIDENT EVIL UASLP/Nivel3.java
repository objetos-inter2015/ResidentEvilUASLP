import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * El Nivel3 es el ultimmo nivel del juego, en el aparecen todos los personajes creados para este juego.
 * 
 * @author Marco Antonio Pescina Ruiz
 * @version 15/06/2015
 */
public class Nivel3 extends Escenario
{
    /**
     * Constructor for objects of class Nivel3.
     * Si los score estan disponibles guarda el score que tienes para sumarlo si tierminas el nivel.
     */
    public Nivel3()
    {   
        relojT = 300;//tiempo del nivel
        reloj.setValue(relojT);
        Nivel=3;
        texto tNivel = new texto("Level 3",25);
        addObject(tNivel,955,90);
        if (UserInfo.isStorageAvailable()) 
        {
            UserInfo myInfo = UserInfo.getMyInfo();
            newScore=myInfo.getScore();
        }
        else
            newScore=0;
        
        arma ar = new arma();
        addObject(ar,235,555);
    }

    /**
     * Act.
     * Dependiendo de los tiempos crea y agrega los enemigos y la nave Pelican.
     */
    public void act()
    {
        super.act();
        p = new Pelican(true);
        if(timerEvento.millisElapsed() > 8000)//jackals
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

        if(timerEvento5.millisElapsed() > 30000)//Banshee
        {
            if(a>=0 && a<=50)
                b = new Banshee(75);
            if(a>=51 && a<=100)
                b = new Banshee(80);
            if(a>=101 && a<=150)
                b = new Banshee(85);
            if(a>=151 && a<=200)
                b = new Banshee(90);
            if(a>=201 && a<=250)
                b = new Banshee(95);
            if(a>=251 && a<=300)
                b = new Banshee(100);
            addObject(b,1030,a);
            timerEvento5.mark();
        }

        if(timerEvento6.millisElapsed() > 100000)//Pelican
        {
            addObject(p,-100,150);
            timerEvento6.mark();
        }
    }
}
