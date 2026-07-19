package io.github.dr8b.platformcat.uikit.controllers;

import io.github.dr8b.platformcat.uikit.UiKit;
import io.github.dr8b.platformcat.uikit.services.UiComponentRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UiKitGalleryController {

    private final UiComponentRegistry registry;

    public UiKitGalleryController(UiComponentRegistry registry) {
        this.registry = registry;
    }

    @GetMapping(UiKit.BASE_PATH)
    public String gallery(Model model) {
        model.addAttribute("basePath", UiKit.BASE_PATH);
        model.addAttribute("staticPath", UiKit.STATIC_PATH);
        model.addAttribute("components", registry.all());
        return "ui-kit/gallery";
    }
}
