
public class Person {
    private final boolean man;
    private final String name;
    private Person spouse;

    public Person(boolean man, String name) {
        this.man = man;
        this.name = name;
    }

    public boolean getMan() {
        return this.man;
    }

    public String getName() {
        return this.name;
    }

    public String getSpouseName() {
        if (this.spouse == null) {
            return "none";
        } else {
            return this.spouse.getName();
        }
    }

    public boolean marry(Person person) {
        if (this.spouse != person) {
            if (this.man != person.getMan()) {
                divorce();
                this.spouse = person;
                person.marry(this);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean divorce() {
        if (this.spouse != null) {
            this.spouse.spouse = null;
            this.spouse = null;
            return true;
        } else {
            return false;
        }
    }

}
