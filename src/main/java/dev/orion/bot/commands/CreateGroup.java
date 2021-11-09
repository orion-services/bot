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
import dev.orion.bot.rest.BlocksClient;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

/**
 * Create Group.
 */
public class CreateGroup extends Command {

    protected BlocksClient blocks;

    public CreateGroup(BlocksClient blocks) {
        this.blocks = blocks;
    }

    @Override
    public void execute(Message message) {

        String alias = message.getContent().toLowerCase().split(" ")[1];
        String discriminator = message.getUserData().discriminator();

        String returnMessage = null;
        try {
            Group group = blocks.createGroup(alias, discriminator);
            returnMessage = "Group created: " + group.getName();
        } catch (WebApplicationException e) {
            returnMessage = e.getResponse().readEntity(String.class);
        }

        final MessageChannel channel = message.getChannel().block();
        if (channel != null)
            channel.createMessage(returnMessage).block();

    }

    @Override
    public String getHelp() {
        return "!group alias - the bot will create a group with the alias";
    }

}