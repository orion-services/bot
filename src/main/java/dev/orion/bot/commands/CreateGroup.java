/**
 * Copyright 2021 Orion Bot @ https://github.com/orion-services/bot
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.orion.bot.commands;

import javax.ws.rs.WebApplicationException;

import dev.orion.bot.model.Group;
import dev.orion.bot.rest.BlocklyClient;
import discord4j.core.object.entity.Message;

/**
 * Create a Group in Blockly service.
 */
public class CreateGroup extends Command {

    public CreateGroup(BlocklyClient blockly) {
        super(blockly);
    }

    @Override
    public void execute(Message message) {

        String strMessage = null;
        try {
            String alias = message.getContent().toLowerCase().split(" ")[1];
            String discriminator = message.getUserData().discriminator();

            Group group = blockly.createGroup(alias, discriminator);
            strMessage = "Group created: " + group.getName();
        } catch (ArrayIndexOutOfBoundsException e) {
            strMessage = "Invalid command: " + this.getHelp();
        } catch (WebApplicationException e) {
            strMessage = e.getResponse().readEntity(String.class);
        }

        this.sendMessage(message, strMessage);

    }

    @Override
    public String getHelp() {
        return "!group group_name - the bot will try to create a group with name equal group_name variable.";
    }

}