package io.github.dr8b.platformcat.uikit;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@AutoConfiguration
@EnableConfigurationProperties(UiKitProperties.class)
@ConditionalOnProperty(prefix = "io.github.dr8b.platformcat.ui-kit-starter", name = "enabled", matchIfMissing = true)
public class UiKitAutoConfiguration {
}
