package co.develhope.Interceptors02.entities;

public class Month {

    private Integer monthNumber;
    private String englishName;
    private String italianName;
    private String germanName;

    public Month(){}

    public Month(int monthName, String englishName, String italianName, String germanName) {
        this.monthNumber = monthName;
        this.englishName = englishName;
        this.italianName = italianName;
        this.germanName = germanName;
    }

    public int getMonthName() {
        return monthNumber;
    }

    public void setMonthName(int monthName) {
        this.monthNumber = monthName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getItalianName() {
        return italianName;
    }

    public void setItalianName(String italianName) {
        this.italianName = italianName;
    }

    public String getGermanName() {
        return germanName;
    }

    public void setGermanName(String germanName) {
        this.germanName = germanName;
    }

}
