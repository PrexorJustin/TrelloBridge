package me.prexorjustin.trellobridge.url;

import lombok.AccessLevel;
import lombok.Getter;
import me.prexorjustin.trellobridge.url.domain.TrelloAPIEndpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

//TODO: Add possibility to add arguments // parameters
@Getter
public class TrelloUrl {

    @Getter(AccessLevel.NONE)
    private static final String API_URL = "https://api.trello.com/1";
    @Getter(AccessLevel.NONE)
    private static final String AUTH_KEY = "key={applicationKey}&token={userToken}";

    private final TrelloAPIEndpoint baseUrl;
    private final List<DomainArgument> arguments;

    private TrelloUrl(TrelloUrlBuilder builder) {
        this.baseUrl = builder.baseUrl;
        this.arguments = builder.arguments;
    }

    public static TrelloUrlBuilder builder(TrelloAPIEndpoint baseUrl) {
        return new TrelloUrlBuilder(baseUrl);
    }

    public static class TrelloUrlBuilder {

        private final TrelloAPIEndpoint baseUrl;
        private final List<DomainArgument> arguments;

        private TrelloUrlBuilder(TrelloAPIEndpoint baseUrl) {
            this.baseUrl = baseUrl;
            this.arguments = new ArrayList<>();
        }

        public TrelloUrlBuilder withArguments(DomainArgument... arguments) {
            this.arguments.addAll(List.of(arguments));
            return this;
        }

        public TrelloUrlBuilder withArgument(DomainArgument argument) {
            this.arguments.add(argument);
            return this;
        }

        public String build() {
            StringBuilder builder = new StringBuilder(API_URL)
                    .append(this.baseUrl.getUrl())
                    .append(AUTH_KEY);

            // If there are arguments, add them
            if (!this.arguments.isEmpty()) {
                StringJoiner joiner = new StringJoiner("&");
                this.arguments.forEach(argument -> joiner.add(argument.key() + "=" + argument.value()));
                builder.append("&").append(joiner);
            }

            return builder.toString();
        }
    }
}
