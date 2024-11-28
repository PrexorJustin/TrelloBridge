package me.prexorjustin.trellobridge.url.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ActionAPIEndpoint implements TrelloAPIEndpoint {

    // Action operations
    GET_ACTION("/actions/{id}"),
    DELETE_ACTION("/actions/{id}"),
    GET_ACTION_FIELD("/actions/{id}/{field}"),

    // Related resources
    GET_ACTION_BOARDS("/actions/{id}/board"),
    GET_ACTION_CARD("/actions/{id}/card"),
    GET_ACTION_LIST("/actions/{id}/list"),
    GET_ACTION_MEMBER("/actions/{id}/member"),
    GET_ACTION_CREATOR("/actions/{id}/memberCreator"),
    GET_ACTION_ORGANIZATION("/actions/{id}/organization"),

    // Reactions
    CREATE_ACTION_REACTION("/actions/{id}/reactions"),
    GET_ACTION_REACTIONS("/actions/{id}/reactions"),
    GET_ACTION_REACTION("/actions/{id}/reactions/{reactionId}"),
    DELETE_ACTION_REACTION("/actions/{id}/reactions/{reactionId}"),
    LIST_ACTION_REACTION_SUMMARY("/actions/{id}/reactionsSummary"),

    // Comments
    UPDATE_ACTION_COMMENT("/actions/{id}/text?value={value}");

    private final String url;
}
