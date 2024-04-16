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
 * Ping.
 */
/**
 * Represents a command that responds with a "Pong!" message.
 */
public class Ping extends Command {

    /**
     * Constructor.
     *
     * @param blockly The Blockly client
     */
    protected Ping(final BlocklyClient blockly) {
        super(blockly);
    }

    /**
     * Constructor.
     */
    public Ping() {
        super(null);
    }

    /**
     * Executes the command.
     *
     * @param message The message
     */
    @Override
    public void execute(final Message message) {
        final MessageChannel channel = message.getChannel().block();
        if (channel != null)
            channel.createMessage("Pong!").block();
    }

    /**
     * Returns the help message.
     *
     * @return The help message
     */
    @Override
    public String getHelp() {
        return "!ping - the bot will return the Pong! message";
    }
}
