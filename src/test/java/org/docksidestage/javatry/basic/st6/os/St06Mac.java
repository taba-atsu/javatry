package org.docksidestage.javatry.basic.st6.os;

public class St06Mac extends St6OperationSystem {

    private static final String osType = "Mac";

    public St06Mac(String loginId){
        super(osType, loginId);
    }

    @Override
    protected String getFileSeparator(){
        return "/";
    }

    @Override
    protected String getUserDirectory(){
        return "/Users/" + loginId;
    }
}
