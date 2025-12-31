package org.docksidestage.javatry.basic.st6.os;

public class St06Windows extends St6OperationSystem {

    private static final String osType = "Windows";

    public St06Windows(String loginId){
        super(osType, loginId);
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
