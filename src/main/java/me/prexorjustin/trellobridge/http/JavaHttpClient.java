package me.prexorjustin.trellobridge.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
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
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Utils.injectParameter(url, parameters)))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            return handleResponse(this.httpClient.send(request, HttpResponse.BodyHandlers.ofString()), clazz);
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public <T> T put(String url, Object body, Class<T> clazz, String... parameters) {
        try {
            HttpRequest.Builder builder = HttpRequest.newBuilder()
                    .uri(URI.create(Utils.injectParameter(url, parameters)))
                    .header("Content-Type", "application/json");

            if (body == null) builder.PUT(HttpRequest.BodyPublishers.noBody());
            else builder.PUT(HttpRequest.BodyPublishers.ofString(this.objectMapper.writeValueAsString(body)));

            HttpRequest request = builder.build();

            return handleResponse(this.httpClient.send(request, HttpResponse.BodyHandlers.ofString()), clazz);
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public <T> T putWithoutBody(String url, Class<T> clazz, String... parameters) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Utils.injectParameter(url, parameters)))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .build();

            return handleResponse(this.httpClient.send(request, HttpResponse.BodyHandlers.ofString()), clazz);
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void putWithoutResponse(String url, Object body, String... parameters) {
        try {
            HttpRequest.Builder builder = HttpRequest.newBuilder()
                    .uri(URI.create(Utils.injectParameter(url, parameters)))
                    .header("Content-Type", "application/json");

            if (body == null) builder.PUT(HttpRequest.BodyPublishers.noBody());
            else builder.PUT(HttpRequest.BodyPublishers.ofString(this.objectMapper.writeValueAsString(body)));

            HttpRequest request = builder.build();

            HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println("response.body() = " + response.body());
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public <T> T delete(String url, Class<T> clazz, String... parameters) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Utils.injectParameter(url, parameters)))
                    .DELETE()
                    .build();

            return handleResponse(this.httpClient.send(request, HttpResponse.BodyHandlers.ofString()), clazz);
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public <T> T post(String url, Object body, Class<T> clazz, String... parameters) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Utils.injectParameter(url, parameters)))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(this.objectMapper.writeValueAsString(body)))
                    .build();

            return handleResponse(this.httpClient.send(request, HttpResponse.BodyHandlers.ofString()), clazz);
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public <T> T postWithoutBody(String url, Class<T> clazz, String... parameters) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Utils.injectParameter(url, parameters)))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();

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
            System.out.println("response.body() = " + response.body());
            throw new DeserializationException("Error parsing JSON: Unexpected token or structure. \n" + new GsonBuilder().setPrettyPrinting().create().toJson(JsonParser.parseString(response.body())));
        }
    }
}
