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
import com.lleps.jsamp.SAMPConstants;
import com.lleps.jsamp.data.Vector3D;
import com.lleps.jsamp.player.Player;

import java.time.Duration;

/**
 * @author spell
 */
public class CameraTransition {
    public enum CutMode {
        CAMERA_CUT(SAMPConstants.CAMERA_CUT),
        CAMERA_MOVE(SAMPConstants.CAMERA_MOVE);

        private int id;

        CutMode(int id) {
            this.id = id;
        }
    }

    private CutMode cutMode;

    private Vector3D fromPosition, toPosition;
    private Vector3D fromLookAt, toLookAt;
    private Duration duration;

    public CameraTransition setFromPosition(Vector3D fromPosition) {
        this.fromPosition = fromPosition;
        return this;
    }

    public CameraTransition setToPosition(Vector3D toPosition) {
        this.toPosition = toPosition;
        return this;
    }

    public CameraTransition setFromLookAt(Vector3D fromLookAt) {
        this.fromLookAt = fromLookAt;
        return this;
    }

    public CameraTransition setToLookAt(Vector3D toLookAt) {
        this.toLookAt = toLookAt;
        return this;
    }

    public CameraTransition setDuration(Duration duration) {
        this.duration = duration;
        return this;
    }

    public void apply(Player player) {
        Vector3D fromPosition = this.fromPosition, toPosition = this.toPosition;
        Vector3D fromLookAt = this.fromLookAt, toLookAt = this.toLookAt;

        if (fromPosition == null) fromPosition = player.getPosition();
        if (toPosition == null) toPosition = player.getPosition();

        if (fromLookAt == null) fromLookAt = player.getCameraLookAt();
        if (toLookAt == null) toLookAt = player.getCameraLookAt();

        FunctionAccess.InterpolateCameraLookAt(player.getId(), fromLookAt.getX(), fromLookAt.getY(), fromLookAt.getZ(), toLookAt.getX(),
                toLookAt.getY(), toLookAt.getZ(), (int)duration.toMillis(), cutMode.id);

        FunctionAccess.InterpolateCameraPos(player.getId(), fromPosition.getX(), fromPosition.getY(), fromPosition.getZ(),
                toPosition.getX(), toPosition.getY(), toPosition.getZ(), (int)duration.toMillis(), cutMode.id);

    }
}