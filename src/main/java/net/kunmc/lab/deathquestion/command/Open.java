package net.kunmc.lab.deathquestion.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Open implements CommandExecutor, TabCompleter {

    /** サブコマンド */
    private Set<SubCommand> allowed;

    {
        allowed = EnumSet.of(SubCommand.KILL, SubCommand.CHANGE_TO_SPECTATOR);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // TODO 投票受付中か

        // TODO 第一引数が存在しているか
        // TODO 引数が正常か

        // TODO 開票処理

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length != 1) {
            return null;
        }

        return allowed.stream()
                .map(SubCommand::commandName)
                .filter(e -> e.startsWith(args[0]))
                .collect(Collectors.toList());
    }
}
