package net.kunmc.lab.deathquestion.game;

import net.kunmc.lab.deathquestion.config.Config;
import net.kunmc.lab.deathquestion.game.question.Question;
import net.kunmc.lab.deathquestion.game.question.Symbol;
import net.kunmc.lab.deathquestion.userInterface.ActionBarLogic;
import net.kunmc.lab.deathquestion.userInterface.BossBarLogic;
import net.kunmc.lab.deathquestion.userInterface.NameTagLogic;
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

            NameTagLogic.setIncompleteTag(player);
        });

        Bukkit.broadcast(Component.text(DecorationConst.RED + "◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇"));
        Bukkit.broadcast(Component.text(DecorationConst.RED + "投票開始"));
        Bukkit.broadcast(Component.text(DecorationConst.RED + "投票コマンドを入力してください"));
        Bukkit.broadcast(Component.text(DecorationConst.RED + "/vote a - " + DecorationConst.AQUA + choisesA + DecorationConst.RED + "に投票"));
        Bukkit.broadcast(Component.text(DecorationConst.RED + "/vote b - " + DecorationConst.LIGHT_PURPLE + choisesB + DecorationConst.RED + "に投票"));
        Bukkit.broadcast(Component.text(DecorationConst.RED + "◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇"));

        // バーを表示
        BossBarLogic.showBar(theme);
        ActionBarLogic.createActionBar(choisesA, choisesB);
    }

    /**
     * 投票する
     * */
    public void vote(Player voter, Symbol symbol) {
        NameTagLogic.setCompleteTag(voter);
        question.vote(voter, symbol);
    }

    /**
     * 開票する
     * */
    public void open(CommandSender sender) {
        question.open();
    }
}
