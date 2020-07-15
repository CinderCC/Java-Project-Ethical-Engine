
import ethicalengine.*;
import ethicalengine.Character;

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Name: SHIYANG CHEN
 * ID: 931880
 * UserName: SHIYANGC1
 * The class Audit is to creat an assigned number of random scenarios
 * and then let the EthicalEngine to decide on each one.
 * After that it summarizes the statistic of results to calculate the surviving ratio.
 * Also, it could simulate a variety of scenarios.
 */
public class Audit {
    String auditType;
    AuditType auditType1;
    static int runs;
    double personNum, personNumSurvive;
    double animalNum, animalNumSurvive;
    double doctorNum, doctorNumSurvive;
    double ceoNum, ceoNumSurvive;
    double criminalNum, criminalNumSurvive;
    double homelessNum, homelessNumSurvive;
    double unemployedNumSurvive, unemployedNum;
    double unknownNumSurvive, unknownNum;
    double noneNumSurvive, noneNum;
    double retirementNumSurvive, retirementNum;
    double layerNumSurvive, layerNum;
    double babyNumSurvive, babyNum;
    double childNumSurvive, childNum;
    double adultNumSurvive, adultNum;
    double seniorNumSurvive, seniorNum;
    double maleNumSurvive, maleNum;
    double femaleNumSurvive, femaleNum;
    double ageSurvive, allNum;
    double greenNum, greenNumSurvive;
    double redNum, redNumSurvive;
    double averageNum, averageNumSurvive;
    double arhleticNum, arhleticNumSurvive;
    double overweightNum, overweightNumSurvive;
    double unspecifiedNum, unspecifiedNumSurvive;
    double pregnantNum, pregnantNumSurvive;
    double dogNum, dogNumSurvive;
    double catNum, catNumSurvive;
    double birdNum, birdNumSurvive;
    double ferretNum, ferretNumSurvive;
    double petNum, petNumSurvive;
    double youNum, youNumSurvive;
    Scenario[] scenarios;
    String algorithmOrUser;

    /**
     * @return String
     */
    public String getAlgorithmOrUser() {
        return algorithmOrUser;
    }

    /**
     * @param algorithmOrUser algorithmOrUser
     */
    public void setAlgorithmOrUser(String algorithmOrUser) {
        this.algorithmOrUser = algorithmOrUser;
    }

    public Audit() {
        this.auditType1 = AuditType.UNSPECIFID;
    }

    /**
     * @param scenarios Scenario[]
     */
    public Audit(Scenario[] scenarios) {
        this.scenarios = scenarios;
        this.auditType1 = AuditType.UNSPECIFID;
    }

    /**
     * @param scenario scenario
     * @param survivorDecision survivorDecision
     */
    public void run(Scenario scenario, EthicalEngine.Decision survivorDecision) {
        runs++;
        greenOrRed(scenario);
        //passengers
        Character[] passengersTemp = scenario.getPassengers();
        setAttributes(passengersTemp);
        Character[] pedestriansTemp = scenario.getPedestrians();
        setAttributes(pedestriansTemp);
        //print Scenario
        //System.out.println(scenarioTemp.toString());
        //survivors
        Character[] survivors = (survivorDecision == EthicalEngine.Decision.PASSENGERS ?
                scenario.getPassengers() : scenario.getPedestrians());
        //set attributes by survivors
        greenOrRedSurvive(scenario, survivors.length);
        setAttributesSurvive(survivors);
    }

    public void run() {
        runs = scenarios.length;
        for (int i = 0; i < scenarios.length; i++) {
            scenarios[i] = scenarios[i];
            greenOrRed(scenarios[i]);
            //passengers
            Character[] passengersTemp = scenarios[i].getPassengers();
            setAttributes(passengersTemp);
            Character[] pedestriansTemp = scenarios[i].getPedestrians();
            setAttributes(pedestriansTemp);
            //print Scenario
            //System.out.println(scenarioTemp.toString());
            //survivors
            EthicalEngine.Decision survivorDecision = EthicalEngine.decide(scenarios[i]);
            Character[] survivors = (survivorDecision == EthicalEngine.Decision.PASSENGERS ?
                    scenarios[i].getPassengers() : scenarios[i].getPedestrians());
            //set attributes by survivors
            greenOrRedSurvive(scenarios[i], survivors.length);
            setAttributesSurvive(survivors);
        }
    }

    /**
     * @param runs runs
     */
    public void run(int runs) {
        this.runs += runs;
        Scenario[] scenarios = new Scenario[runs];
        for (int i = 0; i < this.runs; i++) {
            ScenarioGenerator scenarioGenerator = new ScenarioGenerator(System.nanoTime(),
                    2, 5, 2, 5);
            scenarioGenerator.generate();
            Scenario scenarioTemp = scenarioGenerator.getScenario();
            scenarios[i] = scenarioTemp;
            greenOrRed(scenarioTemp);
            //passengers
            Character[] passengersTemp = scenarioTemp.getPassengers();
            setAttributes(passengersTemp);
            Character[] pedestriansTemp = scenarioTemp.getPedestrians();
            setAttributes(pedestriansTemp);
            //print Scenario
            //System.out.println(scenarioTemp.toString());
            //survivors
            EthicalEngine.Decision survivorDecision = EthicalEngine.decide(scenarioTemp);
            Character[] survivors = (survivorDecision == EthicalEngine.Decision.PASSENGERS ?
                    scenarioTemp.getPassengers() : scenarioTemp.getPedestrians());
            //set attributes by survivors
            greenOrRedSurvive(scenarioTemp, survivors.length);
            setAttributesSurvive(survivors);

        }
        this.scenarios = scenarios;
    }

    /**
     * @param auditType auditType
     */
    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    /**
     * @return auditType
     */
    public String getAuditType() {
        return auditType;
    }


    public String toString() {
        String temp = "";
        if ("user".equals(algorithmOrUser)) {
            temp += "======================================\n" +
                    "# User Audit\n" +
                    "======================================\n";
            temp += "- % SAVED AFTER " + runs + " RUNS\n";
        } else if ("algorithm".equals(algorithmOrUser)) {
            temp += "======================================\n" +
                    "# Algorithm Audit\n" +
                    "======================================\n";
            temp += "- % SAVED AFTER " + runs + " RUNS\n";
        } else {
            temp += "======================================\n" +
                    "# Unspecified Audit\n" +
                    "======================================\n";
            temp += "- % SAVED AFTER " + runs + " RUNS\n";
        }
        Map<String, Double> map = new TreeMap<String, Double>();
        if (unemployedNum != 0)
            map.put("unemployed", addNum((int) (unemployedNumSurvive * 10 / unemployedNum) / 10.0));
        if (doctorNum != 0)
            map.put("doctor", addNum((int) (doctorNumSurvive * 10 / doctorNum) / 10.0));
        if (ceoNum != 0)
            map.put("ceo", addNum((int) (ceoNumSurvive * 10 / ceoNum) / 10.0));
        if (seniorNum != 0)
            map.put("senior", addNum((int) (seniorNumSurvive * 10 / seniorNum) / 10.0));
        if (pregnantNum != 0)
            map.put("pregnant", addNum((int) (pregnantNumSurvive * 10 / pregnantNum) / 10.0));
        if (femaleNum != 0)
            map.put("female", addNum((int) (femaleNumSurvive * 10 / femaleNum) / 10.0));
        if (averageNum != 0)
            map.put("average", addNum((int) (averageNumSurvive * 10 / averageNum) / 10.0));
        if (arhleticNum != 0)
            map.put("athletic", addNum((int) (arhleticNumSurvive * 10 / arhleticNum) / 10.0));
        if (overweightNum != 0)
            map.put("overweight", addNum((int) (overweightNumSurvive * 10 / overweightNum) / 10.0));
        if (babyNum != 0)
            map.put("baby", addNum((int) (babyNumSurvive * 10 / babyNum) / 10.0));
        if (adultNum != 0)
            map.put("adult", addNum((int) (adultNumSurvive * 10 / adultNum) / 10.0));
        if (youNum != 0)
            map.put("you", addNum((int) (youNumSurvive * 10 / youNum) / 10.0));
        if (unknownNum != 0)
            map.put("unknown", addNum((int) (unknownNumSurvive * 10 / unknownNum) / 10.0));
        if (personNum != 0)
            map.put("person", addNum((int) (personNumSurvive * 10 / personNum) / 10.0));
        if (criminalNum != 0)
            map.put("criminal", addNum((int) (criminalNumSurvive * 10 / criminalNum) / 10.0));
        if (homelessNum != 0)
            map.put("homeless", addNum((int) (homelessNumSurvive * 10 / homelessNum) / 10.0));
        if (maleNum != 0)
            map.put("male", addNum((int) (maleNumSurvive * 10 / maleNum) / 10.0));
        if (greenNum != 0)
            map.put("green", addNum((int) (greenNumSurvive * 10 / greenNum) / 10.0));
        if (redNum != 0)
            map.put("red", addNum((int) (redNumSurvive * 10 / redNum) / 10.0));
        if (childNum != 0)
            map.put("child", addNum((int) (childNumSurvive * 10 / childNum) / 10.0));
        if (animalNum != 0)
            map.put("animal", addNum((int) (animalNumSurvive * 10 / animalNum) / 10.0));
        if (dogNum != 0)
            map.put("dog", addNum((int) (dogNumSurvive * 10 / dogNum) / 10.0));
        if (catNum != 0)
            map.put("cat", addNum((int) (catNumSurvive * 10 / catNum) / 10.0));
        if (birdNum != 0)
            map.put("bird", addNum((int) (birdNumSurvive * 10 / birdNum) / 10.0));
        if (ferretNum != 0)
            map.put("ferret", addNum((int) (ferretNumSurvive * 10 / ferretNum) / 10.0));
        if (petNum != 0)
            map.put("pet", addNum((int) (petNumSurvive * 10 / petNum) / 10.0));

        //set map.entrySet() into list
        List<Map.Entry<String, Double>> list = new ArrayList<Map.Entry<String, Double>>(map.entrySet());
        //sort by Collections.sort
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            //in descending order
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        for (Map.Entry<String, Double> mapping : list) {
            temp += mapping.getKey() + ": " + mapping.getValue() + "\n";
        }
        temp += "--\n";
        //int ageSurvive, allNum;
        temp += "average age: " + (int) (ageSurvive * 10 / allNum) / 10.0 + "\n";
        return temp;
    }

    public void printStatistic() {
        System.out.print(toString());
    }

    /**
     * @param filepath filepath
     * @throws IOException IOException
     */
    public void printToFile(String filepath) throws IOException {
        File file = new File(filepath);
        //show the results of your audit on the console-line
        //save your audit results to results.log
        if (file.exists() && file.isFile()) {
            FileWriter fr = new FileWriter(filepath, true);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(toString());
            br.flush();
            fr.close();
        } else if (file.isDirectory()) {
            file.mkdirs();
            File temp = new File(filepath + "\\results.log");
            temp.createNewFile();
            FileWriter fr = new FileWriter(temp);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(toString());
            br.flush();
            fr.close();
        } else {
            file.createNewFile();
            FileWriter fr = new FileWriter(filepath, true);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(toString());
            br.flush();
            fr.close();
            return;
        }
    }

    /**
     * @param filepath filepath
     * @throws IOException IOException
     */
    public void printToFileForlogs(String filepath) throws IOException {
        //The results must be saved in ASCII code, i.e., human-readable.
        File fileDir = new File("logs");
        if (fileDir.exists() && fileDir.isDirectory()) {
        } else {
            fileDir.mkdirs();
        }
        File file = new File("logs" + filepath);
        //show the results of your audit on the console-line

        //save your audit results to results.log
        if (file.exists() && file.isFile()) {
            FileWriter fr = new FileWriter(filepath, true);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(toString());
            br.flush();
            fr.close();
        } else {
            file.createNewFile();
            FileWriter fr = new FileWriter("logs" + filepath, true);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(toString());
            br.flush();
            fr.close();
        }
    }

    /**
     * @param scenario scenario
     * @param filePath filePath
     * @param flag flag
     * @throws IOException IOException
     */
    //save  passengers  or  pedestrians
    public static void savePedestriansOrPassengers(Scenario scenario, String filePath, char flag)
            throws IOException {
        File file = new File(filePath);
        if (!file.exists() || file .isDirectory()) {
            file.createNewFile();
        }

        FileWriter fr = new FileWriter(filePath, true);
        BufferedWriter br = new BufferedWriter(fr);
        if (flag == 'A') {
            br.newLine();
            br.write(scenario.toString());
            br.newLine();
            br.write("The result(user select to save passengers).");
            Character[] passengers = scenario.getPassengers();
            for (int i = 0; i < passengers.length; i++) {
                br.newLine();
                br.write(passengers[i].toString());
            }
        } else {
            br.newLine();
            br.write(scenario.toString());
            br.newLine();
            br.write("The result(user select to save pedestrians).");
            Character[] pedestrians = scenario.getPedestrians();
            for (int i = 0; i < pedestrians.length; i++) {
                br.newLine();
                br.write(pedestrians[i].toString());
            }
        }
        br.flush();
        fr.close();
    }

    /**
     * @param characters filepath
     */
    //save the attributes for survivors
    public void setAttributesSurvive(Character[] characters) {
        for (int i1 = 0; i1 < characters.length; i1++) {
            if (characters[i1] instanceof Person) {
                Person personTemp = (Person) characters[i1];
                addProfessionSurvive(personTemp.getProfession());
                addAgeCategorySurvive(personTemp.getAgeCategory());
                addGenderSurvive(personTemp.getGender());
                addBodyTypeSurvive(personTemp.getBodyType());
                addPregnantSurvive(personTemp);
                isYouSurvive(personTemp.isYou());
                personNumSurvive++;
                allNum++;
                ageSurvive += personTemp.getAge();
            } else {
                Animal animalTemp = (Animal) characters[i1];
                animalNumSurvive++;
                //addGenderSurvive(animalTemp.getGender());
                addPregnantSurvive(animalTemp);
                addSpeciesSurvive(animalTemp.getSpecies());
                addPetSurvive(animalTemp.isPet());
            }
        }
    }

    /**
     * @param characters filepath
     */
    //set the attributes for all persons
    public void setAttributes(Character[] characters) {
        for (int i1 = 0; i1 < characters.length; i1++) {
            if (characters[i1] instanceof Person) {
                Person personTemp = (Person) characters[i1];
                addProfession(personTemp.getProfession());
                addAgeCategory(personTemp.getAgeCategory());
                addGender(personTemp.getGender());
                addBodyType(personTemp.getBodyType());
                addPregnant(personTemp);
                isYou(personTemp.isYou());
                personNum++;

            } else {
                Animal animalTemp = (Animal) characters[i1];
                animalNum++;
                //addGender(animalTemp.getGender());
                addPregnant(animalTemp);
                addSpecies(animalTemp.getSpecies());
                addPet(animalTemp.isPet());
            }
        }
    }

    /**
     * @param profession profession
     */
    ////add attributes by survivors
    public void addProfession(Person.Profession profession) {
        //DOCTOR, CEO, CRIMINAL, HOMELESS, UNEMPLOYED, UNKNOWN, NONE, RETIREMENT, LAYER
        switch (profession) {
            case DOCTOR:
                doctorNum++;
                break;
            case CEO:
                ceoNum++;
                break;
            case CRIMINAL:
                criminalNum++;
                break;
            case HOMELESS:
                homelessNum++;
                break;
            case UNEMPLOYED:
                unemployedNum++;
                break;
            case UNKNOWN:
                unknownNum++;
                break;
            case RETIREMENT:
                retirementNum++;
                break;
            case LAYER:
                layerNum++;
                break;
            case NONE:
                noneNum++;
                break;
        }
    }

    /**
     * @param profession profession
     */
    //add profession attributes by survivors
    public void addProfessionSurvive(Person.Profession profession) {
        //DOCTOR, CEO, CRIMINAL, HOMELESS, UNEMPLOYED, UNKNOWN, NONE, RETIREMENT, LAYER
        switch (profession) {
            case DOCTOR:
                doctorNumSurvive++;
                break;
            case CEO:
                ceoNumSurvive++;
                break;
            case CRIMINAL:
                criminalNumSurvive++;
                break;
            case HOMELESS:
                homelessNumSurvive++;
                break;
            case UNEMPLOYED:
                unemployedNumSurvive++;
                break;
            case UNKNOWN:
                unknownNumSurvive++;
                break;
            case RETIREMENT:
                retirementNumSurvive++;
                break;
            case LAYER:
                layerNumSurvive++;
                break;
            case NONE:
                noneNumSurvive++;
                break;
        }
    }

    /**
     * @param ageCategory ageCategory
     */
    public void addAgeCategory(Person.AgeCategory ageCategory) {
        switch (ageCategory) {
            case BABY:
                babyNum++;
                break;
            case CHILD:
                childNum++;
                break;
            case ADULT:
                adultNum++;
                break;
            case SENIOR:
                seniorNum++;
                break;
        }
    }

    /**
     * @param ageCategory ageCategory
     */
    public void addAgeCategorySurvive(Person.AgeCategory ageCategory) {
        switch (ageCategory) {
            case BABY:
                babyNumSurvive++;
                break;
            case CHILD:
                childNumSurvive++;
                break;
            case ADULT:
                adultNumSurvive++;
                break;
            case SENIOR:
                seniorNumSurvive++;
                break;
        }
    }

    /**
     * @param gender gender
     */
    public void addGender(Character.Gender gender) {
        switch (gender) {
            case MALE:
                maleNum++;
                break;
            case FEMALE:
                femaleNum++;
                break;
            case UNKNOWN:
                unknownNum++;
        }
    }

    /**
     * @param gender gender
     */
    public void addGenderSurvive(Character.Gender gender) {
        switch (gender) {
            case MALE:
                maleNumSurvive++;
                break;
            case FEMALE:
                femaleNumSurvive++;
                break;
            case UNKNOWN:
                unknownNumSurvive++;
        }
    }

    /**
     * @param bodyType bodyType
     */
    public void addBodyType(Character.BodyType bodyType) {
        switch (bodyType) {
            case OVERWEIGHT:
                overweightNum++;
                break;
            case UNSPECIFIED:
                unspecifiedNum++;
                break;
            case AVERAGE:
                averageNum++;
                break;
            case ATHLETIC:
                arhleticNum++;
                break;
        }
    }

    /**
     * @param bodyType bodyType
     */
    public void addBodyTypeSurvive(Character.BodyType bodyType) {
        switch (bodyType) {
            case OVERWEIGHT:
                overweightNumSurvive++;
                break;
            case UNSPECIFIED:
                unspecifiedNumSurvive++;
                break;
            case AVERAGE:
                averageNumSurvive++;
                break;
            case ATHLETIC:
                arhleticNumSurvive++;
                break;
        }
    }

    /**
     * @param person person
     */
    public void addPregnant(Character person) {
        if (person.isPregnant())
            pregnantNum++;
    }

    /**
     * @param person person
     */
    public void addPregnantSurvive(Character person) {
        if (person.isPregnant())
            pregnantNumSurvive++;
    }

    /**
     * @param species species
     */
    public void addSpecies(String species) {
        if ("cat".equals(species.toLowerCase())) {
            catNum++;
        }
        if ("dog".equals(species.toLowerCase())) {
            dogNum++;
        }
        if ("bird".equals(species.toLowerCase())) {
            birdNum++;
        }
        if ("ferret".equals(species.toLowerCase())) {
            ferretNum++;
        }
    }

    /**
     * @param species species
     */
    public void addSpeciesSurvive(String species) {
        if ("cat".equals(species.toLowerCase())) {
            catNumSurvive++;
        }
        if ("dog".equals(species.toLowerCase())) {
            dogNumSurvive++;
        }
        if ("bird".equals(species.toLowerCase())) {
            birdNumSurvive++;
        }
        if ("ferret".equals(species.toLowerCase())) {
            ferretNumSurvive++;
        }
    }

    /**
     * @param ispet ispet
     */
    public void addPet(boolean ispet) {
        if (ispet) {
            petNum++;
        }
    }

    /**
     * @param ispet ispet
     */
    public void addPetSurvive(boolean ispet) {
        if (ispet) {
            petNumSurvive++;
        }
    }

    /**
     * @param scenarioTemp scenarioTemp
     */
    public void greenOrRed(Scenario scenarioTemp) {
        if (scenarioTemp.isLegalCrossing())
            greenNum += scenarioTemp.getPassengerCount() + scenarioTemp.getPedestrianCount();
        else redNum += scenarioTemp.getPassengerCount() + scenarioTemp.getPedestrianCount();
    }

    /**
     * @param scenarioTemp scenarioTemp
     * @param num num
     */
    public void greenOrRedSurvive(Scenario scenarioTemp, int num) {
        if (scenarioTemp.isLegalCrossing()) greenNumSurvive += num;
        else redNumSurvive += num;
    }

    /**
     * @param isYou isYou
     */
    public void isYou(boolean isYou) {
        if (isYou) youNum++;
    }

    /**
     * @param isYou isYou
     */
    public void isYouSurvive(boolean isYou) {
        if (isYou) youNumSurvive++;
    }

    /**
     * @param num num
     * @return double
     */
    public double addNum(Double num) {
        DecimalFormat df = new DecimalFormat("0.00");
        return Double.parseDouble(df.format((num)));
    }

    enum AuditType {
        USER, AUTO, UNSPECIFID;
        /**
         * @param type type
         * @return boolean
         */
        public static boolean contains(String type) {
            for (AuditType auditType : AuditType.values()) {
                if (auditType.name().equals(type.toUpperCase())) {
                    return true;
                }
            }
            return false;
        }
    }

}

