package me.prexorjustin.trellobridge.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {

    public String id, avatarHash, avatarUrl, avatarSource, bio, fullName, idEnterprise, idMemberReferrer, initials, memberType, url, username, status, email, gravatarHash, uploadedAvatarHash, uploadedAvatarUrl, dateLastImpression, dateLastActive, domainClaimed;
    public boolean activityBlocked, confirmed, nonPublicAvailable;
    public int credentialsRemovedCount;
    public List<String> idEnterprisesDeactivated, idPremOrgsAdmin, idBoards, idOrganizations, idEnterprisesAdmin, loginTypes, oneTimeMessagesDismissed, trophies, premiumFeatures, idBoardsPinned;
    public List<Integer> products;
    public NonPublic nonPublic;
    public Preferences prefs;
    public Limits limits;
    public List<MessagesDismissed> messagesDismissed;

}
