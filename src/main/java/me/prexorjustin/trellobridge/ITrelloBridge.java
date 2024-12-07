package me.prexorjustin.trellobridge;

import me.prexorjustin.trellobridge.domain.action.Action;
import me.prexorjustin.trellobridge.domain.action.ActionFields;
import me.prexorjustin.trellobridge.domain.board.Board;
import me.prexorjustin.trellobridge.domain.board.BoardStar;
import me.prexorjustin.trellobridge.domain.board.mypref.MyPrefs;
import me.prexorjustin.trellobridge.domain.board.plugin.BoardPlugin;
import me.prexorjustin.trellobridge.domain.card.Card;
import me.prexorjustin.trellobridge.domain.checklist.Checklist;
import me.prexorjustin.trellobridge.domain.customfield.CustomField;
import me.prexorjustin.trellobridge.domain.label.Label;
import me.prexorjustin.trellobridge.domain.list.TrelloList;
import me.prexorjustin.trellobridge.domain.member.AddMemberToBoardResult;
import me.prexorjustin.trellobridge.domain.member.Member;
import me.prexorjustin.trellobridge.domain.member.MemberShort;
import me.prexorjustin.trellobridge.domain.membership.Membership;
import me.prexorjustin.trellobridge.domain.membership.MembershipType;
import me.prexorjustin.trellobridge.domain.organization.Organization;
import me.prexorjustin.trellobridge.domain.plugin.Plugin;
import me.prexorjustin.trellobridge.url.DomainArgument;

import java.util.List;

public interface ITrelloBridge {

    //region ACTION
    // ---- ACTION ---- //

    Action getAction(String actionId);

    void deleteAction(String actionId);

    <T> T getActionField(String actionId, ActionFields actionField);

    Board getBoardForAction(String actionId);

    Card getCardForAction(String actionId);

    TrelloList getListForAction(String actionId);

    Member getMemberForAction(String actionId);

    Member getMemberCreatorForAction(String actionId);

    Organization getOrganizationForAction(String actionId);

    Action updateActionComment(String actionId, String comment);


    //endregion

    //region MEMBER
    // ---- MEMBER ---- //

    Member getMember(String memberId);

    Member getMemberWithFields(String memberId, String... requiredFields);

    Member updateMember(Member member);

    //endregion

    //region BOARD
    // ---- BOARD ---- //

    Board getBoard(String boardId);

    Board updateBoard(Board board);

    void deleteBoard(String boardId);

    Board createBoard(String name, DomainArgument... arguments);

    List<Action> getActionsForBoard(String boardId, DomainArgument... arguments);

    List<Board> getBoardsForMember(String memberId, DomainArgument... arguments);

    List<BoardStar> getBoardStars(String boardId, DomainArgument... arguments);

    List<MemberShort> getBoardMember(String boardId, DomainArgument... arguments);

    void inviteMemberToBoard(String boardId, String email, String fullName);

    AddMemberToBoardResult addMemberToBoard(String boardId, String userId, MembershipType membershipType);

    Board removeMemberFromBoard(String boardId, String userId);

    Membership updateMembershipForBoard(String boardId, String userId, MembershipType membershipType);

    MyPrefs getMyPrefsForBoard(String boardId);

    MyPrefs updateEmailPositionForBoard(String boardId, MyPrefs.EmailPosition position);

    MyPrefs updateIdEmailListForBoard(String boardId, String idList);

    MyPrefs updateShowSidebarForBoard(String boardId, boolean showSidebar);

    MyPrefs updateShowSidebarMembersForBoard(String boardId, boolean showSidebarMembers);

    MyPrefs updateShowSidebarBoardActionsForBoard(String boardId, boolean showSidebarBoardActions);

    MyPrefs updateShowSidebarActivityForBoard(String boardId, boolean showSidebarActivity);

    MyPrefs updateShowListGuideForBoard(String boardId, boolean showListGuide);

    MyPrefs updateAiEmailEnabledForBoard(String boardId, boolean aiEmailEnabled);

    MyPrefs updateAiSlackEnabledForBoard(String boardId, boolean aiSlackEnabled);

    MyPrefs updateShowCompactMirrorCardsForBoard(String boardId, boolean showCompactMirrorCards);

    MyPrefs createCalendarKeyForBoard(String boardId);

    MyPrefs createEmailKeyForBoard(String boardId);

    void setTagForBoard(String boardId, String tagId);

    void markBoardAsViewed(String boardId);

    List<BoardPlugin> getEnabledPowerUpsForBoard(String boardId);

    List<Plugin> getPowerUpsForBoard(String boardId);

    //endregion

    //region MEMBERSHIPS
    // ---- MEMBERSHIPS ---- //

    List<Membership> getMembershipsForBoard(String boardId, DomainArgument... arguments);

    //endregion

    //region CARD
    // ---- CARD ---- //

    Card getCard(String cardId);

    Card getCardOnBoard(String boardId, String cardId, DomainArgument... arguments);

    List<Card> getCardsForBoard(String boardId, DomainArgument... arguments);

    List<Card> getCardsForList(String listId, DomainArgument... arguments);

    //endregion

    //region LIST
    // ---- LIST ---- //

    TrelloList getList(String listId);

    TrelloList updateList(TrelloList list);

    TrelloList createListForBoard(String boardId, TrelloList list);

    List<TrelloList> getListsForBoard(String boardId, DomainArgument... arguments);

    //endregion

    //region CHECKLIST
    // ---- CHECKLIST ---- //

    List<Checklist> getChecklistsForBoard(String boardId, DomainArgument... arguments);

    //endregion

    //region LABELS
    // ---- LABELS ----- //

    Label createLabelForBoard(String boardId, Label label);

    List<Label> getLabelsForBoard(String boardId, DomainArgument... arguments);

    //endregion

    //region CUSTOM FIELDS
    // ---- CUSTOM FIELDS ---- //

    List<CustomField> getCustomFieldsForBoard(String boardId, DomainArgument... arguments);

    //endregion

}
