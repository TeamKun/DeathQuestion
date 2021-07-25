package net.kunmc.lab.deathquestion.game;

import net.kunmc.lab.deathquestion.game.question.Symbol;
import net.kunmc.lab.deathquestion.util.DecorationConst;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Waiting implements State {

    public void vote(Player voter, Symbol symbol) {
        voter.sendMessage(DecorationConst.RED + "現在投票を受付けていません");
    }

    /**
     * 開票する
     * */
    public void open(CommandSender sender) {
        sender.sendMessage(DecorationConst.RED + "現在投票中ではありません");
    }
}
