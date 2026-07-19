package io.github.dr8b.platformcat.standardkit.config;

import io.github.dr8b.platformcat.uikit.config.UiKitAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@AutoConfiguration(after = UiKitAutoConfiguration.class)
@EnableConfigurationProperties(StandardKitProperties.class)
@ConditionalOnProperty(prefix = "io.github.dr8b.platformcat.standard-kit-starter", name = "enabled", matchIfMissing = true)
public class StandardKitAutoConfiguration {
}
