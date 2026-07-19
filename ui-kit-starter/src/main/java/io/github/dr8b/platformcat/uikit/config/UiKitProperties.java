package io.github.dr8b.platformcat.uikit.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("io.github.dr8b.platformcat.ui-kit-starter")
public class UiKitProperties {

    private boolean enabled = true;

    private String theme = "default";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
