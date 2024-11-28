package me.prexorjustin.trellobridge.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.prexorjustin.trellobridge.domain.membership.Membership;
import me.prexorjustin.trellobridge.domain.TrelloModel;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddMemberToBoardResult extends TrelloModel {

    private String id;
    private List<Member> member;
    private List<Membership> memberships;

}
