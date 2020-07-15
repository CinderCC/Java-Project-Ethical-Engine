
import ethicalengine.*;
import ethicalengine.Character;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static ethicalengine.Character.Gender.FEMALE;
import static ethicalengine.Person.AgeCategory.ADULT;
import static ethicalengine.Person.AgeCategory.BABY;

/**
 * Name: SHIYANG CHEN
 * ID: 931880
 * UserName: SHIYANGC1
 * The class EthicalEngine is the main class of the system.
 * The core function of it is decide function to decide which party of
 * passengers or pedestrian should be saved based on the algorithm or users' decision.
 * And the class also can import the file and handle the statistic of the file.
 * Program setup is also one part of the class.
 */
public class EthicalEngine {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        if (args.length == 1) {
            interactiveWithUsers();
            //judge '-'?
            String arguments_1 = args[0];
            char[] charTemp = arguments_1.toCharArray();
            if (charTemp[0] == '-') {
                //drop '-'
                String commandTemp = arguments_1.replaceAll("-", "");
                if (isHelp(commandTemp)) {
                    getHelp();
                    System.exit(0);
                }
                if (isInteractive(commandTemp)) {
                    getInteractiveAuto();
                }
            }
        } else if (args.length == 2) {
            boolean canSave = weatherToSave(false);
            String arguments_1 = args[0];
            char[] charTemp = arguments_1.toCharArray();
            String arguments_2 = args[1];
            boolean isConfig = false;
            File file = new File(arguments_2);
            if (!file.exists()) {
                System.out.print("ERROR: could not find config file.\n");
                System.exit(0);
            }
            if (charTemp[0] == '-') {
                //drop '-'
                String commandTemp = arguments_1.replaceAll("-", "");
                if (isConfig(commandTemp)) {
                    isConfig = true;
                }
            }
            if (isConfig && canSave) {
                int[] scenarioFlag = getScenarioLineCount(arguments_2);
                //get the config scenarios
                Scenario[] scenarios = getScenarios(arguments_2, scenarioFlag);
                interactiveWithUsers();
                Audit audit = new Audit(scenarios);
                audit.run();
                audit.printStatistic();
                audit.setAlgorithmOrUser("user");
                String resultsFilePath = "results.log";
                audit.printToFile(resultsFilePath);
            }

        } else if (args.length == 3) {
            boolean canSave = false;
            //judge '-'
            String arguments_1 = args[0];
            boolean isInteractive = false;
            String arguments_2 = args[1];
            boolean isConfig = false;
            boolean isResults = false;
            String arguments_3 = args[2];
            char[] charTemp1 = arguments_1.toCharArray();
            if (charTemp1[0] == '-') {
                //drop '-'
                String commandTemp = arguments_1.replaceAll("-", "");
                if (isInteractive(commandTemp))
                    isInteractive = true;
            }
            char[] charTemp2 = arguments_2.toCharArray();
            if (charTemp2[0] == '-') {
                //drop '-'
                String commandTemp = arguments_2.replaceAll("-", "");
                if (isConfig(commandTemp))
                    isConfig = true;
                if (isResults(commandTemp))
                    isResults = true;
            }
            // read the config file and display the results
            if (isInteractive && isConfig) {
                int[] scenarioFlag = getScenarioLineCount(arguments_3);
                //get the scenarios scenarioFlag.length
                Scenario[] scenarios = getScenarios(arguments_3, scenarioFlag);
                interactiveWithUsers();
                canSave = weatherToSave(false);
                //path
                String filepath = "user.log";
                Audit audit = new Audit(scenarios);
                audit.setAlgorithmOrUser("user");
                int j = 3;
                int m = 0;
                while (input.hasNext()) {
                    for (int i = m; i < j; i++) {
                    /*System.out.println(" Would you like to continue? (yes/no)");
                    String yesOrNo = input.next();
                    if (yesOrNo.equals("yes")) {*/
                        System.out.print(scenarios[i].toString());
                        System.out.print("\nWho should be saved? (passenger(s) [1] or pedestrian(s) [2])\n");
                        String temp = input.next();
                        if (temp.equals("passenger") ||
                                temp.equals("passengers") ||
                                temp.equals("1")) {
                            audit.run(scenarios[i], Decision.PASSENGERS);
                            audit.savePedestriansOrPassengers(scenarios[i], filepath, 'A');
                        } else {
                            audit.run(scenarios[i], Decision.PEDESTRIANS);
                            audit.savePedestriansOrPassengers(scenarios[i], filepath, 'B');
                        }
                    /*} else if (yesOrNo.equals("no")) {
                        break;
                    }*/
                    }
                    m = j;
                    j += 3;
                    if (j > scenarios.length) {
                        j = scenarios.length;
                    }

                    System.out.print(audit.toString());
                    if (input.hasNext()) {
                        String yesOrNo = input.next();
                        if (yesOrNo.equals("yes") || yesOrNo.equals("no")) {
                            System.out.print("Would you like to continue? (yes/no)\n");
                        }
                        if (yesOrNo.equals("yes")) {
                            continue;
                        } else if (yesOrNo.equals("no")) {
                            break;
                        }
                    }
                }
                String resultsFilePath = "results.log";
                audit.printToFile(resultsFilePath);
                System.out.print("That's all. Press Enter to quit.\n");
            }
        } else if (args.length == 5) {
            boolean canSave = false;
            //judge '-'
            String arguments_1 = args[0];
            boolean isInteractive = false;
            String arguments_2 = args[1];
            boolean isConfig = false;
            String arguments_3 = args[3];
            boolean isResults = false;
            char[] charTemp1 = arguments_1.toCharArray();
            if (charTemp1[0] == '-') {
                //drop '-'
                String commandTemp = arguments_1.replaceAll("-", "");
                if (isInteractive(commandTemp))
                    isInteractive = true;
            }
            char[] charTemp2 = arguments_2.toCharArray();
            if (charTemp2[0] == '-') {
                //drop '-'
                String commandTemp = arguments_2.replaceAll("-", "");
                if (isConfig(commandTemp))
                    isConfig = true;
            }
            char[] charTemp3 = arguments_3.toCharArray();
            if (charTemp3[0] == '-') {
                //drop '-'
                String commandTemp = arguments_3.replaceAll("-", "");
                if (isResults(commandTemp))
                    isResults = true;
            }
            if (isInteractive && isConfig && isResults) {
                int[] scenarioFlag = getScenarioLineCount(args[2]);
                //get the scenarios scenarioFlag.length
                Scenario[] scenarios = getScenarios(args[2], scenarioFlag);
                interactiveWithUsers();
                canSave = weatherToSave(isResults);
                Audit audit = new Audit(scenarios);
                audit.setAlgorithmOrUser("user");
                audit.run();
                System.out.print(audit.toString() + "\n");
                if (canSave) {
                    if (isResults) {
                        audit.printToFileForlogs(args[4]);
                    } else {
                        audit.printToFile("results.log");
                    }
                }
            }
        } else {
            getHelp();
            System.exit(0);
        }

    }

    /**
     * @param isResults isResults
     * @return isResults
     */
    public static boolean weatherToSave(boolean isResults) {
        try {
            System.out.print("Do you consent to have your decisions saved to a file? (yes/no)\n");
            if (isResults) {
                File file = new File("logs");
                if (file.exists() && file.isDirectory()) {

                } else {
                    throw new FileNotFoundException();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.print("Error: could not find config file\n");
        }
        //get user consent and present three scenarios
        boolean canSave = false;
        //Invalid response. Do you consent to have your decisions saved to a file? (yes/no)
        String s = null;
        while ((s = input.next()) != null) {
            if ("yes".equals(s)) {
                canSave = true;
                //System.out.println("Ok.We will save your results in the file \"user.log\".");
                break;
            }
            if ("no".equals(s)) {
                canSave = false;
                //System.out.println("Ok.We will not save your results.");
                break;
            }
            System.out.print("Invalid response. Do you consent " +
                    "to have your decisions saved to a file? (yes/no)\n");
        }
        return canSave;
    }

    /**
     * @throws IOException IOException
     */
    public static void interactiveWithUsers() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("welcome.ascii"), "UTF-8"));
        String s = null;
        while ((s = br.readLine()) != null) {
            System.out.print(s + "\n");
        }
    }

    /**
     * @param argument argument
     * @return boolean
     */
    //arguments -i --interactive
    public static boolean isInteractive(String argument) {
        String commandTemp = argument.replaceAll("-", "");
        if (commandTemp.toLowerCase().equals("interactive") || commandTemp.toLowerCase().equals("i"))
            return true;
        else
            return false;
    }

    /**
     * @param argument argument
     * @return boolean
     */
    //arguments -h --help
    public static boolean isHelp(String argument) {
        String commandTemp = argument.replaceAll("-", "");
        if (commandTemp.toLowerCase().equals("help") || commandTemp.toLowerCase().equals("h"))
            return true;
        else
            return false;
    }

    /**
     * @param argument argument
     * @return boolean
     */
    //arguments -c --config
    public static boolean isConfig(String argument) {
        String commandTemp = argument.replaceAll("-", "");
        if (commandTemp.toLowerCase().equals("config") || commandTemp.toLowerCase().equals("c"))
            return true;
        else
            return false;
    }

    /**
     * @param argument argument
     * @return boolean
     */
    //arguments -r --results
    public static boolean isResults(String argument) {
        String commandTemp = argument.replaceAll("-", "");
        if (commandTemp.toLowerCase().equals("results") || commandTemp.toLowerCase().equals("r"))
            return true;
        else
            return false;
    }

    /**
     * @throws IOException IOException
     */
    //get random three scenarios
    public static void getInteractiveAuto() throws IOException {
        Scanner input = new Scanner(System.in);
        Audit audit = new Audit();
        audit.run(3);
        String filepath = "user.log";
        Scenario[] scenarios = audit.scenarios;
        for (int i = 0; i < 3; i++) {
            System.out.print("\nWho should be saved? (passenger(s) [1] or pedestrian(s) [2])\n");
            String result = input.next();
            if (result.equals("passenger") ||
                    result.equals("passengers") ||
                    result.equals("1"))
                audit.savePedestriansOrPassengers(scenarios[i], filepath, 'A');
            else
                audit.savePedestriansOrPassengers(scenarios[i], filepath, 'B');
        }
        audit.printStatistic();
        System.out.print("That's all. Press Enter to quit.\n");
        Scanner in0 = new Scanner(System.in);
        String line = in0.nextLine();
        Scanner in2 = new Scanner(line);
        in2.hasNextInt();
        //if (input.next() != null)
        //System.exit(0);
    }

    /**
     * @param scenario scenario
     * @return  Decision
     */
    //return the survivors
    public static Decision decide(Scenario scenario) {
        //passengers
        Character[] passengers = scenario.getPassengers();
        //number of persons in passengers
        double[] passengersArray = getDifferentstatic(passengers);
        double passengerPerson = passengersArray[0];
        //number of animals in passengers
        double passengerAnimal = passengersArray[1];
        double allPassenger = passengerPerson + passengerAnimal;
        //pedestrians
        Character[] pedestrians = scenario.getPedestrians();
        double[] pedestriansArray = getDifferentstatic(pedestrians);
        //number of persons in pedestrians
        double pedestrianPerson = pedestriansArray[0];
        double pedestrianAnimal = pedestriansArray[1];
        double allPedestrians = pedestrianPerson + pedestrianAnimal;
        //number of animals in pedestrians
        //isLegal?
        boolean isLegalCrossing = scenario.isLegalCrossing();
        //System.out.println("Here is the EthicalEngine machine's result.");
        if (isLegalCrossing) {
            return Decision.PEDESTRIANS;

        } else if (allPassenger * 2.5 > allPedestrians) {
            if (pedestrianPerson <= passengerPerson) {
                return Decision.PASSENGERS;
            } else {
                if (pedestrianPerson - passengerPerson > 2) {
                    return Decision.PEDESTRIANS;
                } else {
                    return Decision.PASSENGERS;
                }
            }
        } else {
            if (pedestrianPerson <= passengerPerson) {
                return Decision.PASSENGERS;
            } else {
                if (pedestrianPerson - passengerPerson > 2) {
                    return Decision.PEDESTRIANS;
                } else {
                    return Decision.PASSENGERS;
                }
            }
        }
    }

    /**
     * @param characters Character[]
     * @return  double[]
     */
    //get the number of persons and animals
    public static double[] getDifferentstatic(Character[] characters) {
        int length = characters.length;
        double array_1 = 0;
        double array_2 = 0;
        for (int i = 0; i < length; i++) {
            if (characters[i] instanceof Person) {
                Person temp = (Person) characters[i];
                if (temp.isPregnant()) array_1 += 2;
                else if (temp.isYou()) array_1 = array_1 * 1.5;
                else if (temp.getAgeCategory().equals(BABY)) array_1 += array_1 * 1.4;
                else if (temp.getAgeCategory().equals(ADULT)) array_1 += array_1 * 1.2;
                else if (temp.getGender().equals(FEMALE)) array_1 += array_1 * 1.1;
                else
                    array_1++;
            } else {
                array_2++;
            }
        }
        double[] result = {array_1, array_2};
        return result;
    }

    /**
     * @param filepath filepath
     * @param scenarioFlag int[]
     * @return  Scenario[]
     * @throws IOException IOException
     */
    @SuppressWarnings("unchecked")
    public static Scenario[] getScenarios(String filepath, int[] scenarioFlag) throws IOException {
        FileReader fileReader = new FileReader(filepath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s = null;
        int line = 0;
        int fileLength = getCount(filepath);
        int[] result = new int[scenarioFlag.length + 1];
        for (int i = 0; i < result.length - 1; i++) {
            result[i] = scenarioFlag[i];
        }
        result[result.length - 1] = fileLength + 1;
        Scenario[] scenarios = new Scenario[scenarioFlag.length];
        for (int i = 0; i < result.length - 1; i++) {
            List passengers = new LinkedList();
            List pedestrians = new LinkedList();
            boolean isLegalCrossing = false;
            while ((s = bufferedReader.readLine()) != null) {
                line++;
                if (line == result[i]) {
                    char[] charTemp = s.toCharArray();
                    if (charTemp[9] == 'g')
                        isLegalCrossing = true;
                }
                int length = s.split(",").length;
                try {
                    if (length != 10 && !s.startsWith("scenario:")) {
                        throw new InvalidDataFormatException();
                    }
                } catch (InvalidDataFormatException e) {
                    System.out.print("WARNING: invalid data format in config file in line " + line + "\n");
                    continue;
                }
                if (line > result[i] && line < result[i + 1]) {
                    String[] temp = s.split(",");
                    //person,female,24,average,doctor,FALSE,FALSE,,,passenger
                    if (temp[9].equals("passenger")) {
                        if (temp[0].equals("person")) {
                            Person tempPerson = getTempPerson(temp, line);
                            passengers.add(tempPerson);
                        } else {
                            Animal tempAnimal = getTempAnimal(temp, line);
                            passengers.add(tempAnimal);
                        }
                    } else {
                        if (temp[0].equals("person")) {
                            Person tempPerson = getTempPerson(temp, line);
                            pedestrians.add(tempPerson);
                        } else {
                            Animal tempAnimal = getTempAnimal(temp, line);
                            pedestrians.add(tempAnimal);
                        }
                    }
                }
                if (line == result[i + 1] - 1)
                    break;

            }
            Character[] passengersArray = new Character[passengers.size()];
            for (int i1 = 0; i1 < passengers.size(); i1++) {
                passengersArray[i1] = (Character) passengers.get(i1);
            }
            Character[] pedestriansArray = new Character[pedestrians.size()];
            for (int i1 = 0; i1 < pedestrians.size(); i1++) {
                pedestriansArray[i1] = (Character) pedestrians.get(i1);
            }
            scenarios[i] = new Scenario(passengersArray, pedestriansArray, isLegalCrossing);
        }
        return scenarios;
    }

    /**
     * @param path path
     * @return  int
     * @throws IOException IOException
     */
    //get the line number of the file
    public static int getCount(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader b = new BufferedReader(fileReader);
        int count = 0;
        String s = null;
        while ((s = b.readLine()) != null) {
            if (!s.equals(""))
                count++;
        }
        b.close();
        return count;
    }

    /**
     * @param temp String[]
     * @param line line
     * @return  int
     */
    //get the person
    public static Person getTempPerson(String[] temp, int line) {
        //default
        Character.Gender gender = Character.Gender.UNKNOWN;
        try {
            if (!Character.Gender.contains(temp[1].toUpperCase()))
                throw new InvalidCharacteristicException();
            gender = Character.Gender.valueOf(temp[1].toUpperCase());
        } catch (InvalidCharacteristicException e) {
            System.out.print("WARNING: invalid characteristic in config file in line " + line + "\n");
        }
        //default
        int age = 0;
        try {
            if (!temp[2].matches("[0-9]+"))
                throw new NumberFormatException();
            age = Integer.parseInt(temp[2]);
        } catch (NumberFormatException e) {
            System.out.print("WARNING: invalid number format in config file in line " + line + "\n");
        }
        //default
        Character.BodyType bodyType = Character.BodyType.UNSPECIFIED;
        try {
            if (!Character.BodyType.contains(temp[3].toUpperCase()))
                throw new InvalidCharacteristicException();
            bodyType = Character.BodyType.valueOf(temp[3].toUpperCase());
        } catch (InvalidCharacteristicException e) {
            System.out.print("WARNING: invalid characteristic in config file in line " + line + "\n");
        }
        //default
        Person.Profession profession = Person.Profession.NONE;
        if (!temp[4].equals("")) {
            try {
                if (!Person.Profession.contains(temp[4].toUpperCase()))
                    throw new InvalidCharacteristicException();
                profession = Person.Profession.valueOf(temp[4].toUpperCase());
            } catch (InvalidCharacteristicException e) {
                System.out.print("WARNING: invalid characteristic in config file in line " + line + "\n");
            }
        }
        boolean isPregnant = Boolean.valueOf(temp[5].toLowerCase());
        boolean isYou = Boolean.valueOf(temp[6].toLowerCase());
        Person tempPerson = new Person(age, gender, bodyType, profession, isPregnant, isYou);
        return tempPerson;
    }

    /**
     * @param temp: String[]
     * @param line: line
     * @return get a random animal
     */
    public static Animal getTempAnimal(String[] temp, int line) {
        //default
        Character.Gender gender = Character.Gender.UNKNOWN;
        try {
            if (!Character.Gender.contains(temp[1].toUpperCase()))
                throw new InvalidCharacteristicException();
            gender = Character.Gender.valueOf(temp[1].toUpperCase());
        } catch (InvalidCharacteristicException e) {
            System.out.print("WARNING: invalid characteristic in config file in line " + line + "\n");
        }
        boolean isNum = temp[2].matches("[0-9]+");
        int age = 0;
        try {
            if (!isNum)
                throw new NumberFormatException();
            age = Integer.parseInt(temp[2]);
        } catch (NumberFormatException e) {
            System.out.print("WARNING: invalid number format in config file in line " + line + "\n");
            age = 18;
        }
        boolean isPregnant = Boolean.valueOf(temp[5].toLowerCase());
        boolean isYou = Boolean.valueOf(temp[6].toLowerCase());
        String species = temp[7];
        boolean isPet = Boolean.valueOf(temp[8].toLowerCase());
        Animal tempAnimal = new Animal(age, gender, species, isPet, isPregnant, isYou);
        return tempAnimal;
    }

    /**
     * @param path: a path to a file
     * @return lines for scenarios
     * @throws IOException IOException
     */
    @SuppressWarnings("unchecked")
    public static int[] getScenarioLineCount(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader b = new BufferedReader(fileReader);
        String s = null;
        int line = 0;
        List<Integer> list = new LinkedList<Integer>();
        while ((s = b.readLine()) != null) {
            line++;
            String[] temp = s.split(",");
            char[] charTemp = temp[0].toCharArray();
            //9
            if (charTemp[0] == 's') {
                list.add(line);
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        b.close();
        return result;
    }

    public static void getHelp() {
        System.out.print("Usage: java EthicalEngine [arguments]\n" +
                "   Arguments:\n" +
                "-c or --config      Optional:path to config file\n" +
                "-h or --help        Print Help (this message) and exit\n" +
                "-r or --results     Optional:path to results log file\n" +
                "-i or --interactive Optional: launches interactive mode\n");
    }

    public enum Decision {
        PEDESTRIANS, PASSENGERS
    }
}

class InvalidDataFormatException extends Exception {
}

class NumberFormatException extends Exception {
}

class InvalidCharacteristicException extends Exception {
}
