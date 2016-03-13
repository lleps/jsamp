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
package com.lleps.jsamp.world.entity;

import com.lleps.jsamp.FunctionAccess;
import com.lleps.jsamp.SAMPConstants;
import com.lleps.jsamp.SAMPFunctions;
import com.lleps.jsamp.constant.BodyPart;
import com.lleps.jsamp.constant.SkinModel;
import com.lleps.jsamp.constant.model.WeaponModel;
import com.lleps.jsamp.data.Animation;
import com.lleps.jsamp.data.AnimationRunner;
import com.lleps.jsamp.data.Vector3D;
import com.lleps.jsamp.player.Player;
import com.lleps.jsamp.server.ObjectNativeIDS;
import com.lleps.jsamp.server.SAMPServer;

import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author spell
 */
public class Actor extends GlobalEntity {
    public static Optional<Actor> getById(int id) {
        return Optional.ofNullable(ObjectNativeIDS.get(ObjectNativeIDS.getInstance().actors, id));
    }

    public interface OnDamagedListener {
        /**
         * Called when a player shots an actor. Note that this is not called if actor is invulnerable.
         * @param actor actor damaged
         * @param damager damager
         * @param lostHealth health lost.
         * @param weaponModel weapon
         * @param bodyPart body part
         * @return false if you want to damage the actor, true to ignore the shot.
         */
        boolean onDamaged(Actor actor, Player damager, float lostHealth, WeaponModel weaponModel, BodyPart bodyPart);
    }


    public interface OnDeathListener {
        void onDeath(Actor actor, Player killer);
    }

    private SkinModel skin;
    private Vector3D position;
    private float facingAngle;
    private float health = 100;
    private boolean invulnerable = true;

    private OnDamagedListener onDamagedListener;
    private OnDeathListener onDeathListener;

    private Duration timeToRespawn = Duration.ofSeconds(5);

    private Animation animation;
    private AnimationRunner animationRunner;

    private Set<WorldEntity> attachedEntities = new HashSet<>();

    public Actor(SkinModel skin, Vector3D position, float facingAngle) {
        this.skin = skin;
        this.position = position;
        this.facingAngle = facingAngle;
    }

    public void setSkin(SkinModel skin) {
        this.skin = skin;
    }

    public SkinModel getSkin() {
        return skin;
    }

    public void setPosition(Vector3D position) {
        this.position = position;
        if (isCreated()) FunctionAccess.SetActorPos(id, position.getX(), position.getY(), position.getZ());
    }

    @Override
    public Vector3D getPosition() {
        return position;
    }

    public void setTimeToRespawn(Duration timeToRespawn) {
        this.timeToRespawn = timeToRespawn;
    }

    public Duration getTimeToRespawn() {
        return timeToRespawn;
    }

    public void setFacingAngle(float facingAngle) {
        this.facingAngle = facingAngle;
        if (isCreated()) FunctionAccess.SetActorFacingAngle(id, facingAngle);
    }

    public float getFacingAngle() {
        return facingAngle;
    }

    public void setHealth(float health) {
        this.health = health;
        if (isCreated()) {
            FunctionAccess.SetActorHealth(id, health);
        }
    }

    public float getHealth() {
        if (isCreated()) health = FunctionAccess.GetActorHealth(id);
        return health;
    }

    public void setInvulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;

        if (isCreated()) FunctionAccess.SetActorInvulnerable(id, invulnerable);
    }

    public boolean isInvulnerable() {
        return invulnerable;
    }

    @Override
    protected int getInvalidId() {
        return SAMPConstants.INVALID_ACTOR_ID;
    }

    public void setOnDamagedListener(OnDamagedListener onDamagedListener) {
        this.onDamagedListener = onDamagedListener;
    }

    public boolean onDamaged(Player damager, float lostHealth, WeaponModel weaponModel, BodyPart bodyPart) {
        return onDamagedListener != null && onDamagedListener.onDamaged(this, damager, lostHealth, weaponModel, bodyPart);
    }

    public void setOnDeathListener(OnDeathListener onDeathListener) {
        this.onDeathListener = onDeathListener;
    }

    public void onDeath(Player killer) {
        if (onDeathListener != null) onDeathListener.onDeath(this, killer);

        SAMPServer.runLater(timeToRespawn, () -> {
            health = 100;
            recreate();
        });
    }

    public void setAnimation(Animation animation, AnimationRunner animationRunner) {
        if (animationRunner.isRepeatAfterFinish()) {
            this.animation = animation;
            this.animationRunner = animationRunner;
        } else {
            this.animation = null;
            this.animationRunner = null;
        }

        animationRunner.applyToActor(animation, id);
    }

    public Animation getAnimation() {
        return animation;
    }

    public void attachLabel(Label label, Vector3D offSets) {
        attachedEntities.add(label);
        AttachedData<Actor> attachedData = new AttachedData<>(this, offSets);
        label.setAttachedActor(attachedData);

        if (isCreated()) playerIds.forEach(playerId -> label.create(playerId, 0, 0));
    }

    public void detachLabel(Label label) {
        if (attachedEntities.remove(label)) {
            label.setAttachedActor(null);
            if (isCreated()) playerIds.forEach(label::destroy);
        }
    }

    public Set<WorldEntity> getAttachedEntities() {
        return Collections.unmodifiableSet(attachedEntities);
    }

    @Override
    public boolean create(int playerId, int worldId, int interiorId) {
        boolean created = super.create(playerId, worldId, interiorId);
        if (created) {
            attachedEntities.forEach(e -> e.create(playerId, worldId, interiorId));
        }
        return created;
    }

    @Override
    public void destroy(int playerId) {
        super.destroy(playerId);
        attachedEntities.forEach(e -> e.destroy(playerId));
    }

    @Override
    protected int createNatively(int worldId) {
        return FunctionAccess.CreateActor(skin.getId(), position.getX(), position.getY(), position.getZ(), facingAngle);
    }

    @Override
    protected void applyState(int id, int worldId, int interiorId) {
        super.applyState(id, worldId, interiorId);

        FunctionAccess.SetActorVirtualWorld(id, worldId);
        FunctionAccess.SetActorInvulnerable(id, invulnerable);
        FunctionAccess.SetActorHealth(id, health);

        if (animation != null && animationRunner != null) {
            animationRunner.applyToActor(animation, id);
        }
    }

    @Override
    protected void destroyNatively(int id) {
        FunctionAccess.DestroyActor(id);
    }

    @Override
    protected Object[] getIDSArray() {
        return ObjectNativeIDS.getInstance().actors;
    }
}