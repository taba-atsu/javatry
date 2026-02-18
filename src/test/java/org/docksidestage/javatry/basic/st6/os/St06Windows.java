package org.docksidestage.javatry.basic.st6.os;

public class St06Windows extends St6OperationSystem {

    public St06Windows(String loginId){
        super( loginId);
    }

    @Override
    public String getFileSeparator(){
        return "\\";
    }

    @Override
    public String getUserDirectory(){
        return "/Users/" + loginId;
    }
}
