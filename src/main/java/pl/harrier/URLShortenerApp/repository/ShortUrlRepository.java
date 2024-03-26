package pl.harrier.URLShortenerApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.harrier.URLShortenerApp.entity.ShortUrl;

import java.util.Optional;
@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl,Long> {
    Optional<ShortUrl> findByShortenedUrl(String shortenedUrl);
}
