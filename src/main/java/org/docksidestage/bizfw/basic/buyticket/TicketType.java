package org.docksidestage.bizfw.basic.buyticket;

public enum TicketType {
    ONE_DAY_PASS(7400,1,false),
    TWO_DAY_PASS(13200,2,false),
    FOUR_DAY_PASS(22400,4,false),
    NIGHT_ONLY_TWO_DAY_PASS(7400,2,true);

    private final int price;
    private final int days;
    private final boolean nightOnly;

    TicketType(int price, int days, boolean nightOnly){
        this.price = price;
        this.days = days;
        this.nightOnly = nightOnly;
    }

    public int getPrice(){
        return price;
    }

    public int getDays(){
        return days;
    }

    public boolean isNightOnly(){
        return nightOnly;
    }
}
