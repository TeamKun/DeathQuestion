package net.kunmc.lab.deathquestion.game.question;

import net.kunmc.lab.deathquestion.game.State;
import net.kunmc.lab.deathquestion.util.DecorationConst;
import org.bukkit.entity.Player;

public class Waiting implements State {
    public void vote(Player voter, Symbol symbol) {
        voter.sendMessage(DecorationConst.RED + "現在投票を受付けていません");
    }
}
