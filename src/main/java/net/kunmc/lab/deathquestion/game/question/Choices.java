package net.kunmc.lab.deathquestion.game.question;

import net.kunmc.lab.deathquestion.util.DecorationConst;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Choices {

    private Symbol symbol;
    /** 名前 */
    private String name;

    /** 投票者リスト */
    List<Player> VoterList;

    Choices(Symbol symbol, String name) {
        this.symbol = symbol;
        this.name = name;
        VoterList = new ArrayList<>();
    }

    /**
     * 投票する
     * */
    void vote(Player voter) {
        VoterList.add(voter);
        voter.sendMessage(DecorationConst.GREEN + this.symbol.name() + "." + this.name + "に投票しました");
    }

    /**
     * リストから削除する
     * */
    void remove(Player voter) {
        VoterList.remove(voter);
    }

    /**
     *　シンボルをチェック
     * */
    boolean isMatchSymbol(Symbol symbol) {
        return this.symbol.equals(symbol);
    }
}
