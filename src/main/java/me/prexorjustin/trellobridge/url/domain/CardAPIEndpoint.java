package me.prexorjustin.trellobridge.url.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CardAPIEndpoint implements TrelloAPIEndpoint {

    GET_CARD("/cards/{id}?");

    private final String url;

}
