package me.prexorjustin.trellobridge.url;

import lombok.AccessLevel;
import lombok.Getter;
import me.prexorjustin.trellobridge.url.domain.ITrelloDomain;

//TODO: Add possibility to add arguments // parameters
@Getter
public class TrelloUrl {

    @Getter(AccessLevel.NONE)
    private static final String API_URL = "https://api.trello.com/1";
    @Getter(AccessLevel.NONE)
    private static final String AUTH_KEY = "key={applicationKey}&token={userToken}";

    private final ITrelloDomain baseUrl;

    private TrelloUrl(TrelloUrlBuilder builder) {
        this.baseUrl = builder.baseUrl;
    }

    public static TrelloUrlBuilder builder() {
        return new TrelloUrlBuilder();
    }

    public static class TrelloUrlBuilder {

        private ITrelloDomain baseUrl;

        private TrelloUrlBuilder() {

        }

        public TrelloUrlBuilder baseUrl(ITrelloDomain baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public String build() {
            return API_URL + this.baseUrl.getUrl() + AUTH_KEY;
        }
    }
}
