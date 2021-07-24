package net.kunmc.lab.deathquestion.command;

import net.kunmc.lab.deathquestion.util.DecorationConst;
import org.bukkit.command.CommandSender;

import java.util.Set;

public class ErrorChecker {

    /** エラー：不正な引数 */
    private static final String ERR_MSG_ILLEGAL_ARGS = "引数が不正です";

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
}
