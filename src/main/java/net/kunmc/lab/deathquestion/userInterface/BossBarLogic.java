package net.kunmc.lab.deathquestion.userInterface;

import net.kunmc.lab.deathquestion.util.DecorationConst;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class BossBarLogic {
    private static BossBar bar = Bukkit.createBossBar("", BarColor.RED, BarStyle.SEGMENTED_10);

    /**
     * バーを全員に表示する
     * */
    public static void showBar(String name) {
        bar.setTitle(DecorationConst.RED + name);

        Bukkit.getOnlinePlayers().forEach(player -> {
            bar.addPlayer(player);
        });

        bar.setVisible(true);
    }

    /**
     * 表示するプレイヤーを追加する
     * */
    public static void addPLayer(Player player) {
        bar.addPlayer(player);
    }

    /**
     * バーを非表示にする
     * */
    public static void hideBar() {
        bar.setVisible(false);
    }
}
