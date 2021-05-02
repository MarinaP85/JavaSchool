import org.junit.Assert;
import org.junit.Test;
import ru.sbt.*;

import java.io.File;

public class MyPluginManagerTest {
    @Test
    public void loadPluginTest() {
        Plugin plugin0 = new MyPlugin();
        plugin0.doUseful();

        PluginManager pluginManager1 = new PluginManager(
                "G:\\IdeaProjects\\JavaSchoolM\\lesson7\\target\\classes\\ru\\plugin1");
        Plugin plugin1 = pluginManager1.load("MyPlugin.class", "ru.plugin1.MyPlugin");
        plugin1.doUseful();

        PluginManager pluginManager2 = new PluginManager(
                "G:\\IdeaProjects\\JavaSchoolM\\lesson7\\target\\classes\\ru\\plugin2");
        Plugin plugin2 = pluginManager2.load("MyPlugin.class", "ru.plugin2.MyPlugin");
        plugin2.doUseful();

        PluginManager pluginManager3 = new PluginManager(
                "G:\\IdeaProjects\\JavaSchoolM\\lesson7\\target\\classes\\ru\\sbt");
        Plugin plugin3 = pluginManager3.load("MyPlugin.class", "ru.sbt.MyPlugin");
        plugin3.doUseful();

        Assert.assertNotEquals(plugin0.getClass(), plugin1.getClass());
        Assert.assertNotEquals(plugin1.getClass(), plugin2.getClass());
        Assert.assertEquals(plugin0.getClass(), plugin3.getClass());
    }

    @Test
    public void EncryptedClassLoaderTest() {
        Plugin plugin0 = new MyPlugin();
        plugin0.doUseful();

        String key = "93568";
        Plugin plugin = new MyPlugin();
        try {
            String dir = "G:\\IdeaProjects\\JavaSchoolM\\lesson7\\target\\classes";
            File fileClass = new File(dir + "\\ru\\sbt\\MyPlugin.class");
            if (!fileClass.isFile())
                throw new ClassNotFoundException("Не найден класс");
            FileDecoder.encodeFile(fileClass, key);
            EncryptedClassLoader pluginLoader = new EncryptedClassLoader(key, dir, ClassLoader.getSystemClassLoader());
            plugin = (Plugin) pluginLoader.findClass("ru.sbt.MyPlugin").newInstance();
            plugin.doUseful();
        } catch (ClassNotFoundException e) {
            System.err.println("Не найден класс");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.err.println("Ошибка доступа");
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.err.println("Ошибка при попытке создания объекта");
            e.printStackTrace();
        }

        Assert.assertNotEquals(plugin0.getClass(), plugin.getClass());
    }
}
