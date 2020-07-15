package ethicalengine;

/**
 * Name: SHIYANG CHEN
 * ID: 931880
 * UserName: SHIYANGC1
 * The  class Person represents a human in the scenario.
 * And it contains some basic character of persons in the class,
 * such as age, profession, pregnant, if the person is you etc..
 */
public class Person extends Character {
    private Profession profession;
    private boolean isPregnant;

    public Person() {
    }

    /**
     * @param age age
     * @param gender gender
     * @param bodyType bodyType
     */
    public Person(int age, Gender gender, BodyType bodyType) {
        super(age, gender, bodyType);
    }

    /**
     * @param age age
     * @param profession profession
     * @param gender gender
     * @param bodyType bodyType
     * @param isPregnant isPregnant
     */
    public Person(int age, Profession profession, Gender gender, BodyType bodyType,
                  boolean isPregnant) {
        super(age, gender, bodyType);
        if (age >= 17 && age <= 68)
            this.profession = profession;
        else
            this.profession = Profession.NONE;
        this.isPregnant = isPregnant;
    }

    /**
     * @param age age
     * @param gender gender
     * @param bodyType bodyType
     * @param profession profession
     * @param isPregnant isPregnant
     * @param isYou isYou
     */
    public Person(int age, Gender gender, BodyType bodyType, Profession profession,
                  boolean isPregnant, boolean isYou) {
        super(age, gender, bodyType);
        if (age >= 17 && age <= 68)
            this.profession = profession;
        else
            this.profession = Profession.NONE;
        this.isPregnant = isPregnant;
        this.isYou = isYou;
    }

    /**
     * @param person person
     */
    public Person(Person person) {
        super(person.getAge(), person.getGender(), person.getBodyType());
        this.isPregnant = person.isPregnant;
        this.profession = person.profession;
    }

    /**
     * @return AgeCategory
     */
    public AgeCategory getAgeCategory() {
        int age = this.getAge();
        if (age >= 0 && age <= 4) {
            return AgeCategory.BABY;
        } else if (age >= 5 && age <= 16) {
            return AgeCategory.CHILD;
        } else if (age >= 17 && age <= 68) {
            return AgeCategory.ADULT;
        } else {
            return AgeCategory.SENIOR;
        }
    }

    /**
     * @return Profession
     */
    public Profession getProfession() {
        int age = this.getAge();
        if (age < 17) {
            return Profession.NONE;
        }
        if (age > 68) {
            return Profession.RETIREMENT;
        }
        return this.profession;
    }

    /**
     * @return isPregnant
     */
    public boolean isPregnant() {
        if (this.getGender() != Gender.FEMALE) {
            return false;
        } else return isPregnant;
    }

    /**
     * @param pregnant pregnant
     */
    public void setPregnant(boolean pregnant) {
        if (this.getGender() == Gender.FEMALE) {
            this.isPregnant = pregnant;
        } else
            this.isPregnant = false;
    }

    /**
     * @return isYou
     */
    public boolean isYou() {
        return isYou;
    }

    /**
     * @param isYou isYou
     */
    public void setAsYou(boolean isYou) {
        this.isYou = isYou;
    }

    /**
     * @return String
     */
    public String toString() {
        String temp = "";
        if (isYou)
            temp += "you ";
        temp = temp + getBodyType() + " " + getAgeCategory();
        if (getAge() >= 17 && getAge() <= 68&&getAgeCategory()!=AgeCategory.BABY)
            temp = temp + " " + getProfession();
        temp = temp + " " + getGender();
        if (getGender() == Gender.FEMALE && isPregnant == true&&getAgeCategory()!=AgeCategory.BABY)
            temp = temp + " " + "pregnant";
        return temp.toLowerCase();
    }

    public enum AgeCategory {
        BABY, CHILD, ADULT, SENIOR;
        /**
         * @param type type
         * @return boolean
         */
        public static boolean contains(String type) {
            for (AgeCategory ageCategory: AgeCategory.values()) {
                if (ageCategory.name().equals(type.toUpperCase())) {
                    return true;
                }
            }
            return false;
        }
    }

    public enum Profession {
        DOCTOR, CEO, CRIMINAL, HOMELESS, UNEMPLOYED, UNKNOWN, NONE, RETIREMENT, LAYER, WAITER, POLICE;
        /**
         * @param type type
         * @return boolean
         */
        public static boolean contains(String type) {
            for (Profession profession: Profession.values()) {
                if (profession.name().equals(type.toUpperCase())) {
                    return true;
                }
            }
            return false;
        }
    }

}