package me.prexorjustin.trellobridge;

import com.google.gson.GsonBuilder;
import lombok.Getter;
import me.prexorjustin.trellobridge.domain.member.Member;
import me.prexorjustin.trellobridge.http.IHttpClient;
import me.prexorjustin.trellobridge.http.JavaHttpClient;
import me.prexorjustin.trellobridge.url.TrelloUrl;
import me.prexorjustin.trellobridge.url.domain.MemberUrl;

import java.net.http.HttpClient;
import java.util.Arrays;
import java.util.stream.Stream;

@Getter
public class TrelloBridge {

    @Getter
    private static TrelloBridge instance;

    private final String applicationKey, accessToken;

    private final IHttpClient httpClient;

    public TrelloBridge(String applicationKey, String accessToken, IHttpClient httpClient) {
        instance = this;
        this.accessToken = accessToken;
        this.applicationKey = applicationKey;
        this.httpClient = httpClient;
    }

    public TrelloBridge(String applicationKey, String accessToken) {
        this(applicationKey, accessToken, new JavaHttpClient(HttpClient.newHttpClient()));
    }

    public static void main(String[] args) {
        TrelloBridge trelloBridge = new TrelloBridge("*", "*");

        Member member = trelloBridge.getMember();
        System.out.println("member = " + new GsonBuilder().setPrettyPrinting().create().toJson(member));
    }

    private Member getMember() {
        String[] params = {"me"};
        return this.httpClient.get(TrelloUrl.builder().baseUrl(MemberUrl.GET_MEMBER).build(), Member.class, addAuthenticationParams(params));
    }

    private String[] addAuthenticationParams(String[] parameters) {
        return Stream.concat(Arrays.stream(parameters), Stream.of(applicationKey, accessToken)).toArray(String[]::new);
    }
}
