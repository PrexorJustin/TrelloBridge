package me.prexorjustin.trellobridge.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import me.prexorjustin.trellobridge.domain.TrelloModel;

@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MemberShort extends TrelloModel {

    @Setter(AccessLevel.NONE)
    private String id;

    private String fullName, username, avatarHash, avatarUrl, idMemberReferrer, initials;
    private Boolean nonPublicAvailable, activityBlocked;
    private NonPublic nonPublic;

    // ---- API ---- //
    public Member getMember() {
        return this.trelloBridge.getMember(this.id);
    }
}
