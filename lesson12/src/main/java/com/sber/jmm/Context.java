package com.sber.jmm;

import java.util.concurrent.atomic.AtomicInteger;

public interface Context {
    /**
     * @return - возвращает количество тасков,
     * которые на текущий момент успешно выполнились
     */
    int getCompletedTaskCount();

    /**
     * @return - возвращает количество тасков,
     * при выполнении которых произошел Exception
     */
    int getFailedTaskCount();

    /**
     * @return - возвращает количество тасков,
     * которые не были выполены из-за отмены interrupt()
     */
    int getInterruptedTaskCount();

    /**
     * отменяет выполнения тасков,
     * которые еще не начали выполняться
     */
    void interrupt();

    /**
     * @return - вернет true, если все таски были выполнены или отменены,
     * false в противном случае
     */
    boolean isFinished();

}
