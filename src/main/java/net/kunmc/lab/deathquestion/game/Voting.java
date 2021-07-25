package net.kunmc.lab.deathquestion.game;

import net.kunmc.lab.deathquestion.game.question.Question;
import net.kunmc.lab.deathquestion.game.question.Symbol;
import net.kunmc.lab.deathquestion.util.DecorationConst;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Voting implements State {

    /** 質問 */
    Question question;

    Voting(String theme, String choisesA, String choisesB) {
        question = new Question(theme, choisesA, choisesB);
        Bukkit.broadcast(Component.text(DecorationConst.GREEN + "◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇"));
        Bukkit.broadcast(Component.text(DecorationConst.GREEN + theme));
        Bukkit.broadcast(Component.text(DecorationConst.GREEN + "A." + choisesA));
        Bukkit.broadcast(Component.text(DecorationConst.GREEN + "B." + choisesB));
        Bukkit.broadcast(Component.text(DecorationConst.GREEN + "◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇"));
    }

    /**
     * 投票する
     * */
    public void vote(Player voter, Symbol symbol) {
        question.vote(voter, symbol);
    }
}
