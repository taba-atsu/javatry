/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.basic.st6.dbms;

/**
 * @author jflute
 * @author tabaatsu
 */
public class St6MySql extends St06Dbms {

    // TODO tabata superでprotectedのものが、subでpublicになっている by jflute (2026/02/04)
    // 意図していないなら、protectedのままで隠蔽しましょう。
    // (subでpublicにするケースは少ないけど、ピンポイントでこの具象クラスだけは部品を呼び出しってときに)
    @Override
    public String createPagingString(int offset, int pageSize) {
        return "limit " + offset + ", " + pageSize;
    }
    
    // done tabata まだコピペが残っている。重複している箇所... by jflute (2026/01/07)
    // 1. calculate (再利用)
    // 2. 文字列生成 (DBMS独自)
    // この流れ自体がコピペになっている。
    // もし、1と2の間に新しい処理を入れようと仕様変更あった場合、
    // 1. calculate (再利用)
    // 1.5. 何かの追加処理
    // 2. 文字列生成 (DBMS独自)
    // これを、MySQLとPostgreSQL両方に追加しないといけない。
    // つまり、処理の流れが再利用されていない。
    // これを、一箇所追加で済むようにしたいところ。

    // こっちPostgreSQL:
    //@Override
    //public String buildPagingQuery(int pageSize, int pageNumber) {
    //    int offset = calculateOffset(pageSize, pageNumber);
    //    return "offset " + offset + " limit " + pageSize;
    //}
}
