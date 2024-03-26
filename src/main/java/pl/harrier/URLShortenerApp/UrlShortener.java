package pl.harrier.URLShortenerApp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class UrlShortener {

    public static String generateShortUrl(String originalUrl) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(originalUrl.getBytes());
            // Konwersja bajtów na ciąg Base64 i wzięcie pierwszych 8 znaków
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hash).substring(0, 8);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Nie znaleziono algorytmu hashującego", e);
        }
    }
}

