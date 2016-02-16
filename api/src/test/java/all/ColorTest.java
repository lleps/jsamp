package all;

import com.lleps.jsamp.data.Color;
import junit.framework.TestCase;

/**
 * @author Leandro on 27/1/2016.
 */
public class ColorTest extends TestCase {

    public void testConstructors() {
        assertEquals(Color.ofRGB(15, 20, 25), Color.ofRGBA(15, 20, 25, 255));
        assertEquals(Color.ofRGB(0xFFFFFF), Color.ofRGBA(255, 255, 255, 255));
        assertEquals(Color.ofRGBA(0xFFFFFF00), Color.ofRGBA(255, 255, 255, 0));
    }

    public void testImmutability() {
        Color color0 = Color.CYAN;
        Color color1 = Color.ofRGB(color0.getRed(), color0.getGreen(), color0.getBlue());
        assertSame(color0, color1);
    }

    public void testToHexMethods() {
        Color getMethod = Color.ofRGBA(0xAABBCCDD);
        assertEquals("as RGB", 0xAABBCC, getMethod.getRGB());
        assertEquals("as RGBA", 0xAABBCCDD, getMethod.getRGBA());
        assertEquals("as ARGB", 0xDDAABBCC, getMethod.getARGB());
    }

    public void testWithMethods() {
        Color withMethod = Color.ofRGB(0, 0, 0);
        assertEquals(withMethod.withRed(5), Color.ofRGB(5, 0, 0));
        assertEquals(withMethod.withGreen(5), Color.ofRGB(0, 5, 0));
        assertEquals(withMethod.withBlue(5), Color.ofRGB(0, 0, 5));
        assertEquals(withMethod.withAlpha(5), Color.ofRGBA(0, 0, 0, 5));
    }

    public void testVehColors() {
        Color vehColor = Color.ofRGBA(0x2A77A1FF);
        assertEquals(2, vehColor.getVehicleColorID());
    }
}