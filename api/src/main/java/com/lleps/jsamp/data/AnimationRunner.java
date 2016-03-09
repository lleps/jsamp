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
package com.lleps.jsamp.data;

import com.lleps.jsamp.FunctionAccess;

import java.time.Duration;

/**
 * @author spell
 */
public final class AnimationRunner {
    public static final AnimationRunner DEFAULT = new AnimationRunner(4.1f, false, false, false, Duration.ZERO);

    private float speed;
    private boolean repeatAfterFinish;
    private boolean keepPosition;
    private boolean freeze;
    private Duration duration;

    public AnimationRunner(float speed, boolean repeatAfterFinish, boolean keepPosition, boolean freeze, Duration duration) {
        this.speed = speed;
        this.repeatAfterFinish = repeatAfterFinish;
        this.keepPosition = keepPosition;
        this.freeze = freeze;
        this.duration = duration;
    }

    public float getSpeed() {
        return speed;
    }

    public AnimationRunner withSpeed(float speed) {
        return new AnimationRunner(speed, repeatAfterFinish, keepPosition, freeze, duration);
    }

    public AnimationRunner withRepeatAfterFinish(boolean repeatAfterFinish) {
        return new AnimationRunner(speed, repeatAfterFinish, keepPosition, freeze, duration);
    }

    public boolean isRepeatAfterFinish() {
        return repeatAfterFinish;
    }

    public boolean isKeepPosition() {
        return keepPosition;
    }

    public AnimationRunner withKeepPosition(boolean keepPosition) {
        return new AnimationRunner(speed, repeatAfterFinish, keepPosition, freeze, duration);
    }

    public boolean isFreeze() {
        return freeze;
    }

    public AnimationRunner withFreeze(boolean freeze) {
        return new AnimationRunner(speed, repeatAfterFinish, keepPosition, freeze, duration);
    }

    public Duration getDuration() {
        return duration;
    }

    public AnimationRunner withDuration(Duration duration) {
        return new AnimationRunner(speed, repeatAfterFinish, keepPosition, freeze, duration);
    }

    public void applyToActor(Animation animation, int actorId) {
        FunctionAccess.ApplyActorAnimation(actorId, animation.getLibrary(), animation.getName(), speed,
                repeatAfterFinish, !keepPosition, !keepPosition, freeze, (int)duration.toMillis());
    }

    public void applyToPlayer(Animation animation, int playerId, boolean forceSync) {
        FunctionAccess.ApplyAnimation(playerId, animation.getLibrary(), animation.getName(), speed,
                repeatAfterFinish, !keepPosition, !keepPosition, freeze, (int)duration.toMillis(), forceSync);
    }
}