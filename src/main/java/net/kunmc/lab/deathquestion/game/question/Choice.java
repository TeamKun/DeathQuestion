package net.kunmc.lab.deathquestion.game.question;

import net.kunmc.lab.deathquestion.game.GameLogic;
import net.kunmc.lab.deathquestion.util.DecorationConst;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Choice {
    private Symbol symbol;
    /** 名前 */
    private String name;

    /** 投票者リスト */
    List<Player> voterList;

    Choice(Symbol symbol, String name) {
        this.symbol = symbol;
        this.name = name;
        voterList = new ArrayList<>();
    }

    /**
     * 投票する
     * */
    void vote(Player voter) {
        voterList.add(voter);
        voter.sendMessage(DecorationConst.GREEN + this.symbol.name() + "." + this.name + "に投票しました");
    }

    /**
     * リストから削除する
     * */
    void remove(Player voter) {
        voterList.remove(voter);
    }

    /**
     *　シンボルをチェック
     * */
    boolean isMatchSymbol(Symbol symbol) {
        return this.symbol.equals(symbol);
    }

    /**
     * 得票数を取得
     * */
    public int numberOfVotes() {
        return voterList.size();
    }

    /**
     * 処刑する
     * */
    public void execute() {
        if (voterList.size() == 0) {
            Bukkit.broadcast(Component.text(DecorationConst.GREEN + "少数派はいませんでした"));
            return;
        }

        Bukkit.broadcast(Component.text(DecorationConst.DARK_RED + "少数派を処刑しました"));

        // 処刑処理
        GameLogic.execute(voterList);
    }

    /**
     * 投票先をネームタグに表示する
     * */
    public void setNameTag(String colorConst) {
        voterList.forEach(player -> {
            player.playerListName(Component.text(player.getName() + " : "+ colorConst + symbol));
        });
    }

    /**
     * 文字列を取得
     * */
    public String name() {
        return this.name;
    }

    /**
     * プレイヤーがリストに含まれているか判定する
     * */
    boolean contains(Player player) {
        return voterList.contains(player);
    }
}
