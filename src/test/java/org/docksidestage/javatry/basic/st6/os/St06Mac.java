package org.docksidestage.javatry.basic.st6.os;

// done tabata javadocお願いします(^^ by jflute (2026/01/07)
/**
 * @author tabaatsu
 */
public class St06Mac extends St6OperationSystem {

    // done tabata どのみち、Javaの慣習としてstatic finalな定数は、大文字スネークケース by jflute (2026/01/07)
    // private static final String OS_TYPE = "Mac";
    // 残すとしたらこんな感じ。
    // #1on1: オブジェクトのstatic finalの場合は、大文字スネークケースとは限らないことも(人寄る) (2026/02/18)

    public St06Mac(String loginId){
        super( loginId);
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
