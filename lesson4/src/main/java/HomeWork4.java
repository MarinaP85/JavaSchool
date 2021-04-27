
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class HomeWork4 {
    /**
     * ...
     *
     * @throws IOException если не удается считать данные из базы аккаунтов.
     */
    public static void main(String[] args) throws IOException {
        String pin;
        String msgSum;
        int flagWD = -1;
        String nameCard;
        int flagSum = -1;
        int sum = 0;
        boolean locked = false;
        int flagLocked = 1;
        long secondDif;
        LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.now());

        System.out.println("Введите номер карты: ");
        Scanner in = new Scanner(System.in);
        nameCard = in.nextLine();
        System.out.println("Введите пинкод: ");
        pin = in.nextLine();
        TerminalImpl terminal = new TerminalImpl(nameCard, pin);
        String msg;

        do {
            msg = terminal.check(locked);
            if (msg.equals("Номер карты не найден")) {
                System.out.println(msg);
                System.out.println("Введите номер карты: ");
                nameCard = in.nextLine();
                System.out.println("Введите пинкод: ");
                pin = in.nextLine();
                terminal.setNameCard(nameCard);
                terminal.setPin(pin);
            }
            if (msg.equals("NOTOK")) {
                System.out.println("Не верный пинкод");
                flagLocked++;
                if (flagLocked > 3) {
                    locked = true;
                    today = LocalDateTime.of(LocalDate.now(), LocalTime.now()).plusSeconds(15);
                    flagLocked = 0;
                    System.out.println("Аккаунт временно заблокирован, ожидайте 15 секунд");
                }
                System.out.println("Введите пинкод: ");
                pin = in.nextLine();
                terminal.setPin(pin);
                if (locked && !LocalDateTime.of(LocalDate.now(), LocalTime.now()).isBefore(today)) {
                    locked = false;
                }
            }
            if (msg.equals("BLOCKED")) {
                secondDif = ChronoUnit.SECONDS.between(LocalDateTime.of(LocalDate.now(), LocalTime.now()), today);
                System.out.println("Аккаунт временно заблокирован, ожидайте " + secondDif + " секунд");
                System.out.println("Введите пинкод: ");
                pin = in.nextLine();
                terminal.setPin(pin);
                if (!LocalDateTime.of(LocalDate.now(), LocalTime.now()).isBefore(today)) {
                    locked = false;
                }
            }
        } while (!msg.equals("OK"));

        System.out.println("На вашем счете " + terminal.getMoney());
        while (flagWD == -1) {
            System.out.println("Выберите операцию (0 - снять деньги, 1 - положить деньги, 100 - выход): ");
            if (in.hasNextInt()) {
                flagWD = in.nextInt();
            } else {
                flagWD = 100;
            }
        }
        if (flagWD == 0) {
            while (flagSum == -1) {
                System.out.println("Снятие: введите сумму кратную 100 (-1  - выход): ");
                if (in.hasNextInt()) {
                    sum = in.nextInt();
                    if (sum == -1) {
                        System.out.println("выход");
                        break;
                    }
                    if (sum % 100 == 0) {
                        flagSum = 0;
                    }
                } else {
                    System.out.println("выход");
                    break;
                }
            }
            if (sum > 0) {
                msgSum = terminal.withdrawMoney(sum);
                System.out.println(msgSum);
            }
            System.out.println("На вашем счете " + terminal.getMoney());
        }
        if (flagWD == 1) {
            while (flagSum == -1) {
                System.out.println("Пополнение: введите сумму кратную 100 (-1  - выход): ");
                if (in.hasNextInt()) {
                    sum = in.nextInt();
                    if (sum == -1) {
                        System.out.println("выход");
                        break;
                    }
                    if (sum % 100 == 0) {
                        flagSum = 0;
                    }
                } else {
                    System.out.println("выход");
                    break;
                }
            }
            if (sum > 0) {
                msgSum = terminal.depositMoney(sum);
                System.out.println(msgSum);
            }
            System.out.println("На вашем счете " + terminal.getMoney());
        }
        if (flagWD > 1) {
            System.out.println("выход");
        }
    }
}
