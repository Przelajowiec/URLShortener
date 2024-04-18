package pl.harrier.URLShortenerApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.harrier.URLShortenerApp.UrlShortener;
import pl.harrier.URLShortenerApp.entity.ShortUrl;
import pl.harrier.URLShortenerApp.repository.ShortUrlRepository;

@Service
public class ShortUrlService {

    @Value("${app.base.url}") //
    private String baseUrl;

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    public ShortUrl shortenUrl(String originalUrl) {
        String shortenedUrl = UrlShortener.generateShortUrl(originalUrl);
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setOriginalUrl(originalUrl);
        shortUrl.setShortenedUrl(baseUrl + "/" + shortenedUrl);
        return shortUrlRepository.save(shortUrl); // Zapisuje w bazie danych i zwraca zapisany obiekt
    }


    public String getOriginalUrl(String shortenedUrl) {
        return shortUrlRepository.findByShortenedUrl(shortenedUrl)
                .map(ShortUrl::getOriginalUrl)
                .orElse(null); // Zwraca null, jeśli URL nie został znaleziony
    }

}

