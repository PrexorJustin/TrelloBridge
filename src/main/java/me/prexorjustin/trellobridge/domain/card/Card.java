package me.prexorjustin.trellobridge.domain.card;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.prexorjustin.trellobridge.domain.TrelloModel;
import me.prexorjustin.trellobridge.domain.badge.Badge;
import me.prexorjustin.trellobridge.domain.card.limits.CardLimits;
import me.prexorjustin.trellobridge.domain.label.Label;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Card extends TrelloModel {

    private String id, address, coordinates, creationMethod, desc, dueReminder, idBoard, idList, idAttachmentCover, locationName, name, shortLink, shortUrl, url;
    private List<String> idCheckLists, idLabels, idMembers, idMembersVoted;
    private List<Label> labels;
    private List<CardCheckItemState> checkItemStates;
    private Integer idShort, votes, attachments, checkItems, checkItemsChecked, comments;
    private Float pos;
    private Boolean closed, manualCoverAttachment, subscribed, viewingMemberVoted, location;
    private Date dateLastActivity, due;
    private Badge badges;
    private CardLimits limits;
    private CardCover cover;

}
