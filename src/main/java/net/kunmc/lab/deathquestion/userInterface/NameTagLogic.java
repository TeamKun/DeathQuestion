package net.kunmc.lab.deathquestion.userInterface;

import net.kunmc.lab.deathquestion.util.DecorationConst;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class NameTagLogic {
    /**
     * 投票開始時のネームタグを付ける
     */
    public static void setIncompleteTag(Player player) {
        player.playerListName(Component.text(DecorationConst.GRAY + "× " + DecorationConst.WHITE + player.getName()));
    }

    /**
     * 投票完了時のネームタグを付ける
     */
    public static void setCompleteTag(Player player) {
        player.playerListName(Component.text(DecorationConst.GOLD + "✓ " + DecorationConst.WHITE + player.getName()));
    }

    /**
     * 脱落者のネームタグを付ける
     */
    public static void setDropOutTag(Player player) {
        player.playerListName(Component.text(DecorationConst.STRIKETHROUGH + player.getName()));
    }

    /**
     * ネームタグをクリアする
     */
    public static void clearNameTag() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.playerListName(Component.text(player.getName()));
        });
    }
}
