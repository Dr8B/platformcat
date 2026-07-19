package io.github.dr8b.platformcat.uikit;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@AutoConfiguration
@EnableConfigurationProperties(UiKitProperties.class)
@ConditionalOnProperty(prefix = "platformcat.ui-kit", name = "enabled", matchIfMissing = true)
public class UiKitAutoConfiguration {
}
