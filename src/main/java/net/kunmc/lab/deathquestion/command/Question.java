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

public class Question implements CommandExecutor, TabCompleter {

    /** サブコマンド */
    private Set<SubCommand> allowedSubcommand;

    /** ignorePlayerのサブコマンド */
    private Set<SubCommand> allowedIgnorePlayerSubcommand;

    {
        allowedSubcommand = EnumSet.of(SubCommand.ASK, SubCommand.IGNORE_PLAYER, SubCommand.CANCEL);
        allowedIgnorePlayerSubcommand = EnumSet.of(SubCommand.ADD, SubCommand.REMOVE);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // TODO 引数が１つ以上存在するか

        // TODO サブコマンドが正常か

        switch (SubCommand.getSubCommand(args[0])) {
            case ASK:
                ask(sender,args);
                break;
            case IGNORE_PLAYER:
                ignorePlayer(sender,args);
                break;
            case CANCEL:
                cancel(sender,args);
                break;

        }
        return false;
    }

    private void ask(CommandSender sender, String[] args) {
        // TODO 引数が足りているか

        // TODO 出題処理
    }

    private void ignorePlayer(CommandSender sender, String[] args) {

        // TODO 引数が足りているか

        // TODO 引数が正常か

        // TODO プレイヤーが存在するか

        switch (SubCommand.getSubCommand(args[1])) {
            case ADD:
                // TODO すでに追加されている
                // TODO 追加処理
                break;
            case REMOVE:

                // TODO リストに存在しない
                // TODO 削除処理
                break;
        }
    }

    private void cancel(CommandSender sender, String[] args) {
        // TODO 投票受付中か

        // TODO キャンセル処理
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        SubCommand subCommand = SubCommand.getSubCommand(args[0]);
        switch (args.length) {
            case 1:
                return first(args);
            case 2:
                return second(subCommand);
            case 3:
                return third(subCommand);
            case 4:
                return fourth(subCommand);
            default:
                return new ArrayList<>();
        }
    }

    private List<String> first(String[] args) {
        return allowedSubcommand.stream()
                .map(SubCommand::commandName)
                .filter(e -> e.startsWith(args[0]))
                .collect(Collectors.toList());
    }

    private List<String> second(SubCommand subCommand) {
        switch (subCommand) {
            case ASK:
                return Collections.singletonList("<質問>");
            case IGNORE_PLAYER:
                return null;
            default:
                return new ArrayList<>();
        }
    }

    private List<String> third(SubCommand subCommand) {
        if (SubCommand.ASK.equals(subCommand)) {
            return Collections.singletonList("<選択肢A>");
        }

        return new ArrayList<>();
    }

    private List<String> fourth(SubCommand subCommand) {
        if (SubCommand.ASK.equals(subCommand)) {
            return Collections.singletonList("<選択肢B>");
        }

        return new ArrayList<>();
    }
}
