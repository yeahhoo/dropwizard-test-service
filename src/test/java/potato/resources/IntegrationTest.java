package potato.resources;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import potato.PotatoBagsApplication;
import potato.PotatoBagConfiguration;
import potato.api.PotatoBag;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
public class IntegrationTest {

    private static final String CONFIG_PATH = ResourceHelpers.resourceFilePath("test-example.yml");

    public static final DropwizardAppExtension<PotatoBagConfiguration> RULE = new DropwizardAppExtension<>(
            PotatoBagsApplication.class, CONFIG_PATH);

    @Test
    public void testContextIsOk() {
        final List<PotatoBag> list = RULE.client().target("http://localhost:" + RULE.getLocalPort() + "/potatobags")
                .path("3/extract")
                .request()
                .get(List.class);
        assertThat(list.isEmpty()).isTrue();
    }
}
