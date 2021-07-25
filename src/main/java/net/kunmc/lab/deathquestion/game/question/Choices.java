package net.kunmc.lab.deathquestion.game.question;

import org.bukkit.entity.Player;

public class Choices {

    /** 選択肢A */
    private Choice choiceA;

    /** 選択肢B */
    private Choice choiceB;

    Choices(String choisesA, String choisesB) {
        this.choiceA = new Choice(Symbol.A, choisesA);
        this.choiceB = new Choice(Symbol.B, choisesB);
    }

    /**
     * 投票する
     * */
    void vote(Player voter, Symbol symbol) {
        // 選択肢両方のリストから削除
        choiceA.remove(voter);
        choiceB.remove(voter);

        // 投票
        if (choiceA.isMatchSymbol(symbol)) {
            choiceA.vote(voter);
        }

        if (choiceB.isMatchSymbol(symbol)) {
            choiceB.vote(voter);
        }
    }

    /**
     * 得票数が同数か判定する
     * */
    boolean isSameNumberOfVotes() {
        return choiceA.numberOfVotes() == choiceB.numberOfVotes();
    }

    /**
     * 多数派を取得する
     * */
    Choice majority() {
        if (isSameNumberOfVotes()) {
            return null;
        }
        if (choiceA.numberOfVotes() > choiceB.numberOfVotes()) {
            return choiceA;
        }

        return choiceB;
    }

    /**
     * 少数派を取得する
     * */
    Choice minority() {
        if (isSameNumberOfVotes()) {
            return null;
        }
        if (choiceA.numberOfVotes() < choiceB.numberOfVotes()) {
            return choiceA;
        }

        return choiceB;
    }
}
