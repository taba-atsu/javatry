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

// TODO done tabata author追加お願いします (他のクラスも、さわったらauthor追加を) by jflute (2025/09/25)
/**
 * @author jflute
 * @author taba-atsu
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400;// when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200;
    private static final int FOUR_DAY_PRICE = 22400;
    private static final int NIGHT_ONLY_TWO_DAY_PASSPORT = 7400;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity = MAX_QUANTITY;
    private int salesProceeds = 0;

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
    // TODO done tabata javadoc, @return を書いてみましょう (日本語でOK: ↑を参考に) by jflute (2025/09/25)
    // #1on1: JavaDocは、Javaで決められたフォーマットになっています。/** ... */
    // JavaDocにしておくと、メソッドの補完時などに表示されて、呼び出し側が助かる。
    // IntelliJで、メソッド補完時にcontrol+Jを押すとJavaDoc表示されるので、見たいときはぱっとcontrol+J！
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @return TicketBuyResult The result data of the 1-Day passport purchase.
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public TicketBuyResult buyOneDayPassport(int handedMoney) {
        validateAndRegisterSale(handedMoney,ONE_DAY_PRICE);
        Ticket purchasedTicket = new Ticket(ONE_DAY_PRICE, 1,false);
        int change = handedMoney - ONE_DAY_PRICE;
        TicketBuyResult result = new TicketBuyResult(purchasedTicket, change);
        return result;
    }
    // TicketBuyResultクラスを変更したので、それにともなってTicketBoothクラスも変更
    
    // #1on1: コピペをしない仕組みを作る意識の一方で、コピペは時々やらざるを得ないので...
    // 開発者としては、コピペ修正で漏れがおきないような工夫を持っておくと良い。
    // o 変数のハイライトで指差し確認
    // o キーワード検索で指差し確認 (別のファイルに一時的にコピーして確認など)
    // o そもそも修正を別ファイルで一括置換で直す
    // #1on1: 一時的な作業するテキストファイルをバッと開けるようにしておくといいかも。
    public TicketBuyResult buyTwoDayPassport(int handedMoney){
        validateAndRegisterSale(handedMoney,TWO_DAY_PRICE);
        // #1on1: (特にローカル)変数のスコープは短ければ短いほどよい。
        // いまここでは業務的な順序に制限がないので、プログラミングの都合(安全)を優先して良い。
        Ticket purchasedTicket = new Ticket(TWO_DAY_PRICE, 2, false);
        int change = handedMoney - TWO_DAY_PRICE;
        TicketBuyResult result = new TicketBuyResult(purchasedTicket, change);
        return result;
    }

    public TicketBuyResult buyFourDayPassport(int handedMoney){
        validateAndRegisterSale(handedMoney,FOUR_DAY_PRICE);
        Ticket purchasedTicket = new Ticket(FOUR_DAY_PRICE, 4, false);
        int change = handedMoney - FOUR_DAY_PRICE;
        TicketBuyResult result = new TicketBuyResult(purchasedTicket, change);
        return result;
    }

    public TicketBuyResult buyNightOnlyTwoDayPassport(int handedMoney){
        validateAndRegisterSale(handedMoney, NIGHT_ONLY_TWO_DAY_PASSPORT);
        Ticket purchasedTicket = new Ticket(NIGHT_ONLY_TWO_DAY_PASSPORT, 2, true);
        int change = handedMoney - NIGHT_ONLY_TWO_DAY_PASSPORT;
        TicketBuyResult result = new TicketBuyResult(purchasedTicket, change);
        return result;
    }

    // TODO done tabata publicのbuyとprivateのbuyが先頭同じだと何かと紛らわしいので... by jflute (2025/09/25)
    // doBuy... というように、先頭文字を変えるという慣習もある。
    // e.g. doBuyPassport(), internalBuyPassport()
    // jfluteは、do... をよく使う。jfluteも、世の中のOSSのコードを読んで真似た。
    // IntelliJ で rename のショートカットを使って、名前を変えてみましょう。
    // #1on1: shift + shift からの ren で Rename... でやる方法と...
    // control + T から Refacter Thisメニューで Rename... を選択 (こっちがオススメ)
    // Renameが気軽にできると、ちょっと名前こうした方がいいな、ってのを積極的にできるようになる。
    private void validateAndRegisterSale(int handedMoney,int price){
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < price) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        registerSale(price);
        // 共通している処理をまとめるprivate関数を追加した。全て引数に持たせるようにしたが、これが筋の良いやり方なのか自分ではあまり判断できなかった、、
        // #1on1: 引数がまだ少ないので、全然悪くないです。よくやります。
        // つまり、引数で業務(チケットの種類)を抽象化して再利用できるようにしたと言える。
        // ただ、引数が多くなってきたら、だんだんつらくなってくる。
        // なので、引数が多くなってきたら、privateメソッドではなく、
        // 引数オブジェクトを作るとか、別クラスに切り出すとか...そういったもう少し大きな対応が必要になる。
        // という感じなので、人間の限界を超えるか？超えないか？の話なので線引は若干曖昧にある。
    }

    private void registerSale(int price){
        quantity--;
        salesProceeds += price;
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

    public int getSalesProceeds() {
        return salesProceeds;
    }
}
