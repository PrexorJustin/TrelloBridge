package me.prexorjustin.trellobridge.url.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoardDomain implements ITrelloDomain {

    GET_BOARD("/boards/{idBoard}?");

    private final String url;
}
