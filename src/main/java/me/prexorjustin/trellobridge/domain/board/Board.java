package me.prexorjustin.trellobridge.domain.board;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.prexorjustin.trellobridge.domain.Membership;
import me.prexorjustin.trellobridge.domain.TrelloModel;
import me.prexorjustin.trellobridge.domain.board.limits.BoardLimits;
import me.prexorjustin.trellobridge.domain.board.preferences.BoardPreferences;
import me.prexorjustin.trellobridge.url.DomainArgument;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Board extends TrelloModel {

    private String id;
    private String name;
    private String desc;
    private String idMemberCreator;
    private String idOrganization;
    private String url;
    private String shortUrl;
    private String memberships;
    private String shortLink;
    private String powerUps;
    private String dataLastActivity;
    private String dateLastView;
    private String idTags;
    private String datePluginDisable;
    private String creationMethod;
    private String templateGallery;

    private Boolean closed;
    private Boolean pinned;
    private Boolean starred;
    private Boolean subscribed;
    private Boolean enterpriseOwned;

    private Integer ixUpdate;

    private BoardPreferences prefs;
    private BoardLimits limits;

    private Map<String, String> labelNames;

    public List<Membership> getMemberships(DomainArgument... arguments) {
        return this.trelloBridge.getMembershipsForBoard(this.id, arguments);
    }

}
