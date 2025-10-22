package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.sleep.ShortSleeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The object for cow(牛).
 * @author taba-atsu
 */
public class Cow extends Animal implements ShortSleeper {
    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(Cow.class);


    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Cow() {
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    @Override
    protected String getBarkWord() {
        return "moo";
    }

    // ===================================================================================
    //                                                                              Sleeper
    //                                                                              ======
    @Override
    public void sleep() {
        logger.debug("Sleeping 4h");
    }
}
