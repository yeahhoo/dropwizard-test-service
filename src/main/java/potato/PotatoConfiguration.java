package potato;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Configuration for the PotatoBags service. */
public final class PotatoConfiguration extends Configuration {

    private Integer defaultNumberBags = 3;

    @JsonProperty
    public Integer getDefaultNumberBags() {
        return defaultNumberBags;
    }

    @JsonProperty
    public void setDefaultNumberBags(Integer defaultNumberBags) {
        this.defaultNumberBags = defaultNumberBags;
    }
}
