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
import discord4j.core.object.entity.channel.MessageChannel;

/**
 * Abstract command.
 */
public abstract class Command {

    /* The name of the command */
    private String name;

    /** The blockly client */
    protected BlocklyClient blockly;

    /* Constructors */
    protected Command() {
    }

    protected Command(BlocklyClient blockly) {
        this.blockly = blockly;
    }

    /**
     * Sends a message to the user.
     *
     * @param The        discord4j message object
     * @param strMessage The text to send to the user
     */
    protected void sendMessage(Message message, String strMessage) {
        final MessageChannel channel = message.getChannel().block();
        if (channel != null)
            channel.createMessage(strMessage).block();
    }

    /**
     * Executes a command.
     */
    public abstract void execute(Message message);

    /**
     * Returns the help of a command.
     *
     * @return A String with the help of the command
     */
    public abstract String getHelp();

    public String getCommand() {
        return name;
    }

    public void setCommand(String command) {
        this.name = command;
    }

}