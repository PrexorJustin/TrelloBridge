package me.prexorjustin.trellobridge.domain.board.mypref;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MyPrefs {

    private Boolean showSidebar, showSidebarMembers, showSidebarBoardActions, showSidebarActivity,
            showListGuide, aiEmailEnabled, aiSlackEnabled, showCompactMirrorCards;

    private String idEmailList, emailPosition;

    public static enum EmailPosition {
        TOP, BOTTOM;
    }
}
