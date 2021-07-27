package net.kunmc.lab.deathquestion.game.question;

import net.kunmc.lab.deathquestion.config.Config;
import net.kunmc.lab.deathquestion.game.GameLogic;
import net.kunmc.lab.deathquestion.util.DecorationConst;
import net.kunmc.lab.deathquestion.util.MessageUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 選択肢Aを取得する
     * */
    public Choice a() {
        return choiceA;
    }

    /**
     * 選択肢Bを取得する
     * */
    public Choice b() {
        return choiceB;
    }

    /**
     * 投票していないプレイヤーを処刑
     * */
    public void executeNoVotePlayerList(List<Player> targetPlayerList) {
        List<Player> noVotePlayerList = new ArrayList<>();

        targetPlayerList.forEach(player -> {
            // 投票しているか
            if (choiceA.contains(player) || choiceB.contains(player)) {
                return;
            }

            // 対象外リストに含まれるか
            if (Config.containsIgnorePlayerList(player)) {
                return;
            }

            noVotePlayerList.add(player);
        });

        if (noVotePlayerList.size() == 0) {
            return;
        }

        // 処刑処理
        MessageUtil.broadcast(DecorationConst.RED + "投票していないプレイヤーを処刑しました");

        // ネームタグをセット
        noVotePlayerList.forEach(player -> {
            player.playerListName(Component.text(player.getName() + " : " + "無投票"));
        });

        // 処刑処理
        GameLogic.execute(noVotePlayerList);
    }
}
