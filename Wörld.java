import greenfoot.*;

public class Wörld extends World
{
    public Wörld()
    {    
        super(1400, 800, 1); 
        spawnPerson();
    }
    
    public void spawnPerson() {
        for (int i=0; i<30; i++) {
            addObject(new Person(false), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }
        addObject(new Person(true), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
    }
}