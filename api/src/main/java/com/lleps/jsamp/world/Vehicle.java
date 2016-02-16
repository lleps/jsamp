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
package com.lleps.jsamp.world;

import com.lleps.jsamp.SAMPConstants;
import com.lleps.jsamp.FunctionAccess;
import com.lleps.jsamp.constant.Paintjob;
import com.lleps.jsamp.constant.SpeedUnit;
import com.lleps.jsamp.constant.VehicleSeat;
import com.lleps.jsamp.constant.model.VehicleComponent;
import com.lleps.jsamp.constant.model.VehicleModel;
import com.lleps.jsamp.data.Color;
import com.lleps.jsamp.data.Vector;
import com.lleps.jsamp.data.Vector3D;
import com.lleps.jsamp.data.Vector4D;
import com.lleps.jsamp.data.vehicle.DoorState;
import com.lleps.jsamp.data.vehicle.VehicleDamageState;
import com.lleps.jsamp.data.vehicle.VehicleParams;
import com.lleps.jsamp.data.vehicle.WindowState;
import com.lleps.jsamp.gamemode.ObjectNativeIDS;
import com.lleps.jsamp.player.Player;

import java.util.*;

/**
 * @author spell
 */
public class Vehicle extends GlobalEntity {
    public static Optional<Vehicle> getById(int id) {
        return Optional.ofNullable(ObjectNativeIDS.get(ObjectNativeIDS.getInstance().vehicles,
                id));
    }

    public interface OnPlayerEnterAnimListener { void onPlayerEnterAnim(Vehicle vehicle, Player player, VehicleSeat seat); }
    public interface OnPlayerExitAnimListener { void onPlayerExitAnim (Vehicle vehicle, Player player); }

    public interface OnPlayerEnteredListener { void onPlayerEntered(Vehicle vehicle, Player player, VehicleSeat seat); }
    public interface OnPlayerExitedListener { void onPlayerExited(Vehicle vehicle, Player player); }

    public interface OnSpawnListener { void onSpawn(Vehicle vehicle); }
    public interface OnDeathListener { void onDeath(Vehicle vehicle, Optional<Player> killer); }

    public interface OnSirenStateChangeListener { void onSirenStateChange(Player driver, boolean started); }

    public interface OnCrashListener { void onCrash(Vehicle vehicle, Player driver, float oldHealth, float newHealth); }

    public interface OnModBuyListener { void onModBuy(Vehicle vehicle, Player player, VehicleComponent component, int paidPrice); }

    // TODO: listeners
    public static final int DEFAULT_RESPAWN_DELAY_MS = 1000 * 60 * 3;
    public static final float VEHICLE_MAX_HEALTH = 1_000f;

    private VehicleModel model;
    private Vector3D spawnPosition;
    private float spawnAngle;
    private Vector3D position;
    private float angle;
    private Color primaryColor;
    private Color secondaryColor;
    private int respawnDelay;
    private boolean hasSiren;
    private Paintjob paintjob;
    private String plate;
    private Map<VehicleComponent.Slot, VehicleComponent> components;
    private VehicleDamageState damageState;
    private VehicleParams params;
    private DoorState doorState;
    private WindowState windowState;
    private float health;

    public Vehicle() {
        components = new HashMap<>();
        damageState = new VehicleDamageState();
        params = new VehicleParams();
        doorState = new DoorState();
        windowState = new WindowState();
        health = VEHICLE_MAX_HEALTH;
        plate = "";
    }

    public Vehicle(VehicleModel model, Vector3D position, float angle, Color primaryColor) {
        this(model, position, angle, primaryColor, Color.WHITE);
    }

    public Vehicle(VehicleModel model, Vector3D position, float angle, Color primaryColor, Color secondaryColor) {
        this();
        this.model = model;
        this.position = position;
        this.angle = angle;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.respawnDelay = DEFAULT_RESPAWN_DELAY_MS;
        this.paintjob = Paintjob.DEFAULT;

        this.spawnPosition = position;
        this.spawnAngle = angle;
    }

    public VehicleModel getModel() {
        return model;
    }

    public void setModel(VehicleModel model) {
        this.model = model;
    }

    @Override
    public Vector getPosition() {
        if (isCreated()) position = Vector3D.of(FunctionAccess.GetVehiclePos(id));
        return position;
    }

    public void setPosition(Vector3D position) {
        dispatchPositionChange(this.position, position);

        this.position = position;
        if (isCreated()) {
            FunctionAccess.SetVehiclePos(id, position.getX(), position.getY(), position.getZ());
        }
    }

    public void setAngle(float angle) {
        this.angle = angle;
        if (isCreated()) FunctionAccess.SetVehicleZAngle(id, angle);
    }

    public float getAngle() {
        if (isCreated()) angle = FunctionAccess.GetVehicleZAngle(id);
        return angle;
    }

    public void setPrimaryColor(Color primaryColor) {
        this.primaryColor = primaryColor;
        if (isCreated()) FunctionAccess.ChangeVehicleColor(id,
                primaryColor.getVehicleColorID(), secondaryColor.getVehicleColorID());
    }

    public Color getPrimaryColor() {
        return primaryColor;
    }

    public void setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = secondaryColor;
        if (isCreated()) FunctionAccess.ChangeVehicleColor(id,
                primaryColor.getVehicleColorID(), secondaryColor.getVehicleColorID());
    }

    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public void setRespawnDelay(int respawnDelay) {
        this.respawnDelay = respawnDelay;
    }

    public int getRespawnDelay() {
        return respawnDelay;
    }

    public void setHasSiren(boolean hasSiren) {
        this.hasSiren = hasSiren;
    }

    public boolean hasSiren() {
        return hasSiren;
    }

    public void setPaintjob(Paintjob paintjob) {
        this.paintjob = paintjob;
        if (isCreated() && paintjob.isCompatibleWith(model))
            FunctionAccess.ChangeVehiclePaintjob(id, paintjob.getId());
    }

    public Paintjob getPaintjob() {
        return paintjob;
    }

    public class IllegalComponentException extends Exception {
        VehicleComponent component;

        IllegalComponentException(VehicleComponent component) {
            this.component = component;
        }

        public VehicleComponent getComponent() {
            return component;
        }
    }

    public void addComponent(VehicleComponent component) throws IllegalComponentException {
        if (!component.isCompatibleWith(model)) throw new IllegalComponentException(component);

        components.put(component.getSlot(), component);
        if (isCreated()) FunctionAccess.AddVehicleComponent(id, component.getComponentId());
    }

    public Optional<VehicleComponent> getComponent(VehicleComponent.Slot slot) {
        return Optional.ofNullable(components.get(slot));
    }

    public void removeComponent(VehicleComponent component) {
        removeComponent(component.getSlot());
    }

    public void removeComponent(VehicleComponent.Slot slot) {
        VehicleComponent component = components.get(slot);
        if (component != null) {
            components.remove(slot);
            if (isCreated()) FunctionAccess.RemoveVehicleComponent(id, component.getComponentId());
        }
    }

    public void setDamageState(VehicleDamageState damageState) {
        this.damageState = damageState;
        if (isCreated()) {
            damageState.apply(id);
        }
    }

    public VehicleDamageState getDamageState() {
        if (isCreated()) damageState = new VehicleDamageState(FunctionAccess.GetVehicleDamageStatus(id));
        // Return a copy of the object, since the original object is used internally and should not be modified
        // by user.
        return damageState.clone();
    }

    public void setPlate(String plate) {
        this.plate = plate;
        if (isCreated()) FunctionAccess.SetVehicleNumberPlate(id, plate);
    }

    public String getPlate() {
        return plate;
    }

    public void setParams(VehicleParams params) {
        this.params = params;

        if (isCreated()) {
            params.apply(id);
        }
    }

    public VehicleParams getParams() {
        return params.clone();
    }

    public void setDoorState(DoorState doorState) {
        this.doorState = doorState;
        if (isCreated()) doorState.apply(id);
    }

    public DoorState getDoorState() {
        return doorState.clone();
    }

    public void setWindowState(WindowState windowState) {
        this.windowState = windowState;
        if (isCreated()) windowState.apply(id);
    }

    public WindowState getWindowState() {
        return windowState.clone();
    }

    public void setHealth(float health) {
        this.health = health;
        if (isCreated()) {
            FunctionAccess.SetVehicleHealth(id, health);
        }
    }

    public float getHealth() {
        if (isCreated()) health = FunctionAccess.GetVehicleHealth(id);
        return health;
    }

    public void setSpawnPosition(Vector3D spawnPosition) {
        this.spawnPosition = spawnPosition;
    }

    public Vector3D getSpawnPosition() {
        return spawnPosition;
    }

    public void setSpawnAngle(float spawnAngle) {
        this.spawnAngle = spawnAngle;
    }

    public float getSpawnAngle() {
        return spawnAngle;
    }

    public void respawn() {
        // fix all
        damageState.fixAll();
        health = VEHICLE_MAX_HEALTH;

        // set position to spawn position
        this.position = spawnPosition;
        this.angle = spawnAngle;

        // recreate
        recreate();
    }

    public Vector3D getLinearVelocity(SpeedUnit unit) {
        if (isCreated()) {
            float[] xyz = FunctionAccess.GetVehicleVelocity(id);
            return Vector3D.of(unit.forNativeSpeed(xyz[0]), unit.forNativeSpeed(xyz[1]),
                    unit.forNativeSpeed(xyz[2]));
        }
        return Vector3D.empty();
    }

    public void setLinearVelocity(Vector3D velocity, SpeedUnit speedUnit) {
        if (isCreated()) {
            FunctionAccess.SetVehicleVelocity(id, speedUnit.forNativeSpeed(velocity.getX()),
                    speedUnit.forNativeSpeed(velocity.getY()),
                    speedUnit.forNativeSpeed(velocity.getZ()));
        }
    }

    public void attachTrailer(Vehicle trailer) {
        if (isCreated()) {
            FunctionAccess.AttachTrailerToVehicle(trailer.getId(), id);
        }
    }

    public void detachCurrentTrailer() {
        if (isCreated()) {
            FunctionAccess.DetachTrailerFromVehicle(id);
        }
    }

    public Optional<Vehicle> getAttachedTrailer() {
        if (isCreated()) {
            return getById(FunctionAccess.GetVehicleTrailer(id));
        }
        return Optional.empty();
    }

    public void setAngularVelocity(Vector3D velocity, SpeedUnit speedUnit) {
        if (isCreated()) {
            FunctionAccess.SetVehicleAngularVelocity(id, speedUnit.forNativeSpeed(velocity.getX()),
                    speedUnit.forNativeSpeed(velocity.getY()),
                    speedUnit.forNativeSpeed(velocity.getZ()));
        }
    }

    public Vector4D getRotation() {
        if (isCreated()) {
            return Vector4D.of(FunctionAccess.GetVehicleRotationQuat(id));
        }
        return Vector4D.empty();
    }

    @Override
    protected int createNatively(int worldId) {
        return FunctionAccess.CreateVehicle(model.getModelId(), position.getX(), position.getY(),
                position.getZ(), angle, primaryColor.getVehicleColorID(), secondaryColor.getVehicleColorID(),
                respawnDelay, hasSiren);
    }

    @Override
    protected void applyState(int id, int worldId, int interiorId) {
        super.applyState(id, worldId, interiorId);

        if (paintjob != Paintjob.DEFAULT) FunctionAccess.ChangeVehiclePaintjob(id, paintjob.getId());

        components.forEach((slot, component) -> FunctionAccess.AddVehicleComponent(id, component.getComponentId()));

        FunctionAccess.SetVehicleVirtualWorld(id, worldId);
        FunctionAccess.LinkVehicleToInterior(id, interiorId);

        if (!damageState.isAllFixed()) damageState.apply(id);

        if (!params.isAllOff()) params.apply(id);

        if (!doorState.isAllClosed()) doorState.apply(id);

        if (!windowState.isAllClosed()) windowState.apply(id);

        if (health != VEHICLE_MAX_HEALTH) FunctionAccess.SetVehicleHealth(id, health);

        FunctionAccess.SetVehicleNumberPlate(id, plate);
    }

    @Override
    protected void saveState(int id) {
        super.saveState(id);
        getPosition();
        getAngle();
        getDamageState();
        getHealth();
    }

    @Override
    protected int getInvalidId() {
        return SAMPConstants.INVALID_VEHICLE_ID;
    }

    @Override
    protected void destroyNatively(int id) {
        FunctionAccess.DestroyVehicle(id);
    }

    @Override
    protected Object[] getIDSArray() {
        return ObjectNativeIDS.getInstance().vehicles;
    }
}