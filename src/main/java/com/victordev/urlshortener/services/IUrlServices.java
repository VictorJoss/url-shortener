package com.victordev.urlshortener.services;

import com.victordev.urlshortener.model.Url;

import java.util.Optional;

public interface IUrlServices {

    String shortenUrl(String originalUrl);
    Optional<Url> getOriginalUrl(String shortUrl);
    String generateShortUrl();
}
