
public interface Calculator {
    /**
     * Расчет факториала числа.
     *
     * @param number - заданное число, для которого нужно вычислить факториал
     */
    @Cache
    @Metric
    int calc(int number);

}
