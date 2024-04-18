package pl.harrier.URLShortenerApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.harrier.URLShortenerApp.entity.ShortUrl;
import pl.harrier.URLShortenerApp.service.ShortUrlService;

import java.net.URI;

@Controller
public class ShortUrlController {

    @Autowired
    private ShortUrlService shortUrlService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/result")
    public String result(@RequestParam String shortenedUrl, Model model) {
        model.addAttribute("shortenedUrl", shortenedUrl);
        return "result";
    }

    @PostMapping("/shorten")
    public String shortenUrl(@RequestParam String originalUrl, Model model) {
        ShortUrl shortUrl = shortUrlService.shortenUrl(originalUrl);
        model.addAttribute("shortenedUrl", shortUrl.getShortenedUrl());
        return "index";
    }


    @GetMapping("/{shortenedUrl}")
    public String redirectToOriginalUrl(@PathVariable String shortenedUrl, RedirectAttributes redirectAttributes) {
        String originalUrl = shortUrlService.getOriginalUrl(shortenedUrl);
        if (originalUrl == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Nie znaleziono oryginalnego URL-a dla: " + shortenedUrl);
            return "redirect:/";
        }
        return "redirect:" + originalUrl;
    }

}


