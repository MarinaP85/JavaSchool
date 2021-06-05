import com.sber.patterns.tractor.Field;
import com.sber.patterns.tractor.Orientation;
import com.sber.patterns.tractor.Position;
import com.sber.patterns.tractor.Tractor;
import org.junit.Assert;
import org.junit.Test;

public class PatternsTest {

    @Test
    public void tractorTest() {
        Tractor tractor=new Tractor();
        tractor.setPosition(new Position(1,2));
        tractor.setField(new Field(10, 10));
        tractor.move("F");
        tractor.move("F");
        tractor.move("T");
        Assert.assertEquals(tractor.getPosition().getX(), 1);
        Assert.assertEquals(tractor.getPosition().getY(), 4);
        Assert.assertEquals(tractor.getOrientation(), Orientation.EAST);
    }
}
