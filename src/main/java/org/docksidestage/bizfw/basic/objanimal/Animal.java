/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.barking.BarkingProcess;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The object for animal(動物).
 * @author jflute
 */
public abstract class Animal implements Loudable {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(Animal.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected int hitPoint; // is HP
    
    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Animal() {
        hitPoint = getInitialHitPoint();
    }

    protected int getInitialHitPoint() {
        return 10; // as default
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    public BarkedSound bark() {
        return new BarkingProcess().bark(this, this.getBarkWord());
    }

    // done tabata 要件的な話、吠える専用メソッドと言えるので、これもBarkingProcessに移動 by jflute (2026/01/07)
    // TODO tabata ↑もういっこ↓これも by jflute (2026/02/18)
    public void prepareAbdominalMuscle() { // also actually depends on barking
        logger.debug("...Using my abdominal muscle for barking"); // dummy implementation
        downHitPoint();
    }

    // #1on1: javaのpackageスコープ、物理構造に依存するロジックになっちゃうので、jflute的に避けがち (2026/01/07)
    // 個人的には、testクラスとかだと定型的で使うけど(mainとtestの関係)、main内だけ閉じるときは極力使わない。
    // done tabata 修行++: カプセル化的にはpublicをprotectedに戻したいところ by jflute (2026/01/07)
    // #1on1: こっちは参照のみなので、BarkingProcessがgetBarkWord()を呼ぶ必要はない。 (2026/02/18)
    // あらかじめ読んで値だけを渡せば良い。引数/戻り値デザインを高度な世界の中でも忘れずに。
    protected abstract String getBarkWord();

    public BarkedSound doBark(String barkWord) {
        downHitPoint();
        return new BarkedSound(barkWord);
    }

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    // TODO tabata 修行#: どうにかしてpublicにしてしまったのをprotectedに戻したい (package移動はせず) by jflute (2026/02/18)
    public void downHitPoint() {
        --hitPoint;
        if (hitPoint <= 0) {
            throw new IllegalStateException("I'm very tired, so I want to sleep" + getBarkWord());
        }
    }

    // ===================================================================================
    //                                                                               Loud
    //                                                                              ======
    @Override
    public String soundLoudly() {
        return bark().getBarkWord();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getHitPoint() {
        return hitPoint;
    }
}
