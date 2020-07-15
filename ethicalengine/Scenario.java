package ethicalengine;

/**
 * Name: SHIYANG CHEN
 * ID: 931880
 * UserName: SHIYANGC1
 * The class Scenario is made up of all relevant details and information
 * about one presented scenario.
 * The information includes the car's passengers and the pedestrians on the street
 * and if the person are crossing legally (green or red).
 */
public class Scenario {
    Character[] passengers;
    Character[] pedestrians;
    boolean isLegalCrossing;
    boolean hasYouInCar;
    boolean hasYouInLane;

    public Scenario() {
        this.isLegalCrossing = true;
        this.hasYouInCar = true;
        this.hasYouInLane = true;
    }

    /**
     * @param passengers Character[]
     * @param pedestrians Character[]
     * @param isLegalCrossing boolean
     */
    public Scenario(Character[] passengers, Character[] pedestrians, boolean isLegalCrossing) {
        this.passengers = passengers;
        this.pedestrians = pedestrians;
        this.isLegalCrossing = isLegalCrossing;
        this.hasYouInCar = false;
        this.hasYouInLane = false;
        for (int i = 0; i < passengers.length; i++) {
            if (passengers[i].isYou) {
                this.hasYouInCar = true;
            }
        }
        for (int i = 0; i < pedestrians.length; i++) {
            if (pedestrians[i].isYou) {
                this.hasYouInLane = true;
            }
        }
    }

    /**
     * @return hasYouInCar
     */
    public boolean hasYouInCar() {
        return hasYouInCar;
    }

    /**
     * @return hasYouInLane
     */
    public boolean hasYouInLane() {
        return hasYouInLane;
    }

    /**
     * @return passengers Character[]
     */
    public Character[] getPassengers() {
        return passengers;
    }

    /**
     * @return pedestrians Character[]
     */
    public Character[] getPedestrians() {
        return pedestrians;
    }

    /**
     * @return isLegalCrossing boolean
     */
    public boolean isLegalCrossing() {
        return isLegalCrossing;
    }

    /**
     * @param legalCrossing boolean
     */
    public void setLegalCrossing(boolean legalCrossing) {
        isLegalCrossing = legalCrossing;
    }

    /**
     * @return getPassengerCount
     */
    public int getPassengerCount() {
        return passengers.length;
    }

    /**
     * @return getPedestrianCount
     */
    public int getPedestrianCount() {
        return pedestrians.length;
    }

    public String toString() {
        String temp = "======================================\n" +
                "# Scenario\n" +
                "======================================\n" +
                "Legal Crossing: ";
        if (isLegalCrossing)
            temp = temp + "yes\n";
        else
            temp = temp + "no\n";
        temp = temp + "Passengers (" + getPassengerCount() + ")\n";
        for (int i = 0; i < getPassengerCount(); i++) {
            temp = temp + "- " + passengers[i].toString().toLowerCase() + "\n";
        }
        temp = temp + "Pedestrians (" + getPedestrianCount() + ")\n";
        for (int i = 0; i < getPedestrianCount(); i++) {
            if (i == getPedestrianCount() - 1) {
                temp = temp + "- " + pedestrians[i].toString().toLowerCase();
            } else {
                temp = temp + "- " + pedestrians[i].toString().toLowerCase() + "\n";
            }
        }
        return temp;
    }
}
