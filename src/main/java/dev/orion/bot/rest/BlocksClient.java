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

@Path("/api/v1/")
@RegisterRestClient
public interface BlocksClient {

        @POST
        @Path("/createUser")
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        @Produces(MediaType.APPLICATION_JSON)
        public User createUser(@FormParam("name") final String name, @FormParam("hashUser") final String discriminator)
                        throws WebApplicationException;

        @POST
        @Path("/createGroup")
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        @Produces(MediaType.APPLICATION_JSON)
        public Group createGroup(@FormParam("namegroup") final String alias,
                        @FormParam("hashUser") final String discriminator) throws WebApplicationException;

        /**
         * Allow one user join in a group
         *
         * @param discriminator : The Discord discriminator
         * @param alias         : An unique name of the group
         * @return
         */
        @PUT
        @Path("/joingroup")
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        @Produces(MediaType.APPLICATION_JSON)
        public Group joinGroup(@FormParam("hashUser") final String discriminator,
                        @FormParam("namegroup") final String alias) throws WebApplicationException;

        /**
         * Creates an activity to the group
         *
         * @param alias : An unique name of the group
         * @return The blocks editor Activity object
         */
        @POST
        @Path("/createActivity")
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        @Produces(MediaType.APPLICATION_JSON)
        public Activity createActivity(@FormParam("alias") String alias);

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
         * Asks to participates in a group activity
         *
         * @param alias : An unique name of the group
         * @return Return a URL to participates of a activity
         */
        @POST
        @Path("/checkStatus")
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        @Produces(MediaType.TEXT_PLAIN)
        public String participates(@FormParam("alias") String alias);

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