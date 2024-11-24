package me.prexorjustin.trellobridge;

import me.prexorjustin.trellobridge.domain.Membership;
import me.prexorjustin.trellobridge.domain.board.Board;
import me.prexorjustin.trellobridge.domain.member.Member;
import me.prexorjustin.trellobridge.url.DomainArgument;

import java.util.List;

public interface ITrelloBridge {

    // ---- MEMBER ---- //

    Member getMember(String memberId);

    Member getMemberWithFields(String memberId, String... requiredFields);

    Member updateMember(Member member);

    // ---- BOARD ---- //

    Board getBoard(String boardId);

    Board updateBoard(Board board);

    List<Board> getBoardsForMember(String memberId, DomainArgument... arguments);

    List<Membership> getMembershipsForBoard(String boardId, DomainArgument... arguments);

    void deleteBoard(String boardId);
}
