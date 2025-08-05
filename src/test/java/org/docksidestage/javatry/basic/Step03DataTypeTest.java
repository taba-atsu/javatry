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
package org.docksidestage.javatry.basic;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of data type. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author taba-atsu
 */
public class Step03DataTypeTest extends PlainTestCase {

    // ===================================================================================
    //                                                                          Basic Type
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_datatype_basicType() {
        String sea = "mystic";
        Integer land = 416;
        LocalDate piari = LocalDate.of(2001, 9, 4);
        LocalDateTime bonvo = LocalDateTime.of(2001, 9, 4, 12, 34, 56);
        Boolean dstore = true;
        BigDecimal amba = new BigDecimal("9.4");

        piari = piari.plusDays(1);
        land = piari.getYear();
        bonvo = bonvo.plusMonths(1);
        land = bonvo.getMonthValue();
        land--;
        if (dstore) {
            BigDecimal addedDecimal = amba.add(new BigDecimal(land));
            sea = String.valueOf(addedDecimal);
        }
        log(sea); // your answer? => 18.4
        // Integerクラスは参照型なのでlandのには参照が入る。最終的にbonvo.getMonthValue();が代入されてからデクリメントされているので
        // landのに代入されている参照先の値は9になる。if文のなかに入るので、seaにはaddedDecimalの値が代入される。
        // BigDecimalのコンストラクタは数値型と文字列型の両方を受け取れる。したがって、addedDecimalの数字が最終的な出力となるので、答えは18.4。
    }

    // ===================================================================================
    //                                                                           Primitive
    //                                                                           =========
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_primitive() {
        byte sea = 127; // max
        short land = 32767; // max
        int piari = 1;
        long bonvo = 9223372036854775807L; // max
        float dstore = 1.1f;
        double amba = 2.3d;
        char miraco = 'a';
        boolean dohotel = miraco == 'a';
        if (dohotel && dstore >= piari) {
            bonvo = sea;
            land = (short) bonvo;
            bonvo = piari;
            sea = (byte) land;
            if (amba == 2.3D) {
                sea = (byte) amba;
            }
        }
        if ((int) dstore > piari) {
            sea = 0;
        }
        log(sea); // your answer? =>0
        // int型とfloat型を比較する際、暗黙の型変換が行われてint型は自動でfloat型になる。
        // したがって、一つ目のif文の条件式は両方trueになり中に入る。bonvoにseaを代入することで127になる。landも127になる。
        // seaにlandを代入するのでseaは引き続き127のまま。一つ目のif文の中のif文のtrueになるので、sea = (byte) amba;が実行される。
        // 小数から整数へキャストされるときは、小数点以下は切り捨てられるのでseaの値は2へ変化する。
        // 二つ目のif文の条件式でもfloat型がキャストされており小数点が切り捨てられ、1と1を比較することなるので条件式はfalseになり2つ目のif文の中には入らない。
        // したがって、正しい答えは2になる。
        // 自分が回答した時は、二つ目の条件式の>を>=に勘違いしてしまっており、0と回答してしまいました、、
    }

    // ===================================================================================
    //                                                                              Object
    //                                                                              ======
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_object() {
        St3ImmutableStage stage = new St3ImmutableStage("hangar");
        String sea = stage.getStageName();
        log(sea); // your answer? => hangar
        // getStageNameメソッドはstageName変数を返すので、答えばhangarになる。
    }

    private static class St3ImmutableStage {

        private final String stageName;

        public St3ImmutableStage(String stageName) {
            this.stageName = stageName;
        }

        public String getStageName() {
            return stageName;
        }
    }
}
