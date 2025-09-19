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
        return new Ticket(price,ticketDays);
    }

    public int getChange(){
        return handedMoney - price;
    }
}
