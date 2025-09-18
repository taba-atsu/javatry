package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author taba-atsu
 */
public class TicketBuyResult {

    private final int handedMoney;
    private final int price;

    public TicketBuyResult(int handedMoney,int price) {
        this.handedMoney = handedMoney;
        this.price = price;
    }

    public Ticket getTicket(){
        return new Ticket(price);
    }

    public int getChange(){
        return handedMoney - price;
    }
}
