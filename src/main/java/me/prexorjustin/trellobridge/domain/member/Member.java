package me.prexorjustin.trellobridge.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import me.prexorjustin.trellobridge.domain.TrelloModel;
import me.prexorjustin.trellobridge.domain.board.Board;
import me.prexorjustin.trellobridge.domain.member.limits.MemberLimits;
import me.prexorjustin.trellobridge.domain.member.preferences.MemberPreferences;
import me.prexorjustin.trellobridge.url.DomainArgument;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member extends TrelloModel {

    private String id;
    private String avatarHash;
    private String avatarUrl;
    @Setter
    private String avatarSource;
    @Setter
    private String bio;
    @Setter
    private String fullName;
    private String idEnterprise;
    private String idMemberReferrer;
    @Setter
    private String initials;
    private String memberType;
    private String url;
    @Setter
    private String username;
    private String status;
    private String email;
    private String gravatarHash;
    private String uploadedAvatarHash;
    private String uploadedAvatarUrl;
    private String dateLastImpression;
    private String dateLastActive;
    private String domainClaimed;

    private Boolean activityBlocked;
    private Boolean confirmed;
    private Boolean nonPublicAvailable;

    private Integer credentialsRemovedCount;

    private List<String> idEnterprisesDeactivated;
    private List<String> idPremOrgsAdmin;
    private List<String> idBoards;
    private List<String> idOrganizations;
    private List<String> idEnterprisesAdmin;
    private List<String> loginTypes;
    private List<String> oneTimeMessagesDismissed;
    private List<String> trophies;
    private List<String> premiumFeatures;
    private List<String> idBoardsPinned;

    private List<Integer> products;

    private NonPublic nonPublic;
    private MemberPreferences prefs;
    private MemberLimits limits;
    private List<MessagesDismissed> messagesDismissed;

    public List<Board> getBoards(DomainArgument... arguments) {
        return this.trelloBridge.getBoardsForMember(this.id, arguments);
    }
}
