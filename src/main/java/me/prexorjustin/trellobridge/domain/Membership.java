package me.prexorjustin.trellobridge.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Membership {

    private String id;
    private String idMember;
    private String memberType;

    private Boolean unconfirmed;
    private Boolean deactivated;

}
