package me.prexorjustin.trellobridge.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
class MessagesDismissed {

    private String name, count, lastDismissed, _id;

}
