package net.kunmc.lab.deathquestion.game;

import net.kunmc.lab.deathquestion.DeathQuestion;
import net.kunmc.lab.deathquestion.config.Config;
import net.kunmc.lab.deathquestion.config.ExecutionMethod;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class GameLogic {
    /**
     * 処刑処理
     * */
    public static void execute(List<Player> targetList) {
        new BukkitRunnable() {
            public void run() {
                targetList.forEach(player -> {
                    if (Config.executionMethod().equals(ExecutionMethod.KILL)) {
                        player.damage(10000);
                    } else {
                        player.setGameMode(GameMode.SPECTATOR);
                    }
                });
            }
        }.runTask(DeathQuestion.plugin);
    }
}