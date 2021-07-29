package net.kunmc.lab.deathquestion.userInterface;

import net.kunmc.lab.deathquestion.game.Manager;
import net.kunmc.lab.deathquestion.util.DecorationConst;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BossBarLogic extends BukkitRunnable {
    private static BossBar bar = Bukkit.createBossBar("", BarColor.RED, BarStyle.SOLID);

    @Override
    public void run() {
        addPLayer();
    }

    /**
     * バーを全員に表示する
     * */
    public static void showBar(String name) {
        bar.setTitle(DecorationConst.RED + name);
        bar.setProgress(0);

        Bukkit.getOnlinePlayers().forEach(player -> {
            bar.addPlayer(player);
        });

        bar.setVisible(true);
    }

    /**
     * 表示するプレイヤーを追加する
     * */
    public static void addPLayer() {
        if (!Manager.isVoting()) {
            return;
        }
        Bukkit.getOnlinePlayers().forEach(player -> {
            if(bar.getPlayers().contains(player)) {
                return;
            }
            bar.addPlayer(player);
        });
    }

    /**
     * バーを非表示にする
     * */
    public static void hideBar() {
        bar.setVisible(false);
    }
}
