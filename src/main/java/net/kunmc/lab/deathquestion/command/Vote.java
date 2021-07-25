package net.kunmc.lab.deathquestion.command;

import net.kunmc.lab.deathquestion.util.DecorationConst;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class Vote implements CommandExecutor, TabCompleter {

    /** サブコマンド */
    private Set<SubCommand> allowed;

    /** エラー；投票権がありません */
    private final static String HAVE_NOT_RIGHT_TO_VOTE = "投票権がありません";

    {
        allowed = EnumSet.of(SubCommand.A, SubCommand.B);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        SubCommand subCommand;
        Player player = Bukkit.getPlayer(sender.getName());
        // 引数の数をチェック
        if (!ErrorChecker.isCorrectArgsLength(sender, args,1)) {
            return false;
        }

        subCommand = ErrorChecker.existSubCommand(sender, args[0]);
        // 引数が正常か
        if (subCommand == null) {
            return false;
        }

        if (!ErrorChecker.canCombine(sender, subCommand, allowed)) {
            return false;
        }
        // TODO 投票受付中か

        // スペクテーターでないか
        if (player.getGameMode().equals(GameMode.SPECTATOR)) {
            sender.sendMessage(DecorationConst.RED + HAVE_NOT_RIGHT_TO_VOTE);
            return false;
        }

        // TODO 投票対象外プレイヤーでないか

        // TODO 投票処理
        sender.sendMessage("投票処理");

        return true;
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
