
import java.util.HashMap;
import java.util.Map;

public class TerminalServer implements ITerminalServer {
    private String nameCard;
    private static final Map<String, Integer> accounts = new HashMap<>();

    static {
        accounts.put("4569645", 127340);
        accounts.put("1754216", 54800);
        accounts.put("7369963", 2000000);
    }

    public TerminalServer(String nameCard) {
        this.nameCard = nameCard;
    }

    public void setNameCard(String nameCard) {
        this.nameCard = nameCard;
    }

    public int getMoney() {
        return accounts.get(this.nameCard);
    }

    public void withdraw(int sum) throws CardException {
        if (!accounts.containsKey(this.nameCard)) {
            throw new CardException("Номер карты не найден", this.nameCard);
        } else {
            if (accounts.get(this.nameCard) < sum) {
                throw new CardException("Недостаточно денег на счете", this.nameCard);
            } else {
                accounts.replace(this.nameCard, accounts.get(this.nameCard) - sum);
            }
        }
    }

    public void deposit(int sum) throws CardException {
        if (!accounts.containsKey(this.nameCard)) {
            throw new CardException("Номер карты не найден", this.nameCard);
        } else {
            accounts.replace(this.nameCard, accounts.get(this.nameCard) + sum);
        }
    }
}
