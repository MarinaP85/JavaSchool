
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PinValidator implements IPinValidator {
    private String nameCard;
    private String pin;
    private static final String fileName = "G:\\IdeaProjects\\ForTests\\cards.txt";

    public PinValidator(String nameCard, String pin) {
        this.nameCard = nameCard;
        this.pin = pin;
    }

    public void setNameCard(String nameCard) {
        this.nameCard = nameCard;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public boolean checkAccount(boolean locked) throws CardException, IOException, AccountIsLockedException {
        if (locked) throw new AccountIsLockedException("Аккаунт временно заблокирован", this.pin);
        String cardTemp = "";
        String pinTemp = "";
        List<String> str;
        String[] card;
        str = Files.readAllLines(Paths.get(fileName), Charset.forName("Cp1251"));
        for (String s : str) {
            card = s.split("\\s+");
            if (card[0].equals(this.nameCard)) {
                cardTemp = card[0];
                if (card.length > 1) pinTemp = card[1];
                break;
            }
        }
        if (cardTemp.length() == 0) {
            throw new CardException("Номер карты не найден", this.nameCard);
        } else {
            return pinTemp.equals(this.pin);
        }
    }
}
