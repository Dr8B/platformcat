package io.github.dr8b.platformcat.uikit.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("io.github.dr8b.platformcat.ui-kit-starter")
public class UiKitProperties {

    private boolean enabled = true;

    private boolean gallery = false;

    private String theme = "default";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isGallery() {
        return gallery;
    }

    public void setGallery(boolean gallery) {
        this.gallery = gallery;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
