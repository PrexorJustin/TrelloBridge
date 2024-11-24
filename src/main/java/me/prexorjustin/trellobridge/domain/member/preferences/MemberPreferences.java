package me.prexorjustin.trellobridge.domain.member.preferences;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberPreferences {

    private TimezoneInfo timezoneInfo;
    private Privacy privacy;
    private boolean sendSummaries;
    @Setter
    private boolean colorBlind;
    @Setter
    private int minutesBetweenSummaries;
    private int minutesBeforeDeadlineToNotify;
    @Setter
    private String locale;
    private String timezone;
    private TwoFactor twoFactor;

}
