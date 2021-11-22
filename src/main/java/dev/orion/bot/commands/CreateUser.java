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

import dev.orion.bot.model.User;
import dev.orion.bot.rest.BlocklyClient;
import discord4j.core.object.entity.Message;
import discord4j.discordjson.json.UserData;

/**
 * Create User.
 */
public class CreateUser extends Command {

    public CreateUser(BlocklyClient blockly) {
        super(blockly);
    }

    @Override
    public void execute(Message message) {

        String strMessage = null;
        UserData author = message.getUserData();

        try {
            User user = this.blockly.createUser(author.username(), author.discriminator());
            strMessage = "User created: " + user.getName();
        } catch (WebApplicationException e) {
            strMessage = e.getResponse().readEntity(String.class);
        }

        this.sendMessage(message, strMessage);
    }

    @Override
    public String getHelp() {
        return "!user - the bot will create the user in the service";
    }

}