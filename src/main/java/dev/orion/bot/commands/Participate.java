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

import dev.orion.bot.rest.BlocklyClient;
import discord4j.core.object.entity.Message;
import jakarta.ws.rs.WebApplicationException;

/**
 * Represents a command to participate in an activity in a group.
 * Extends the base Command class.
 */
public class Participate extends Command {

    /**
     * Constructs a new Participate command with the given BlocklyClient.
     *
     * @param blockly the BlocklyClient to use for participating in activities
     */
    public Participate(final BlocklyClient blockly) {
        super(blockly);
    }

    /**
     * Executes the Participate command.
     *
     * @param message the message
     */
    @Override
    public void execute(final Message message) {

        String strMessage = null;
        try {
            String discriminator = message.getUserData().discriminator();
            String groupName = message.getContent().toLowerCase().split(" ")[1];

            String url = blockly.participates(discriminator, groupName);
            strMessage = url;
        } catch (ArrayIndexOutOfBoundsException e) {
            strMessage = "Invalid command: " + this.getHelp();
        } catch (WebApplicationException e) {
            strMessage = e.getResponse().readEntity(String.class);
        }

        this.sendMessage(message, strMessage);

    }

    /**
     * Gets the help message for the Participate command.
     *
     * @return the help message
     */
    @Override
    public String getHelp() {
        return "!participates group_name - participates of one activity in a group";
    }
}
