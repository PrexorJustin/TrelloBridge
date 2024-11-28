package me.prexorjustin.trellobridge.domain.board;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import me.prexorjustin.trellobridge.domain.TrelloModel;
import me.prexorjustin.trellobridge.domain.action.Action;
import me.prexorjustin.trellobridge.domain.board.limits.BoardLimits;
import me.prexorjustin.trellobridge.domain.board.mypref.MyPrefs;
import me.prexorjustin.trellobridge.domain.board.preferences.BoardPreferences;
import me.prexorjustin.trellobridge.domain.board.preferences.ImageDescriptor;
import me.prexorjustin.trellobridge.domain.card.Card;
import me.prexorjustin.trellobridge.domain.checklist.Checklist;
import me.prexorjustin.trellobridge.domain.customfield.CustomField;
import me.prexorjustin.trellobridge.domain.label.Label;
import me.prexorjustin.trellobridge.domain.list.TrelloList;
import me.prexorjustin.trellobridge.domain.member.AddMemberToBoardResult;
import me.prexorjustin.trellobridge.domain.member.MemberShort;
import me.prexorjustin.trellobridge.domain.membership.Membership;
import me.prexorjustin.trellobridge.domain.membership.MembershipType;
import me.prexorjustin.trellobridge.url.DomainArgument;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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

    public static Builder builder(String name) {
        return new Builder(URLEncoder.encode(name, StandardCharsets.UTF_8));
    }

    // ---- API ---- //

    public Board update() {
        return this.trelloBridge.updateBoard(this);
    }

    public void delete() {
        this.trelloBridge.deleteBoard(this.id);
    }

    public List<Membership> getMemberships(DomainArgument... arguments) {
        return this.trelloBridge.getMembershipsForBoard(this.id, arguments);
    }

    public List<Card> getCards(DomainArgument... arguments) {
        return this.trelloBridge.getCardsForBoard(this.id, arguments);
    }

    public Card getCard(String cardId, DomainArgument... arguments) {
        return this.trelloBridge.getCardOnBoard(this.id, cardId, arguments);
    }

    public List<BoardStar> getStars(DomainArgument... arguments) {
        return this.trelloBridge.getBoardStars(this.id, arguments);
    }

    public List<Checklist> getChecklists(DomainArgument... arguments) {
        return this.trelloBridge.getChecklistsForBoard(this.id, arguments);
    }

    public List<CustomField> getCustomFields(DomainArgument... arguments) {
        return this.trelloBridge.getCustomFieldsForBoard(this.id, arguments);
    }

    public List<Label> getLabels(DomainArgument... arguments) {
        return this.trelloBridge.getLabelsForBoard(this.id, arguments);
    }

    public Label createLabel(Label label) {
        return this.trelloBridge.createLabelForBoard(this.id, label);
    }

    public List<TrelloList> getLists(DomainArgument... arguments) {
        return this.trelloBridge.getListsForBoard(this.id, arguments);
    }

    public TrelloList createList(TrelloList list) {
        return this.trelloBridge.createListForBoard(this.id, list);
    }

    public List<MemberShort> getMembers(DomainArgument... arguments) {
        return this.trelloBridge.getBoardMember(this.id, arguments);
    }

    public void inviteMember(String email, String fullName) {
        this.trelloBridge.inviteMemberToBoard(this.id, email, fullName);
    }

    public AddMemberToBoardResult addMember(String userId, MembershipType type) {
        return this.trelloBridge.addMemberToBoard(this.id, userId, type);
    }

    public Board removeMember(String userId) {
        return this.trelloBridge.removeMemberFromBoard(this.id, userId);
    }

    public Membership updateMembership(String membershipId, MembershipType membershipType) {
        return this.trelloBridge.updateMembershipForBoard(this.id, membershipId, membershipType);
    }

    public List<Action> getActions(DomainArgument... arguments) {
        return this.trelloBridge.getActionsForBoard(this.id, arguments);
    }

    public MyPrefs getMyPrefs() {
        return this.trelloBridge.getMyPrefsForBoard(this.id);
    }

    public MyPrefs updateMyPrefsEmailPosition(MyPrefs.EmailPosition position) {
        return this.trelloBridge.updateEmailPositionForBoard(this.id, position);
    }

    public MyPrefs updateMyPrefsIdEmailList(String idList) {
        return this.trelloBridge.updateIdEmailListForBoard(this.id, idList);
    }

    public MyPrefs updateMyPrefsShowSidebar(boolean showSidebar) {
        return this.trelloBridge.updateShowSidebarForBoard(this.id, showSidebar);
    }

    public MyPrefs updateMyPrefsShowSidebarActivity(boolean showSidebarActivity) {
        return this.trelloBridge.updateShowSidebarActivityForBoard(this.id, showSidebarActivity);
    }

    public MyPrefs updateMyPrefsShowSidebarBoardActions(boolean showSidebarBoardActions) {
        return this.trelloBridge.updateShowSidebarBoardActionsForBoard(this.id, showSidebarBoardActions);
    }

    public MyPrefs updateMyPrefsShowSidebarMembers(boolean showSidebarMembers) {
        return this.trelloBridge.updateShowSidebarMembersForBoard(this.id, showSidebarMembers);
    }

    public MyPrefs updateMyPrefsShowListGuide(boolean showListGuide) {
        return this.trelloBridge.updateShowListGuideForBoard(this.id, showListGuide);
    }

    public MyPrefs updateMyPrefsAiEmailEnabled(boolean showAiEmailEnabled) {
        return this.trelloBridge.updateAiEmailEnabledForBoard(this.id, showAiEmailEnabled);
    }

    public MyPrefs updateMyPrefsAiSlackEnabled(boolean showAiSlackEnabled) {
        return this.trelloBridge.updateAiSlackEnabledForBoard(this.id, showAiSlackEnabled);
    }

    public MyPrefs updateMyPrefsShowCompactMirrorCards(boolean showCompactMirrorCards) {
        return this.trelloBridge.updateShowCompactMirrorCardsForBoard(this.id, showCompactMirrorCards);
    }

    public MyPrefs createCalendarKeyForBoard() {
        return this.trelloBridge.createCalendarKeyForBoard(this.id);
    }

    public MyPrefs createEmailKeyForBoard() {
        return this.trelloBridge.createEmailKeyForBoard(this.id);
    }

    public void setTagForBoard(String tagId) {
        this.trelloBridge.setTagForBoard(this.id, tagId);
    }

    public void markBoardAsViewed() {
        this.trelloBridge.markBoardAsViewed(this.id);
    }


    @Data
    @lombok.Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Preferences {

        private String permissionLevel, votes, comments, invitations, background,
                backgroundImage, backgroundBrightness, backgroundBottomColor,
                backgroundTopColor, cardAging, voting;

        private Boolean hideVotes, selfJoin, cardCovers, isTemplate, calendarFeedEnabled,
                backgroundTile, canBePublic, canBeEnterprise, canBeOrg, canInvite;

        private List<ImageDescriptor> backgroundImageScaled;

    }

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Builder {
        private final String name;

        private String desc, idOrganization, idBoardSource, keepFromSource, powerUps;
        private boolean defaultLists, defaultLabels;
        private String prefs_permissionLevel = "private", prefs_voting = "disabled", prefs_comments = "members",
                prefs_invitations = "members", prefs_background = "blue", prefs_cardAging = "regular";
        private boolean prefs_cardCovers, selfJoin;


        public Builder setDesc(String desc) {
            this.desc = URLEncoder.encode(desc, StandardCharsets.UTF_8);
            return this;
        }

        public Builder setIdOrganization(String idOrganization) {
            this.idOrganization = idOrganization;
            return this;
        }

        public Builder setIdBoardSource(String idBoardSource) {
            this.idBoardSource = idBoardSource;
            return this;
        }

        public Builder setKeepFromSource(String keepFromSource) {
            this.keepFromSource = keepFromSource;
            return this;
        }

        public Builder setPowerUps(String powerUps) {
            this.powerUps = powerUps;
            return this;
        }

        public Builder setDefaultLists(boolean defaultLists) {
            this.defaultLists = defaultLists;
            return this;
        }

        public Builder setDefaultLabels(boolean defaultLabels) {
            this.defaultLabels = defaultLabels;
            return this;
        }

        public Builder setPrefs_permissionLevel(String prefs_permissionLevel) {
            this.prefs_permissionLevel = prefs_permissionLevel;
            return this;
        }

        public Builder setPrefs_voting(String prefs_voting) {
            this.prefs_voting = prefs_voting;
            return this;
        }

        public Builder setPrefs_comments(String prefs_comments) {
            this.prefs_comments = prefs_comments;
            return this;
        }

        public Builder setPrefs_invitations(String prefs_invitations) {
            this.prefs_invitations = prefs_invitations;
            return this;
        }

        public Builder setPrefs_background(String prefs_background) {
            this.prefs_background = prefs_background;
            return this;
        }

        public Builder setPrefs_cardAging(String prefs_cardAging) {
            this.prefs_cardAging = prefs_cardAging;
            return this;
        }

        public Builder setPrefs_cardCovers(boolean prefs_cardCovers) {
            this.prefs_cardCovers = prefs_cardCovers;
            return this;
        }

        public Builder setSelfJoin(boolean selfJoin) {
            this.selfJoin = selfJoin;
            return this;
        }

        public DomainArgument[] toDomainArguments() {
            return new DomainArgument[]{
                    new DomainArgument("desc", desc),
                    new DomainArgument("idOrganization", this.idOrganization),
                    new DomainArgument("idBoardSource", this.idBoardSource),
                    new DomainArgument("keepFromSource", this.keepFromSource),
                    new DomainArgument("powerUps", this.powerUps),
                    new DomainArgument("defaultLists", String.valueOf(this.defaultLists)),
                    new DomainArgument("defaultLabels", String.valueOf(this.defaultLabels)),
                    new DomainArgument("prefs_permissionLevel", this.prefs_permissionLevel),
                    new DomainArgument("prefs_voting", this.prefs_voting),
                    new DomainArgument("prefs_comments", this.prefs_comments),
                    new DomainArgument("prefs_invitations", this.prefs_invitations),
                    new DomainArgument("prefs_background", this.prefs_background),
                    new DomainArgument("prefs_cardAging", this.prefs_cardAging),
                    new DomainArgument("prefs_cardCovers", String.valueOf(this.prefs_cardCovers)),
                    new DomainArgument("selfJoin", String.valueOf(this.selfJoin))
            };
        }
    }
}
