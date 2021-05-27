package com.sber.jmm;

public interface ExecutionManager {
    /**
     * Метод execute принимает массив тасков, это задания которые ExecutionManager должен выполнять параллельно.
     *
     * @param callback - После завершения всех тасков должен выполниться callback (ровно 1 раз)
     * @param tasks    - массив тасков
     * @return - возвращает объект Context
     */
    Context execute(Runnable callback, Runnable... tasks);
}
