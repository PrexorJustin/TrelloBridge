package me.prexorjustin.trellobridge.http;

public interface IHttpClient {

    <T> T get(String url, Class<T> clazz, String... parameters);

    <T> T put(String url, Object body, Class<T> clazz, String... parameters);

    <T> T delete(String url, Class<T> clazz, String... parameters);
}
