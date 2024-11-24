package me.prexorjustin.trellobridge.url.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberApiEndpoint implements TrelloApiEndpoint {

    GET_MEMBER("/members/{id}?"),
    UPDATE_MEMBER("/members/{id}?"),
    GET_MEMBER_BOARDS("/members/{id}/boards?");

    private final String url;

}
