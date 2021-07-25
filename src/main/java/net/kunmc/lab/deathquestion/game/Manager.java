package net.kunmc.lab.deathquestion.game;

import net.kunmc.lab.deathquestion.game.question.Symbol;
import net.kunmc.lab.deathquestion.util.DecorationConst;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Manager {

    /**
     * ゲームの状態
     */
    private static State state = new Waiting();

    /**
     * 投票を開始する
     */
    public static void startVoting(String theme, String choisesA, String choisesB) {
        state = new Voting(theme, choisesA, choisesB);
    }

    /**
     * 質問をキャンセルする
     * */
    public static boolean cancelVoting() {
        if (!(state instanceof Voting)) {
            return false;
        }
        state = new Waiting();
        Bukkit.broadcast(Component.text(DecorationConst.YELLOW + "質問がキャンセルされました"));
        return true;
    }

    /**
     * 投票する
     * */
    public static void vote(Player voter, Symbol symbol) {
        state.vote(voter, symbol);
    }
}
