package org.docksidestage.javatry.basic.st6.os;

// TODO done tabata javadocお願いします(^^ by jflute (2026/01/07)
/**
 * @author tabaatsu
 */
public class St06Mac extends St6OperationSystem {

    // TODO tabata どのみち、Javaの慣習としてstatic finalな定数は、大文字スネークケース by jflute (2026/01/07)
    private static final String osType = "Mac";

    public St06Mac(String loginId){
        super(osType, loginId);
    }

    @Override
    public String getFileSeparator(){
        return "/";
    }

    @Override
    public String getUserDirectory(){
        return "/Users/" + loginId;
    }
}
