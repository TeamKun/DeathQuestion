package net.kunmc.lab.deathquestion.command;

public enum Command {
    OPEN ("open", new Open()),
    VOTE ("vote", new Vote()),
    QUESTION ("question", new Question());

    private String name;
    private Object instance;

    Command(String name, Object instance) {
        this.name = name;
        this.instance = instance;
    }

    /**
     * コマンド名を取得する
     * */
    public String commandName() {
        return this.name;
    }

    /**
     * インスタンスを取得する
     * */
    public Object instance() {
        return this.instance;
    }
}
