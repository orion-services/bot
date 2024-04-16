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
 * Create Activity.
 */
public class CreateActivity extends Command {

    /**
     * Constructor.
     * @param blockly The Blockly client
     */
    public CreateActivity(final BlocklyClient blockly) {
        super(blockly);
    }

    /**
     * Executes the command.
     *
     * @param message The message
     */
    @Override
    public void execute(final Message message) {

        String strMessage = null;
        try {
            String groupName = message.getContent()
                .toLowerCase().split(" ")[1];

            blockly.createActivity(groupName);
            strMessage = "Activity created";
        } catch (ArrayIndexOutOfBoundsException e) {
            strMessage = "Invalid command: " + this.getHelp();
        } catch (WebApplicationException e) {
            strMessage = e.getResponse().readEntity(String.class);
        }

        this.sendMessage(message, strMessage);
    }

    /**
     * Returns the help message.
     */
    @Override
    public String getHelp() {
        return "!activity group_name - creates one activity for a group in the bot";
    }
}
