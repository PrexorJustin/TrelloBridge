package me.prexorjustin.trellobridge.domain.plugin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Plugin {

    @Setter(AccessLevel.NONE)
    private String id;

    private String idOrganizationOwner, author, iframeConnectorURL, name, privacyURL, moderatedState, supportEmail, url;
    private Boolean jsonSchemaPublic;
    private List<String> capabilities, capabilitiesOptions, categories, tags, claimedDomains;
    private HeroImageURL heroImageUrl;
    private Icon icon;
    private Listing listing;
    private UsageBrackets usageBrackets;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class HeroImageURL {

        private String id, the1x, the2x;

    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Icon {

        private String url;

    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Listing {

        private String name, locale, description, overview;

    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class UsageBrackets {

        private long boards;

    }
}

