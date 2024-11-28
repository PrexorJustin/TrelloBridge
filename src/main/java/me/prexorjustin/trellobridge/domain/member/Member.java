package me.prexorjustin.trellobridge.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import me.prexorjustin.trellobridge.domain.TrelloModel;
import me.prexorjustin.trellobridge.domain.board.Board;
import me.prexorjustin.trellobridge.domain.member.limits.MemberLimits;
import me.prexorjustin.trellobridge.domain.member.preferences.MemberPreferences;
import me.prexorjustin.trellobridge.url.DomainArgument;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member extends TrelloModel {

    @Setter(AccessLevel.NONE)
    private String id;
    private String fullName, avatarHash, avatarUrl, avatarSource, bio, idEnterprise,
            idMemberReferrer, initials, memberType, url, username, status, email,
            gravatarHash, uploadedAvatarHash, uploadedAvatarUrl, dateLastImpression,
            dateLastActive, domainClaimed;

    private Boolean activityBlocked, confirmed, nonPublicAvailable;

    private Integer credentialsRemovedCount;

    private List<String> idEnterprisesDeactivated, idPremOrgsAdmin, idBoards, idOrganizations,
            idEnterprisesAdmin, loginTypes, oneTimeMessagesDismissed, trophies,
            premiumFeatures, idBoardsPinned;

    private List<Integer> products;

    private NonPublic nonPublic;
    private MemberPreferences prefs;
    private MemberLimits limits;
    private List<MessagesDismissed> messagesDismissed;

    public List<Board> getBoards(DomainArgument... arguments) {
        return this.trelloBridge.getBoardsForMember(this.id, arguments);
    }
}
