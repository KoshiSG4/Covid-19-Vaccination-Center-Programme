public class Booth{
    private int boothNum;
    private String boothStatus;

    public Booth() {
        this.boothNum = -1;
        this.boothStatus = "e";
    }

    public void setBoothNum(int boothNum) {
        this.boothNum = boothNum;
    }

    public int getBoothNum() {
        return boothNum;
    }

    public String getBoothStatus() {
        return boothStatus;
    }

    public void setBoothStatus(String boothStatus) {
        this.boothStatus = boothStatus;
    }

    @Override
    public String toString() {
        return "Booth{" +
                ", boothNum=" + boothNum +
                ", boothStatus='" + boothStatus + '\'' +
                '}';
    }

}



