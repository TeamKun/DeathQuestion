package net.kunmc.lab.deathquestion.game;

import net.kunmc.lab.deathquestion.DeathQuestion;
import net.kunmc.lab.deathquestion.config.Config;
import net.kunmc.lab.deathquestion.game.question.Choice;
import net.kunmc.lab.deathquestion.game.question.Choices;
import net.kunmc.lab.deathquestion.game.question.Question;
import net.kunmc.lab.deathquestion.game.question.Symbol;
import net.kunmc.lab.deathquestion.userInterface.ActionBarLogic;
import net.kunmc.lab.deathquestion.userInterface.BossBarLogic;
import net.kunmc.lab.deathquestion.userInterface.NameTagLogic;
import net.kunmc.lab.deathquestion.util.DecorationConst;
import net.kunmc.lab.deathquestion.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class Voting implements State {

    /** 質問 */
    Question question;

    Voting(String theme, String choisesA, String choisesB) {
        question = new Question(theme, choisesA, choisesB);

        Bukkit.getOnlinePlayers().forEach(player -> {
            if (Config.containsIgnorePlayerList(player)) {
                return;
            }

            if (player.getGameMode().equals(GameMode.SPECTATOR)) {
                NameTagLogic.setDropOutTag(player);
                return;
            }

            NameTagLogic.setIncompleteTag(player);
        });

        MessageUtil.broadcast(DecorationConst.RED + "◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇");
        MessageUtil.broadcast(DecorationConst.RED + "投票開始");
        MessageUtil.broadcast(DecorationConst.RED + "投票コマンドを入力してください");
        MessageUtil.broadcast(DecorationConst.RED + "/vote a - " + DecorationConst.AQUA + choisesA + DecorationConst.RED + "に投票");
        MessageUtil.broadcast(DecorationConst.RED + "/vote b - " + DecorationConst.LIGHT_PURPLE + choisesB + DecorationConst.RED + "に投票");
        MessageUtil.broadcast(DecorationConst.RED + "◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇");

        // バーを表示
        BossBarLogic.showBar(theme);
        ActionBarLogic.createActionBar(choisesA, choisesB);
    }

    /**
     * 投票する
     * */
    public void vote(Player voter, Symbol symbol) {
        NameTagLogic.setCompleteTag(voter);
        question.vote(voter, symbol);
    }

    /**
     * 開票する
     * */
    public void open(CommandSender sender) {
        // 開票開始時のプレイヤーを取得
        List<Player> playerList = (List<Player>) Bukkit.getOnlinePlayers();
        // 開票演出
        new BukkitRunnable() {
            public void run() {

                // 開票演出
                try {
                    // 開票中フラグを立てる
                    Manager.isOpening = true;

                    Choices choices = question.choices();
                    // 開票開始演出
                    DirectionLogic.startOpen();

                    // 開票演出
                    DirectionLogic.open(choices);

                    // 得票数を比較
                    // 同数獲得
                    if (question.isSameNumberOfVotes()) {
                        DirectionLogic.draw();
                        DirectionLogic.showProperty(question);
                        choices.executeNoVotePlayerList(playerList);
                        return;
                    }

                    /** 多数派 */
                    Choice majority = question.majority();
                    /** 少数派 */
                    Choice minority = question.minority();

                    // 結果表示演出
                    DirectionLogic.resultShow(majority);

                    // タイトルクリア
                    MessageUtil.clearTitle();

                    // 少数派を処刑
                    minority.execute();

                    // 投票していないプレイヤーを処刑
                    choices.executeNoVotePlayerList(playerList);

                    // 結果の詳細を表示
                    DirectionLogic.showProperty(question);

                    // 投票先を開示
                    majority.setNameTag(DecorationConst.AQUA);
                    minority.setNameTag(DecorationConst.DARK_RED);

                    // 開票中フラグを寝かせる
                    Manager.isOpening = false;

                } catch (Exception e) {
                    Manager.isOpening = false;
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(DeathQuestion.plugin);
    }
}
