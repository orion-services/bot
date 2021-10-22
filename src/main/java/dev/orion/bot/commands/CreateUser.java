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

import dev.orion.bot.rest.BlocksClient;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.discordjson.json.UserData;

/**
 * Create User.
 */
public class CreateUser extends Command {

    protected BlocksClient blocks;

    public CreateUser(BlocksClient blocks) {
        this.blocks = blocks;
    }

    @Override
    public void execute(Message message) {

        String returnMessage;
        UserData author = message.getUserData();

        if (this.blocks != null) {
            this.blocks.createUser(author.username() + "#" + author.discriminator());
            returnMessage = author.username() + "#" + author.discriminator();
        } else {
            returnMessage = "Service down";
        }

        final MessageChannel channel = message.getChannel().block();
        if (channel != null)
            channel.createMessage(returnMessage).block();
    }

    @Override
    public String getHelp() {
        return "!create - the bot will create the user in the service";
    }

}