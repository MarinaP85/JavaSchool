
import java.io.IOException;

public class TerminalImpl {
    private final ITerminalServer server;
    private final IPinValidator pinValidator;

    public TerminalImpl(String nameCard, String pin) {
        server = new TerminalServer(nameCard);
        pinValidator = new PinValidator(nameCard, pin);
    }

    public void setNameCard(String nameCard) {
        pinValidator.setNameCard(nameCard);
        server.setNameCard(nameCard);
    }

    public void setPin(String pin) {
        pinValidator.setPin(pin);
    }

    public int getMoney() {
        return server.getMoney();
    }

    public String check(boolean locked) throws IOException {
        try {
            if (pinValidator.checkAccount(locked)) {
                return "OK";
            } else {
                return "NOTOK";
            }
        } catch (CardException e) {
            return e.getMessage();
        } catch (AccountIsLockedException e) {
            return "BLOCKED";
        }
    }

    public String withdrawMoney(int sum) {
        try {
            server.withdraw(sum);
            return "OK";
        } catch (CardException e) {
            return e.getMessage();
        }
    }

    public String depositMoney(int sum) {
        try {
            server.deposit(sum);
            return "OK";
        } catch (CardException e) {
            return e.getMessage();
        }
    }

}
