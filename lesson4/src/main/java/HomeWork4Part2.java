
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class HomeWork4Part2 {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String url;

        do {
            System.out.println("Введите URL");
            url = scanner.next();
            try {
                readContent(url);
                break;
            } catch (MalformedURLException e) {
                System.out.println("Указан некорректный URL.");
            } catch (UnknownHostException e) {
                System.out.println("Неизвестный хост!");
            } catch (IOException e) {
                System.out.println("Невозможно прочитать контент. " + e.getMessage());
                throw e;
            }
            System.out.println("Попробуйте снова.");
        } while (true);
    }

    private static void readContent(String url) throws IOException {
        Object content = new URL(url).getContent();
        if (content instanceof InputStream) {
            InputStream contentStream = (InputStream) content;
            try (Scanner contentScanner = new Scanner(contentStream)) {
                while (contentScanner.hasNext()) {
                    System.out.println(contentScanner.next());
                }
            }
        } else {
            throw new IOException("Невозможно прочитать контент.");
        }
    }
}
