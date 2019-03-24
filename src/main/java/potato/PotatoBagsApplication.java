package potato;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import potato.resources.PotatoStockResource;

/** Potato bags service application. */
public class PotatoBagsApplication extends Application<PotatoBagConfiguration> {

    public static void main(final String[] args) throws Exception {
        new PotatoBagsApplication().run(args);
    }

    @Override
    public String getName() {
        return "PotatoBag";
    }

    @Override
    public void initialize(final Bootstrap<PotatoBagConfiguration> bootstrap) {}

    @Override
    public void run(final PotatoBagConfiguration configuration,
                    final Environment environment) {
        final PotatoStockResource resource = new PotatoStockResource(configuration.getDefaultNumberBags());
        environment.jersey().register(resource);
    }
}
