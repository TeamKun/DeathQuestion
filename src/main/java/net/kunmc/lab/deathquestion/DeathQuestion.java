package net.kunmc.lab.deathquestion;

import net.kunmc.lab.deathquestion.command.Command;
import net.kunmc.lab.deathquestion.config.Config;
import net.kunmc.lab.deathquestion.game.Event;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathQuestion extends JavaPlugin {

    /** プラグインオブジェクト */
    public static DeathQuestion plugin;

    @Override
    public void onEnable() {
        plugin = this;

        // コンフィグ
        Config.loadConfig();

        // コマンド
        getCommand(Command.OPEN.commandName()).setExecutor((CommandExecutor) Command.OPEN.instance());
        getCommand(Command.VOTE.commandName()).setExecutor((CommandExecutor) Command.VOTE.instance());
        getCommand(Command.QUESTION.commandName()).setExecutor((CommandExecutor) Command.QUESTION.instance());

        getCommand(Command.OPEN.commandName()).setTabCompleter((TabCompleter) Command.OPEN.instance());
        getCommand(Command.VOTE.commandName()).setTabCompleter((TabCompleter) Command.VOTE.instance());
        getCommand(Command.QUESTION.commandName()).setTabCompleter((TabCompleter) Command.QUESTION.instance());

        // イベント
        getServer().getPluginManager().registerEvents(new Event(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
