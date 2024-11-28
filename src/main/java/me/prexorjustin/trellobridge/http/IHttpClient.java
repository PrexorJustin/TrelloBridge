package me.prexorjustin.trellobridge.http;

public interface IHttpClient {

    <T> T get(String url, Class<T> clazz, String... parameters);

    <T> T put(String url, Object body, Class<T> clazz, String... parameters);

    void putWithoutResponse(String url, Object body, String... parameters);

    <T> T putWithoutBody(String url, Class<T> clazz, String... parameters);

    <T> T delete(String url, Class<T> clazz, String... parameters);

    <T> T post(String url, Object body, Class<T> clazz, String... parameters);

    <T> T postWithoutBody(String url, Class<T> clazz, String... parameters);
}
