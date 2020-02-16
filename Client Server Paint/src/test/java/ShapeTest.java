import model.Line;
import model.Oval;
import model.Rectangle;
import org.junit.Assert;
import org.junit.Test;

public class ShapeTest {
    @Test
    public void nameTest(){
        Assert.assertEquals("line", Line.NAME);
        Assert.assertEquals("oval", Oval.Name);
        Assert.assertEquals("rect", Rectangle.Name);
    }
}
