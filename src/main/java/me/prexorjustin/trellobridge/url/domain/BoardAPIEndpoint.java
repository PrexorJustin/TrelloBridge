package me.prexorjustin.trellobridge.url.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoardAPIEndpoint implements TrelloAPIEndpoint {

    // Board operations
    GET_BOARD("/boards/{id}?"),
    UPDATE_BOARD("/boards/{id}?"),
    DELETE_BOARD("/boards/{id}?"),
    CREATE_BOARD("/boards?name={name}&"),

    // Calendar and email keys
    CREATE_BOARD_CALENDAR_KEY("/boards/{id}/calendarKey/generate?"),
    CREATE_BOARD_EMAIL_KEY("/boards/{id}/emailKey/generate?"),

    // Tags and viewing
    SET_BOARD_TAG("/boards/{id}/idTags?value={value}&"),
    MARK_BOARD_AS_VIEWED("/boards/{id}/markedAsViewed?"),

    // Plugins
    GET_ENABLED_PLUGINS("/boards/{id}/boardPlugins?"),
    GET_PLUGINS("/boards/{id}/plugins?"),

    // Board details
    GET_BOARD_FIELD("/boards/{id}/{field}?"),
    GET_BOARD_STARS("/boards/{id}/boardStars?"),
    GET_BOARD_CUSTOM_FIELDS("/boards/{id}/customFields?"),

    // Memberships
    GET_BOARD_MEMBERSHIPS("/boards/{id}/memberships?"),
    UPDATE_BOARD_MEMBERSHIP("/boards/{id}/memberships/{membershipId}?type={type}&"),

    // Cards
    GET_BOARD_CARD("/boards/{id}/cards/{cardId}?"),
    GET_BOARD_CARDS("/boards/{id}/cards?"),

    // Lists
    GET_BOARD_LISTS("/boards/{id}/lists?"),
    CREATE_BOARD_LIST("/boards/{id}/lists?"),

    // Actions
    GET_BOARD_ACTIONS("/boards/{id}/actions?"),

    // Checklists
    GET_BOARD_CHECKLISTS("/boards/{id}/checklists?"),

    // Labels
    GET_BOARD_LABELS("/boards/{id}/labels?"),
    CREATE_BOARD_LABEL("/boards/{id}/labels?"),

    // Members
    GET_BOARD_MEMBERS("/boards/{id}/members?"),
    INVITE_BOARD_MEMBER("/boards/{id}/members?email={email}&"),
    ADD_BOARD_MEMBER("/boards/{id}/members/{memberId}?type={type}&"),
    REMOVE_BOARD_MEMBER("/boards/{id}/members/{memberId}?"),

    // MyPrefs
    GET_BOARD_MYPREFS("/boards/{id}/myprefs?"),
    UPDATE_BOARD_MYPREFS_EMAIL_POSITION("/boards/{id}/myprefs/emailPosition?value={value}&"),
    UPDATE_BOARD_MYPREFS_ID_EMAIL_LIST("/boards/{id}/myprefs/idEmailList?value={value}&"),
    UPDATE_BOARD_MYPREFS_SHOW_SIDEBAR("/boards/{id}/myprefs/showSidebar?value={value}&"),
    UPDATE_BOARD_MYPREFS_SHOW_SIDEBAR_ACTIVITY("/boards/{id}/myprefs/showSidebarActivity?value={value}&"),
    UPDATE_BOARD_MYPREFS_SHOW_SIDEBAR_BOARD_ACTIONS("/boards/{id}/myprefs/showSidebarBoardActions?value={value}&"),
    UPDATE_BOARD_MYPREFS_SHOW_SIDEBAR_MEMBERS("/boards/{id}/myprefs/showSidebarMembers?value={value}&"),
    UPDATE_BOARD_MYPREFS_SHOW_LIST_GUIDE("/boards/{id}/myprefs/showListGuide?value={value}&"),
    UPDATE_BOARD_MYPREFS_AI_EMAIL_ENABLED("/boards/{id}/myprefs/aiEmailEnabled?value={value}&"),
    UPDATE_BOARD_MYPREFS_AI_SLACK_ENABLED("/boards/{id}/myprefs/aiSlackEnabled?value={value}&"),
    UPDATE_BOARD_MYPREFS_SHOW_COMPACT_MIRROR_CARDS("/boards/{id}/myprefs/showCompactMirrorCards?value={value}&");

    private final String url;
}
