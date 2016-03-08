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
import com.lleps.jsamp.constant.model.VehicleModel;
import com.lleps.jsamp.data.Color;
import com.lleps.jsamp.data.Vector3D;
import com.lleps.jsamp.data.bodymaterial.ColorMaterial;
import com.lleps.jsamp.data.bodymaterial.TextMaterial;
import com.lleps.jsamp.data.bodymaterial.TextureMaterial;
import com.lleps.jsamp.player.Player;
import com.lleps.jsamp.server.SAMPServer;
import com.lleps.jsamp.transition.BodyTransition;
import com.lleps.jsamp.world.entity.Body;
import com.lleps.jsamp.world.entity.Vehicle;
import com.lleps.test.CommandListener;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author spell
 */
public class BodyTest implements CommandListener {
    public static Map<String, Body> bodiesByKeys = new HashMap<>();

    @Override
    public boolean onCommand(Player player, String command, String[] args) {
        if (command.equals("/body_new")) {
            String key = args[0];
            BodyModel model = BodyModel.getById(Integer.parseInt(args[1]));
            Body body = new Body(model, player.getPosition(), Vector3D.empty());
            bodiesByKeys.put(key, body);
            return true;
        }
        if (command.equals("/body_create")) {
            String key = args[0];
            Body body = bodiesByKeys.get(key);
            body.create(player.getId(),
                    FunctionAccess.GetPlayerVirtualWorld(player.getId()),
                    FunctionAccess.GetPlayerInterior(player.getId()));
            return true;
        }


        if (command.equals("/body_destroy")) {
            String key = args[0];
            Body body = bodiesByKeys.get(key);
            body.destroy(player.getId());
            return true;
        }

        if (command.equals("/body_position")) {
            String key = args[0];
            Body body = bodiesByKeys.get(key);
            body.setPosition(player.getPosition());
            return true;
        }

        if (command.equals("/body_rotation")) {
            String key = args[0];
            Body body = bodiesByKeys.get(key);
            body.setRotation(Vector3D.of(Float.parseFloat(args[1]), Float.parseFloat(args[2]), Float.parseFloat(args[3])));
            return true;
        }

        if (command.equals("/body_drawdistance")) {
            String key = args[0];
            Body body = bodiesByKeys.get(key);
            body.setDrawDistance(Float.parseFloat(args[1]));
            return true;
        }

        if (command.equals("/body_model")) {
            String key = args[0];
            Body body = bodiesByKeys.get(key);
            body.setModel(BodyModel.getById(Integer.parseInt(args[1])));
            return true;
        }

        if (command.equals("/body_recreate")) {
            String key = args[0];
            Body body = bodiesByKeys.get(key);
            body.recreate();
            return true;
        }

        if (command.equals("/body_onshooted")) {
            String key = args[0];
            Body body = bodiesByKeys.get(key);
            body.setOnShootedListener((body1, player1, offSets, weapon) -> {
                player1.sendMessage("you shooted body with a " + weapon + " offsets " + offSets);
            });
            return true;
        }

        if (command.equals("/body_colormaterial")) {
            String key = args[0];
            Body body = bodiesByKeys.get(key);
            body.setMaterial(Integer.parseInt(args[1]),
                    new ColorMaterial(Color.ofRGBA(Integer.parseInt(args[2]), Integer.parseInt(args[3]),
                            Integer.parseInt(args[4]), Integer.parseInt(args[5]))));
            return true;
        }

        if (command.equals("/body_textmaterial")) {
            String key = args[0];
            Body body = bodiesByKeys.get(key);
            TextMaterial textMaterial = new TextMaterial();
            textMaterial.setText(args[1]);
            body.setMaterial(Integer.parseInt(args[1]), textMaterial);
            return true;
        }

        if (command.equals("/body_texturematerial")) {
            String key = args[0];
            Body body = bodiesByKeys.get(key);
            TextureMaterial material = new TextureMaterial(BodyModel.getById(Integer.parseInt(args[2])), args[3], args[4]);
            body.setMaterial(Integer.parseInt(args[1]), material);
            return true;
        }

        if (command.equals("/body_open")) {
            Body body = bodiesByKeys.get(args[0]);
            BodyTransition openAnimation = new BodyTransition()
                    .setToRotation(body.getRotation().withY(20))
                    .setDuration(Duration.ofSeconds(3))
                    .setOnFinishListener(b -> {
                                player.sendMessage("la barrera se abrió, puede pasar");
                            });
            openAnimation.play(body);
        }

        if (command.equals("/body_close")) {
            Body body = bodiesByKeys.get(args[0]);
            BodyTransition closeAnimation = new BodyTransition()
                    .setToRotation(body.getRotation().withY(90))
                    .setDuration(Duration.ofSeconds(3))
                    .setOnFinishListener(b -> {
                        player.sendMessage("la barrera se cerró.");
                    });
            closeAnimation.play(body);
        }
        return false;
    }
}