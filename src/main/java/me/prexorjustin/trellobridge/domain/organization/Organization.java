package me.prexorjustin.trellobridge.domain.organization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import me.prexorjustin.trellobridge.domain.TrelloModel;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Organization extends TrelloModel {

    @Setter(AccessLevel.NONE)
    private String id;

    private String name, displayName, desc, url, website, teamType, logoHash, logoUrl, offering;
    private List<Integer> products, powerUps;

}
