package net.kunmc.lab.deathquestion.command;

import net.kunmc.lab.deathquestion.util.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class Vote implements CommandExecutor, TabCompleter {

    /** サブコマンド */
    private Set<SubCommand> allowed;

    {
        allowed = EnumSet.of(SubCommand.A, SubCommand.B);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length != 1) {
            return new ArrayList<>();
        }

        return allowed.stream()
                .map(SubCommand::commandName)
                .filter(e -> e.startsWith(args[0]))
                .collect(Collectors.toList());
    }
}
