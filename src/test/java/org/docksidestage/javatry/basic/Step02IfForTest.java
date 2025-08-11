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

import java.util.*;

import org.docksidestage.unit.PlainTestCase;

// done tabata javadocのauthorお願いしますm(_ _)m by jflute (2025/07/31)
/**
 * The test of if-for. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author taba-atsu
 */
public class Step02IfForTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        if Statement
    //                                                                        ============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_if_basic() { // example, so begin from the next method
        int sea = 904;
        if (sea >= 904) {
            sea = 2001;
        }
        log(sea); // your answer? => 2001
        // if文の条件に当てはまるのでseaは変更される
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_else_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else {
            sea = 7;
        }
        log(sea); // your answer? => 7
        // if文の条件式に当てはまらないので7になる
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (sea >= 904) {
            sea = 7;
        } else if (sea >= 903) {
            sea = 8;
        } else {
            sea = 9;
        }
        log(sea); // your answer? => 7
        // 二つ目の条件式に当てはまるので7になる
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_nested() {
        boolean land = false;
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (land && sea >= 904) {
            sea = 7;
        } else if (sea >= 903 || land) {
            sea = 8;
            if (!land) {
                land = true;
            } else if (sea <= 903) {
                sea++;
            }
        } else if (sea == 8) {
            sea++;
            land = false;
        } else {
            sea = 9;
        }
        if (sea >= 9 || (sea > 7 && sea < 9)) {
            sea--;
        }
        if (land) {
            sea = 10;
        }
        log(sea); // your answer? => 10
        // 一つ目のif文でseaが8,landがtrueになる.また、二つ目のif文の条件式にも当てはまるのでseaは7になるが、３つ目のif文の条件式にも当てはまるので最終的にsea
        // は10になる.
        // done jflute 1on1にてソースコードリーディングのお話予定 (2025/07/31)
        // 漠然読みで構造だけ先に把握する、フォーカス読みで知りたいところだけ読む(逆読みもする)
        // BigDecimalの方のadd()でのソースコードリーディングの例も
    }

    // ===================================================================================
    //                                                                       for Statement
    //                                                                       =============
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_inti_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (int i = 0; i < stageList.size(); i++) {
            String stage = stageList.get(i);
            if (i == 1) {
                sea = stage;
            }
        }
        log(sea); // your answer? => dockside
        // 出力されるのはiが1の時なので、配列の2番目の要素である "dockside" になる.
        // #1on1: indexなら0始まり、numberなら1始まり、が多い。
        // 少なくとも自分の中で統一的な書き方をしましょう。
        // 読むときは多少先入観も使うけど、一応何originなのかを確認する。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            sea = stage;
        }
        log(sea); // your answer? => magiclamp
        // for文で配列の要素が前から順番にseaに代入されていくので、最後の要素である "magiclamp" が出力される.
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_continueBreak() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) {
                continue;
            }
            sea = stage;
            if (stage.contains("ga")) {
                break;
            }
        }
        log(sea); // your answer? => hangar
        // continueは次のループに進むがbreakはループを抜ける。seaにhangarが代入されたとき、gaが含まれているのでbreakによりループを抜ける。
        // そのため、出力されるのは "hangar" になる.
        // done tabata [いいね] こちらがJavaの文法的には「拡張for文」と呼ばれるもので、普通のfor文読んじゃったりします by jflute (2025/07/31)
        // 次のforEach()と違って、Javaの文法として組み込まれているループ機能となります。
        // done jflute 1on1にて、Javaのfor文いっぱい話 (2025/07/31)
        // #1on1: 文法用語と現場用語の違いの話、クラスメソッド、インスタンスメソッドなど (2025/08/01)
        // 拡張for文も同じく。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_listforeach_basic() {
        List<String> stageList = prepareStageList();
        StringBuilder sb = new StringBuilder();
        stageList.forEach(stage -> {
            if (sb.length() > 0) {
                return;
            }
            if (stage.contains("i")) {
                sb.append(stage);
            }
        });
        String sea = sb.toString();
        log(sea); // your answer? => dockside
        // stageListの中身をforEachメソッドを使用して一つずつ処理していっており、処理の中身はsbの長さが0より大きいとreturnし、
        // stage(stageListから取り出されている一つ一つの要素)がiを含んでいるとsbにstageを追加する。
        // prepareStageListを見てみると、"dockside" がiを含んでいるのでsbに追加される。そして、sbの長さが0ではなくなるので、それ以降のループでは全てreturn
        //　される。したがって、seaの中身は "dockside" になる。
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Make list containing "a" from list of prepareStageList() and show it as log by loop. (without Stream API) <br>
     * (prepareStageList()のリストから "a" が含まれているものだけのリストを作成して、それをループで回してログに表示しましょう。(Stream APIなしで))
     */
    public void test_iffor_making() {
        // write if-for here
        List<String> stageList = prepareStageList();
        List<String> resultList = new ArrayList<>();
        for (String stage : stageList) {
            if (stage.contains("a")) {
                resultList.add(stage);
            }
        }
        resultList.forEach(stage -> log(stage));
        //　通常のfor文はループの制御にインデックスを使用するが、拡張for文はインデックスを使用せずコレクションを直接取り出して処理する？
        // 今回の場合、for文とforEachメソッドをどのように使い分けるのが適切なのかわかりませんでした、、
        // break,continueやインデックスを使用する場合はfor文を使う必要がある？
        // done tabata [ふぉろー]拡張for文は内部的には resultList.iterator() が利用されます。 by jflute (2025/07/31)
        // #1on1: iterator()のソースコードちょっと読んでみた (少しだけLinkedListの紹介)
        // done tabata [へんじ] 1on1にて使い分けふぉろー致しますね！ by jflute (2025/07/31)
        // #1on1: 何でもできることが便利とは限らない、何でもできる必要がない場面なら、最低限できる機能の方が安心 (2025/08/01)
        // done jflute 1on1にて、iterator()の挙動と現実的な使い分けについて (2025/07/31)
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Change foreach statement to List's forEach() (keep result after fix) <br>
     * (foreach文をforEach()メソッドへの置き換えてみましょう (修正前と修正後で実行結果が同じになるように))
     */
    public void test_iffor_refactor_foreach_to_forEach() {
        List<String> stageList = prepareStageList();
        String[] res = new String[1];
        res[0] = "";
        stageList.forEach(stage->{
            if(res[0].contains("ga")){
                return;
            }
            if(stage.startsWith("br")) {
                return;
            }
            res[0] = stage;
        });
        String sea = (res[0] == "") ? null : res[0];
        log(sea);

        // forEach()の特徴をGeminiと整理
        // 戻り値は常にvoid
        // breakとcontinueを使用できない
        // ラムダ式の中から、ラムダ式の定義されたスコープ外のローカル変数にアクセスする場合、その変数はfinalもしくは事実上finalでなければならない

        // continueはreturnすることで再現できる。しかし、breakは再現できないので最終的なアプトプットを行うres[0]にgaが含まれたら残りのループをスキップ。
        // この実装だと無駄なループが回ってしまう、、
        // forEachの中で何が変更できるのかまだあまり理解できていない。参照型なら変更できる、、？？？
        // res[0]の初期値をnullにしようと考えていたが、.containsメソッドでnullだとエラーが出てしまうので初期値を空文字で代用。
        // seaに代入する際に三項演算子を使って空文字の場合nullへ変換を行う。
        // done tabata [いいね] ロジック的にはかなりスマートに実現できてますね！ by jflute (2025/07/31)
        // done tabata [ふぉろー] この場合、break代わりの無駄ループは確かに走ってしまいますね。それを避けるとなったらもう例外throwとか使うしか無いかなと。 by jflute (2025/07/31)
        // ただ、例外をnewしてthrowすること自体もコストが掛かるので、空ループと比較するとそんなに変わらないかもしれません。(回数に依る)
        // done tabata [ふぉろー] mutableな型をどうしても連れてこないといけないわけですが、配列を使われたというのはシンプルで良いと思います。 by jflute (2025/07/31)
        // 他にもStringBuilderとかも代用できる候補ではあります。
        // (まあ、こういうforEach()を書くことが良いかどうかはさておいて: 1on1でお話します)
        // done tabata [ふぉろー] 配列の初期値のnull, 一応こうすれば空文字初期化じゃなくnull初期化でもいけるかも!? by jflute (2025/07/31)
        //  e.g. if(res[0] != null && res[0].contains("ga")){
        
        // TODO jflute 1on1にて、ちょこっと振り返り予定 (2025/08/11)
    }

    /**
     * Make your original exercise as question style about if-for statement. <br>
     * (if文for文についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     * 拡張for文を使用せずに書き換えてください
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_iffor_yourExercise() {
        Set<String> fruits = new HashSet<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");

        for (String fruit : fruits) {
            log(fruit);
        }
        // done tabata Setというのがいいですね(^^、順不同で表示されます by jflute (2025/07/31)
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private List<String> prepareStageList() {
        List<String> stageList = new ArrayList<>();
        stageList.add("broadway");
        stageList.add("dockside");
        stageList.add("hangar");
        stageList.add("magiclamp");
        return stageList;
    }
}
