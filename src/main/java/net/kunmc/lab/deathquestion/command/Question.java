package net.kunmc.lab.deathquestion.command;

import net.kunmc.lab.deathquestion.config.Config;
import net.kunmc.lab.deathquestion.game.Manager;
import net.kunmc.lab.deathquestion.util.DecorationConst;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
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

        SubCommand subCommand;

        // 引数が１つ以上存在するか
        if (!ErrorChecker.isEnoughArgsLength(sender, args, 1)) {
            return false;
        }

        subCommand = SubCommand.getSubCommand(args[0]);

        // サブコマンドが正常か
        if (subCommand == null) {
            return false;
        }

        if (!ErrorChecker.canCombine(sender, subCommand, allowedSubcommand)) {
            return false;
        }

        switch (SubCommand.getSubCommand(args[0])) {
            case ASK:
                return ask(sender,args);
            case IGNORE_PLAYER:
                return ignorePlayer(sender,args);
            case CANCEL:
                return cancel(sender,args);
            default:
                return false;
        }
    }

    private boolean ask(CommandSender sender, String[] args) {
        // 引数が足りているか
        if (!ErrorChecker.isCorrectArgsLength(sender, args, 4)) {
            return false;
        }

        // 出題処理
        Manager.startVoting(args[1], args[2], args[3]);
        return true;
    }

    private boolean ignorePlayer(CommandSender sender, String[] args) {
        SubCommand subCommand;
        //引数なし
        if (args.length < 2) {
            // リスト表示処理
            Config.showIgnorePlayerList(sender);
            return true;
        }

        // 引数が足りているか
        if (!ErrorChecker.isCorrectArgsLength(sender, args, 3)) {
            return false;
        }

        // 引数が正常か
        subCommand = ErrorChecker.existSubCommand(sender, args[1]);
        if (subCommand == null) {
            return false;
        }

        if (!ErrorChecker.canCombine(sender, subCommand, allowedIgnorePlayerSubcommand)) {
            return false;
        }

        Player target = Bukkit.getPlayer(args[2]);

        if (target == null) {
            sender.sendMessage(DecorationConst.RED + "存在しないプレイヤーです");
            return true;
        }

        switch (SubCommand.getSubCommand(args[1])) {
            case ADD:
                // 追加処理
                Config.addIgnorePlayerList(sender, target);
                return true;
            case REMOVE:

                // 削除処理
                Config.removeIgnorePlayerList(sender, target);
                return true;
            default:
                return false;
        }
    }

    private boolean cancel(CommandSender sender, String[] args) {
        if (!Manager.cancelVoting()) {
            sender.sendMessage(DecorationConst.RED + "現在投票を受け付けていません");
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        SubCommand subCommand = SubCommand.getSubCommand(args[0]);
        switch (args.length) {
            case 1:
                return first(args);
            case 2:
                return second(subCommand, args);
            case 3:
                return third(subCommand, args);
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

    private List<String> second(SubCommand subCommand, String[] args) {
        if (subCommand == null) {
            return new ArrayList<>();
        }

        switch (subCommand) {
            case ASK:
                return Collections.singletonList("<質問>");
            case IGNORE_PLAYER:
                return allowedIgnorePlayerSubcommand.stream()
                        .map(SubCommand::commandName)
                        .filter(e -> e.startsWith(args[1]))
                        .collect(Collectors.toList());
            default:
                return new ArrayList<>();
        }
    }

    private List<String> third(SubCommand subCommand1, String[] args) {
        if (subCommand1 == null) {
            return new ArrayList<>();
        }
        if (SubCommand.ASK.equals(subCommand1)) {
            return Collections.singletonList("<選択肢A>");
        }

        SubCommand subCommand2 = SubCommand.getSubCommand(args[1]);

        if (subCommand2 == null) {
            return new ArrayList<>();
        }

        if (subCommand1.equals(SubCommand.IGNORE_PLAYER) &&
                (subCommand2.equals(SubCommand.ADD) || subCommand2.equals(SubCommand.REMOVE))) {
            return null;
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
