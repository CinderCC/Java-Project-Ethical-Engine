package ethicalengine;

import java.util.Random;


/**
 * Name: SHIYANG CHEN
 * ID: 931880
 * UserName: SHIYANGC1
 * The class ScenarioGenerator can be used to generate a variety
 * of scenarios. It generates elements with random ways to guarantee
 * a balanced set of scenarios.
 */
public class ScenarioGenerator {
    long seed;
    int passengerCountMinimum;
    int passengerCountMaximum;
    int pedestrianCountMinimum;
    int pedestrianCountMaximum;
    Random r;
    Scenario scenario;

    public ScenarioGenerator() {
        seed = System.nanoTime();
        r= new Random(seed);
        passengerCountMinimum = 1;
        passengerCountMaximum = 5;
        pedestrianCountMinimum = 1;
        pedestrianCountMaximum = 5;
    }

    /**
     * @param seed seed
     */
    public ScenarioGenerator(long seed) {
        this.seed = seed;
        r= new Random(seed);
        passengerCountMinimum = 1;
        passengerCountMaximum = 5;
        pedestrianCountMinimum = 1;
        pedestrianCountMaximum = 5;
    }

    /**
     * @param seed seed
     * @param passengerCountMinimum passengerCountMinimum
     * @param passengerCountMaximum passengerCountMaximum
     * @param pedestrianCountMinimum pedestrianCountMinimum
     * @param pedestrianCountMaximum pedestrianCountMaximum
     */
    public ScenarioGenerator(long seed, int passengerCountMinimum, int passengerCountMaximum,
                             int pedestrianCountMinimum, int pedestrianCountMaximum) {
        this.seed = seed;
        r= new Random(seed);
        this.passengerCountMinimum = passengerCountMinimum;
        this.passengerCountMaximum = passengerCountMaximum;
        this.pedestrianCountMinimum = pedestrianCountMinimum;
        this.pedestrianCountMaximum = pedestrianCountMaximum;
    }

    /**
     * @param  min min
     */
    public void setPassengerCountMin(int min) {
        this.passengerCountMinimum = passengerCountMinimum;
    }

    /**
     * @param  max max
     */
    public void setPassengerCountMax(int max) {
        this.passengerCountMaximum = passengerCountMaximum;
    }

    /**
     * @param  min min
     */
    public void setPedestrianCountMin(int min) {
        this.pedestrianCountMinimum = pedestrianCountMinimum;
    }

    /**
     * @param  max max
     */
    public void setPedestrianCountMax(int max) {
        this.pedestrianCountMaximum = pedestrianCountMaximum;
    }

    /**
     * @param result result
     * @param scenario1Str scenario1Str
     * @param scenario2Str scenario2Str
     */
    public void assertNotEquals(String result, String scenario1Str, String scenario2Str){
        result.equals(scenario1Str);
        result.equals(scenario2Str);
    }

    /**
     * @return random person
     */
    public Person getRandomPerson() {
        int age = r.nextInt(100);
        Person.Profession profession = Person.Profession.values()[r.nextInt(Person.Profession.values().length)];
        Character.Gender gender = Character.Gender.values()[r.nextInt(Character.Gender.values().length)];
        Character.BodyType bodyType = Character.BodyType.values()[r.nextInt(Character.BodyType.values().length)];
        boolean isPregnant;
        if (gender == Character.Gender.FEMALE) {
            isPregnant = r.nextBoolean();
        } else {
            isPregnant = false;
        }
        Person temp = new Person(age, gender, bodyType, profession, isPregnant,false);
        return temp;
    }

    /**
     * @return  random animal
     */
    public Animal getRandomAnimal() {
        int age = r.nextInt(5);
        Character.Gender gender = Character.Gender.values()[r.nextInt(Character.Gender.values().length)];
        boolean isPregnant;
        if (gender == Character.Gender.FEMALE) {
            isPregnant = r.nextBoolean();
        } else {
            isPregnant = false;
        }
        boolean isPet =false;
        String species="";
        if(age%2==0){
            species= Animal.Pet.values()[r.nextInt(Animal.Pet.values().length)].toString();
            isPet=true;
        }else{
            species="notAPet";
        }
        boolean isYou =false;
       Animal temp=new  Animal(age,  gender, species, isPet, isPregnant, isYou);
        return temp;
    }

    /**
     * @return  Scenario
     */
    public Scenario generate() {
        int passengerNum = r.nextInt(passengerCountMaximum - passengerCountMinimum)
                + passengerCountMinimum;
        int pedestrianNum = r.nextInt(pedestrianCountMaximum - pedestrianCountMinimum)
                + pedestrianCountMinimum;
        Character[] passengers = new Character[passengerNum];
        Character[] pedestrians = new Character[pedestrianNum];
        Person tempPassenger = getRandomPerson();
        tempPassenger.setAsYou(r.nextBoolean());
        boolean flagIsYou = false;
        if (tempPassenger.isYou())
            flagIsYou = true;
        passengers[0] = tempPassenger;
        for (int i = 1; i < passengerNum; i++) {
            String what = Passenger.values()[r.nextInt(Passenger.values().length)].toString();
            Character temp = getInstance(what);
            temp.isYou=false;
            passengers[i] = temp;
        }
        //add an pedestrian
        Person tempPedestrian = getRandomPerson();
        if(flagIsYou)
            tempPedestrian.setAsYou(false);
        else{
            tempPedestrian.setAsYou(r.nextBoolean());
        }
        pedestrians[0] = tempPedestrian;
        for (int i = 1; i < pedestrianNum; i++) {
            //person or animal
            String what = Passenger.values()[r.nextInt(Passenger.values().length)].toString();
            Character temp = getInstance(what);
            temp.isYou=false;
            pedestrians[i] = temp;
        }
        //the traffic light is red or green randomly
        boolean isLegalCrossing = r.nextBoolean();
        scenario = new Scenario(passengers, pedestrians, isLegalCrossing);
        return scenario;
    }

    /**
     * @param what what
     * @return  Character
     */
    public Character getInstance(String what) {
        if("PERSON".equals(what)){
            Person temp = getRandomPerson();
            temp.setAsYou(false);
            return temp;
        }else{
            return getRandomAnimal();
        }
    }

    /**
     * @return  Scenario
     */
    public Scenario getScenario() {
        return scenario;
    }
}

enum Passenger {
    PERSON, ANIMAL
}