
public class CalculatorImpl implements Calculator {

    public static final String FACTORIAL = "FACTORIAL";
    private static final String FACTORIAL2 = "FACTORIAL2";

    public int calc(int number) {
        int factorial = 1;
        for (int i = 2; i <= number; i++) {
            factorial = factorial * i;
        }
        return factorial;
    }
}
