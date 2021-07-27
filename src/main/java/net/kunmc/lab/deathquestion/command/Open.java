package net.kunmc.lab.deathquestion.command;

import net.kunmc.lab.deathquestion.config.Config;
import net.kunmc.lab.deathquestion.config.ExecutionMethod;
import net.kunmc.lab.deathquestion.game.Manager;
import net.kunmc.lab.deathquestion.util.DecorationConst;
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
        SubCommand subCommand;

        // 第一引数が存在しているか
        if (args.length >= 1) {
            subCommand = ErrorChecker.existSubCommand(sender, args[0]);
            // 引数が正常か
            if (subCommand == null) {
                return false;
            }

            if (!ErrorChecker.canCombine(sender, subCommand, allowed)) {
                return false;
            }

            ExecutionMethod executionMethod = ExecutionMethod.getMethodName(args[0]);

            // 処刑方法変更
            Config.setExecutionMethod(executionMethod);
            sender.sendMessage(DecorationConst.GREEN + "処刑方法を" + executionMethod.methodName() + "に設定しました");
        }

        // 開票処理
        Manager.open(sender);
        return true;
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
