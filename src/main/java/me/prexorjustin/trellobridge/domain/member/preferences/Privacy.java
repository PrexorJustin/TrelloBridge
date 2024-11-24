package me.prexorjustin.trellobridge.domain.member.preferences;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
class Privacy {

    private String fullName, avatar;

}
