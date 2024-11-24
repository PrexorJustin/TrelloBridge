package me.prexorjustin.trellobridge.domain.member.limits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberLimits {

    private MemberLimitCategory boards, orgs;

}
