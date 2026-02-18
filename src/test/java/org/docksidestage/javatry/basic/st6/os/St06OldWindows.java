package org.docksidestage.javatry.basic.st6.os;

public class St06OldWindows extends St6OperationSystem {

    public St06OldWindows(String loginId){
        super( loginId);
    }

    @Override
    public String getFileSeparator(){
        return "\\";
    }

    @Override
    public String getUserDirectory(){
        return "/Documents and Settings/" + loginId;
    }
}
