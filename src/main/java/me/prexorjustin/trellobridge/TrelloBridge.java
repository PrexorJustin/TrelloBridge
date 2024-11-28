package me.prexorjustin.trellobridge;

import com.google.gson.GsonBuilder;
import lombok.Getter;
import me.prexorjustin.trellobridge.domain.TrelloModel;
import me.prexorjustin.trellobridge.domain.action.Action;
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
import me.prexorjustin.trellobridge.domain.plugin.Plugin;
import me.prexorjustin.trellobridge.http.IHttpClient;
import me.prexorjustin.trellobridge.http.JavaHttpClient;
import me.prexorjustin.trellobridge.url.DomainArgument;
import me.prexorjustin.trellobridge.url.TrelloUrl;
import me.prexorjustin.trellobridge.url.domain.BoardAPIEndpoint;
import me.prexorjustin.trellobridge.url.domain.CardAPIEndpoint;
import me.prexorjustin.trellobridge.url.domain.ListAPIEndpoint;
import me.prexorjustin.trellobridge.url.domain.MemberAPIEndpoint;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Getter
public class TrelloBridge implements ITrelloBridge {

    @Getter
    private static TrelloBridge instance;

    private final String applicationKey, accessToken;

    private final IHttpClient httpClient;

    public TrelloBridge(String applicationKey, String accessToken, IHttpClient httpClient) {
        instance = this;
        this.accessToken = accessToken;
        this.applicationKey = applicationKey;
        this.httpClient = httpClient;
    }

    public TrelloBridge(String applicationKey, String accessToken) {
        this(applicationKey, accessToken, new JavaHttpClient(HttpClient.newHttpClient()));
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream resourceAsStream = TrelloBridge.class.getResourceAsStream("/auth.env")) {
            properties.load(resourceAsStream);

            TrelloBridge trelloBridge = new TrelloBridge(properties.getProperty("KEY"), properties.getProperty("TOKEN"));

            System.out.println("---------------------- TEST ----------------------");

            Board board = trelloBridge.getBoard("674384e5599007d6274106e8");

            System.out.println("OUT = " + new GsonBuilder().setPrettyPrinting().create().toJson(board.getName()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //region MEMBER
    // ---- MEMBER ---- //

    @Override
    public Member getMember(String memberId) {
        Member member = get(TrelloUrl.builder(MemberAPIEndpoint.GET_MEMBER).build(), Member.class, memberId);
        member.setTrelloBridge(this);
        return member;
    }

    @Override
    public Member getMemberWithFields(String memberId, String... requiredFields) {
        Member member = get(TrelloUrl.builder(MemberAPIEndpoint.GET_MEMBER).withArgument(new DomainArgument("fields", String.join(",", requiredFields))).build(), Member.class, memberId);
        member.setTrelloBridge(this);
        return member;
    }

    @Override
    public List<MemberShort> getBoardMember(String boardId, DomainArgument... arguments) {
        return asList(() -> get(TrelloUrl.builder(BoardAPIEndpoint.GET_BOARD_MEMBERS).withArguments(arguments).build(), MemberShort[].class, boardId));
    }

    @Override
    public Member updateMember(Member member) {
        Member updatedMember = put(TrelloUrl.builder(MemberAPIEndpoint.UPDATE_MEMBER).build(), member, Member.class, member.getId());
        updatedMember.setTrelloBridge(this);
        return updatedMember;
    }
    //endregion

    //region BOARD
    // ---- BOARD ---- //

    @Override
    public Board getBoard(String boardId) {
        Board board = get(TrelloUrl.builder(BoardAPIEndpoint.GET_BOARD).build(), Board.class, boardId);
        board.setTrelloBridge(this);
        return board;
    }

    @Override
    public Board updateBoard(Board board) {
        Board updatedBoard = put(TrelloUrl.builder(BoardAPIEndpoint.UPDATE_BOARD).build(), board, Board.class, board.getId());
        updatedBoard.setTrelloBridge(this);
        return updatedBoard;
    }

    @Override
    public void deleteBoard(String boardId) {
        delete(TrelloUrl.builder(BoardAPIEndpoint.DELETE_BOARD).build(), Board.class, boardId);
    }

    @Override
    public Board createBoard(String name, DomainArgument... arguments) {
        return postWithoutBody(TrelloUrl.builder(BoardAPIEndpoint.CREATE_BOARD).withArguments(arguments).build(), Board.class, name);
    }

    @Override
    public List<Action> getActionsForBoard(String boardId, DomainArgument... arguments) {
        return List.of(get(TrelloUrl.builder(BoardAPIEndpoint.GET_BOARD_ACTIONS).withArguments(arguments).build(), Action[].class, boardId));
    }

    @Override
    public List<Board> getBoardsForMember(String memberId, DomainArgument... arguments) {
        return asList(() -> get(TrelloUrl.builder(MemberAPIEndpoint.GET_MEMBER_BOARDS).withArguments(arguments).build(), Board[].class, memberId));
    }

    @Override
    public List<BoardStar> getBoardStars(String boardId, DomainArgument... arguments) {
        return List.of(get(TrelloUrl.builder(BoardAPIEndpoint.GET_BOARD_STARS).withArguments(arguments).build(), BoardStar[].class, boardId));
    }

    @Override
    public void inviteMemberToBoard(String boardId, String email, String fullName) {
        putWithoutResponse(TrelloUrl.builder(BoardAPIEndpoint.INVITE_BOARD_MEMBER).build(), null, boardId, email);
    }

    @Override
    public AddMemberToBoardResult addMemberToBoard(String boardId, String userId, MembershipType membershipType) {
        AddMemberToBoardResult result = put(TrelloUrl.builder(BoardAPIEndpoint.ADD_BOARD_MEMBER).build(), null, AddMemberToBoardResult.class, boardId, userId, membershipType.name().toLowerCase());
        result.setTrelloBridge(this);
        return result;
    }

    @Override
    public Board removeMemberFromBoard(String boardId, String userId) {
        Board board = delete(TrelloUrl.builder(BoardAPIEndpoint.REMOVE_BOARD_MEMBER).build(), Board.class, boardId, userId);
        board.setTrelloBridge(this);
        return board;
    }

    @Override
    public Membership updateMembershipForBoard(String boardId, String userId, MembershipType membershipType) {
        return putWithoutBody(TrelloUrl.builder(BoardAPIEndpoint.UPDATE_BOARD_MEMBERSHIP).build(), Membership.class, boardId, userId, membershipType.name().toLowerCase());
    }

    @Override
    public MyPrefs getMyPrefsForBoard(String boardId) {
        return get(TrelloUrl.builder(BoardAPIEndpoint.GET_BOARD_MYPREFS).build(), MyPrefs.class, boardId);
    }

    @Override
    public MyPrefs updateEmailPositionForBoard(String boardId, MyPrefs.EmailPosition position) {
        return putWithoutBody(TrelloUrl.builder(BoardAPIEndpoint.UPDATE_BOARD_MYPREFS_EMAIL_POSITION).build(), MyPrefs.class, boardId, position.name().toLowerCase());
    }

    @Override
    public MyPrefs updateIdEmailListForBoard(String boardId, String idList) {
        return putWithoutBody(TrelloUrl.builder(BoardAPIEndpoint.UPDATE_BOARD_MYPREFS_ID_EMAIL_LIST).build(), MyPrefs.class, boardId, idList);
    }

    @Override
    public MyPrefs updateShowSidebarForBoard(String boardId, boolean showSidebar) {
        return putWithoutBody(TrelloUrl.builder(BoardAPIEndpoint.UPDATE_BOARD_MYPREFS_SHOW_SIDEBAR).build(), MyPrefs.class, boardId, String.valueOf(showSidebar));
    }

    @Override
    public MyPrefs updateShowSidebarMembersForBoard(String boardId, boolean showSidebarMembers) {
        return putWithoutBody(TrelloUrl.builder(BoardAPIEndpoint.UPDATE_BOARD_MYPREFS_SHOW_SIDEBAR_MEMBERS).build(), MyPrefs.class, boardId, String.valueOf(showSidebarMembers));
    }

    @Override
    public MyPrefs updateShowSidebarBoardActionsForBoard(String boardId, boolean showSidebarBoardActions) {
        return putWithoutBody(TrelloUrl.builder(BoardAPIEndpoint.UPDATE_BOARD_MYPREFS_SHOW_SIDEBAR_BOARD_ACTIONS).build(), MyPrefs.class, boardId, String.valueOf(showSidebarBoardActions));
    }

    @Override
    public MyPrefs updateShowSidebarActivityForBoard(String boardId, boolean showSidebarActivity) {
        return putWithoutBody(TrelloUrl.builder(BoardAPIEndpoint.UPDATE_BOARD_MYPREFS_SHOW_SIDEBAR_ACTIVITY).build(), MyPrefs.class, boardId, String.valueOf(showSidebarActivity));
    }

    @Override
    public MyPrefs updateShowListGuideForBoard(String boardId, boolean showListGuide) {
        return putWithoutBody(TrelloUrl.builder(BoardAPIEndpoint.UPDATE_BOARD_MYPREFS_SHOW_LIST_GUIDE).build(), MyPrefs.class, boardId, String.valueOf(showListGuide));
    }

    @Override
    public MyPrefs updateAiEmailEnabledForBoard(String boardId, boolean aiEmailEnabled) {
        return put(TrelloUrl.builder(BoardAPIEndpoint.UPDATE_BOARD_MYPREFS_AI_EMAIL_ENABLED).build(), null, MyPrefs.class, boardId, String.valueOf(aiEmailEnabled));
    }

    @Override
    public MyPrefs updateAiSlackEnabledForBoard(String boardId, boolean aiSlackEnabled) {
        return put(TrelloUrl.builder(BoardAPIEndpoint.UPDATE_BOARD_MYPREFS_AI_SLACK_ENABLED).build(), null, MyPrefs.class, boardId, String.valueOf(aiSlackEnabled));
    }

    @Override
    public MyPrefs updateShowCompactMirrorCardsForBoard(String boardId, boolean showCompactMirrorCards) {
        return put(TrelloUrl.builder(BoardAPIEndpoint.UPDATE_BOARD_MYPREFS_SHOW_COMPACT_MIRROR_CARDS).build(), null, MyPrefs.class, boardId, String.valueOf(showCompactMirrorCards));
    }

    @Override
    public MyPrefs createCalendarKeyForBoard(String boardId) {
        return postWithoutBody(TrelloUrl.builder(BoardAPIEndpoint.CREATE_BOARD_CALENDAR_KEY).build(), MyPrefs.class, boardId);
    }

    @Override
    public MyPrefs createEmailKeyForBoard(String boardId) {
        return postWithoutBody(TrelloUrl.builder(BoardAPIEndpoint.CREATE_BOARD_EMAIL_KEY).build(), MyPrefs.class, boardId);
    }

    @Override
    public void setTagForBoard(String boardId, String tagId) {
        postWithoutBody(TrelloUrl.builder(BoardAPIEndpoint.SET_BOARD_TAG).build(), MyPrefs.class, boardId, tagId);
    }

    @Override
    public void markBoardAsViewed(String boardId) {
        postWithoutBody(TrelloUrl.builder(BoardAPIEndpoint.MARK_BOARD_AS_VIEWED).build(), MyPrefs.class, boardId);
    }

    @Override
    public List<BoardPlugin> getEnabledPowerUpsForBoard(String boardId) {
        return asList(() -> get(TrelloUrl.builder(BoardAPIEndpoint.GET_ENABLED_PLUGINS).build(), BoardPlugin[].class, boardId));
    }

    @Override
    public List<Plugin> getPowerUpsForBoard(String boardId) {
        return List.of(get(TrelloUrl.builder(BoardAPIEndpoint.GET_PLUGINS).build(), Plugin[].class, boardId));
    }

    //endregion

    //region MEMBERSHIP
    // ---- MEMBERSHIP ---- //

    @Override
    public List<Membership> getMembershipsForBoard(String boardId, DomainArgument... arguments) {
        return List.of(get(TrelloUrl.builder(BoardAPIEndpoint.GET_BOARD_MEMBERSHIPS).withArguments(arguments).build(), Membership[].class, boardId));
    }

    //endregion

    //region CARD
    // ---- CARD ---- //

    @Override
    public Card getCard(String cardId) {
        Card card = get(TrelloUrl.builder(CardAPIEndpoint.GET_CARD).build(), Card.class, cardId);
        card.setTrelloBridge(this);
        return card;
    }

    @Override
    public Card getCardOnBoard(String boardId, String cardId, DomainArgument... arguments) {
        return get(TrelloUrl.builder(BoardAPIEndpoint.GET_BOARD_CARD).withArguments(arguments).build(), Card.class, boardId, cardId);
    }

    @Override
    public List<Card> getCardsForBoard(String boardId, DomainArgument... arguments) {
        return asList(() -> get(TrelloUrl.builder(BoardAPIEndpoint.GET_BOARD_CARDS).withArguments(arguments).build(), Card[].class, boardId));
    }

    @Override
    public List<Card> getCardsForList(String listId, DomainArgument... arguments) {
        return asList(() -> get(TrelloUrl.builder(ListAPIEndpoint.GET_LIST_CARDS).withArguments(arguments).build(), Card[].class, listId));
    }

    //endregion

    //region LIST
    // ---- LIST ---- //

    @Override
    public TrelloList getList(String listId) {
        return get(TrelloUrl.builder(ListAPIEndpoint.GET_LIST).build(), TrelloList.class, listId);
    }

    @Override
    public TrelloList updateList(TrelloList list) {
        TrelloList updatedList = put(TrelloUrl.builder(ListAPIEndpoint.UPDATE_LIST).build(), list, TrelloList.class, list.getId());
        updatedList.setTrelloBridge(this);
        return updatedList;
    }

    @Override
    public TrelloList createListForBoard(String boardId, TrelloList list) {
        TrelloList createdList = post(TrelloUrl.builder(BoardAPIEndpoint.CREATE_BOARD_LIST).build(), list, TrelloList.class, boardId);
        createdList.setTrelloBridge(this);
        return createdList;
    }

    @Override
    public List<TrelloList> getListsForBoard(String boardId, DomainArgument... arguments) {
        return asList(() -> get(TrelloUrl.builder(BoardAPIEndpoint.GET_BOARD_LISTS).withArguments(arguments).build(), TrelloList[].class, boardId));
    }

    //endregion

    //region CHECKLIST
    // ---- CHECKLIST ---- //

    @Override
    public List<Checklist> getChecklistsForBoard(String boardId, DomainArgument... arguments) {
        return asList(() -> get(TrelloUrl.builder(BoardAPIEndpoint.GET_BOARD_CHECKLISTS).withArguments(arguments).build(), Checklist[].class, boardId));
    }

    //endregion

    //region LABEL
    // ---- LABEL ---- //

    @Override
    public Label createLabelForBoard(String boardId, Label label) {
        Label createdLabel = post(TrelloUrl.builder(BoardAPIEndpoint.CREATE_BOARD_LABEL).build(), label, Label.class, boardId);
        createdLabel.setTrelloBridge(this);
        return createdLabel;
    }

    @Override
    public List<Label> getLabelsForBoard(String boardId, DomainArgument... arguments) {
        return asList(() -> get(TrelloUrl.builder(BoardAPIEndpoint.GET_BOARD_LABELS).withArguments(arguments).build(), Label[].class, boardId));
    }

    //endregion

    //region CUSTOM FIELD
    // ---- CUSTOM FIELD ---- //

    @Override
    public List<CustomField> getCustomFieldsForBoard(String boardId, DomainArgument... arguments) {
        return asList(() -> get(TrelloUrl.builder(BoardAPIEndpoint.GET_BOARD_CUSTOM_FIELDS).withArguments(arguments).build(), CustomField[].class, boardId));
    }

    //endregion

    //region HTTP METHODS
    // ---- HTTP METHODS ---- //

    private <T> T get(String url, Class<T> clazz, String... parameters) {
        return this.httpClient.get(url, clazz, addAuthenticationParams(parameters));
    }

    private <T> T put(String url, Object body, Class<T> clazz, String... parameters) {
        return this.httpClient.put(url, body, clazz, addAuthenticationParams(parameters));
    }

    private <T> T putWithoutBody(String url, Class<T> clazz, String... parameters) {
        return this.httpClient.putWithoutBody(url, clazz, addAuthenticationParams(parameters));
    }

    private void putWithoutResponse(String url, Object body, String... parameters) {
        this.httpClient.putWithoutResponse(url, body, addAuthenticationParams(parameters));
    }

    private <T> T post(String url, Object body, Class<T> clazz, String... parameters) {
        return this.httpClient.post(url, body, clazz, addAuthenticationParams(parameters));
    }

    private <T> T postWithoutBody(String url, Class<T> clazz, String... parameters) {
        return this.httpClient.postWithoutBody(url, clazz, addAuthenticationParams(parameters));
    }

    private <T> T delete(String url, Class<T> clazz, String... parameters) {
        return this.httpClient.delete(url, clazz, addAuthenticationParams(parameters));
    }

    private <T extends TrelloModel> List<T> asList(Supplier<T[]> supplier) {
        return Arrays.stream(supplier.get()).peek(model -> model.setTrelloBridge(this)).toList();
    }

    private String[] addAuthenticationParams(String[] parameters) {
        return Stream.concat(Arrays.stream(parameters), Stream.of(applicationKey, accessToken)).toArray(String[]::new);
    }

    //endregion
}
