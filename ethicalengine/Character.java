package ethicalengine;

/**
 * Name: SHIYANG CHEN
 * ID: 931880
 * UserName: SHIYANGC1
 * The class Character is an Abstract Class and from which all character types
 * inherit. And there are two enum in it which are Gender and BodyType to
 * illustrate the basic elements of animals and persons.
 */
public abstract class Character {
    private int age;
    private Gender gender;
    private BodyType bodyType;
    protected boolean isYou;

    //default
    public Character() {
        this.age = 0;
        this.gender = Gender.UNKNOWN;
        this.bodyType = BodyType.UNSPECIFIED;
    }

    /**
     * @param age age
     * @param gender gender
     */
    public Character(int age, Gender gender) {
        this.age = age;
        this.gender = gender;
    }

    /**
     * @param age age
     * @param gender gender
     * @param bodyType bodyType
     */
    public Character(int age, Gender gender, BodyType bodyType) {
        this.age = age;
        this.gender = gender;
        this.bodyType = bodyType;
    }

    /**
     * @param c Character
     */
    public Character(Character c) {
        this.age = c.getAge();
        this.gender = c.getGender();
        this.bodyType = c.getBodyType();
        this.isYou = c.isYou();

    }

    /**
     * @return age
     */
    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    /**
     * @return bodyType
     */
    public BodyType getBodyType() {
        return bodyType;
    }

    /**
     * @param  age age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @param gender gender
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * @param  bodyType bodyType
     */
    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    /**
     * @return isYou
     */
    public boolean isYou() {
        return isYou;
    }

    /**
     * @param  you you
     */
    public void setYou(boolean you) {
        isYou = you;
    }

    /**
     * @return isPregnant
     */
    public abstract boolean isPregnant();

    public enum Gender {
        FEMALE, MALE, UNKNOWN;
        /**
         * @param type type
         * @return boolean
         */
        public static boolean contains(String type) {
            for (Gender gender : Gender.values()) {
                if (gender.name().equals(type.toUpperCase())) {
                    return true;
                }
            }
            return false;
        }
    }

    public enum BodyType {
        AVERAGE, ATHLETIC, OVERWEIGHT, UNSPECIFIED;
        /**
         * @param type type
         * @return boolean
         */
        public static boolean contains(String type) {
            for (BodyType bodyType: BodyType.values()) {
                if (bodyType.name().equals(type.toUpperCase())) {
                    return true;
                }
            }
            return false;
        }
    }
}
