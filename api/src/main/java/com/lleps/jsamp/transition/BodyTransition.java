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

import com.lleps.jsamp.data.Vector3D;
import com.lleps.jsamp.world.Body;

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
    private float speed;

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

    public BodyTransition setSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    public BodyTransition play(Body body) {
        if (fromPosition != null) {
            body.setPosition(fromPosition);
        }
        if (fromRotation != null) {
            body.setRotation(fromRotation);
        }

        if (toRotation == null) toRotation = Vector3D.of(-10000, -10000, -10000);
        return this;
    }
}

/**


 Body barreraPeaje = new Body(..)


 pickupAbrirBarrera.setOnPickupListener((player) -> {
    BodyTransition closeAnimation = new BodyTransition()
        .setToRitation(barreraPeaje.getRotation().withY(10)
        .setTime(Duration.seconds(2))
    BodyTransition openAnimation = new BodyTransition();
        .setToRotation(barreraPeaje.getRotation().withY(60))
        .setTime(Duration.seconds(2))
        .setOnFinishListener(b -> {
            player.sendMessage("la barrera se abrió, puede pasar");
            GameMode.runLater(Duration.seconds(7), () -> {
                closeAnimation.play();
            });
        }
        openAnimation.play(body);
 });
 }
 */