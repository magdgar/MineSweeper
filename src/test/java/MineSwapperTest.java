import junit.framework.TestCase;
import org.junit.Assert;


/**
 * Created by magdgar on 2016-03-13.
 */
public class MineSwapperTest extends TestCase {
    private MineSwapper mineSwapper;
    private String mineFiled;

    protected void setUp(){
        mineSwapper = new MineSwapper();
        mineFiled="";
    }

    public void testSetMineFiled(){
        try {
            mineSwapper.setMineFiled(mineFiled);
            fail( "My method didn't throw when I expected it to" );
        } catch (IllegalArgumentException illegalArgumentException) {}

        mineFiled = "..*.\n**p**\n...*";
        try {
            mineSwapper.setMineFiled(mineFiled);
            fail( "My method didn't throw when I expected it to" );
        } catch (IllegalArgumentException illegalArgumentException) {}

        mineFiled = "..**.\n****\n...*";
        try {
            mineSwapper.setMineFiled(mineFiled);
            fail( "My method didn't throw when I expected it to" );
        } catch (IllegalArgumentException illegalArgumentException) {}

        mineFiled = "..*.\n****\n...*";
        mineSwapper.setMineFiled(mineFiled);
        org.junit.Assert.assertTrue(mineSwapper.getMineFiled() == mineFiled);
    }

    public void testGetHintFiled(){
        try {
            mineSwapper.getHintFiled();
            fail( "My method didn't throw when I expected it to" );
        } catch (IllegalArgumentException illegalArgumentException){}

        mineFiled = "..*.\n****\n...*";
        mineSwapper.setMineFiled(mineFiled);
        Assert.assertTrue("24*3\n****\n234*".equals(mineSwapper.getHintFiled()));

        mineFiled = "*...\n..*.\n....\n";
        mineSwapper.setMineFiled(mineFiled);
        Assert.assertTrue("*211\n12*1\n0111".equals(mineSwapper.getHintFiled()));
    }
}
