package net.kunmc.lab.deathquestion.game.question;

import org.bukkit.entity.Player;

public class Question {
    /** テーマ */
    private String theme;

    /** 選択肢A */
    private Choices choicesA;

    /** 選択肢B */
    private Choices choicesB;

    public Question(String theme, String choisesA, String choisesB) {
        this.theme = theme;
        this.choicesA = new Choices(Symbol.A, choisesA);
        this.choicesB = new Choices(Symbol.B, choisesB);
    }

    public void vote(Player voter, Symbol symbol) {
        // 選択肢両方のリストから削除
        choicesA.remove(voter);
        choicesB.remove(voter);

        // 投票
        if (choicesA.isMatchSymbol(symbol)) {
            choicesA.vote(voter);
        }

        if (choicesB.isMatchSymbol(symbol)) {
            choicesB.vote(voter);
        }
    }
}
