package dev.orion.bot;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class App {
    public static void main(final String... args) {
        Quarkus.run(Bot.class, args);
    }

    public static class Bot implements QuarkusApplication {

        @Override
        public int run(final String... args) throws Exception {
            Quarkus.waitForExit();
            return 0;
        }
    }
}
