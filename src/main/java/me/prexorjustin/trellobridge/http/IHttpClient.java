package me.prexorjustin.trellobridge.http;

public interface IHttpClient {

    <T> T get(String url, Class<T> clazz, String... parameters);
}
