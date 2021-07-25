package net.kunmc.lab.deathquestion.game;

import net.kunmc.lab.deathquestion.game.question.Symbol;
import org.bukkit.entity.Player;

public interface State {
    void vote(Player voter, Symbol symbol);
}
