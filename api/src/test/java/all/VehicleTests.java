/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package all;

import com.lleps.jsamp.constant.model.VehicleModel;
import com.lleps.jsamp.data.Color;
import com.lleps.jsamp.data.Vector3D;
import com.lleps.jsamp.data.vehicle.VehicleDamageState;
import com.lleps.jsamp.world.Vehicle;
import junit.framework.TestCase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author spell
 */
public class VehicleTests extends TestCase {

    public void testVehicles() {
        Vehicle vehicle = new Vehicle(VehicleModel.LANDSTALKER, Vector3D.empty(), 128, Color.RED, Color.AMBER);
        vehicle.setPosition(Vector3D.of(15, 50, 15));
        assertEquals(vehicle.getPosition(), Vector3D.of(15, 50, 15));

        VehicleDamageState state = vehicle.getDamageState();
        state.setTirePopped(VehicleDamageState.TIRE_DRIVER, true);

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("vehiculos/.json"))) {
            outputStream.writeObject(vehicle);
        } catch (IOException e) { }
    }
}