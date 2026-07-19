package io.github.dr8b.platformcat.standardkit;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("platformcat.standard-kit")
public class StandardKitProperties {

    private boolean enabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
