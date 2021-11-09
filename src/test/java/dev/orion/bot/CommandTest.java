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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dev.orion.bot.commands.Command;
import dev.orion.bot.commands.CreateGroup;
import dev.orion.bot.commands.CreateUser;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CommandTest {

    public static OrionBot bot;

    @BeforeAll
    public static void init() {
        bot = new OrionBot();
        bot.setup();
    }

    @Test
    @DisplayName("User command")
    void userCommand() {
        Command command = bot.selectCommand("!user");
        assertEquals(CreateUser.class, command.getClass());
    }

    @Test
    @DisplayName("Group command")
    void groupCommand() {
        Command command = bot.selectCommand("!group");
        assertEquals(CreateGroup.class, command.getClass());
    }

    @Test
    @DisplayName("Null command")
    void nullCommand() {
        Command command = bot.selectCommand(null);
        assertEquals(null, command);
    }

    @Test
    @DisplayName("Wrong command")
    void wrongCommand() {
        Command command = bot.selectCommand("null");
        assertEquals(null, command);
    }

}
