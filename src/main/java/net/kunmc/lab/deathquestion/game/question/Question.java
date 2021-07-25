package net.kunmc.lab.deathquestion.game.question;

import net.kunmc.lab.deathquestion.util.DecorationConst;
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
     * 投票する
     * */
    public void vote(Player voter, Symbol symbol) {
        choices.vote(voter, symbol);
    }

    /**
     * 開票する
     * */
    public void open() {
        // TODO 開票演出

        // 得票数を比較
        // 同数獲得
        if (choices.isSameNumberOfVotes()) {
            return;
        }

        /** 多数派 */
        Choice majority = choices.majority();
        /** 少数派 */
        Choice minority = choices.minority();

        // 投票先を開示
        majority.setNameTag(DecorationConst.AQUA);
        minority.setNameTag(DecorationConst.DARK_RED);

        // 少数派を処刑
        minority.execute();
    }
}
