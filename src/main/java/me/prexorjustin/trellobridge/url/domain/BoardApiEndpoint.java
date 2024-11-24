package me.prexorjustin.trellobridge.url.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoardApiEndpoint implements TrelloApiEndpoint {

    GET_BOARD("/boards/{id}?"),
    GET_BOARD_MEMBERSHIPS("/boards/{id}/memberships?"),

    UPDATE_BOARD("/boards/{id}?"),

    DELETE_BOARD("/boards/{id}?");
    private final String url;
}
