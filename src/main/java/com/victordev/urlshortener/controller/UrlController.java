package com.victordev.urlshortener.controller;

import com.victordev.urlshortener.model.Url;
import com.victordev.urlshortener.services.impl.UrlServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/url")
public class UrlController {

    @Autowired
    private UrlServiceImpl urlService;

    @PostMapping("/shorten")
    public ResponseEntity<Map<String, String>> shortenUrl(@RequestBody Map<String, String> request){
        if(request != null) {
            String originalUrl = request.get("url");
            String shortUrl = urlService.shortenUrl(originalUrl);
            Map<String, String> response = new HashMap<String, String>();
            response.put("url", "http://localhost:8080/" + shortUrl);
            return ResponseEntity.ok(response);
        }
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Solicitud inválida: falta el campo 'url' o el cuerpo de la solicitud es nulo.");
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Object> redirectToOriginalUrl(@PathVariable String shortUrl){

        Optional<Url> urlOptional = urlService.getOriginalUrl(shortUrl);
        if(urlOptional.isPresent()){
            Url url = urlOptional.get();
            return ResponseEntity.status(302).location(URI.create(url.getOriginalUrl())).build();
        }
        return ResponseEntity.notFound().build();
    }
}
