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

package dev.orion.bot;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import dev.orion.bot.commands.Command;
import dev.orion.bot.commands.CreateGroup;
import dev.orion.bot.commands.CreateUser;
import dev.orion.bot.commands.Ping;
import dev.orion.bot.rest.BlocksClient;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

/**
 * Orion Bot for Discord.
 */
@ApplicationScoped
public class OrionBot {

    @Inject
    @RestClient
    protected BlocksClient blocks;

    private static final String TOKEN = "ODU2MjUwMTM4MDYyMzU2NDkx.YM-TFQ.QZcvz07W3kzEpsZfwELoK7gDe1g";
    private DiscordClient client;
    private GatewayDiscordClient gateway;

    private Map<String, Command> commands;

    /**
     * Load the bot commands.
     */
    public void setup() {
        this.commands = new HashMap<>();
        this.loadCommands();

        this.client = DiscordClient.create(TOKEN);
        this.gateway = client.login().block();
    }

    /**
     * Listen the users inputs.
     */
    public void start() {
        gateway.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            Command command = this.selectCommand(message.getContent().toLowerCase().split(" ")[0]);
            if (command != null) {
                command.execute(message);
            }
        });
    }

    /**
     * Returns a command.
     *
     * @param command
     * @return a command
     */
    public Command selectCommand(String command) {
        return this.commands.get(command);
    }

    /**
     * Loads the commands in the bot.
     */
    private void loadCommands() {
        this.commands.put("!ping", new Ping());
        this.commands.put("!user", new CreateUser(this.blocks));
        this.commands.put("!group", new CreateGroup(this.blocks));
    }

}
