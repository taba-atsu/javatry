package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author taba-atsu
 */

public class BarkingProcess {
    private static final Logger logger = LoggerFactory.getLogger(BarkingProcess.class);

    public BarkedSound bark(Animal animal, String barkWord) {
        breatheIn(animal);
        animal.prepareAbdominalMuscle();
        BarkedSound barkedSound = animal.doBark(barkWord);
        return barkedSound;
    }

    private void breatheIn( Animal animal) { // actually depends on barking
        logger.debug("...Breathing in for barking"); // dummy implementation
        animal.downHitPoint();
    }
    // ここでbreathInを定義するためにdownHitPointをpublicにしてしまったが、本当にこれでいいのだろうか、、
    // #1on1: ↑素晴らしい悩み。リファクタリングをしたら、別のところのカプセル化を壊してしまった状態。 (2026/02/18)
}
