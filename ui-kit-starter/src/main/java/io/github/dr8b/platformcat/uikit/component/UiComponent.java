package io.github.dr8b.platformcat.uikit.component;

import java.util.List;

public record UiComponent(String key, String tag, String title, String category, String description,
                          List<UiComponentAttribute> attributes, List<String> examples) {
}
