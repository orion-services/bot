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

import dev.orion.bot.commands.Command;
import dev.orion.bot.commands.Ping;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import java.util.HashMap;
import java.util.Map;

/**
 * Orion Bot for Discord.
 */
public class OrionBot {

    private final String token = "ODU2MjUwMTM4MDYyMzU2NDkx.YM-TFQ.8EBd1FeCIUPPOQhfMXw-ph3P6ss";
    private DiscordClient client;
    private GatewayDiscordClient gateway;

    private Map<String, Command> commands;

    public OrionBot() {
        this.commands = new HashMap<String, Command>();
        this.loadCommands();
        
        this.client = DiscordClient.create(this.token);
        this.gateway = client.login().block();
    }

    /**
     * Listen the users inputs.
     */
    public void start() {
        gateway.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            System.out.println(message.getContent());
            Command command = this.selectCommand(message.getContent());
            if (command != null) {
                command.execute(message);
            }
        });
        //gateway.onDisconnect().block();
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
    }

}
