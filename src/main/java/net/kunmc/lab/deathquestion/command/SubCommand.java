package net.kunmc.lab.deathquestion.command;

import java.util.Arrays;

public enum SubCommand {
    A ("a"),
    B ("b"),
    KILL ("kill"),
    CHANGE_TO_SPECTATOR ("changeToSpectator"),
    ASK ("ask"),
    ADD_IGNORE_PLAYER ("addIgnorePlayer"),
    CANCEL ("cancel");

    private String name;

    SubCommand(String name) {
        this.name = name;
    }

    /**
     * コマンド名を取得する
     * */
    public String commandName() {
        return this.name;
    }

    /**
     * コマンド名からサブコマンドを引き当てる
     * */
    public static SubCommand getSubCommand(String commandName) {
        return Arrays.stream(SubCommand.values())
                .filter(data -> data.commandName().equalsIgnoreCase(commandName))
                .findFirst()
                .orElse(null);
    }
}
