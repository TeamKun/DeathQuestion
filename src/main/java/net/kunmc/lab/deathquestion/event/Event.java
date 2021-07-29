package net.kunmc.lab.deathquestion.event;

import net.kunmc.lab.deathquestion.config.Config;
import net.kunmc.lab.deathquestion.config.ExecutionMethod;
import net.kunmc.lab.deathquestion.game.Manager;
import net.kunmc.lab.deathquestion.userInterface.ActionBarLogic;
import net.kunmc.lab.deathquestion.userInterface.BossBarLogic;
import net.kunmc.lab.deathquestion.userInterface.NameTagLogic;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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
    public void onPlayerQuit(PlayerQuitEvent event) {
        // 開票中にログアウトしたときのペナルティ
        if (!Manager.isOpening()) {
            return;
        }

        Player player = event.getPlayer();

        // アイテムをクリア
        player.getInventory().clear();

        // 処刑方法がスペクテイターに変更の場合
        if (Config.executionMethod().equals(ExecutionMethod.CHANGE_TO_SPECTATOR)) {
            player.setGameMode(GameMode.SPECTATOR);
        }
    }
}
