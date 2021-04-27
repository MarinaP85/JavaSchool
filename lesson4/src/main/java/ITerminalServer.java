
public interface ITerminalServer {

    //задание номера карты
    void setNameCard(String nameCard);

    //получение текущей суммы на счете
    int getMoney();

    //снятие денег со счета

    /**
     * ...
     *
     * @param sum сумма, которую следует снять со счета
     * @throws CardException если не найден номер карты в базе счетов.
     */
    void withdraw(int sum) throws CardException;

    //пополнение счета

    /**
     * ...
     *
     * @param sum сумма, которую следует положить на счет
     * @throws CardException если не найден номер карты в базе счетов.
     */
    void deposit(int sum) throws CardException;
}
