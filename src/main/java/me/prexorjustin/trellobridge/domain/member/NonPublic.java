package me.prexorjustin.trellobridge.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
class NonPublic {

    private String fullName, initials, avatarUrl, avatarHash;

}
