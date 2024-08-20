package com.victordev.urlshortener.services.impl;

import com.victordev.urlshortener.model.Url;
import com.victordev.urlshortener.repository.UrlRepository;
import com.victordev.urlshortener.services.IUrlServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlServiceImpl implements IUrlServices {

    @Autowired
    private UrlRepository urlRepository;


    @Override
    public String shortenUrl(String originalUrl) {
        String shortUrl = generateShortUrl();
        Url  url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(shortUrl);
        url.setExpirationDate(LocalDateTime.now().plusDays(30));
        urlRepository.save(url);
        return shortUrl;
    }

    @Override
    public Optional<Url> getOriginalUrl(String shortUrl){
        Optional<Url> urlOptional = urlRepository.findByShortUrl(shortUrl);
        if(urlOptional.isPresent()){
            Url url = urlOptional.get();
            if(url.getExpirationDate().isAfter(LocalDateTime.now())){
                return Optional.of(url);
            }
            else{
                urlRepository.delete(url);
            }
        }
        return Optional.empty();
    }

    @Override
    public String generateShortUrl() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder shortUrl = new StringBuilder();
        Random random = new Random();
        int length = 5 + random.nextInt(6);
        for (int i = 0; i < length; i++) {
            shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortUrl.toString();
    }
}
