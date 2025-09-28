package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author taba-atsu
 */
public class TicketBuyResult {

    private final int handedMoney;
    private final int price;
    private final int ticketDays;
    private final Ticket purchasedTicket;
    private final int change;

    public TicketBuyResult(Ticket purchasedTicket, int change) {
        this.purchasedTicket = purchasedTicket;
        this.change = change;
    }

    public Ticket getTicket(){
        // TODO done tabata getが複数呼ばれた時に、違うインスタンスが戻ってしまうと紛らわしいことがある by jflute (2025/09/25)
        // 呼び出し側は、getを2回呼んでも同じものが戻ってくると思ってしまいがち。(getは特別なニュアンスを持つので)

        // チケットオブジェクトをTicketBuyResultクラスでnewするのではなく、TicketBoothクラスでnewする仕様に変更する。
        // その仕様の方がクラスごとの責務が明らかになるし、getするごとに別のオブジェクトが作成されることを防ぐことができる。
        // このメソッドは、TicketBuyResultオブジェクトを作成するときに受け取ったTicketオブジェクトを返す仕様にする。
        return purchasedTicket;
    }

    public int getChange(){
        // TODO done tabata getが複数呼ばれた時に、同じ金額が戻ってくるけど...計算処理が毎回走るのが無駄感ある by jflute (2025/09/25)
        // もちろん、この程度だったらというのはあるけど、万が一少し重い処理とかだったらあまりgetでやりたくない。
        // そういうことを考えるのも面倒ではあるので、最初からgetではロジック計算しない方が無難って考え方も。

        // getで計算しないという方針で修正する。
        // そもそもResultクラスでは計算するのではなく、TicketBoothで計算をしたものを保持するだけのクラスに変更する
        return change;
    }
}
