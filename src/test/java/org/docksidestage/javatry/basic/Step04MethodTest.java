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

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of method. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author taba-atsu
 */
public class Step04MethodTest extends PlainTestCase {

    // ===================================================================================
    //                                                                         Method Call
    //                                                                         ===========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_method_call_basic() {
        String sea = supplySomething();
        log(sea); // your answer? => over
        // supplySomething()メソッドではメソッド内で定義されているsea変数がreturnされているので、中身はoverになる。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_call_many() {
        String sea = functionSomething("mystic");
        consumeSomething(supplySomething());
        runnableSomething();
        log(sea); // your answer? => mysmys
        // functionSomething()メソッドではticをmysに置き換えた文字列が返されており、他のメソッドでtest_method_call_many()内のsea
        // に影響を与えていない。したがって、中身はmysmysになる。
    }

    private String functionSomething(String name) {
        String replaced = name.replace("tic", "mys");
        log("in function: {}", replaced);
        return replaced;
    }

    private String supplySomething() {
        String sea = "over";
        log("in supply: {}", sea);
        return sea;
    }

    private void consumeSomething(String sea) {
        log("in consume: {}", sea.replace("over", "mystic"));
    }

    private void runnableSomething() {
        String sea = "outofshadow";
        log("in runnable: {}", sea);
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_object() {
        St4MutableStage mutable = new St4MutableStage();
        int sea = 904;
        boolean land = false;
        helloMutable(sea - 4, land, mutable);
        if (!land) {
            sea = sea + mutable.getStageName().length();
        }
        log(sea); // your answer? => 910
        // helloMutable()メソッドの引数にseaとlandが使用されているが、別メソッドの引数は別の変数なのでtest_method_object()メソッドのseaとlandには影響がない
        // ただ、mutableのstageNameをmysticに変更しているので長さは6になる。
        // したがって、最終的な出力は904+6の結果の910になる。
    }

    private int helloMutable(int sea, Boolean land, St4MutableStage piari) {
        sea++;
        land = true;
        piari.setStageName("mystic");
        return sea;
    }

    private static class St4MutableStage {

        private String stageName;

        public String getStageName() {
            return stageName;
        }

        public void setStageName(String stageName) {
            this.stageName = stageName;
        }
    }

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private int inParkCount;
    private boolean hasAnnualPassport;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_instanceVariable() {
        hasAnnualPassport = true;
        int sea = inParkCount;
        offAnnualPassport(hasAnnualPassport);
        for (int i = 0; i < 100; i++) {
            goToPark();
            quote("", "'");
        }
        ++sea;
        sea = inParkCount;
        log(sea); // your answer? => 100
        // 100になるだろうなと予想できたが、goToPark();が呼び出される時になぜhasAnnualPassportとinParkCountを使用できるのか説明できなかったので調べて深掘り。
        // これが可能だったのは、hasAnnualPassportとinParkCountがインスタンス変数だから。ローカル変数ではないので、スコープが異なっている。
        // インスタンス変数は同じインスタンス内の全てのメソッドからアクセス可能である。
        // #1on1: 全てのメソッド → 全てのインスタンスメソッド
    }

    private void offAnnualPassport(boolean hasAnnualPassport) {
        hasAnnualPassport = false;
    }

    private void goToPark() {
        if (hasAnnualPassport) {
            ++inParkCount;
        }
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    // write instance variables here
    /**
     * Make private methods as followings, and comment out caller program in test method:
     * <pre>
     * o replaceAwithB(): has one argument as String, returns argument replaced "A" with "B" as String 
     * o replaceCwithB(): has one argument as String, returns argument replaced "C" with "B" as String 
     * o quote(): has two arguments as String, returns first argument quoted by second argument (quotation) 
     * o isAvailableLogging(): no argument, returns private instance variable "availableLogging" initialized as true (also make it separately)  
     * o showSea(): has one argument as String argument, no return, show argument by log()
     * </pre>
     * (privateメソッドを以下のように定義して、テストメソッド内の呼び出しプログラムをコメントアウトしましょう):
     * <pre>
     * o replaceAwithB(): 一つのString引数、引数の "A" を "B" に置き換えたStringを戻す 
     * o replaceCwithB(): 一つのString引数、引数の "C" を "B" に置き換えたStringを戻す 
     * o quote(): 二つのString引数、第一引数を第二引数(引用符)で囲ったものを戻す 
     * o isAvailableLogging(): 引数なし、privateのインスタンス変数 "availableLogging" (初期値:true) を戻す (それも別途作る)  
     * o showSea(): 一つのString引数、戻り値なし、引数をlog()で表示する
     * </pre>
     */
    public void test_method_making() {
        // use after making these methods
        String replaced = replaceCwithB(replaceAwithB("ABC"));
        String sea = quote(replaced, "'");
        if (isAvailableLogging()) {
            showSea(sea);
        }
    }

    // TODO tabata [いいね] privateメソッドの定義順が呼び出し順序と一致してて直感的でわかりやすい by jflute (2025/08/29)
    // #1on1: クラス内のカテゴリを意識して、メソッドを配置したい (タグコメント話) (2025/08/29)
    // privateメソッドの種別:
    // A. 単に意味的に大きさ的に切り出しただけで、再利用される想定ではないもの
    // B. 再利用される想定のもの (若干グレーゾーンもあるが)
    //
    // (AからBに昇格する場合もある)
    // 例えば、quote()が再利用されることになった場合の例: Small Helperで囲うなど
    //
    // (Aの場合でも、意味的に重要性の高い独立性の高いものは独立カテゴリにすることもある)
    // 例えば、replace系が重要度の高いロジックの例: Replace Logicで囲うなど
    //
    // (privateメソッドから、独立クラスに昇格する場合もある)
    //
    // #1on1: 一番下に追加するパターンのなぜ？
    // S. 既存クラスの構造が把握するスキルが足りない (コードを読む力が足りない)
    // T. 既存クラスの構造を把握しようとしない (お邪魔します感)
    //  → 会社のコードの責任の話、追加作業するからには自分も責任者の１人
    //  → javatryにおける@authorのこだわりの話
    // U. そもそも既存クラスの構造がぐちゃぐちゃ (既存を書いた人の力不足)
    //  → 次のジレンマへ
    //
    // #1on1: 既存コードがぐちゃぐちゃ直したくてしょうがないケースどうする？
    // E: その場で直してチケットブランチのプルリクで一緒に出す
    // F: 別のチケットに登録して後で別タスクとして直す
    //     → だいたい、その別タスクが後回しになって3年後も残ってる by jflute
    //     → なので、週1のリファクタリングタイムなどを設ける現場もある by jflute
    //     → なのでなので、"F" は相当な覚悟と仕組みがないと成り立たない？ by jflute
    // G: その間の子、プルリク差分が見にくくなければ一緒でOK (程度の問題)
    //     → 程度が人に依るって感じになっちゃうけど...現実的 by jflute
    // H: 直さない、スルー
    //
    // ↑チームでポリシーを決めないことにはどうにもならない
    //  チームで決めてなかったら決めるように促す
    //
    // write methods here
    private String replaceAwithB(String input){
        return input.replace('A','B');
    }
    private  String replaceCwithB(String input){
        return input.replace('C','B');
    }
    // TODO tabata [いいね] 第二引数の引数名が業務的な名前が付いててわかりやすい by jflute (2025/08/29)
    private String quote(String replaced, String quotation){
        return quotation + replaced + quotation;
    }
    // TODO tabata [いいね] 第二引数の引数名が業務的な名前が付いててわかりやすい by jflute (2025/08/29)
    private boolean availableLogging = true;

    private boolean isAvailableLogging() {
        return availableLogging;
    }
    private void showSea(String input){
        log(input);
    }
}
