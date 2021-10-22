package dev.orion.bot;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class AppLifecycleBean {

    @Inject
    OrionBot orionBot;

    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("The Orion Bot is starting...");
        this.orionBot.setup();
        this.orionBot.start();
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("The Orion bot is stopping...");
    }

}
