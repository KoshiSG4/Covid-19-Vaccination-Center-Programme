import java.util.Comparator;
import java.util.Scanner;

public class Patient {

    private String firstName;
    private String surName;
    private int age;
    private String city;
    private int nicOrPassportNo;
    private String vaccineType;

    public Patient() {
        this.firstName = "";
        this.surName = "";
        this.age = 0;
        this.city = "";
        this.nicOrPassportNo = 0;
        this.vaccineType = "";
    }

    public void additionalData(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the patient's details below as required:");
        System.out.println("2. First Name");
        firstName = scan.nextLine();
        setFirstName(firstName);
        System.out.println("3. Surname:");
        surName = scan.nextLine();
        setSurName(surName);
        System.out.println("4. Age:");
        age = scan.nextInt();
        setAge(age);
        System.out.println("5. City");
        city = scan.next();
        setCity(city);
        System.out.println("6. NIC or Passport Number:");
        nicOrPassportNo = scan.nextInt();
        setNicOrPassportNo(nicOrPassportNo);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        city = city;
    }

    public int getNicOrPassportNo() {
        return nicOrPassportNo;
    }

    public void setNicOrPassportNo(int nicOrPassportNo) {
        this.nicOrPassportNo = nicOrPassportNo;
    }

    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    @Override
    public String toString() {
        return "patient :\n" +
                " firstName : " + firstName + "\n" +
                " surName : " + surName + "\n" +
                " age : " + age + "\n" +
                " City : " + city + "\n" +
                " nicOrPassportNo : " + nicOrPassportNo + "\n" +
                " vaccineType : " + vaccineType + "\n";
    }
    public  static Comparator<Patient> patientsNameComparator = new Comparator<Patient>() {

        @Override
        public int compare(Patient o1, Patient o2) {
            String patientName1 = o1.getFirstName().toUpperCase();
            String patientName2 = o2.getFirstName().toUpperCase();
            return patientName1.compareTo(patientName2);
        }
    };

    public void setBoothNum(int x) {
    }
}
