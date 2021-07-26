package net.kunmc.lab.deathquestion.event;

import net.kunmc.lab.deathquestion.game.Manager;
import net.kunmc.lab.deathquestion.userInterface.ActionBarLogic;
import net.kunmc.lab.deathquestion.userInterface.BossBarLogic;
import net.kunmc.lab.deathquestion.userInterface.NameTagLogic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class Event implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onPluginReload(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().equalsIgnoreCase("/reload confirm")) {
            NameTagLogic.clearNameTag();

            // バーを非表示
            BossBarLogic.hideBar();
            ActionBarLogic.clearActionBar();
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerLogin(PlayerLoginEvent event) {
        if (!Manager.isVoting()) {
            return;
        }

        Player player = event.getPlayer();
        // バーを表示
        BossBarLogic.addPLayer(player);
    }
}
