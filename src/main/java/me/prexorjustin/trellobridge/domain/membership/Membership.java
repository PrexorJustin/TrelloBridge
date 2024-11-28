package me.prexorjustin.trellobridge.domain.membership;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import me.prexorjustin.trellobridge.domain.member.MemberShort;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Membership {

    private String id;
    private String idMember, memberType;

    private Boolean unconfirmed, deactivated;

    private MemberShort member;

}
