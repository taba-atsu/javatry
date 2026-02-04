package org.docksidestage.javatry.basic.st6.dbms;

// done tabata フォーマット、javadoc直下の空行削除、calculateOffset()のインデント by jflute (2026/01/07)
/**
 * @author tabaatsu
 */
abstract public class St06Dbms {

    // done tabata calculateOffset()はpublicの必要ない。protectedで十分 by jflute (2026/01/07)
    protected int calculateOffset(int pageSize, int pageNumber){
        return pageSize * (pageNumber - 1);
    }

    // done tabata [いいね] ポリモーフィズムは実現できている by jflute (2026/01/07)
    public String buildPagingQuery(int pageSize, int pageNumber){
        int offset = calculateOffset(pageSize, pageNumber);
        return createPagingString(offset, pageSize);
    }

    abstract protected String createPagingString(int offset, int pageSize);
}
