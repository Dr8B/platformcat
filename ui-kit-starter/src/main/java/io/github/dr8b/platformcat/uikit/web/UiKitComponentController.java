package io.github.dr8b.platformcat.uikit.web;

import io.github.dr8b.platformcat.uikit.UiKit;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.regex.Pattern;

@RestController
public class UiKitComponentController {

    private static final Pattern COMPONENT_NAME = Pattern.compile("[a-z0-9-]+");

    private static final MediaType JAVASCRIPT = MediaType.parseMediaType("text/javascript;charset=UTF-8");

    @GetMapping(UiKit.STATIC_PATH + "/{component}.js")
    public ResponseEntity<Resource> component(@PathVariable("component") String component) {
        if (!COMPONENT_NAME.matcher(component).matches()) {
            return ResponseEntity.notFound().build();
        }
        Resource resource = new ClassPathResource("ui-kit/static/" + component + ".js");
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(JAVASCRIPT)
                .cacheControl(CacheControl.maxAge(Duration.ofHours(1)).cachePublic())
                .body(resource);
    }
}
