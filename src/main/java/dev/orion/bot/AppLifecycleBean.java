package dev.orion.bot;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

import org.jboss.logging.Logger;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class AppLifecycleBean {

    /* Logger */
    private static final Logger LOGGER = Logger.getLogger(AppLifecycleBean.class.getName());

    @Inject
    OrionBot orionBot;

    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("The Orion Bot is starting...");
        this.orionBot.setup();
        this.orionBot.start();
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("The Orion bot is stopping...");
    }

}
