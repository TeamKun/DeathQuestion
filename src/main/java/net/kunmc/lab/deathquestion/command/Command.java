package net.kunmc.lab.deathquestion.command;

public enum Command {
    OPEN("open"),
    VOTE("vote"),
    QUESTION("question");

    private String name;

    Command(String name) {
        this.name = name;
    }

    /**
     * コマンド名を取得する.
     * */
    public String commandName() {
        return this.name;
    }
}
