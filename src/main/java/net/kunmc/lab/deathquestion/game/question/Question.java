package net.kunmc.lab.deathquestion.game.question;

import org.bukkit.entity.Player;

public class Question {
    /** テーマ */
    private String theme;

    /** 選択肢 */
    private Choices choices;

    public Question(String theme, String choisesA, String choisesB) {
        this.theme = theme;
        this.choices = new Choices(choisesA, choisesB);
    }

    /**
     * テーマを取得する
     * */
    public String theme() {
        return this.theme;
    }

    /**
     * 投票する
     * */
    public void vote(Player voter, Symbol symbol) {
        choices.vote(voter, symbol);
    }

    /**
     * 得票数が同数か判定する
     * */
    public boolean isSameNumberOfVotes() {
        return choices.isSameNumberOfVotes();
    }

    /**
     * 多数派を取得する
     * */
    public Choice majority() {
        return choices.majority();
    }

    /**
     * 少数派を取得する
     * */
    public Choice minority() {
        return choices.minority();
    }

    /**
     * 選択肢を取得する
     * */
    public Choices choices() {
        return this.choices;
    }
}
