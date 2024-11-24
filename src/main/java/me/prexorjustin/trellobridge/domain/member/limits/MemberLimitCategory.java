package me.prexorjustin.trellobridge.domain.member.limits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import me.prexorjustin.trellobridge.utils.limit.LimitData;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class MemberLimitCategory {

    private LimitData totalPerMember;

}
