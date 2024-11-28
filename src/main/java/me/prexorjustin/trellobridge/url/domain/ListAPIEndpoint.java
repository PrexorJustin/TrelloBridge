package me.prexorjustin.trellobridge.url.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ListAPIEndpoint implements TrelloAPIEndpoint {

    GET_LIST("/lists/{id}?"),
    GET_LIST_CARDS("/lists/{id}/cards?"),

    UPDATE_LIST("/lists/{id}?");

    private final String url;
}
