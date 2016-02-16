package all;

import com.lleps.jsamp.data.vehicle.VehicleDamageState;
import junit.framework.TestCase;

/**
 * @author Leandro on 10/2/2016.
 */
public class VehicleDamageStateTest extends TestCase {
    public void disabled_testTires() {
        VehicleDamageState state = new VehicleDamageState(0, 0, 0, 0b1111);
        assertEquals(0b1111, state.getTires());
        state.setTirePopped(VehicleDamageState.TIRE_PASSENGER, true);
        assertEquals(0b1101, state.getTires());
    }

    public void disabled_testDoors() {
        VehicleDamageState state = new VehicleDamageState(0, 0, 0b1111, 0);
        assertEquals(0b1111, state.getLights());
        state.setLightDestroyed(VehicleDamageState.LIGHT_PASSENGER, true);
        assertEquals(0b1011, state.getLights());
    }

    public void testPanels() {

    }

    public void testLights() {

    }
}
