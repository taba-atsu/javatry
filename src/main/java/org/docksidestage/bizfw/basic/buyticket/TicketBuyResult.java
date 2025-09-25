package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author taba-atsu
 */
public class TicketBuyResult {

    private final int handedMoney;
    private final int price;
    private final int ticketDays;

    public TicketBuyResult(int handedMoney, int price, int ticketDays) {
        this.handedMoney = handedMoney;
        this.price = price;
        this.ticketDays = ticketDays;
    }

    public Ticket getTicket(){
        // TODO tabata getが複数呼ばれた時に、違うインスタンスが戻ってしまうと紛らわしいことがある by jflute (2025/09/25)
        // 呼び出し側は、getを2回呼んでも同じものが戻ってくると思ってしまいがち。(getは特別なニュアンスを持つので)
        return new Ticket(price,ticketDays);
    }

    public int getChange(){
        // TODO tabata getが複数呼ばれた時に、同じ金額が戻ってくるけど...計算処理が毎回走るのが無駄感ある by jflute (2025/09/25)
        // もちろん、この程度だったらというのはあるけど、万が一少し重い処理とかだったらあまりgetでやりたくない。
        // そういうことを考えるのも面倒ではあるので、最初からgetではロジック計算しない方が無難って考え方も。
        return handedMoney - price;
    }
}
