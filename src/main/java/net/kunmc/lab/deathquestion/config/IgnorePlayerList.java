package net.kunmc.lab.deathquestion.config;

import net.kunmc.lab.deathquestion.DeathQuestion;
import net.kunmc.lab.deathquestion.util.DecorationConst;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class IgnorePlayerList {
    public static final String PATH = "ignorePLayers";

    private List<Player> list;

    IgnorePlayerList(List<Player> players) {
        list = new ArrayList<>();

        if (players == null) {
            return;
        }

        players.forEach(player -> {
            if (player == null) {
                return;
            }
            list.add(player);
        });
    }

    /**
     * 変更を保存する
     * */
    void saveChange(FileConfiguration config) {
        config.set(PATH, list);
        DeathQuestion.plugin.saveConfig();
    }

    /**
     * リストを表示する
     * */
    void showList(CommandSender sender) {
        if (list.size() == 0) {
            sender.sendMessage(DecorationConst.YELLOW + "対象プレイヤーなし");
        }
        list.forEach(player -> {
            sender.sendMessage(player.getName());
        });
    }

    /**
     * プレイヤーを追加する
     * */
    void addList(CommandSender sender, Player target) {

        if (list.contains(target)) {
            sender.sendMessage(DecorationConst.RED + "すでにリストに入っています");
            return;
        }

        list.add(target);
        sender.sendMessage(DecorationConst.GREEN + target.getName() + "を対象外リストに追加しました");
    }

    /**
     * プレイヤーを削除する
     * */
    void removeList(CommandSender sender, Player target) {
        if (!list.remove(target)) {
            sender.sendMessage(DecorationConst.RED + "プレイヤーはリストに入っていません");
            return;
        }

        sender.sendMessage(DecorationConst.GREEN + target.getName() + "を対象外リストから削除しました");
    }

    /**
     * プレイヤーがリストにはいっているか確認する
     * */
    boolean contains(Player target) {
        return list.contains(target);
    }
}
