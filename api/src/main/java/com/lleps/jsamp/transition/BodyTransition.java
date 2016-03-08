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
package com.lleps.jsamp.transition;

import com.lleps.jsamp.FunctionAccess;
import com.lleps.jsamp.SAMPFunctions;
import com.lleps.jsamp.data.Vector3D;
import com.lleps.jsamp.server.SAMPServer;
import com.lleps.jsamp.world.entity.Body;
import org.apache.commons.lang3.RandomUtils;

import java.time.Duration;

/**
 * A class for add movement to a body.
 *
 * @author spell
 */
public class BodyTransition {
    public interface OnFinishListener {
        void onFinish(Body body);
    }

    private Vector3D fromPosition, fromRotation;
    private Vector3D toPosition, toRotation;

    private Duration duration;
    private float speed;

    private enum MovementType {TYPE_DURATION, TYPE_SPEED};

    private MovementType movementType;

    private OnFinishListener onFinishListener;

    public BodyTransition setFromPosition(Vector3D fromPosition) {
        this.fromPosition = fromPosition;
        return this;
    }

    public BodyTransition setToPosition(Vector3D toPosition) {
        this.toPosition = toPosition;
        return this;
    }

    public BodyTransition setFromRotation(Vector3D fromRotation) {
        this.fromRotation = fromRotation;
        return this;
    }

    public BodyTransition setToRotation(Vector3D toRotation) {
        this.toRotation = toRotation;
        return this;
    }

    public BodyTransition setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
        return this;
    }

    public BodyTransition setDuration(Duration duration) {
        this.duration = duration;
        movementType = MovementType.TYPE_DURATION;
        return this;
    }

    public BodyTransition setSpeed(float speed) {
        this.speed = speed;
        movementType = MovementType.TYPE_SPEED;
        return this;
    }

    public BodyTransition play(Body body) {
        Vector3D fromPos = fromPosition, toPos = toPosition;
        Vector3D fromRot = fromRotation, toRot = toRotation;

        if (fromPos != null) {
            if (!body.getPosition().equals(fromPos)) { // Set only if position changed
                body.setPosition(fromPos);
            }
        } else {
            fromPos = body.getPosition();
        }

        if (toPos == null) {
            // if ToPos is not specified, position will not change.
            toPos = body.getPosition();
        }

        if (fromRot != null) {
            if (!body.getRotation().equals(fromRot)) {
                body.setRotation(fromRot);
            }
        } else {
            fromRot = body.getRotation();
        }

        if (toRot == null) {
            toRot = body.getRotation();
        }

        float calculatedSpeed = 0;
        float timeToMove = 0;
        if (movementType == MovementType.TYPE_DURATION) {
            float distanceToMove = fromPos.distanceTo(toPos);

            // only rotation changed?  add a little z movement
            if (distanceToMove == 0) {
                SAMPFunctions.SendClientMessageToAll(-1, "dist 2 move is 0");
                float toAdd = (RandomUtils.nextInt(0, 2) == 0) ? -0.01f : 0.01f;
                toPos = toPos.plusZ(toAdd);
                distanceToMove = fromPos.distanceTo(toPos);
            }

            // Calculate speed based on time to move.
            calculatedSpeed = distanceToMove / (duration.toMillis()/1000f);
        } else {
            calculatedSpeed = speed;
        }

        int msToMove = body.move(toPos, toRot, calculatedSpeed);

        SAMPServer.runLater(Duration.ofMillis(msToMove), () -> {
            if (onFinishListener != null) onFinishListener.onFinish(body);
        });
        return this;
    }
}