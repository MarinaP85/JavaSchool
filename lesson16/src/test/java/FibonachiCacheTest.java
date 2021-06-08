import com.sber.jdbc.connection.DataSourceHelper;
import com.sber.jdbc.connection.H2DB;
import com.sber.jdbc.dao.FibonachiDao;
import com.sber.jdbc.dao.FibonachiDaoImpl;
import com.sber.jdbc.proxy.CacheProxyHandler;
import com.sber.jdbc.service.Service;
import com.sber.jdbc.service.ServiceImpl;
import org.h2.tools.Server;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class FibonachiCacheTest {
    private static Connection connection;

    @BeforeClass
    public static void createDao() throws SQLException {
        H2DB db = new H2DB();
        connection = db.connection();
        //DataSourceHelper.createDb(connection);
        Server.createTcpServer().start();
    }

    @Test
    public void fibonachiTest() {
        Service service = (Service) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                ServiceImpl.class.getInterfaces(), new CacheProxyHandler(new ServiceImpl()));

        System.out.println("7F = " + service.fibonachi(new ArrayList<>(), 7).toString());
        System.out.println("9F = " + service.fibonachi(new ArrayList<>(), 9).toString());
        System.out.println("5F = " + service.fibonachi(new ArrayList<>(), 5).toString());
    }

    @Test
    public void fibonachiFromBaseTest() {
        FibonachiDao fibonachiDao = new FibonachiDaoImpl(connection);
        System.out.println(fibonachiDao.getAllFibonachi().toString());
    }
}
