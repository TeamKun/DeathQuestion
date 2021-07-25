package net.kunmc.lab.deathquestion.command;

import net.kunmc.lab.deathquestion.util.DecorationConst;
import org.bukkit.command.CommandSender;

import java.util.Set;

public class ErrorChecker {

    /** エラー：不正な引数 */
    private static final String ERR_MSG_ILLEGAL_ARGS = "引数が不正です";

    /** エラー：存在しないコマンド */
    private static final String ERR_MSG_NOT_EXIST_SUBCOMMAND = "存在しないコマンドです";

    /**
     * コマンドの組み合わせが正常か判定する.
     * */
    static boolean canCombine(CommandSender sender, SubCommand subCommand, Set<SubCommand> allowed) {
        if (!allowed.contains(subCommand)) {
            sender.sendMessage(DecorationConst.RED + ERR_MSG_ILLEGAL_ARGS);
            return false;
        }

        return true;
    }

    /**
     * 引数の数を判定する.
     * */
    static boolean isCorrectArgsLength(CommandSender sender, String[] args, int number) {
        if (args.length != number) {
            sender.sendMessage(DecorationConst.RED + ERR_MSG_ILLEGAL_ARGS);
            return false;
        }
        return true;
    }

    /**
     * 引数が足りているが判定する.
     * */
    static boolean isEnoughArgsLength(CommandSender sender, String[] args, int number) {
        if (args.length < number) {
            sender.sendMessage(DecorationConst.RED + ERR_MSG_ILLEGAL_ARGS);
            return false;
        }

        return true;
    }

    /**
     * サブコマンドが存在するか判定する.
     * */
    static SubCommand existSubCommand(CommandSender sender, String arg) {
        SubCommand subCommand = SubCommand.getSubCommand(arg);
        if (subCommand == null) {
            sender.sendMessage(DecorationConst.RED + ERR_MSG_NOT_EXIST_SUBCOMMAND);
        }
        return subCommand;
    }
}
