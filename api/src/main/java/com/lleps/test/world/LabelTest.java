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
package com.lleps.test.world;

import com.lleps.jsamp.FunctionAccess;
import com.lleps.jsamp.constant.model.BodyModel;
import com.lleps.jsamp.data.Color;
import com.lleps.jsamp.data.Vector3D;
import com.lleps.jsamp.data.bodymaterial.ColorMaterial;
import com.lleps.jsamp.data.bodymaterial.TextMaterial;
import com.lleps.jsamp.data.bodymaterial.TextureMaterial;
import com.lleps.jsamp.player.Player;
import com.lleps.jsamp.transition.BodyTransition;
import com.lleps.jsamp.world.entity.Body;
import com.lleps.jsamp.world.entity.Label;
import com.lleps.test.CommandListener;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author spell
 */
public class LabelTest implements CommandListener {
    public static Map<String, Label> labelsByKeys = new HashMap<>();

    @Override
    public boolean onCommand(Player player, String command, String[] args) {
        if (command.equals("/label_new")) {
            String key = args[0];
            Label label = new Label(player.getPosition(), Color.CYAN, args[1], 5, false);
            labelsByKeys.put(key, label);
            return true;
        }
        if (command.equals("/label_create")) {
            String key = args[0];
            labelsByKeys.get(key).create(player.getId(), FunctionAccess.GetPlayerVirtualWorld(player.getId()),
                    FunctionAccess.GetPlayerInterior(player.getId()));
            return true;
        }
        if (command.equals("/label_destroy")) {
            String key = args[0];
            labelsByKeys.get(key).destroy(player.getId());
            return true;
        }
        if (command.equals("/label_recreate")) {
            String key = args[0];
            labelsByKeys.get(key).recreate();
            return true;
        }
        if (command.equals("/label_color")) {
            String key = args[0];
            labelsByKeys.get(key).setColor(Color.ofRGBA(Integer.parseInt(args[1]),
                    Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4])));
            return true;
        }
        if (command.equals("/label_text")) {
            String key = args[0];
            labelsByKeys.get(key).setText(args[1]);
            return true;
        }
        if (command.equals("/label_testloss")) {
            String key = args[0];
            labelsByKeys.get(key).setTestLOS(Boolean.parseBoolean(args[1]));
            return true;
        }

        if (command.equals("/label_drawdistance")) {
            String key = args[0];
            labelsByKeys.get(key).setDrawDistance(Float.parseFloat(args[1]));
            return true;
        }

        if (command.equals("/label_position")) {
            String key = args[0];
            labelsByKeys.get(key).setPosition(player.getPosition());
            return true;
        }
        return false;
    }
}