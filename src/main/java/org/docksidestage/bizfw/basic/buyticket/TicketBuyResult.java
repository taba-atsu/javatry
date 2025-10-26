package org.docksidestage.bizfw.basic.buyticket;

// done tabata 購入したチケットの種類、購入時のお釣りの金額と具体例を列挙することでわかりやすい一方で... by jflute (2025/10/08)
// 列挙を断定してしまうと、将来項目が追加された時に、ちょっと誤解を生みやすい文章になってしまう可能性あり。
// 列挙断定の情報はjavadocになくてよくて、具体例を出してイメージを沸かせることだけで良いかなと。
// e.g. 購入時のお釣りの金額 "など" を保持します。他には、"とか"、"e.g."、"例えば" ....
/**
 * パークの入場チケットを購入した際の結果を表すクラスです。 <br>
 * 購入したチケットの種類と購入時のお釣りの金額などを保持します。
 * @author taba-atsu
 */
public class TicketBuyResult {

    private final Ticket purchasedTicket;
    private final int change;

    // #1on1: Macで日本語入力モード中に、shift+space で半角スペースが打てる (2025/10/22)
    /**
     * チケットの購入結果を作成するコンストラクタ
     * @param purchasedTicket 購入されたチケット
     * @param change 購入の際のお釣り
     */
    public TicketBuyResult(Ticket purchasedTicket, int change) {
        this.purchasedTicket = purchasedTicket;
        this.change = change;
    }

    // done tabata 外部はスッキリ内部は明示的ってのもアリだし、統一概念でgetPurchasedTicket()もアリ by jflute (2025/10/08)
    // まあ、意図してなかったということなので、意図しましょう。
    // 統一した方が他の人が混乱する可能性が低くなるのではないかと考えたので、getメソッドの名前を変更しました！ by taba-atsu
    /**
     * @return 購入されたチケットを返す
     */
    public Ticket getPurchasedTicket(){
        // done tabata getが複数呼ばれた時に、違うインスタンスが戻ってしまうと紛らわしいことがある by jflute (2025/09/25)
        // 呼び出し側は、getを2回呼んでも同じものが戻ってくると思ってしまいがち。(getは特別なニュアンスを持つので)

        // チケットオブジェクトをTicketBuyResultクラスでnewするのではなく、TicketBoothクラスでnewする仕様に変更する。
        // その仕様の方がクラスごとの責務が明らかになるし、getするごとに別のオブジェクトが作成されることを防ぐことができる。
        // このメソッドは、TicketBuyResultオブジェクトを作成するときに受け取ったTicketオブジェクトを返す仕様にする。
        return purchasedTicket;
    }

    /**
     * @return 購入した際のお釣りの金額を返す
     */
    public int getChange(){
        // done tabata getが複数呼ばれた時に、同じ金額が戻ってくるけど...計算処理が毎回走るのが無駄感ある by jflute (2025/09/25)
        // もちろん、この程度だったらというのはあるけど、万が一少し重い処理とかだったらあまりgetでやりたくない。
        // そういうことを考えるのも面倒ではあるので、最初からgetではロジック計算しない方が無難って考え方も。

        // getで計算しないという方針で修正する。
        // そもそもResultクラスでは計算するのではなく、TicketBoothで計算をしたものを保持するだけのクラスに変更する
        return change;
    }
}
