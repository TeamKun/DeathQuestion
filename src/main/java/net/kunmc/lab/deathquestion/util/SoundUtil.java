package net.kunmc.lab.deathquestion.util;

import org.bukkit.Bukkit;
import org.bukkit.Sound;

public class SoundUtil {
    /**
     * 全体に音を鳴らす
     * */
    public static void playSoundAll(Sound sound) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.playSound(player.getEyeLocation(), sound, 1f, 1f);
        });
    }
}
