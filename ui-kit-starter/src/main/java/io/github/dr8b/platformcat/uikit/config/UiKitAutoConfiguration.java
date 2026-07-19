package io.github.dr8b.platformcat.uikit.config;

import io.github.dr8b.platformcat.uikit.controllers.UiKitComponentController;
import io.github.dr8b.platformcat.uikit.services.UiComponentRegistry;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@EnableConfigurationProperties(UiKitProperties.class)
@ConditionalOnProperty(prefix = "io.github.dr8b.platformcat.ui-kit-starter", name = "enabled", matchIfMissing = true)
@Import(UiKitComponentController.class)
public class UiKitAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public UiComponentRegistry uiComponentRegistry() {
        return new UiComponentRegistry();
    }
}
