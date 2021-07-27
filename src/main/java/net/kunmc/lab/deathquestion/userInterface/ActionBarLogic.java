package net.kunmc.lab.deathquestion.userInterface;

import net.kunmc.lab.deathquestion.util.DecorationConst;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ActionBarLogic extends BukkitRunnable {

    private static Component message = Component.text(" ");


    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendActionBar(message);
        });
    }

    /**
     * アクションバーをセットする
     * */
    public static void createActionBar(String choiceA, String choiceB) {
        message = Component.text(DecorationConst.AQUA + "A." + choiceA + "  " + DecorationConst.LIGHT_PURPLE + "B." + choiceB);
    }

    /**
     * アクションバーをクリアする
     * */
    public static void clearActionBar() {
        message = Component.text(" ");
    }
}
