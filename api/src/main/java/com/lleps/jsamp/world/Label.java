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

import com.lleps.jsamp.server.ObjectNativeIDS;
import com.lleps.jsamp.SAMPConstants;
import com.lleps.jsamp.FunctionAccess;
import com.lleps.jsamp.data.Color;
import com.lleps.jsamp.data.Vector;
import com.lleps.jsamp.data.Vector3D;

/**
 * @author spell
 */
public class Label extends PerPlayerEntity {
    private Vector3D position;
    private Color color;
    private String text;
    private float drawDistance;
    private boolean testLOS;

    public Label(Vector3D position, Color color, String text, float drawDistance) {
        this(position, color, text, drawDistance, true);
    }

    public Label(Vector3D position, Color color, String text, float drawDistance, boolean testLOS) {
        this.position = position;
        this.color = color;
        this.text = text;
        this.drawDistance = drawDistance;
        this.testLOS = testLOS;
    }

    public void setPosition(Vector3D position) {
        this.position = position;
    }

    @Override
    public Vector getPosition() {
        return position;
    }

    public void setText(String text) {
        this.text = text;
        idsByPlayerIds.forEach((playerId, labelId) -> FunctionAccess.UpdatePlayer3DTextLabelText(playerId,
                labelId, color.getRGBA(), text));
    }

    public String getText() {
        return text;
    }

    public void setColor(Color color) {
        this.color = color;
        idsByPlayerIds.forEach((playerId, labelId) -> FunctionAccess.UpdatePlayer3DTextLabelText(playerId,
                labelId, color.getRGBA(), text));
    }

    public Color getColor() {
        return color;
    }

    public void setDrawDistance(float drawDistance) {
        this.drawDistance = drawDistance;
    }

    public float getDrawDistance() {
        return drawDistance;
    }

    public void setTestLOS(boolean testLOS) {
        this.testLOS = testLOS;
    }

    public boolean isTestLOS() {
        return testLOS;
    }

    @Override
    protected int createNatively(int playerId) {
        return FunctionAccess.CreatePlayer3DTextLabel(playerId, text, color.getRGBA(),
                position.getX(), position.getY(), position.getZ(), drawDistance, SAMPConstants.INVALID_PLAYER_ID,
                SAMPConstants.INVALID_VEHICLE_ID, testLOS);
    }

    @Override
    protected void destroyNatively(int playerId, int entityId) {
        FunctionAccess.DeletePlayer3DTextLabel(playerId, entityId);
    }

    @Override
    protected Object[][] getArrayNativeIDS() {
        return ObjectNativeIDS.getInstance().playerLabels;
    }

    @Override
    protected int getInvalidId() {
        return SAMPConstants.INVALID_3DTEXT_ID;
    }
}