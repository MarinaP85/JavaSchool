import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.Assert;

public class MyIteratorTest {

    private static Integer[] array;

    @BeforeClass
    public static void BeforeTests() {
        array = new Integer[]{134, 1, 97, 5678, 0};
    }

    @Test
    public void TestMyIteratorNext() {
        MyIterator<Integer> myIterator = new MyIterator<>(array);
        for (int i = 0; i < array.length; i++) {
            Assert.assertTrue(myIterator.hasNext());
            Assert.assertNotNull(myIterator.next());
        }
        Assert.assertFalse(myIterator.hasNext());
        Assert.assertNull(myIterator.next());
    }

    @Test
    public void TestMyIteratorEqual() {
        int i = 0;
        MyIterator<Integer> myIterator = new MyIterator<>(array);
        while (myIterator.hasNext()) {
            Assert.assertEquals(array[i++], myIterator.next());
        }
    }

    @AfterClass
    public static void AfterTests() {
        array = null;
    }
}
