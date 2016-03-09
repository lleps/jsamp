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
import com.lleps.jsamp.constant.SkinModel;
import com.lleps.jsamp.constant.model.BodyModel;
import com.lleps.jsamp.data.Color;
import com.lleps.jsamp.data.Vector3D;
import com.lleps.jsamp.data.bodymaterial.ColorMaterial;
import com.lleps.jsamp.data.bodymaterial.TextMaterial;
import com.lleps.jsamp.data.bodymaterial.TextureMaterial;
import com.lleps.jsamp.player.Player;
import com.lleps.jsamp.transition.BodyTransition;
import com.lleps.jsamp.world.entity.Actor;
import com.lleps.jsamp.world.entity.Body;
import com.lleps.jsamp.world.entity.Label;
import com.lleps.test.CommandListener;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author spell
 */
public class ActorTest implements CommandListener {
    public static Map<String, Actor> actorsByKeys = new HashMap<>();

    @Override
    public boolean onCommand(Player player, String command, String[] args) {
        if (command.equals("/actor_new")) {
            String key = args[0];
            SkinModel skin = SkinModel.getById(Integer.parseInt(args[1]));
            Actor actor = new Actor(skin, player.getPosition(), FunctionAccess.GetPlayerFacingAngle(player.getId()));
            actorsByKeys.put(key, actor);
            return true;
        }
        if (command.equals("/actor_create")) {
            String key = args[0];
            actorsByKeys.get(key).create(player.getId(),
                    FunctionAccess.GetPlayerVirtualWorld(player.getId()),
                    FunctionAccess.GetPlayerInterior(player.getId()));
            return true;
        }
        if (command.equals("/actor_destroy")) {
            String key = args[0];
            actorsByKeys.get(key).destroy(player.getId());
            return true;
        }
        if (command.equals("/actor_recreate")) {
            String key = args[0];
            actorsByKeys.get(key).recreate();
            return true;
        }
        if (command.equals("/actor_skin")) {
            String key = args[0];
            actorsByKeys.get(key).setSkin(SkinModel.getById(Integer.parseInt(args[1])));
            return true;
        }
        if (command.equals("/actor_position")) {
            String key = args[0];
            actorsByKeys.get(key).setPosition(player.getPosition());
            return true;
        }
        if (command.equals("/actor_invulnerable")) {
            String key = args[0];
            actorsByKeys.get(key).setInvulnerable(Boolean.parseBoolean(args[1]));
            return true;
        }
        if (command.equals("/actor_sethealth")) {
            String key = args[0];
            actorsByKeys.get(key).setHealth(Float.parseFloat(args[1]));
            return true;
        }
        if (command.equals("/actor_gethealth")) {
            String key = args[0];
            player.sendMessage("your health: " + actorsByKeys.get(key).getHealth());
            return true;
        }
        if (command.equals("/actor_ondamaged")) {
            String key = args[0];
            actorsByKeys.get(key).setOnDamagedListener((actor, damager, lostHealth, weaponModel, bodyPart) -> {
                player.sendMessage("actor damaged lost "+lostHealth+" weap "+weaponModel+" bodypart " + bodyPart);
                return false;
            });
            return true;
        }
        if (command.equals("/actor_ondeath")) {
            String key = args[0];
            actorsByKeys.get(key).setOnDeathListener((actor, killer) -> {
                player.sendMessage("actor killed by " + killer);
            });
            return true;
        }

        if (command.equals("/actor_attachlabel")) {
            String actorKey = args[0];
            String labelKey = args[1];

            Actor a = actorsByKeys.get(actorKey);
            Label l = LabelTest.labelsByKeys.get(labelKey);

            Vector3D offSets = Vector3D.of(Float.parseFloat(args[2]), Float.parseFloat(args[3]), Float.parseFloat(args[4]));

            a.attachLabel(l, offSets);
            return true;
        }

        if (command.equals("/actor_detachlabel")) {
            String actorKey = args[0];
            String labelKey = args[1];

            Actor a = actorsByKeys.get(actorKey);
            Label l = LabelTest.labelsByKeys.get(labelKey);
            a.detachLabel(l);
            return true;
        }
        return false;
    }
}