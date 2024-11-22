package me.prexorjustin.trellobridge.url.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberUrl implements ITrelloDomain {

    GET_MEMBER("/members/{username}?");

    private final String url;
}
