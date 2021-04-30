import org.junit.Assert;
import org.junit.Test;
import ru.sbt.MyPlugin;
import ru.sbt.Plugin;
import ru.sbt.PluginManager;

public class MyPluginManagerTest {
    @Test
    public void loadPluginTest() {
        Plugin plugin0 = new MyPlugin();
        plugin0.doUseful();

        PluginManager pluginManager1 = new PluginManager(
                "G:\\IdeaProjects\\JavaSchoolM\\lesson7\\src\\main\\java\\ru\\plugin1");
        Plugin plugin1 = pluginManager1.load("MyPlugin.java", "ru.plugin1.MyPlugin");
        plugin1.doUseful();

        PluginManager pluginManager2 = new PluginManager(
                "G:\\IdeaProjects\\JavaSchoolM\\lesson7\\src\\main\\java\\ru\\plugin2");
        Plugin plugin2 = pluginManager2.load("MyPlugin.java", "ru.plugin2.MyPlugin");
        plugin2.doUseful();

        PluginManager pluginManager3 = new PluginManager(
                "G:\\IdeaProjects\\JavaSchoolM\\lesson7\\src\\main\\java\\ru\\sbt");
        Plugin plugin3 = pluginManager3.load("MyPlugin.java", "ru.sbt.MyPlugin");
        plugin3.doUseful();

        Assert.assertNotEquals(plugin0.getClass(), plugin1.getClass());
        Assert.assertNotEquals(plugin1.getClass(), plugin2.getClass());
        Assert.assertEquals(plugin0.getClass(), plugin3.getClass());
    }
}
