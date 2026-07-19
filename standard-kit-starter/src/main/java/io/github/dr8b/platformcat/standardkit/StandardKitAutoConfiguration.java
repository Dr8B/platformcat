package io.github.dr8b.platformcat.standardkit;

import io.github.dr8b.platformcat.uikit.UiKitAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@AutoConfiguration(after = UiKitAutoConfiguration.class)
@EnableConfigurationProperties(StandardKitProperties.class)
@ConditionalOnProperty(prefix = "platformcat.standard-kit", name = "enabled", matchIfMissing = true)
public class StandardKitAutoConfiguration {
}
