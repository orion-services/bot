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

package dev.orion.bot.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import dev.orion.bot.model.Activity;
import dev.orion.bot.model.Group;
import dev.orion.bot.model.User;

@Path("/api/v1")
@RegisterRestClient
public interface BlocklyClient {

        /**
         * Creates a user in the service.
         *
         * @param name          The name of the user
         * @param discriminator The Discord discriminator
         * @return A JSON representing the User object
         * @throws WebApplicationException
         */
        @POST
        @Path("/createUser")
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        @Produces(MediaType.APPLICATION_JSON)
        public User createUser(@FormParam("name") final String name, @FormParam("hashUser") final String discriminator)
                        throws WebApplicationException;

        /**
         * Creates a group in the service.
         *
         * @param groupName     The unique name of the group
         * @param discriminator The Discord discriminator of the user
         * @return A JSON representing the group in the service
         * @throws WebApplicationException
         */
        @POST
        @Path("/createGroup")
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        @Produces(MediaType.APPLICATION_JSON)
        public Group createGroup(@FormParam("namegroup") final String groupName,
                        @FormParam("hashUser") final String discriminator) throws WebApplicationException;

        /**
         * Joins the a user in one group.
         *
         * @param discriminator The Discord discriminator
         * @param groupName     An unique name of the group
         * @return A JSON representing the group object
         */
        @PUT
        @Path("/joingroup")
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        @Produces(MediaType.APPLICATION_JSON)
        public Group joinGroup(@FormParam("hashUser") final String discriminator,
                        @FormParam("namegroup") final String groupName) throws WebApplicationException;

        /**
         * Creates an activity to the group
         *
         * @param groupName The unique name of the group
         * @return A JSON representing the activity object
         */
        @POST
        @Path("createActivity")
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        @Produces(MediaType.APPLICATION_JSON)
        public Activity createActivity(@FormParam("namegroup") final String groupName) throws WebApplicationException;

        /**
         * Participates of one activity in a group
         *
         * @param discriminator The Discord discriminator
         * @param groupName     The name of the group in the bot
         * @return One URL for the user participates
         * @throws WebApplicationException
         */
        @PUT
        @Path("/participates")
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        @Produces(MediaType.TEXT_PLAIN)
        public String participates(@FormParam("hashUser") final String discriminator,
                        @FormParam("namegroup") final String groupName) throws WebApplicationException;

        /**
         * Returns the current locks editor Activity object of a group
         *
         * @param alias : An unique name of the group
         * @return The blocks editor Activity object
         */
        @POST
        @Path("/checkStatus")
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        @Produces(MediaType.APPLICATION_JSON)
        public Activity checkStatus(@FormParam("alias") String alias);

        /**
         * Lists all current activities of an user
         *
         * @param discriminator : The Discord discriminator
         * @return Return a list of current activity of a user in all groups
         */
        @POST
        @Path("/checkStatus")
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        @Produces(MediaType.APPLICATION_JSON)
        public List<Activity> listActivities(@FormParam("discriminator") String discriminator);
}