package net.kunmc.lab.deathquestion.game;

import net.kunmc.lab.deathquestion.game.question.Choice;
import net.kunmc.lab.deathquestion.game.question.Choices;
import net.kunmc.lab.deathquestion.game.question.Question;
import net.kunmc.lab.deathquestion.util.DecorationConst;
import net.kunmc.lab.deathquestion.util.MessageUtil;
import net.kunmc.lab.deathquestion.util.SoundUtil;
import org.bukkit.Sound;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DirectionLogic {

    /**
     * 開票開始
     * */
    public static void startOpen() throws InterruptedException {
        MessageUtil.sendTitleAll(DecorationConst.DARK_RED + "開票",
                "",
                20,
                40,
                20);
        Thread.sleep(4000);
    }

    /**
     * 開票演出
     * */
    public static void open(Choices choises) throws InterruptedException {

        int choiceANumberOfVotes = choises.a().numberOfVotes();
        int choiceBNumberOfVotes = choises.b().numberOfVotes();

        int count = 0;
        int countA = 0;
        int countB = 0;


        // 多数派の得票数分ループ
        while (count <= Math.max(choiceANumberOfVotes, choiceBNumberOfVotes)) {
            count ++;

            // 0の時は音を鳴らさない
            if (count != 0) {
                SoundUtil.playSoundAll(Sound.BLOCK_NOTE_BLOCK_BIT);
            }

            MessageUtil.sendTitleAll(DecorationConst.AQUA + countA + DecorationConst.RESET + " - " + DecorationConst.RED + countB,
                    DecorationConst.AQUA + choises.a().name() + DecorationConst.RESET + " - " + DecorationConst.RED + choises.b().name(),
                    0,
                    400,
                    0);

            if (countA < choiceANumberOfVotes) {
                countA ++;
            }

            if (countB < choiceBNumberOfVotes) {
                countB ++;
            }

            Thread.sleep(200);
        }

        Thread.sleep(2000);
    }

    /**
     * 結果表示演出
     * */
    public static void resultShow(Choice majority) throws InterruptedException {
        SoundUtil.playSoundAll(Sound.BLOCK_ANVIL_USE);
        MessageUtil.sendTitleAll(DecorationConst.GREEN + majority.name() + "の勝利",
                "",
                20,
                40,
                20);
        Thread.sleep(4000);
    }
    /**
     * 引き分け演出
     * */
    public static void draw() throws InterruptedException {
        SoundUtil.playSoundAll(Sound.BLOCK_ANVIL_USE);
        MessageUtil.sendTitleAll(DecorationConst.GREEN + "引き分け",
                "",
                20,
                40,
                20);
        Thread.sleep(4000);
    }

    /**
     * 結果の詳細を表示
     * */
    public static void showProperty(Question question) {
        Choices choices = question.choices();
        Choice a = choices.a();
        Choice b = choices.b();

        /** テーマ */
        String theme = question.theme();
        /** 選択肢A */
        String choiceNameA = a.name();
        /** 選択肢B */
        String choiceNameB = b.name();

        /** 選択肢Aの得票数 */
        float NumberOfVotesA = a.numberOfVotes();
        /** 選択肢Bの得票数 */
        float NumberOfVotesB = b.numberOfVotes();

        /** 投票の総数 */
        float totalNumberOfVotes = NumberOfVotesA + NumberOfVotesB;

        /** 選択肢Aの得票率 */
        String percentageOfVotesA;
        /** 選択肢Bの得票率 */
        String percentageOfVotesB;

        // 割合の算出
        // ゼロ除算回避
        if (totalNumberOfVotes <= 0) {
            percentageOfVotesA = "0.00";
            percentageOfVotesB = "0.00";
        } else {
            percentageOfVotesA = new BigDecimal(NumberOfVotesA / totalNumberOfVotes * 100).setScale(2, RoundingMode.HALF_UP).toString();
            percentageOfVotesB = new BigDecimal(NumberOfVotesB / totalNumberOfVotes * 100).setScale(2, RoundingMode.HALF_UP).toString();
        }

        MessageUtil.broadcast("◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇");
        MessageUtil.broadcast(DecorationConst.GREEN + "投票結果");
        MessageUtil.broadcast(DecorationConst.GREEN + "お題: " + theme);
        MessageUtil.broadcast(DecorationConst.GREEN + "投票総数: " + (int)totalNumberOfVotes + "票");
        MessageUtil.broadcast(" ");
        MessageUtil.broadcast(DecorationConst.AQUA + "A: " + choiceNameA);
        MessageUtil.broadcast(DecorationConst.AQUA + "得票数: " + (int)NumberOfVotesA + "票");
        MessageUtil.broadcast(DecorationConst.AQUA + "得票率: " + percentageOfVotesA + "%");
        MessageUtil.broadcast(" ");
        MessageUtil.broadcast(DecorationConst.LIGHT_PURPLE + "B: " + choiceNameB);
        MessageUtil.broadcast(DecorationConst.LIGHT_PURPLE + "得票数: " + (int)NumberOfVotesB + "票");
        MessageUtil.broadcast(DecorationConst.LIGHT_PURPLE + "得票率: " + percentageOfVotesB + "%");
        MessageUtil.broadcast("◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇");
    }
}
