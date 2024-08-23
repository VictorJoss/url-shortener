# URL SHORTENER

> This is a service that allows you to shorten different long URLs and make them more compact and easy to share.

## Description

The URL shortener takes a long URL as input and generates a shortened version containing 5 to 10 characters, using only letters and numbers. The shortened URL is stored in the database along with an expiration date. When the shortened URL (e.g., https://localhost:8080/DXB6V) is accessed, it redirects to the original URL saved in the database. If the shortened URL is not found, a 404 (Not Found) HTTP status code is returned.

## Features

- [ ] Converts long URLs into short versions (5-10 characters).
- [ ] Accessing the short URL redirects to the original or returns a 404 if not found.
- [ ] Shortened URLs have an expiration date stored in the database.


## Technologies Used

- **Spring Boot 3.3.2**
- **Java 17**
- **Maven**
- **Spring Data JPA**
- **H2**
- **Lombok**

## API Documentation

### Main Endpoints

| Method | Endpoint            | Description                      | Authentication |
|--------|---------------------|----------------------------------|----------------|
| POST   | `/url/shorten`        | Sent url to shorten                | No            |
| GET    | `/url/{shortUrl}`   | Get short url              | No             |

### Request and Response Examples

#### Sent url to shorten

- **Endpoint:** `POST /url/shorten`
- **Request:**

```json
{
  "url": "https://www.google.com"
}
```

- **Successful response (200):**

```json
{
  "url": "http://localhost:8080/gfcJF"
}
```
- **Error response (400) If the request body is null or empty:**

```json
{
  "error": "Solicitud inválida: falta el campo 'url' o el cuerpo de la solicitud es nulo."
}
```


#### Get short url

- **Endpoint:** `GET /api/{shorUrl}`
- **Successful response (200):**

```html
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Google</title>
</head>
<body>
    <h1>Example of Simplified Response</h1>
</body>
</html>

```

- **Error response (404) If the shortened URL is not found**

## Contact

If you have any questions or would like to collaborate, you can contact me via <a href="https://www.linkedin.com/in/victor-josu%C3%A9-ramirez-garc%C3%ADa-899bb81a6/" target="_blank" rel="noopener">
    Linkedin
  </a>.
