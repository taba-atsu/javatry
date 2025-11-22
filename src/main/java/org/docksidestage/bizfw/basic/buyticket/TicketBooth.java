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

// done tabata author追加お願いします (他のクラスも、さわったらauthor追加を) by jflute (2025/09/25)
// done tabata javadoc, 説明が先で、あっとまーくのタグが後ろ by jflute (2025/10/08)
// IntelliJで行移動、shift+option+上下
/**
 * パークの入場チケットを販売するチケットブースを表すクラスです。 <br>
 * チケットブースで購入できるチケットの上限の枚数、チケットの種類ごとの値段、チケットブースでの売り上げの金額などを保持します。 <br>
 * チケットを購入するロジックなどを持ちます。チケットが購入可能かの判定、売り上げ金額の計算などができます。 <br>
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
    private int oneDayPassportQuantity = MAX_QUANTITY;
    private int twoDayPassportQuantity = MAX_QUANTITY;
    private int fourDayPassportQuantity = MAX_QUANTITY;
    private int nightOnlyTwoDayPassportQuantity = MAX_QUANTITY;
    private int salesProceeds = 0;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * チケットブースを作成するコンストラクタ
     */
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    // you can rewrite comments for your own language by jflute
    // done tabata javadoc, @return を書いてみましょう (日本語でOK: ↑を参考に) by jflute (2025/09/25)
    // #1on1: JavaDocは、Javaで決められたフォーマットになっています。/** ... */
    // JavaDocにしておくと、メソッドの補完時などに表示されて、呼び出し側が助かる。
    // IntelliJで、メソッド補完時にcontrol+Jを押すとJavaDoc表示されるので、見たいときはぱっとcontrol+J！
    // done tabata returnは、型宣言は不要です。説明だけでOK。 by jflute (2025/10/08)
    // 恐らく、@param, @throws に引きづられて入れたと想像。
    // @paramは、引数って複数ありえるので、どの引数？って特定して説明しないといけない。
    // @throwsも、複数ありえるので、どの例外？って特定して説明しないといけない。
    // 戻り値は、絶対に一個しかないので、なので識別する必要がない。
    // (基本的に、メソッド宣言を読めばわかることは書かない)
//    /**
//     * Buy one-day passport, method for park guest.
//     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
//     * @return The result data of the 1-Day passport purchase.
//     * @throws TicketSoldOutException When ticket in booth is sold out.
//     * @throws TicketShortMoneyException When the specified money is short for purchase.
//     */
    /**
     * 1Dayパスポートを買う、パークゲスト用のメソッド。金額が足りないときや、在庫切れなどのときは購入できない。
     * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
     * @return 1Dayパスポートを購入した結果を返す
     * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
     * @throws TicketShortMoneyException 買うのに金額が足りなかったら
     */
    public TicketBuyResult buyOneDayPassport(int handedMoney) {
        // TODO tabata もし将来、お釣りの計算の仕様が変わった時、一箇所修正で済むようにしたい by jflute (2025/10/22)
        // TODO tabata もし将来、チケットの発行(new Ticket)とお釣りの計算の順序を逆にしないといけないってなったとき、一箇所修正で済むようにしたい by jflute (2025/10/22)
        // 方針としてはTicketBoothクラス自体は情報を保持する役割にして、PurchaseProcessorのような購入する処理を持つクラスを作成する。
        // この方針をとるとお釣りを計算するロジックを変えるときや購入の際のプロセスの順番が変更された場合にも対応できる。仕様の変更に対し、より強い構造になりそう。
        oneDayPassportQuantity = validateAndRegisterSale(handedMoney,ONE_DAY_PRICE,oneDayPassportQuantity);
        Ticket purchasedTicket = new Ticket(ONE_DAY_PRICE, 1,false);
        int change = handedMoney - ONE_DAY_PRICE;
        // done tabata この場合、直接newしたものをreturnしちゃってもいいかなと(少しでも行削減) by jflute (2025/10/08)
        //  e.g. return new TicketBuyResult(purchasedTicket, change);
        return new TicketBuyResult(purchasedTicket, change);
    }
    // TicketBuyResultクラスを変更したので、それにともなってTicketBoothクラスも変更
    
    // #1on1: コピペをしない仕組みを作る意識の一方で、コピペは時々やらざるを得ないので...
    // 開発者としては、コピペ修正で漏れがおきないような工夫を持っておくと良い。
    // o 変数のハイライトで指差し確認
    // o キーワード検索で指差し確認 (別のファイルに一時的にコピーして確認など)
    // o そもそも修正を別ファイルで一括置換で直す
    // #1on1: 一時的な作業するテキストファイルをバッと開けるようにしておくといいかも。
    /**
     * 2Dayパスポートを買う、パークゲスト用のメソッド。金額が足りないときや、在庫切れなどのときは購入できない。
     * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
     * @return 2Dayパスポートを購入した結果を返す
     * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
     * @throws TicketShortMoneyException 買うのに金額が足りなかったら
     */
    public TicketBuyResult buyTwoDayPassport(int handedMoney){
        twoDayPassportQuantity = validateAndRegisterSale(handedMoney,TWO_DAY_PRICE,twoDayPassportQuantity);
        // #1on1: (特にローカル)変数のスコープは短ければ短いほどよい。
        // いまここでは業務的な順序に制限がないので、プログラミングの都合(安全)を優先して良い。
        Ticket purchasedTicket = new Ticket(TWO_DAY_PRICE, 2, false);
        int change = handedMoney - TWO_DAY_PRICE;
        return new TicketBuyResult(purchasedTicket, change);
    }

    /**
     * 4Dayパスポートを買う、パークゲスト用のメソッド。金額が足りないときや、在庫切れなどのときは購入できない。
     * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
     * @return 4Dayパスポートを購入した結果を返す
     * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
     * @throws TicketShortMoneyException 買うのに金額が足りなかったら
     */
    public TicketBuyResult buyFourDayPassport(int handedMoney){
        fourDayPassportQuantity = validateAndRegisterSale(handedMoney,FOUR_DAY_PRICE,fourDayPassportQuantity);
        Ticket purchasedTicket = new Ticket(FOUR_DAY_PRICE, 4, false);
        int change = handedMoney - FOUR_DAY_PRICE;
        return new TicketBuyResult(purchasedTicket, change);
    }

    /**
     * 2Dayの夜限定パスポートを買う、パークゲスト用のメソッド。金額が足りないときや、在庫切れなどのときは購入できない。
     * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
     * @return 2Dayの夜限定パスポートを購入した結果を返す
     * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
     * @throws TicketShortMoneyException 買うのに金額が足りなかったら
     */
    public TicketBuyResult buyNightOnlyTwoDayPassport(int handedMoney){
        nightOnlyTwoDayPassportQuantity = validateAndRegisterSale(handedMoney, NIGHT_ONLY_TWO_DAY_PASSPORT,nightOnlyTwoDayPassportQuantity);
        Ticket purchasedTicket = new Ticket(NIGHT_ONLY_TWO_DAY_PASSPORT, 2, true);
        int change = handedMoney - NIGHT_ONLY_TWO_DAY_PASSPORT;
        return new TicketBuyResult(purchasedTicket, change);
    }

    // done tabata publicのbuyとprivateのbuyが先頭同じだと何かと紛らわしいので... by jflute (2025/09/25)
    // doBuy... というように、先頭文字を変えるという慣習もある。
    // e.g. doBuyPassport(), internalBuyPassport()
    // jfluteは、do... をよく使う。jfluteも、世の中のOSSのコードを読んで真似た。
    // IntelliJ で rename のショートカットを使って、名前を変えてみましょう。
    // #1on1: shift + shift からの ren で Rename... でやる方法と...
    // control + T から Refacter Thisメニューで Rename... を選択 (こっちがオススメ)
    // Renameが気軽にできると、ちょっと名前こうした方がいいな、ってのを積極的にできるようになる。
    // done tabata javadoc, @paramの区切りが全角空白になっている by jflute (2025/10/22)
    /**
     * チケットを購入できるのか判定するメソッド。
     * @param handedMoney 持っている金額
     * @param price チケットの金額
     * @param quantity チケットの在庫
     * @return 引数として与えられたチケットの在庫の数を1減らして返す
     */
    private int validateAndRegisterSale(int handedMoney,int price,int quantity){
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < price) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        return registerSale(price, quantity);
        // 共通している処理をまとめるprivate関数を追加した。全て引数に持たせるようにしたが、これが筋の良いやり方なのか自分ではあまり判断できなかった、、
        // #1on1: 引数がまだ少ないので、全然悪くないです。よくやります。
        // つまり、引数で業務(チケットの種類)を抽象化して再利用できるようにしたと言える。
        // ただ、引数が多くなってきたら、だんだんつらくなってくる。
        // なので、引数が多くなってきたら、privateメソッドではなく、
        // 引数オブジェクトを作るとか、別クラスに切り出すとか...そういったもう少し大きな対応が必要になる。
        // という感じなので、人間の限界を超えるか？超えないか？の話なので線引は若干曖昧にある。
    }

    private int registerSale(int price, int quantity){
        quantity--;
        salesProceeds += price;
        return quantity;
    }

    /**
     * チケットが売り切れの際に使用するクラス
     */
    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        // done tabata 全角空白 by jflute (2025/10/22)
        /**
         * チケットが売り切れの際に表示するエラーメッセージを作成する
         * @param msg 表示するエラーメッセージ
         */
        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    /**
     * チケット購入の際に支払われた金額が足りない際に使用されるクラス
     */
    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        /**
         * チケット購入の際に支払われた金額が足りない際に表示するエラーメッセージを作成する
         * @param msg 表示するエラーメッセージ
         */
        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    /**
     * @return 1Dayチケットの在庫の数を返す
     */
    public int getOneDayPassPortQuantity() {
        return oneDayPassportQuantity;
    }

    /**
     * @return 2Dayチケットの在庫の数を返す
     */
    public int getTwoDayPassPortQuantity() {
        return twoDayPassportQuantity;
    }

    /**
     * @return 4Dayチケットの在庫の数を返す
     */
    public int getFourDayPassPortQuantity() {
        return fourDayPassportQuantity;
    }

    /**
     * @return 夜限定2Dayチケットの在庫の数を返す
     */
    public int getNightOnlyTwoDayPassportQuantity() {
        return nightOnlyTwoDayPassportQuantity;
    }

    /**
     * @return チケットブースの合計売上の金額を返す
     */
    public int getSalesProceeds() {
        return salesProceeds;
    }
}
