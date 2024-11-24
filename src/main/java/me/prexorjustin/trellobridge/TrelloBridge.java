package me.prexorjustin.trellobridge;

import com.google.gson.GsonBuilder;
import lombok.Getter;
import me.prexorjustin.trellobridge.domain.Membership;
import me.prexorjustin.trellobridge.domain.TrelloModel;
import me.prexorjustin.trellobridge.domain.board.Board;
import me.prexorjustin.trellobridge.domain.member.Member;
import me.prexorjustin.trellobridge.http.IHttpClient;
import me.prexorjustin.trellobridge.http.JavaHttpClient;
import me.prexorjustin.trellobridge.url.DomainArgument;
import me.prexorjustin.trellobridge.url.TrelloUrl;
import me.prexorjustin.trellobridge.url.domain.BoardApiEndpoint;
import me.prexorjustin.trellobridge.url.domain.MemberApiEndpoint;

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

            Member member = trelloBridge.getMember("me");
            System.out.println("---------------------- MEMBER ----------------------");
            System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(member));

            //TODO: UNAUTHORIZED
            //member.setBio("Diese BIO wurde von der TrelloBridge gesetzt");
            //Member updatedMember = trelloBridge.updateMember(member);
            //System.out.println("---------------------- UPDATED_MEMBER ----------------------");
            //System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(updatedMember));

            Board board = trelloBridge.getBoard("65cd20571856e8da25b3fd88");
            System.out.println("---------------------- BOARD ----------------------");
            System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(board));

            List<Membership> membershipsForBoard = trelloBridge.getMembershipsForBoard("65cd20571856e8da25b3fd88");
            System.out.println("---------------------- MEMBERSHIPS ----------------------");
            System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(membershipsForBoard));

            board.setDesc("This description was set from the TrelloBridge");
            Board updatedBoard = trelloBridge.updateBoard(board);
            System.out.println("---------------------- UPDATED_BOARD ----------------------");
            System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(updatedBoard));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // ---- MEMBER ---- //

    @Override
    public Member getMember(String memberId) {
        Member member = get(TrelloUrl.builder(MemberApiEndpoint.GET_MEMBER).build(), Member.class, memberId);
        member.setTrelloBridge(this);
        return member;
    }

    @Override
    public Member getMemberWithFields(String memberId, String... requiredFields) {
        Member member = get(TrelloUrl.builder(MemberApiEndpoint.GET_MEMBER).withArgument(new DomainArgument("fields", String.join(",", requiredFields))).build(), Member.class, memberId);
        member.setTrelloBridge(this);
        return member;
    }

    @Override
    public Member updateMember(Member member) {
        Member updatedMember = put(TrelloUrl.builder(MemberApiEndpoint.UPDATE_MEMBER).build(), member, Member.class, member.getId());
        updatedMember.setTrelloBridge(this);
        return updatedMember;
    }

    // ---- BOARD ---- //

    @Override
    public Board getBoard(String boardId) {
        Board board = get(TrelloUrl.builder(BoardApiEndpoint.GET_BOARD).build(), Board.class, boardId);
        board.setTrelloBridge(this);
        return board;
    }

    @Override
    public Board updateBoard(Board board) {
        Board updatedBoard = put(TrelloUrl.builder(BoardApiEndpoint.UPDATE_BOARD).build(), board, Board.class, board.getId());
        updatedBoard.setTrelloBridge(this);
        return updatedBoard;
    }

    @Override
    public void deleteBoard(String boardId) {
        delete(TrelloUrl.builder(BoardApiEndpoint.DELETE_BOARD).build(), Board.class, boardId);
    }

    @Override
    public List<Board> getBoardsForMember(String memberId, DomainArgument... arguments) {
        return asList(() -> get(TrelloUrl.builder(MemberApiEndpoint.GET_MEMBER_BOARDS).withArguments(arguments).build(), Board[].class, memberId));
    }

    @Override
    public List<Membership> getMembershipsForBoard(String boardId, DomainArgument... arguments) {
        return List.of(get(TrelloUrl.builder(BoardApiEndpoint.GET_BOARD_MEMBERSHIPS).withArguments(arguments).build(), Membership[].class, boardId));
    }

    // ---- HTTP METHODS ---- //

    private <T> T get(String url, Class<T> clazz, String... parameters) {
        return this.httpClient.get(url, clazz, addAuthenticationParams(parameters));
    }

    private <T> T put(String url, Object body, Class<T> clazz, String... parameters) {
        return this.httpClient.put(url, body, clazz, addAuthenticationParams(parameters));
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
}
