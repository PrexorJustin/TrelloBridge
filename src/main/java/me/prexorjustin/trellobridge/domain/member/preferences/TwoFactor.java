package me.prexorjustin.trellobridge.domain.member.preferences;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
class TwoFactor {

    private boolean enabled, needsNewBackups;

}
