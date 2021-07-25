package net.kunmc.lab.deathquestion.game;

import net.kunmc.lab.deathquestion.util.DecorationConst;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Logic {
    /**
     * 投票開始時のネームタグを付ける
     * */
    static void setIncompleteTag(Player player) {
        player.playerListName(Component.text(DecorationConst.GRAY + "× " + DecorationConst.WHITE + player.getName()));
    }

    /**
     * 投票完了時のネームタグを付ける
     * */
    static void setCompleteTag(Player player) {
        player.playerListName(Component.text(DecorationConst.GOLD + "✓ " + DecorationConst.WHITE + player.getName()));
    }

    /**
     * ネームタグをクリアする
     * */
    static void clearNameTag() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.playerListName(Component.text(player.getName()));
        });
    }
}
