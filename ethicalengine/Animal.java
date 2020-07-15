package ethicalengine;

/**
 * Name: SHIYANG CHEN
 * ID: 931880
 * UserName: SHIYANGC1
 * The class Animal represents animals in the scenario.
 * Like class Person, it contains some unique characters of animal,
 * such as species, if it is pet.
 */
public class Animal extends Character {
    private String species;
    public boolean isPet;
    private boolean isPregnant;
    public Animal otherAnimal;

    public Animal() {
        this.isYou = false;
        if (species != null && Pet.contains(species.toUpperCase()))
            isPet = true;
        else
            isPet = false;
    }

    /**
     * @param otherAnimal otherAnimal
     */
    public Animal(Animal otherAnimal) {
        if (otherAnimal != null){
            this.otherAnimal = otherAnimal;
        }else {
            this.otherAnimal = new Animal();
        }
    }

    /**
     * @param species species
     */
    public Animal(String species) {
        this.species = species;
        /*this.isYou = false;
        if (Pet.contains(species.toUpperCase()))
            isPet = true;
        else
            isPet = false;*/
    }

    /**
     * @param age age
     * @param gender gender
     * @param bodyType bodyType
     * @param species species
     * @param isPet isPet
     */
    public Animal(int age, Gender gender, BodyType bodyType, String species, boolean isPet) {
        super(age, gender, bodyType);
        this.species = species;
        this.isPet = isPet;
        this.isYou = false;
    }

    /**
     * @param age age
     * @param gender gender
     * @param species species
     * @param isPet isPet
     * @param isPregnant isPregnant
     * @param isYou isYou
     */
    public Animal(int age, Gender gender, String species, boolean isPet, boolean isPregnant, boolean isYou) {
        super(age, gender);
        this.species = species;
        this.isPet = isPet;
        this.isPregnant = isPregnant;
        this.isYou = false;
    }

    /**
     * @param age age
     * @param gender gender
     * @param bodyType bodyType
     * @param species species
     * @param isPet isPet
     * @param isPregnant isPregnant
     */
    public Animal(int age, Gender gender, BodyType bodyType, String species, boolean isPet, boolean isPregnant) {
        super(age, gender, bodyType);
        this.species = species;
        this.isPet = isPet;
        this.isPregnant = isPregnant;
        this.isYou = false;
    }

    /**
     * @return boolean
     */
    public boolean isPregnant() {
        return isPregnant;
    }

    /**
     * @param pregnant pregnant
     */
    public void setPregnant(boolean pregnant) {
        isPregnant = pregnant;
    }

    /**
     * @return boolean
     */
    public boolean isYou() {
        return isYou;
    }

    /**
     * @param you you
     */
    public void setYou(boolean you) {
        isYou = false;
    }

    /**
     * @return String
     */
    public String getSpecies() {
        return species;
    }

    /**
     * @param species species
     */
    public void setSpecies(String species) {
        this.species = species;
    }

    /**
     * @return boolean
     */
    public boolean isPet() {
        return isPet;
    }

    /**
     * @param pet pet
     */
    public void setPet(boolean pet) {
        isPet = pet;
    }

    /**
     * @return Animal
     */
    public Animal getOtherAnimal() {
        if (otherAnimal != null){
            return otherAnimal;
        }else {
            return new Animal();
        }
    }

    /**
     * @param otherAnimal otherAnimal
     */
    public void setOtherAnimal(Animal otherAnimal) {
        if (otherAnimal != null) {
            this.otherAnimal = otherAnimal;
        } else {
            this.otherAnimal = new Animal();
        }
    }

    /**
     * @return String
     */
    public String toString() {
        if(getSpecies() != null){
            String temp = getSpecies().toLowerCase();
            if (isPet())
                temp = temp + " is pet";
            return temp;
        }else{
            return "";
        }
    }

    public enum Pet {
        CAT, DOG, RABBIT, RAT, FISH;
        /**
         * @param type type
         * @return boolean
         */
        public static boolean contains(String type) {
            for (Pet pet : Pet.values()) {
                if (pet.name().equals(type.toUpperCase())) {
                    return true;
                }
            }
            return false;
        }
    }
}