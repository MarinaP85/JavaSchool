
public class AccountIsLockedException extends Exception{
    private final String pin;

    public String getPin () {
        return this.pin;
    }

    public AccountIsLockedException (String message, String pin) {
        super(message);
        this.pin = pin;
    }
}
