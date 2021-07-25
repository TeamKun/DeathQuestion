package net.kunmc.lab.deathquestion.game;

import net.kunmc.lab.deathquestion.command.SubCommand;

import java.util.Arrays;

public enum ExecutionMethod {
    KILL("kill"),
    CHANGE_TO_SPECTATOR("changeToSpectator");

    private String name;

    ExecutionMethod(String name) {
        this.name = name;
    }

    /**
     * 名前を取得する
     * */
    public String methodName() {
        return this.name;
    }

    /**
     * 引き当てる
     * */
    public static SubCommand getMethodName(String methodName) {
        return Arrays.stream(SubCommand.values())
                .filter(data -> data.commandName().equalsIgnoreCase(methodName))
                .findFirst()
                .orElse(null);
    }
}
