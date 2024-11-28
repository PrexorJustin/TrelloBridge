package me.prexorjustin.trellobridge.domain.action;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.prexorjustin.trellobridge.domain.member.Member;

import java.util.Date;

@RequiredArgsConstructor
@Getter
public enum ActionFields {

    ID("id", String.class),
    ID_MEMBER_CREATOR("idMemberCreator", String.class),
    TYPE("type", String.class),
    DATE("date", Date.class),
    DATA("data", Action.Data.class),
    MEMBER_CREATOR("memberCreator", Member.class),
    LIMITS("limits", null),
    DISPLAY("display", Action.Display.class);

    private final String fieldName;
    private final Class<?> responseClass;
}
