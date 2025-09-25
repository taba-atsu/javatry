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
package org.docksidestage.javatry.basic;

import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth.TicketShortMoneyException;
import org.docksidestage.unit.PlainTestCase;
import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBuyResult;

/**
 * The test of class. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう) <br>
 * 
 * If ambiguous requirement exists, you can determine specification that seems appropriate. <br>
 * (要件が曖昧なところがあれば、適切だと思われる仕様を決めても良いです)
 * 
 * @author jflute
 * @author taba-atsu
 */
public class Step05ClassTest extends PlainTestCase {

    // ===================================================================================
    //                                                                          How to Use
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_class_howToUse_basic() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(7400);
        int sea = booth.getQuantity();
        log(sea); // your answer? => 9
        // buyOneDayPassportメソッドではquntityが0でない場合、-1するので9になる。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_overpay() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? => 10000
        // buyOneDayPassportメソッドでsalesProceedsがnullだとhandMoneyの値がそのまま代入されるので10000になる。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_nosales() {
        TicketBooth booth = new TicketBooth();
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? => null
        // salesProceedsは初期化されていないのに取得されているので、javaの仕様でnullになる。
        // Integerはオブジェクトなので初期値はnullに設定される。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_wrongQuantity() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // your answer? => 9
        // doTest_class_ticket_wrongQuantityメソッドが呼ばれるとTicketBoothクラスのオブジェクトが作成される
        // そして↓のメソッドではクラスのメソッドであるbuyOneDayPassportメソッドとgetQuantityメソッドが呼ばれており
        // 最終的にreturnされているのはgetQuantityメソッドの結果である。
        // したがってTicketBooth.javaファイルで実装を見てみるとquantityが返されている。上の実装をみると
        // quantityはMAX_QUANTITYという定数で初期化されており、今回は初期値が10。
        // buyOneDayPassportメソッドでデクリメントされているので、最終的には9が返される。
    }

    private Integer doTest_class_ticket_wrongQuantity() {
        TicketBooth booth = new TicketBooth();
        int handedMoney = 7399;
        try {
            booth.buyOneDayPassport(handedMoney);
            fail("always exception but none");
        } catch (TicketShortMoneyException continued) {
            log("Failed to buy one-day passport: money=" + handedMoney, continued);
        }
        return booth.getQuantity();
    }

    // ===================================================================================
    //                                                                           Let's fix
    //                                                                           =========
    /**
     * Fix the problem of ticket quantity reduction when short money. (Don't forget to fix also previous exercise answers) <br>
     * (お金不足でもチケットが減る問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_ticketQuantityReduction() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // should be max quantity, visual check here
        // handedMoney < ONE_DAY_PRICEという条件が書かれたif文より先にquantityのデクリメントが行われているのが原因と考えて
        // デクリメントする位置をそのif文の下へ変更
    }

    /**
     * Fix the problem of sales proceeds increased by handed money. (Don't forget to fix also previous exercise answers) <br>
     * (受け取ったお金の分だけ売上が増えていく問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_salesProceedsIncrease() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // should be same as one-day price, visual check here
        // salesProceedsにhandMoneyを足すのではなく、ONE_DAY_PRICEを足すように修正。
    }

    /**
     * Make method for buying two-day passport (price is 13200). (which can return change as method return value)
     * (TwoDayPassport (金額は13200) も買うメソッドを作りましょう (戻り値でお釣りをちゃんと返すように))
     */
    public void test_class_letsFix_makeMethod_twoday() {
        // uncomment after making the method
//        TicketBooth booth = new TicketBooth();
//        int money = 14000;
//        int change = booth.buyTwoDayPassport(money);
//        Integer sea = booth.getSalesProceeds() + change;
//        log(sea); // should be same as money
        // buyOneDayPassportを真似して作った。ただchangeをどの位置で定義するのか少し迷った。
        // done jflute 1on1でふぉろー予定 (2025/09/11)

        // and show two-day passport quantity here
        //log(booth.getQuantity());
        // デクリメントする数を2に変更
    }

    /**
     * Recycle duplicate logics between one-day and two-day by e.g. private method in class. (And confirm result of both before and after) <br>
     * (OneDayとTwoDayで冗長なロジックがあったら、クラス内のprivateメソッドなどで再利用しましょう (修正前と修正後の実行結果を確認))
     */
    public void test_class_letsFix_refactor_recycle() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        log(booth.getQuantity(), booth.getSalesProceeds()); // should be same as before-fix
        // この問題まではtwoDaypssportを購入した場合quantityが2減る仕様にしていたが、不自然な仕様だと考えてquantityが2減るのではなく1減るように変更しました。
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Now you cannot get ticket if you buy one-day passport, so return Ticket class and do in-park. <br>
     * (OneDayPassportを買ってもチケットをもらえませんでした。戻り値でTicketクラスを戻すようにしてインしましょう)
     */
    public void test_class_moreFix_return_ticket() {
        // uncomment out after modifying the method
//        TicketBooth booth = new TicketBooth();
//        Ticket oneDayPassport = booth.buyOneDayPassport(10000);
//        log(oneDayPassport.getDisplayPrice()); // should be same as one-day price
//        log(oneDayPassport.isAlreadyIn()); // should be false
//        oneDayPassport.doInPark();
//        log(oneDayPassport.isAlreadyIn()); // should be true
    }

    /**
     * Now also you cannot get ticket if two-day passport, so return class that has ticket and change. <br>
     * (TwoDayPassportもチケットをもらえませんでした。チケットとお釣りを戻すクラスを作って戻すようにしましょう)
     */
    public void test_class_moreFix_return_whole() {
        // uncomment after modifying the method
//        TicketBooth booth = new TicketBooth();
//        int handedMoney = 20000;
//        TicketBuyResult buyResult = booth.buyTwoDayPassport(handedMoney);
//        Ticket twoDayPassport = buyResult.getTicket();
//        int change = buyResult.getChange();
//        log(twoDayPassport.getDisplayPrice() + change); // should be same as money


        // 実装前メモ：booth.buyTwoDayPassport(handedMoney)の型がTicketBuyResultになっている
        // つまりTicketBoothクラスのbuyTwoDayPassportメソッドの返り値をTicketBuyResultクラスのインスタンスにする
        // また、TicketBuyResultクラスにはgetTicketメソッドとgetChangeメソッドを持っており、それぞれ返り値がTicketクラスのインスタンスとint型の整数である
        // TicketBoothクラスのbuyPassportメソッドでは、チケットが購入可能かどうかを管理するのみに変更
        // チケット購入の結果はTicketBuyResultクラスに。
        // 一旦これまでの問題のコンパイルが通らなくなったので、これ以前の問題でエラーがでた箇所はコメントアウトしました。
    }

    /**
     * Now you can use only one in spite of two-day passport, so fix Ticket to be able to handle plural days. <br>
     * (TwoDayPassportなのに一回しか利用できません。複数日数に対応できるようにTicketを修正しましょう)
     */
    public void test_class_moreFix_usePluralDays() {
        // your confirmation code here
        Ticket ticket = new Ticket(20000,2);
        ticket.doInPark();
//        ticket.doInPark();
//        ticket.doInPark();
//        ticket.doInPark();
        // TicketクラスにremaingDaysという変数を持たせることで、複数日程のチケットでも入場可否を判断できるように変更。
        // remainingDaysを2に設定して、doInpark()を呼び出してみて挙動をテストした。
        // 呼び出し回数が2回まではエラーが出ず、3回以上呼び出すと正しくエラーが出ることを確認できた。
        // いくつの場所でコンパイルエラーが出たので一旦コメントアウトしたり、remainingDaysの値を付け加えたりした。
        // Ticketクラスにチケット情報の保持という役割と入場手続きの実行という二つの役割を持ってしまっていることが少し気になった。
}

    /**
     * Accurately determine whether type of bought ticket is two-day passport or not by if-statemet. (fix Ticket classes if needed) <br>
     * (買ったチケットの種別がTwoDayPassportなのかどうかをif文で正確に判定してみましょう。(必要ならTicketクラスたちを修正))
     */
    public void test_class_moreFix_whetherTicketType() {
        // uncomment when you implement this exercise
        TicketBooth booth = new TicketBooth();
        TicketBuyResult buyOneDayPassportResult = booth.buyOneDayPassport(10000);
        Ticket oneDayPassport = buyOneDayPassportResult.getTicket();
        showTicketIfNeeds(oneDayPassport);
        TicketBuyResult buyTwoDayPassportResult = booth.buyTwoDayPassport(20000);
        Ticket twoDayPassport = buyTwoDayPassportResult.getTicket();
        showTicketIfNeeds(twoDayPassport);
        // 今までの問題をといていた影響で元々書かれていたコードではコンパイルエラーが起きるので、実行できるように修正した。
        // 解決策としてはTicketクラスにticketDaysというインスタンス変数を持たせるように変更して、いつでもチケット種別を判定しやすいようにした。
        // その際にその変数を文字列型にするかint型にするか少し迷ったが、int型で日数を渡す方がインスタンスを作成する時にわかりやすく、チケットを使用できる残り日数を
        // 保存しているremainingDays変数の初期化にも活用することができると考えてint型の変数にした。
    }

    // uncomment when you implement this exercise
    private void showTicketIfNeeds(Ticket ticket) {
        if (ticket.getTicketDays() == 2) { // write determination for two-day passport
            log("two-day passport");
        } else {
            log("other");
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Fix it to be able to buy four-day passport (price is 22400). <br>
     * (FourDayPassport (金額は22400) のチケットも買えるようにしましょう)
     */
    public void test_class_moreFix_wonder_four() {
        // your confirmation code here
        TicketBooth booth = new TicketBooth();
        TicketBuyResult buyFourDayPassprtResult = booth.buyFourDayPassport(30000);
        Ticket fourDayPassport = buyFourDayPassprtResult.getTicket();
        log(fourDayPassport.getTicketDays());
    }
    // TODO jflute 次回1on1こっから (2025/09/25)

    /**
     * Fix it to be able to buy night-only two-day passport (price is 7400), which can be used at only night. <br>
     * (NightOnlyTwoDayPassport (金額は7400) のチケットも買えるようにしましょう。夜しか使えないようにしましょう)
     */
    public void test_class_moreFix_wonder_night() {
        // your confirmation code here
    }

    // ===================================================================================
    //                                                                         Bonus Stage
    //                                                                         ===========
    /**
     * Refactor the code to the best readable code you can think of. <br>
     * (自分の中で思う最高に可読性の高いコードにリファクタリングしてみましょう)
     */
    public void test_class_moreFix_yourRefactoring() {
        // your confirmation code here
    }

    /**
     * Write intelligent JavaDoc comments seriously on the public classes/constructors/methods of the Ticket class. <br>
     * (Ticketクラスのpublicなクラス/コンストラクター/メソッドに、気の利いたJavaDocコメントを本気で書いてみましょう) <br>
     * <br>
     * Seriously → With the intention that the Ticket class (for example) becomes open source and is used by hundreds of people. <br>
     * (本気で → Ticketクラスが(例えば)オープンソースになって何百人の人から利用される想定のつもりで。)
     */
    public void test_class_moreFix_yourSuperJavaDoc() {
        // your confirmation code here
    }

    // ===================================================================================
    //                                                                         Devil Stage
    //                                                                         ===========
    /**
     * If your specification is to share inventory (quantity) between OneDay/TwoDay/...,
     * change the specification to separate inventory for each OneDay/TwoDay/.... <br>
     * (もし、OneDay/TwoDay/...で在庫(quantity)を共有する仕様になってたら、
     * OneDay/TwoDay/...ごとに在庫を分ける仕様に変えてみましょう)
     */
    public void test_class_moreFix_zonedQuantity() {
        // your confirmation code here
    }
}
