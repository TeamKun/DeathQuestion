package net.kunmc.lab.deathquestion.config;

import net.kunmc.lab.deathquestion.DeathQuestion;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class Config {
    /** コンフィグオブジェクト */
    static FileConfiguration config;

    /** 対象外リスト */
    static IgnorePlayerList ignorePlayerList;

    /** 処刑方法 */
    static ExecutionMethod executionMethod;

    /**
     * コンフィグをロードする
     * */
    public static void loadConfig() {
        DeathQuestion.plugin.saveDefaultConfig();

        //　コンフィグファイルを取得
        config = DeathQuestion.plugin.getConfig();

        // 対象外プレイヤーリスト
        ignorePlayerList = new IgnorePlayerList((List<Player>) config.getList(IgnorePlayerList.PATH));

        // 処刑方法
        executionMethod = ExecutionMethod.getMethodName(config.getString(ExecutionMethod.PATH));

        if (executionMethod == null) {
            executionMethod = ExecutionMethod.CHANGE_TO_SPECTATOR;
        }
    }

    /**
     * プレイヤーリストを表示
     * */
    public static void showIgnorePlayerList(CommandSender sender) {
        ignorePlayerList.showList(sender);
    }

    /**
     * プレイヤーを追加する
     * */
    public static void addIgnorePlayerList(CommandSender sender, Player target) {
        ignorePlayerList.addList(sender, target);
        ignorePlayerList.saveChange(config);
    }

    /**
     * プレイヤーを削除する
     * */
    public static void removeIgnorePlayerList(CommandSender sender, Player target) {
        ignorePlayerList.removeList(sender,target);
        ignorePlayerList.saveChange(config);
    }

    /**
     * プレイヤーがリストにはいっているか確認する
     * */
    public static boolean containsIgnorePlayerList(Player target) {
        return ignorePlayerList.contains(target);
    }

    /**
     * 処刑方法を取得する
     * */
    public static ExecutionMethod executionMethod() {
        return executionMethod;
    }

    /**
     * 処刑方法を設定する
     * */
    public static void setExecutionMethod(ExecutionMethod method) {
        executionMethod = method;
        config.set(ExecutionMethod.PATH, method.methodName());
        DeathQuestion.plugin.saveConfig();

    }
}
