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
package dev.orion.bot.model;

/**
 * Represents a user in the system.
 */
/**
 * Represents a user in the system.
 */
public class User {

    /** The ID of the user. */
    private long id;

    /** The name of the user. */
    private String name;

    /**
     * Constructs a new User object with the specified name.
     *
     * @param name the name of the user
     */
    public User(final String name) {
        super();
        this.name = name;
    }

    /**
     * Constructs a new User object.
     */
    public User() {
        super();
    }

    /**
     * Returns the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the name of the user
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Returns the ID of the user.
     *
     * @return the ID of the user
     */
    public long getId() {
        return this.id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id the ID of the user
     */
    public void setId(final long id) {
        this.id = id;
    }

}
