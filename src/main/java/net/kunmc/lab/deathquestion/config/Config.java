package net.kunmc.lab.deathquestion.config;

import net.kunmc.lab.deathquestion.DeathQuestion;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class Config {
    static FileConfiguration config;

    static IgnorePlayerList ignorePlayerList;

    /**
     * コンフィグをロードする
     * */
    public static void loadConfig() {
        DeathQuestion.plugin.saveDefaultConfig();

        //　コンフィグファイルを取得
        config = DeathQuestion.plugin.getConfig();
        ignorePlayerList = new IgnorePlayerList((List<Player>) config.getList(IgnorePlayerList.PATH));
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
}
