
public class CardException extends Exception {

    private final String nameCard;

    public CardException(String message, String nameCard) {
        super(message);
        this.nameCard = nameCard;
    }

    public String getNameCard() {
        return this.nameCard;
    }
}
