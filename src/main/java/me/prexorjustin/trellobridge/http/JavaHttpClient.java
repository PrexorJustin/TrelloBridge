package me.prexorjustin.trellobridge.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.prexorjustin.trellobridge.exceptions.BadRequestException;
import me.prexorjustin.trellobridge.exceptions.DeserializationException;
import me.prexorjustin.trellobridge.exceptions.NotAuthorizedException;
import me.prexorjustin.trellobridge.exceptions.NotFoundException;
import me.prexorjustin.trellobridge.utils.Utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JavaHttpClient implements IHttpClient {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JavaHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public <T> T get(String url, Class<T> clazz, String... parameters) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(Utils.injectParameter(url, parameters))).build();

        System.out.println("url = " + request.uri());

        try {
            return handleResponse(this.httpClient.send(request, HttpResponse.BodyHandlers.ofString()), clazz);
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    private <T> T handleResponse(HttpResponse<String> response, Class<T> clazz) throws IOException, InterruptedException {
        if (response.statusCode() == 400) {
            throw new BadRequestException(response.body());
        } else if (response.statusCode() == 401) {
            throw new NotAuthorizedException();
        } else if (response.statusCode() == 404) {
            throw new NotFoundException("API request error: Endpoint not found. " + response.uri());
        }
        try {
            return this.objectMapper.readValue(response.body(), clazz);
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
            throw new DeserializationException("Error parsing JSON: Unexpected token or structure. " + response.body());
        }
    }
}
