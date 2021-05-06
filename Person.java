import greenfoot.*;

public class Person extends Actor
{
    private int age;
    private int infectedTicker;
    private boolean infected;
    private boolean ill;
    private boolean immune;
    private boolean dead;

    public Person(boolean infected) {
        age = Greenfoot.getRandomNumber(99);
        this.infected = infected;
        ill = false;
        immune = false;
        dead = false;
        if (infected) {
            setImage("person_yellow.png");
        } else {
            setImage("person_green.png");
        }
    }

    public void act() 
    {
        go();
        infects();
        becomesIll();
        dies();
    }    

    public void go() {
        if (!dead) {
            int m = Greenfoot.getRandomNumber(10);
            int n = Greenfoot.getRandomNumber(100);
            if (m <= 2) {
                if (n > 50) {
                    turn(n-50);
                } else {
                    turn(-n);
                }
            }
            //je älter desto langsamer
            move(-(age/20)+5);
            if (isAtEdge()) {
                turn(180);
            }
        }
    }

    //infiziert nicht infizierte Personen
    public void infects() {
        if (infected) {
            if (isTouching(Person.class)) {
                Person person = (Person) getOneIntersectingObject(Actor.class);
                if(!person.infected && !person.immune && !person.dead) {
                    person.infected = true;
                    person.setImage("person_yellow.png");
                }
            }
        }
    }

    //lässt infizierte Personen erkranken und wieder genesen
    public void becomesIll() {
        if (infected) {
            int n = Greenfoot.getRandomNumber(999);
            int m = Greenfoot.getRandomNumber(9);
            //erkranken
            if (n < calculateIllness()) {
                ill = true;
                setImage("person_red.png");
            }

            //genesen
            if (n < calculateImmunity()) {
                isImmune();
                setImage("person_blue.png");
            }
            
            infectedTicker++;
            if (infectedTicker > 5000 && m < 5) {
                isDead();
                setImage("person_black.png");
            } 
            if (infectedTicker > 5000 && m > 5) {
                isImmune();
                setImage("person_blue.png");
            }
        }
    }

    //lässt erkrankte Personen sterben
    public void dies() {
        if (ill) {
            int i = Greenfoot.getRandomNumber(999);

            if (i < 1) {
                dead = true;
                ill = false;
                infected = false;
                setImage("person_black.png");
            }
        }
    }

    public void isImmune() {
        ill = false;
        infected = false;
        immune = true;
    }
    
    public void isDead() {
        ill = false;
        infected = false;
        dead = true;
    }

    //ältere werden schneller krank
    public double calculateIllness() {
        return 0.05*age;
    }

    //jüngere werden schneller immun
    public double calculateImmunity() {
        return -(0.01*age)+0.5;
    }

    //ältere sterben schneller
    public double calculateDeadrate() {
        return 0.05*age;
    }
}