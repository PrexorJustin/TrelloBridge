package me.prexorjustin.trellobridge.domain.board.preferences;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardPreferences {

    private String permissionLevel, votes, comments, invitations, background,
            backgroundImage, backgroundBrightness, backgroundBottomColor,
            backgroundTopColor, cardAging, voting;

    private Boolean hideVotes, selfJoin, cardCovers, isTemplate, calendarFeedEnabled,
            backgroundTile, canBePublic, canBeEnterprise, canBeOrg, canInvite;

    private List<ImageDescriptor> backgroundImageScaled;
}
