package net.kunmc.lab.deathquestion.game;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Event implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onPluginReload(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().equalsIgnoreCase("/reload confirm")) {
            Logic.clearNameTag();
        }
    }
}
