package all;

import com.lleps.jsamp.data.Vector2D;
import com.lleps.jsamp.data.Vector3D;
import com.lleps.jsamp.data.Vector4D;
import junit.framework.TestCase;

/**
 * Created by Leandro on 29/11/2015.
 */
public class VectorTest extends TestCase {
    public void testVectors() {
        assertEquals(Vector3D.of(1, 2, 3), Vector3D.of(1, 2, 3));
        assertEquals(Vector2D.of(1, 5), Vector2D.of(1, 4).withY(5));
        assertSame(Vector4D.of(1, 2, 3, 4).plusW(1), Vector4D.of(1, 2, 3, 5));
    }
}
