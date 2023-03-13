package co.develhope.swagger2.entities;

public class ArithmeticOperation {

    private String name;
    private Integer minNumberOfOperands;
    private String description;
    private String[] properties;

    public ArithmeticOperation(){}

    public ArithmeticOperation(String name, Integer minNumberOfOperands, String description, String[] properties){
        this.name = name;
        this.minNumberOfOperands = minNumberOfOperands;
        this.description = description;
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinNumberOfOperands() {
        return minNumberOfOperands;
    }

    public void setMinNumberOfOperands(Integer minNumberOfOperands) {
        this.minNumberOfOperands = minNumberOfOperands;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getProperties() {
        return properties;
    }

    public void setProperties(String[] properties) {
        this.properties = properties;
    }

}
