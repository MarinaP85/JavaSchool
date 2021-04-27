
import java.io.IOException;

public interface IPinValidator {

    //задание номера карты
    void setNameCard(String nameCard);

    //задание номера PIN
    void setPin(String pin);

    //проверка аккаунта

    /**
     * ...
     *
     * @param locked определяет следует ли заблокировать аккаунт
     *               в случае превышения количества попыток ввода пин-кода
     * @return возвращает истина, если проверка пройдена успешно
     * ложь, если не верный номер карты, пин-код или аккаунт заблокирован
     * @throws IOException              если не удается считать данные из базы аккаунтов,
     * @throws CardException            если не найден номер карты в базе аккаунтов,
     * @throws AccountIsLockedException если аккаунт заблокирован из-за
     *                                  превышения количества попыток ввода пин-кода.
     */
    boolean checkAccount(boolean locked) throws CardException, IOException, AccountIsLockedException;


}
