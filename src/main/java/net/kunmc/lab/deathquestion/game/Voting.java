package net.kunmc.lab.deathquestion.game;

import net.kunmc.lab.deathquestion.config.Config;
import net.kunmc.lab.deathquestion.game.question.Question;
import net.kunmc.lab.deathquestion.game.question.Symbol;
import net.kunmc.lab.deathquestion.util.DecorationConst;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Voting implements State {

    /** 質問 */
    Question question;

    Voting(String theme, String choisesA, String choisesB) {
        question = new Question(theme, choisesA, choisesB);

        Bukkit.getOnlinePlayers().forEach(player -> {
            if (Config.containsIgnorePlayerList(player)) {
                return;
            }

            if( player.getGameMode().equals(GameMode.SPECTATOR)) {
                return;
            }

            Logic.setIncompleteTag(player);
        });

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
        Logic.setCompleteTag(voter);
        question.vote(voter, symbol);
    }

    /**
     * 開票する
     * */
    public void open(CommandSender sender) {
        question.open();
    }
}
