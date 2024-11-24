package me.prexorjustin.trellobridge.domain.board.preferences;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardPreferences {

    private String permissionLevel;
    private String votes;
    private String comments;
    private String invitations;
    private String background;
    private String backgroundImage;
    private String backgroundBrightness;
    private String backgroundBottomColor;
    private String backgroundTopColor;
    private String cardAging;
    private String voting;

    private Boolean hideVotes;
    private Boolean selfJoin;
    private Boolean cardCovers;
    private Boolean isTemplate;
    private Boolean calendarFeedEnabled;
    private Boolean backgroundTile;
    private Boolean canBePublic;
    private Boolean canBeEnterprise;
    private Boolean canBeOrg;
    private Boolean canInvite;

    private List<ImageDescriptor> backgroundImageScaled;
}
