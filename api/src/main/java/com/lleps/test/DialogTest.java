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
package com.lleps.test;

import com.lleps.jsamp.dialog.Dialog;
import com.lleps.jsamp.dialog.builder.InfoDialogBuilder;
import com.lleps.jsamp.dialog.builder.InputDialogBuilder;
import com.lleps.jsamp.dialog.builder.ListDialogBuilder;
import com.lleps.jsamp.player.Player;

import java.util.Arrays;

import static com.lleps.jsamp.dialog.builder.ListDialogBuilder.columns;

/**
 * @author spell
 */
public class DialogTest implements CommandListener {

    @Override
    public boolean onCommand(Player player, String command, String[] args) {
        if (command.equals("/listdialog")) {
            player.showDialog(new ListDialogBuilder("Seleccionar modo de juego")
                    .addItem("Creativo - crea y construye bloques gratis", (p, response) ->
                                    player.sendMessage("Modo de juego seteado a creativo")
                    )
                    .addItem("Survival - bueno al revez", (p, r) -> {
                        p.sendMessage("Seteado a survaival loro");
                    })
                    .setPositiveButton("Aceptar", (player1, input, whichItem) -> {
                        player.sendMessage("Acecta2 con " + input + " item " + whichItem);
                    })
                    .setNegativeButton("Cancelar", (player2, input1, whichItem1) -> {
                        player.sendMessage("kncla2");
                    })
                    .build());
            return true;
        }

        if (command.equals("/listdialogheaders")) {
            player.showDialog(new ListDialogBuilder()
                    .setTitle("Selecciona la cosa a komprar")
                    .setHeader(columns("Precio", "Cosa"))
                    .addItem(columns("$400", "pan"), (p, response) ->
                                    player.sendMessage("compraste pan")
                    )
                    .addItem(columns("$150", "ye.other.think"), (p, r) -> {
                        p.sendMessage("compraste el class de java");
                    })
                    .setPositiveButton("Aceptar", (player1, input, whichItem) -> {
                        player.sendMessage("biempñ!!!! con " + input + " item " + whichItem);
                    })
                    .setNegativeButton("Cancelar", (player2, input1, whichItem1) -> {
                        player.sendMessage(":/: :/ :/: :/://: /: /:");
                    })
                    .build());
            return true;
        }

        if (command.equals("/inputdialog")) {
            player.showDialog(new InputDialogBuilder()
                    .setTitle("Escribe tu clave")
                    .setContent("Clave:")
                    .setPositiveButton("Aceptar", (player1, input, whichItem) -> {
                        player.sendMessage("tu input es " + input);
                    })
                    .setNegativeButton("Cancelar", (player2, input1, whichItem1) -> {
                        player.sendMessage("bue cancelaste.");
                    })
                    .build());

            return true;
        }

        if (command.equals("/inputdialogpassword")) {
            player.showDialog(new InputDialogBuilder()
                    .setTitle("Escribe tu clavecon 1 boton")
                    .setContent("Clave:")
                    .setHideInput(true)
                    .setPositiveButton("Aceptar", (player1, input, whichItem) -> {
                        player.sendMessage("tu input es " + input);
                    })
                    .build());

            return true;
        }

        if (command.equals("/infodialog")) {
            Dialog dialog = new InfoDialogBuilder()
                    .setTitle("Información de la versión")
                    .setContent("Navegación: 24.0\n" +
                            "Versión de andriod: lollipop")
                    .setPositiveButton("Aceptar")
                    .build();

            player.showDialog(dialog);
            return true;
        }
        return false;
    }
}
