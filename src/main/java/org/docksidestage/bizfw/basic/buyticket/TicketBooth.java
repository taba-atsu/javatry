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
package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author jflute
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400;// when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity = MAX_QUANTITY;
    private Integer salesProceeds; // null allowed: until first purchase

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    // you can rewrite comments for your own language by jflute
    // e.g. Japanese
    // /**
    // * 1Dayパスポートを買う、パークゲスト用のメソッド。
    // * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
    // * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
    // * @throws TicketShortMoneyException 買うのに金額が足りなかったら
    // */
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public TicketBuyResult buyOneDayPassport(Integer handedMoney) {
        buyPassport(handedMoney,ONE_DAY_PRICE);
        TicketBuyResult result = new TicketBuyResult(handedMoney,ONE_DAY_PRICE);
        return result;
    }
    
    // #1on1: コピペをしない仕組みを作る意識の一方で、コピペは時々やらざるを得ないので...
    // 開発者としては、コピペ修正で漏れがおきないような工夫を持っておくと良い。
    // o 変数のハイライトで指差し確認
    // o キーワード検索で指差し確認 (別のファイルに一時的にコピーして確認など)
    // o そもそも修正を別ファイルで一括置換で直す
    // #1on1: 一時的な作業するテキストファイルをバッと開けるようにしておくといいかも。
    public TicketBuyResult buyTwoDayPassport(int handedMoney){
        buyPassport(handedMoney,TWO_DAY_PRICE);
        // #1on1: (特にローカル)変数のスコープは短ければ短いほどよい。
        // いまここでは業務的な順序に制限がないので、プログラミングの都合(安全)を優先して良い。
        TicketBuyResult result = new TicketBuyResult(handedMoney,TWO_DAY_PRICE);
        return result;
    }

    private void buyPassport(Integer handedMoney,int price){
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < price) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        quantity--;
        if (salesProceeds != null) { // second or more purchase
            salesProceeds = salesProceeds + price;
        } else { // first purchase
            salesProceeds = price;
        }
        // 共通している処理をまとめるprivate関数を追加した。全て引数に持たせるようにしたが、これが筋の良いやり方なのか自分ではあまり判断できなかった、、
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity() {
        return quantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
