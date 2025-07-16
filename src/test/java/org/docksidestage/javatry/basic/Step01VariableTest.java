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

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of variable. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author taba-atsu
 */
public class Step01VariableTest extends PlainTestCase {

    // ===================================================================================
    //                                                                      Local Variable
    //                                                                      ==============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_variable_basic() { // example, so begin from the next method
        String sea = "mystic";
        log(sea); // your answer? => mystic
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_initial() {
        String sea = "mystic";
        Integer land = 8;
        String piari = null;
        String dstore = "mai";
        sea = sea + land + piari + ":" + dstore;
        log(sea); // your answer? => mystic8:mai
        // String型とInteger型の変数を連結する場合、IntegerはStringに変換されて連結される！nullは文字列として連結される！
        // done tabata [いいね] 思考コメントありがとうございます！ by jflute (2025/07/15)
        // そう、String以外のクラスを、文字列に対して+連結したときは、それぞれのtoString()メソッドが暗黙的に呼び出されて、
        // すべて文字列に変換されます。また、インスタンスが入ってない変数の場合は "null" という文字列になります。
        // 昔のインターネット画面だと、よく「こんにちは nullさん」とか表示されるのあったりしました(^^。
        // 最近でも、メールで null って文言が入っちゃってるのを見たことがあります。
        // 一方で、ログとかで値を確認するときは、何も出てこないよりは null って出てくるほうがわかりやすいと思うこともあります。
        // 些細な違いではありますが、この辺は言語によって変わります。
        // #1on1 メールのプログラミングって、業務の優先度は低く思われがちだけど、丁寧に実装しないといけないもの。
        // #1on1 IntelliJで shift + shift の todo で、todo一覧が見られる。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_basic() {
        String sea = "mystic";
        String land = "oneman";
        sea = land;
        land = land + "'s dreams";
        log(sea); // your answer? => oneman's dream
        // seaにlandを代入した際、landの参照がseaに代入される。その下でlandの値を変更しているが、変更の際にlandには別の参照が代入されている。したがって、seaの値は変わらない。
        // という認識であっているのか？
        // done tabata [ふぉろー] Goodです。「参照がseaに代入される」という言葉がとても適切ですね by jflute (2025/07/15)
        // 変数はあくまでインスタンスの(メモリ上のどこかに)置かれた場所のアドレスを保持するだけで、
        // sea = land; もそのアドレスをコピーして互いに共有しただけとも言えます。
        // land = land + "'s dreams"; は、land自身が、そのアドレスを破棄して別のアドレスを受け取ったという感じで。
        // seaが参照するアドレスは以降変わらないですし、その参照されているインスタンス自身にも何も変化は起きていないと。
        // #1on1: 外部研修のお話
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_int() {
        int sea = 94;
        int land = 415;
        sea = land;
        land++;
        log(sea); // your answer? => 415
        // int型の変数はスタック領域にメモリが確保されており、そこに値が直接格納される。seaにlandを代入した際、landの値のコピーがそこに入る。
        // したがって、landの値を変更してもseaの値は変わらない。
        // done tabata [いいね] プリミティブ型は、参照ではなく値そのものが変数に入っているイメージですね。Goodです by jflute (2025/07/15)
        // 一方で、land++; は、実際には land = land + 1; とやっているだけなので、
        // 中の値自体が変化(インクリメント)したというより、結局新しい値に差し替わっただけという感じですね。
        // #1on1: 制御系プログラミングのお話
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_BigDecimal() {
        BigDecimal sea = new BigDecimal(94);
        BigDecimal land = new BigDecimal(415);
        sea = land;
        sea = land.add(new BigDecimal(1));
        sea.add(new BigDecimal(1));
        log(sea); // your answer? => 417
        // BigDecminalは不変オブジェクトなので、.add()メソッドを呼び出した場合新しいインスタンスが生成される。
        // そのためsea.add(new BigDecimal(1));を実行しても、どの変数にも代入していないので結果は416になる。
        // #1on1: IntelliJ上で、BigDecimal(型宣言)にカーソルを当てるとクラスのJavaDocが表示されて、immutableがわかる。
        // add()メソッドも同じくJavaDoc見るといいです。
        // IntelliJで、メソッド補完時にcontrol+Jを押すとJavaDoc表示されるので、見たいときはぱっとcontrol+J！
        // add()のソースコードを読んで構造を知ってimmutableを推測する。
        // #1on1: immutable/mutableのバランス話
    }
    // TODO jflute 1on1ふぉろー、ここまで、次回はここから (2025/07/16)

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private String instanceBroadway;
    private int instanceDockside;
    private Integer instanceHangar;
    private String instanceMagiclamp;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_String() {
        String sea = instanceBroadway;
        log(sea); // your answer? => ""
        // 明示的に初期化しない場合、デフォルト値が自動的に設定されるの。オブジェクト型のデフォルト値はnullになる。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_int() {
        int sea = instanceDockside;
        log(sea); // your answer? => 0
        // int型の変数は、上記同様デフォルト値が設定され0になる。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_Integer() {
        Integer sea = instanceHangar;
        log(sea); // your answer? => null
        // Integer型はオブジェクト型なので、デフォルト値はnullになる。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_via_method() {
        instanceBroadway = "bbb";
        instanceMagiclamp = "magician";
        helpInstanceVariableViaMethod(instanceMagiclamp);
        String sea = instanceBroadway + "|" + instanceDockside + "|" + instanceHangar + "|" + instanceMagiclamp;
        log(sea); // your answer? => 
    }

    private void helpInstanceVariableViaMethod(String instanceMagiclamp) {
        instanceBroadway = "bigband";
        ++instanceDockside;
        instanceMagiclamp = "burn";
    }

    // ===================================================================================
    //                                                                     Method Argument
    //                                                                     ===============
    // -----------------------------------------------------
    //                                 Immutable Method-call
    //                                 ---------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_immutable_methodcall() {
        String sea = "harbor";
        int land = 415;
        helpMethodArgumentImmutableMethodcall(sea, land);
        log(sea); // your answer? => 
    }

    private void helpMethodArgumentImmutableMethodcall(String sea, int land) {
        ++land;
        String landStr = String.valueOf(land); // is "416"
        sea.concat(landStr);
    }

    // -----------------------------------------------------
    //                                   Mutable Method-call
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_mutable_methodcall() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentMethodcall(sea, land);
        log(sea); // your answer? => 
    }

    private void helpMethodArgumentMethodcall(StringBuilder sea, int land) {
        ++land;
        sea.append(land);
    }

    // -----------------------------------------------------
    //                                   Variable Assignment
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_variable_assignment() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentVariable(sea, land);
        log(sea); // your answer? => 
    }

    private void helpMethodArgumentVariable(StringBuilder sea, int land) {
        ++land;
        String seaStr = sea.toString(); // is "harbor"
        sea = new StringBuilder(seaStr).append(land);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Define variables as followings:
     * <pre>
     * o local variable named sea typed String, initial value is "mystic"
     * o local variable named land typed Integer, initial value is null
     * o instance variable named piari typed int, without initial value
     * o show all variables by log() as comma-separated
     * </pre>
     * (変数を以下のように定義しましょう):
     * <pre>
     * o ローカル変数、名前はsea, 型はString, 初期値は "mystic"
     * o ローカル変数、名前はland, 型はInteger, 初期値は null
     * o インスタンス変数、名前はpiari, 型はint, 初期値なし
     * o すべての変数をlog()でカンマ区切りの文字列で表示
     * </pre>
     */
    public void test_variable_writing() {
        // define variables here
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Make your original exercise as question style about variable. <br>
     * (変数についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     * 
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_variable_yourExercise() {
        // write your code here
    }
}
