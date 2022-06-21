import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class vaccinationCenter {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String option = "";
        int vaccineCount = 150;
        Booth[] boothArray = new Booth[6];  //creating array of booth objects
        Patient[] patients = new Patient[6];    //creating array of Patient objects
        LinkedList<Patient> waitingList = new LinkedList<>();

        vaccinationBooths(boothArray,patients); //initialising boothArray
        do {
            System.out.println("\nVaccination Center Menu:\n" +
                    " \n" +
                    "100 or VVB:    View all Vaccination Booths\n" +
                    "101 or VEB:    View all Empty Booths\n" +
                    "102 or APB:    Add Patient to a Booth\n" +
                    "103 or RPB:    Remove Patient from a Booth\n" +
                    "104 or VPS:    View Patients Sorted in alphabetical order (Do not use library sort routine)\n" +
                    "105 or SPD:    Store Program Data into file\n" +
                    "106 or LPD:    Load Program Data from file\n" +
                    "107 or VRV:    View Remaining Vaccinations\n" +
                    "108 or AVS:    Add Vaccinations to the Stock\n" +
                    "999 or EXT:    Exit the Program\n" +
                    "Please Enter Your Option: ");
            option = scan.nextLine();
            switch (option.toUpperCase()) {
                case "100":
                case "VVB":
                    System.out.println("View all Vaccination Booths");
                    viewAllBooths(boothArray,patients);
                    break;
                case "101":
                case "VEB":
                    System.out.println("View all Empty Booths");
                    viewAllEmptyBooths(boothArray);
                    break;
                case "102":
                case "APB":
                    System.out.println("Add Patient to a Booth");
                    vaccineCount-=addPatient(boothArray,patients,vaccineCount,waitingList); //updating the global vaccineCount variable and calling the addPatient function
                    break;
                case "103":
                case "RPB":
                    System.out.println("Remove Patient from a Booth");
                    removePatient(boothArray,patients,waitingList);
                    break;
                case "104":
                case "VPS":
                    System.out.println("View Patients Sorted in alphabetical order");
                    viewPatients(patients);
                    break;
                case "105":
                case "SPD":
                    System.out.println("Store Program Data into file");
                    storeProgramData(boothArray,patients,vaccineCount);
                    break;
                case "106":
                case "LPD":
                    System.out.println("Load Program Data from file");
                    loadProgramData();
                    break;
                case "107":
                case "VRV":
                    System.out.println("View Remaining Vaccinations");
                    viewRemainingVaccines(vaccineCount);
                    break;
                case "108":
                case "AVS":
                    System.out.println("Add Vaccinations to the Stock");
                    vaccineCount = addVaccines(vaccineCount);   //updating the global vaccineCount variable and calling the addVaccines function
                    break;
                case "111":
                case "VPD":
                    System.out.println("Additional Patients Details:");
                case "999":
                case "EXT":
                    System.out.println("Exit the Program");
                    break;
            }
        } while ((!option.toUpperCase().equals("999")) && (!option.toUpperCase().equals("EXT")));
    }
    //initialisation
    public static void vaccinationBooths(Booth[] boothArray, Patient[] patients){
        for (int x = 0; x < 6; x++) {
            boothArray[x] = new Booth();
            boothArray[x].setBoothNum(x);
            patients[x] = new Patient();
            patients[x].setBoothNum(x);
        }
    }
    //printing out the booths' status whether it is empty or occupied.
    private static void viewAllBooths(Booth[] boothArray,Patient[] patients) {
        System.out.println("Booths that give AstraZeneca Vaccine");
        for (int x=0; x<2; x++){
            if (boothArray[x].getBoothStatus().equals("e")){
                System.out.println("Booth "+x+" is empty.");
            }else{
                System.out.println("Booth "+x+" is occupied by "+ patients[x].getFirstName()+" "+patients[x].getSurName());
            }
        }
        System.out.println("\nBooths that give Sinopharm Vaccine");
        for (int x=2; x<4; x++){
            if (boothArray[x].getBoothStatus().equals("e")){
                System.out.println("Booth "+x+" is empty.");
            }else{
                System.out.println("Booth "+x+" is occupied by "+ patients[x].getFirstName()+" "+patients[x].getSurName());
            }
        }
        System.out.println("\nBooths that give Pfizer Vaccine");
        for (int x=4; x<6; x++){
            if (boothArray[x].getBoothStatus().equals("e")){
                System.out.println("Booth "+x+" is empty.");
            }else{
                System.out.println("Booth "+x+" is occupied by "+ patients[x].getFirstName()+" "+patients[x].getSurName());
            }
        }
    }
    //View empty booths only
    private static void viewAllEmptyBooths(Booth[] boothArray){
        System.out.println("Booths that give AstraZeneca Vaccine");
        for (int x=0; x<2; x++){
            if (boothArray[x].getBoothStatus().equals("e")){
                System.out.println("Booth "+x+" is empty.");
            }
        }
        System.out.println("\nBooths that give Sinopharm Vaccine");
        for (int x=2; x<4; x++){
            if (boothArray[x].getBoothStatus().equals("e")){
                System.out.println("Booth "+x+" is empty.");
            }
        }
        System.out.println("\nBooths that give Pfizer Vaccine");
        for (int x=4; x<6; x++){
            if (boothArray[x].getBoothStatus().equals("e")){
                System.out.println("Booth "+x+" is empty.");
            }
        }
    }
    //adding patients to booths
    private static int addPatient(Booth[] boothArray,Patient[] patients,int vaccineCount,LinkedList<Patient> waitingList) {
        Scanner scan = new Scanner(System.in);
        int addedPatientCount = 0;
        try {   //user input errors handling
            System.out.println("Please enter 'C' to add patient details and 'Q' to go back to main menu: ");
            String option = scan.next().toUpperCase();

            while ((!option.equals("q")) && (!option.equals("Q"))) {
                if ((option.equals("c")) || (option.equals("C"))) {
                    System.out.println("Patient requested vaccine Type: (Enter 'A' or 'B' or 'C')\nA. AstraZeneca\nB. Sinopharm\nC. Pfizer");
                    String requestedVacType = scan.next().toUpperCase();
                    int boothNum=-1;
                    Patient patient = new Patient();
                    //for booth 1 & 2 - AstraZeneca
                    if (requestedVacType.equals("A")) {
                        System.out.println("Enter booth number 0 or 1: ");
                        boothNum = scan.nextInt();
                        patients[boothNum].setVaccineType("A");
                        if ((0 <= boothNum) && (boothNum < 2)) {
                            if (boothArray[boothNum].getBoothStatus().equals("e")) {
                                patients[boothNum].additionalData();
                                boothArray[boothNum].setBoothStatus("Occupied");
                                vaccineCount--;      //update vaccine count
                                if (vaccineCount == 0) {    //giving warnings when vaccine stock is getting low
                                    System.out.println("Vaccines stock is over.");
                                } else if (vaccineCount <= 20) {
                                    System.out.println("Vaccines stock is running low, remaining vaccines amount = " + vaccineCount);
                                }
                                addedPatientCount += 1;
                            }else if ((!boothArray[0].getBoothStatus().equals("e"))&& (!boothArray[1].getBoothStatus().equals("e"))){
                                System.out.println("All booths are occupied at the moment and you'll be added to a waiting list");
                                patient.additionalData();
                                waitingList.add(patient);
                                System.out.println(waitingList);
                            }else {
                                System.out.println("The booth " + boothNum + " is occupied by " + patients[boothNum].getFirstName()+" "+patients[boothNum].getSurName() + " at the moment, Please try the other booth.");
                            }
                        } else {
                            System.out.println("You have entered an invalid number option.");
                        }

                        //for booth 2 & 3 - Sinopharm
                    }else if (requestedVacType.equals("B")) {
                        System.out.println("Enter booth number 2 or 3: ");
                        boothNum = scan.nextInt();
                        patients[boothNum].setVaccineType("B");
                        if ((2 <= boothNum) && (boothNum < 4)) {
                            if (boothArray[boothNum].getBoothStatus().equals("e")) {
                                patients[boothNum].additionalData();
                                boothArray[boothNum].setBoothStatus("Occupied");
                                vaccineCount--;      //update vaccine count
                                if (vaccineCount == 0) {    //giving warnings when vaccine stock is getting low
                                    System.out.println("Vaccines stock is over.");
                                } else if (vaccineCount <= 20) {
                                    System.out.println("Vaccines stock is running low, remaining vaccines amount = " + vaccineCount);
                                }
                                addedPatientCount+=1;
                            }else if ((!boothArray[2].getBoothStatus().equals("e"))&& (!boothArray[3].getBoothStatus().equals("e"))){
                                System.out.println("All booths are occupied at the moment and you'll be added to a waiting list");
                                patient.additionalData();
                                waitingList.add(patient);
                            }else {
                                System.out.println("The booth " + boothNum + " is occupied by " + patients[boothNum].getFirstName()+" "+patients[boothNum].getSurName() + " at the moment, try another booth.");
                            }
                        } else {
                            System.out.println("You have entered an invalid number option.");
                        }

                        //for booth 4 & 5- Pfizer
                    } else if (requestedVacType.equals("C")) {
                        System.out.println("Enter booth number 4 or 5: ");
                        boothNum = scan.nextInt();
                        patients[boothNum].setVaccineType("C");
                        if ((4 <= boothNum) && (boothNum < 6)) {
                            if (boothArray[boothNum].getBoothStatus().equals("e")) {
                                patients[boothNum].additionalData();
                                boothArray[boothNum].setBoothStatus("Occupied");
                                vaccineCount--;      //update vaccine count
                                if (vaccineCount == 0) {    //giving warnings when vaccine stock is getting low
                                    System.out.println("Vaccines stock is over.");
                                } else if (vaccineCount <= 20) {
                                    System.out.println("Vaccines stock is running low, remaining vaccines amount = " + vaccineCount);
                                }
                                addedPatientCount+=1;
                            }else if ((!boothArray[4].getBoothStatus().equals("e"))&& (!boothArray[5].getBoothStatus().equals("e"))){
                                System.out.println("All booths are occupied at the moment and you'll be added to a waiting list");
                                patient.additionalData();
                                waitingList.add(patient);
                            }else {
                                System.out.println("The booth " + boothNum + " is occupied by " + patients[boothNum].getFirstName()+" "+patients[boothNum].getSurName() + " at the moment, try another booth.");
                            }
                        } else {
                            System.out.println("You have entered an invalid number option.");
                        }
                    }
                }
                System.out.println("Please enter 'C' to add patient details and 'Q' to go back to main menu: ");
                option = scan.next().toUpperCase();
            }
        } catch (InputMismatchException e) {
            System.out.println("You have entered an invalid input,Please try again.");
        }
        scan.nextLine();
        return addedPatientCount;
    }
    //removing a patient from a booth
    private static void removePatient(Booth[] boothArray,Patient[] patients,LinkedList<Patient> waitingList){
        Scanner scan = new Scanner(System.in);
        try {   //handling user input errors
            System.out.println("Please enter the booth number to remove the patient: ");
            int boothNum = scan.nextInt();
            String removedPatientFirstName= patients[boothNum].getFirstName();
            String removedPatientSurname = patients[boothNum].getSurName();
            if(boothArray[boothNum].getBoothStatus().equals("e")){  //handling errors - if requested to remove a patient from an empty booth
                System.out.println("There is no patients at the moment.");
            }else{
                if (!waitingList.getFirst().equals(null)){
                    System.out.println(waitingList);
                    patients[boothNum] = waitingList.getFirst();
                    waitingList.removeFirst();
                }else{
                    boothArray[boothNum] = new Booth();
                }
                System.out.println("Removed the patient " + removedPatientFirstName+" "+removedPatientSurname+" from the booth number " + boothNum + ".");
            }
        }catch (InputMismatchException e){
            System.out.println("You have entered an invalid input,Please try again.");
        }scan.nextLine();
    }

    //view patients in a sorted in alphabetical order
    private static void viewPatients(Patient[] patients){
        Patient[] sortedPatients = patients.clone();
        Arrays.sort(sortedPatients, Patient.patientsNameComparator);
        for (int i=0;i<sortedPatients.length;i++){
            if(!sortedPatients[i].getFirstName().equals("")){
                System.out.println(sortedPatients[i].getFirstName()+" "+sortedPatients[i].getSurName());
            }
        }
    }
    //store program data into a file
    private static void storeProgramData(Booth[] boothArray,Patient[] patients,int vaccineCount){
        try{    //handling errors
            FileWriter storeVaccinationProgramData = new FileWriter("/home/koshi/Desktop/SD2/PROG/CW/Task 1/Program_Data.txt"); // giving the path to the file
            storeVaccinationProgramData.write("Vaccination Center Program Data:\n");
            storeVaccinationProgramData.write("Detailed Patients List:\n");
            for (int i=0; i<patients.length; i++) {
                if(!patients[i].getFirstName().equals("")) {
                    int x=1;
                    storeVaccinationProgramData.write(x+ ". " +patients[i].toString());
                    x++;
                }
            }
            storeVaccinationProgramData.write("Booths Status:\n");
            for (int x=0; x<boothArray.length; x++){
                if (patients[x].getFirstName().equals("")){
                    storeVaccinationProgramData.write("Booth "+x+" is empty.\n");
                }else{
                    storeVaccinationProgramData.write("Booth "+x+" is occupied by "+patients[x].getFirstName()+" "+patients[x].getSurName()+"\n");
                }
            }
            storeVaccinationProgramData.write("Remaining vaccines count: " + vaccineCount+"\n");
            storeVaccinationProgramData.close();
            System.out.println("Stored program data");
        }catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    //load store program data
    private static void loadProgramData(){
        try {   //handling errors
            File loadVaccinationProgramData = new File("/home/koshi/Desktop/SD2/PROG/CW/Task 1/Program_Data.txt");  //path to the file
            Scanner myReader = new Scanner(loadVaccinationProgramData);
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                System.out.println(data);
            }
        }catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    //view remaining vaccine amount in the store
    private static void viewRemainingVaccines(int vaccineCount){
        System.out.println("Number of remaining vaccines: " +vaccineCount);
    }
    //adding new vaccines to the store
    private static int addVaccines(int vaccineCount){
        Scanner scan = new Scanner(System.in);
        try {   // error handling
            System.out.println("How many vaccines are you adding to the store: ");
            int increasingCount = scan.nextInt();
            vaccineCount += increasingCount;
            System.out.println("Updated number of vaccines: "+vaccineCount);
        }catch (InputMismatchException e){
            System.out.println("You have entered an invalid input,Please try again.");
        }scan.nextLine();
        return vaccineCount;
    }
}