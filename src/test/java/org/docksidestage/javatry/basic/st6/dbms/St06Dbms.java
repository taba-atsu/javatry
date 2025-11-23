package org.docksidestage.javatry.basic.st6.dbms;

/**
 * @author tabaatsu
 */

abstract public class St06Dbms {

     public int calculateOffset(int pageSize, int pageNumber){
        return pageSize * (pageNumber - 1);
    }

    abstract public String buildPagingQuery(int pageSize, int pageNumber);
}
