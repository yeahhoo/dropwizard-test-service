package potato;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import potato.resources.PotatoStockResource;

/** Potato bags service application. */
public class PotatoApplication extends Application<PotatoConfiguration> {

    public static void main(final String[] args) throws Exception {
        new PotatoApplication().run(args);
    }

    @Override
    public String getName() {
        return "Potato";
    }

    @Override
    public void initialize(final Bootstrap<PotatoConfiguration> bootstrap) {}

    @Override
    public void run(final PotatoConfiguration configuration,
                    final Environment environment) {
        final PotatoStockResource resource = new PotatoStockResource(configuration.getDefaultNumberBags());
        environment.jersey().register(resource);
    }
}
