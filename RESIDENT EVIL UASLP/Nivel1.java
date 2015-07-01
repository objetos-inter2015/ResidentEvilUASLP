import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * El Nivel1 es el nivel basico del juego solo aparecen algunos de los enemigos terrestres.
 * 
 * @author Marco Antonio Pescina Ruiz
 * @version 27/junio/2015
 */
public class Nivel1 extends Escenario
{
    /**
     * Constructor for objects of class Nivel1.
     * 
     */
    public Nivel1()
    {   
        relojT = 150;//tiempo del nivel
        reloj.setValue(relojT);
        texto tNivel = new texto("Level 1",25);
        addObject(tNivel,955,90);
    }

    /**
     * Act.
     * Dependiendo de los tiempos crea y agrega los enemigos y la nave Pelican.
     */
    public void act()
    {
        super.act();
        p = new Pelican(false);
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

        if(timerEvento6.millisElapsed() > 50000)//Pelican
        {
            addObject(p,-100,150);
            timerEvento6.mark();
        }
    }
}
