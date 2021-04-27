
public class Car {
    private String model;
    private String type;

    public Car(String model, String type) {
        this.model = model;
        this.type = type;
    }

    public Car() {
    }

    public String getModel() {
        return this.model;
    }

    public String getType() {
        return this.type;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return String.format("'%s %s'", this.model, this.type);
    }
}
