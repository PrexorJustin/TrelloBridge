package me.prexorjustin.trellobridge.domain.action;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import me.prexorjustin.trellobridge.domain.board.BoardShort;
import me.prexorjustin.trellobridge.domain.card.CardShort;
import me.prexorjustin.trellobridge.domain.list.TrelloListShort;
import me.prexorjustin.trellobridge.domain.member.MemberShort;
import me.prexorjustin.trellobridge.domain.plugin.Plugin;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class Action {

    private String id, idMemberCreator, type;
    private Date date;

    private Data data;
    private AppCreator appCreator;
    private MemberShort memberCreator;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Display {

        private String translationKey;
        private Entities entities;

        @Getter
        @JsonIgnoreProperties(ignoreUnknown = true)
        static class Entities {

            private CardShort card;
            private TrelloListShort list;
            private MemberShort memberCreator;

        }

    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Data {

        private String idCard, text;

        private Plugin plugin;
        private BoardShort board;
        private TrelloListShort list;
        private CardShort card;

        private Date dateLastEdited;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class AppCreator {

        private String id;

    }

}
