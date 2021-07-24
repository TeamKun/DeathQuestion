package net.kunmc.lab.deathquestion;

import net.kunmc.lab.deathquestion.command.Command;
import net.kunmc.lab.deathquestion.command.Open;
import net.kunmc.lab.deathquestion.command.Question;
import net.kunmc.lab.deathquestion.command.Vote;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathQuestion extends JavaPlugin {

    /** プラグインオブジェクト */
    public static DeathQuestion plugin;

    @Override
    public void onEnable() {
        plugin = this;

        // コマンド
        getCommand(Command.OPEN.commandName()).setExecutor(new Open());
        getCommand(Command.VOTE.commandName()).setExecutor(new Vote());
        getCommand(Command.QUESTION.commandName()).setExecutor(new Question());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
