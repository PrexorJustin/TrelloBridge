package me.prexorjustin.trellobridge.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Preferences {

    public TimezoneInfo timezoneInfo;
    public Privacy privacy;
    public boolean sendSummaries, colorBlind;
    public int minutesBetweenSummaries, minutesBeforeDeadlineToNotify;
    public String locale, timezone;
    public TwoFactor twoFactor;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TimezoneInfo {

        public int offsetCurrent, offsetNext;
        public String timezoneCurrent, dateNext, timezoneNext;

    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Privacy {

        public String fullName, avatar;

    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TwoFactor {

        public boolean enabled, needsNewBackups;

    }
}
