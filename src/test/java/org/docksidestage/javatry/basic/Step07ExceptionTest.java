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

import java.io.File;
import java.io.IOException;

import org.docksidestage.bizfw.basic.supercar.SupercarClient;
import org.docksidestage.javatry.basic.st7.St7BasicExceptionThrower;
import org.docksidestage.javatry.basic.st7.St7ConstructorChallengeException;
import org.docksidestage.unit.PlainTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The test of variable. <br>
 * Operate as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りに実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author taba-atsu
 */
public class Step07ExceptionTest extends PlainTestCase {
    
    // TODO done tabata javatryだと、superクラスにlog()があるので、そっち使って大丈夫です by jflute (2026/03/04)
    // なので、現時点でもこのlogオブジェクトは unused の警告が出ています。

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_exception_basic_catchfinally() {
        St7BasicExceptionThrower thrower = new St7BasicExceptionThrower();
        StringBuilder sea = new StringBuilder();
        try {
            thrower.land();
            sea.append("dockside");
        } catch (IllegalStateException e) {
            sea.append("hangar");
        } finally {
            sea.append("broadway");
        }
        log(sea); // your answer? => hangarbroadway
        // landメソッドを実行するとIllegalStateExceptionが投げられるので、docksideはseaにappendされない。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_basic_message() {
        St7BasicExceptionThrower thrower = new St7BasicExceptionThrower();
        String sea = null;
        try {
            thrower.land();
            fail("no way here");
        } catch (IllegalStateException e) {
            sea = e.getMessage();
        }
        log(sea); // your answer? => oneman at showbase
        // landメソッドを実行した際に投げられるIllegalStateExceptionがseaに入る
        // failが実行される前にcatchに入る
    }

    /**
     * What class name and method name and row number cause the exception? (you can execute and watch logs) <br>
     * (例外が発生したクラス名とメソッド名と行番号は？(実行してログを見て良い))
     */
    public void test_exception_basic_stacktrace() {
        St7BasicExceptionThrower thrower = new St7BasicExceptionThrower();
        try {
            thrower.land();
            fail("no way here");
        } catch (IllegalStateException e) {
            log(e);
        }
        // your answer? => St7BasicExceptionThrowerクラスのonemanメソッドの3行目(ファイルの40行目)
    }

    // ===================================================================================
    //                                                                           Hierarchy
    //                                                                           =========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_exception_hierarchy_Runtime_instanceof_RuntimeException() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof RuntimeException;
        log(sea); // your answer? => true
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Exception() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Exception;
        log(sea); // your answer? => true
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Error() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Error;
        log(sea); // your answer? => false
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Throwable() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Throwable;
        log(sea); // your answer? => true
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Throwable_instanceof_Exception() {
        Object exp = new Throwable("mystic");
        boolean sea = exp instanceof Exception;
        log(sea); // your answer? => false
    }

    // ===================================================================================
    //                                                                         NullPointer
    //                                                                         ===========
    /**
     * What variable (is null) causes the NullPointerException? And what row number? (you can execute and watch logs) <br>
     * (NullPointerが発生する変数(nullだった変数)と、発生する行番号は？(実行してログを見ても良い))
     */
    public void test_exception_nullpointer_basic() {
        try {
            String sea = "mystic";
            String land = sea.equals("mystic") ? null : "oneman";
            String lowerCase = land.toLowerCase();
            log(lowerCase);
        } catch (NullPointerException e) {
            log(e);
        }
        // your answer? => land,136
        // seaは必ずmisticになるので、landは三項演算子により常にnullが代入される
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_nullpointer_headache() {
        try {
            String sea = "mystic";
            String land = !!!sea.equals("mystic") ? null : "oneman";
            String piari = !!!sea.equals("mystic") ? "plaza" : null;
            int sum = land.length() + piari.length();
            log(sum);
        } catch (NullPointerException e) {
            log(e);
        }
        // your answer? => piari,151
        // landがonemanになるのでpiariは必ずnullになる
    }

    /**
     * Refactor to immediately understand what variable (is null) causes the NullPointerException by row number in stack trace. <br>
     * (どの変数がNullPointerを引き起こしたのか(nullだったのか)、スタックトレースの行番号だけでわかるようにリファクタリングしましょう)
     */
    public void test_exception_nullpointer_refactorCode() {
        try {
            String sea = "mystic";
            String land = !!!sea.equals("mystic") ? null : "oneman";
            String piari = !!!sea.equals("mystic") ? "plaza" : null;
            int landLength = land.length();
            int piariLength = piari.length();

            int sum = landLength +piariLength;
            log(sum);
        } catch (NullPointerException e) {
            log(e);
        }
        // #1on1: 最近のJavaだと、どの変数がnullだったか教えてくれる (2026/03/04)
        // #1on1: statementと行の違い。実行はstatement単位だけど、人間の見た目は行意識。 (2026/03/04)
        // 他の言語だと、テキスト上の見た目と文法をもうちょいつなげてるケースもある。e.g. Python
    }

    // ===================================================================================
    //                                                                   Checked Exception
    //                                                                   =================
    /**
     * Show canonical path of new java.io.File(".") by log(), and if I/O error, show message and stack-trace instead <br>
     * (new java.io.File(".") の canonical path を取得してログに表示、I/Oエラーの時はメッセージとスタックトレースを代わりに表示)
     */
    public void test_exception_checkedException_basic() {
        File sea = new java.io.File(".");

        try {
            String canonicalPath = sea.getCanonicalPath();
            // catchの動作確認用 (getCanonicalPath()を呼んでもなかなか例外発生しないので)
            //new java.io.FileInputStream(sea);
            log(canonicalPath);
        } catch (IOException e) {
            log(e.getMessage());
            // #1on1: これだと配列オブジェクトのtoString()が表示されるだけなので...log(e)でOK。
            //log((Object)e.getStackTrace());
            log(e);
        }
        // 例外処が投げられた場合の処理を検証する方法がよくわからなかったので、必ず例外を投げる処理を挟んで対応した
        
        // #1on1: チェック例外とは？ (2026/03/04)
        // catchを義務化する文法。Throwable,Exceptionとデフォルトではチェック例外。
        // RuntimeException配下(とError配下)だけ特別に実行時例外。
        // ただ、チェック例外は流行ってない。
        // コンセプト的にはチェック例外はそんなに悪くはない。
        // TicketSoldOutExceptionは、チェック例外にしても悪くはないケース。
        //
        // o 一律ただthrowsが付けられてて、若干無意味な場面でもcatchが強制されるとかあって印象悪い
        // o インフラ系は時代が進んで安定もして、インフラ系チェック例外が発生する確率も極端に低くなっている
        //   (隕石が降ってくることを気にして歩かないのと同じ)
        // 
        // そんなこんで、残念ながらJavaの世界でチェック例外を積極的に使ってるケースはとても少ない。
        // jfluteも、チェック例外を使って嬉しかったこと、3,4回しかない。0ではない。でも多くはない。
        // 他の言語の例: Go言語とか、Kotlinとか
        // チェック例外は、実務ではほとんど使わないが、色々な言語の文法を深掘りする勉強素材にはなる。
    }

    // ===================================================================================
    //                                                                               Cause
    //                                                                               =====
    /**
     * What string is sea variable in the catch block?
     * And What is exception class name displayed at the last "Caused By:" of stack trace? <br>
     * (catchブロックの変数 sea, land の中身は？また、スタックトレースの最後の "Caused By:" で表示されている例外クラス名は？)
     */
    public void test_exception_cause_basic() {
        String sea = "mystic";
        String land = "oneman";
        try {
            throwCauseFirstLevel();
            fail("always exception but none");
        } catch (IllegalStateException e) {
            Throwable cause = e.getCause();
            sea = cause.getMessage();
            land = cause.getClass().getSimpleName();
            log(sea); // your answer? => Failed to call the third help method: symbol=-1
            log(land); // your answer? => IllegalArgumentException
            log(e); // your answer? => throwCauseThirdLevel

            // スタックトレースの順番があまり理解できなかったので深く考えてみた。一番上が最終的に投げられた例外、
            // 下に行くほど「さかのぼった原因」by copilot
            // コールスタックから pop された順に出ているのか？とはじめ勘違いしてしまっていたが、実際はラップされた順に、外側→内側へ辿っていく。
        }
    }

    private void throwCauseFirstLevel() {
        int symbol = Integer.MAX_VALUE - 0x7ffffffe; // 1
        try {
            throwCauseSecondLevel(symbol);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Failed to call the second help method: symbol=" + symbol, e);
        }
    }

    private void throwCauseSecondLevel(int symbol) {
        try {
            --symbol;
            symbol--;
            if (symbol < 0) {
                throwCauseThirdLevel(symbol);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to call the third help method: symbol=" + symbol, e);
        }
    }

    private void throwCauseThirdLevel(int symbol) {
        if (symbol < 0) {
            Integer.valueOf("piari");
        }
    }
    // #1on1: ちょこっと例外の翻訳話。それぞれのレイヤーの情報を付け足して翻訳throwしている (2026/03/04)
    // 例外チェーンで、causeを階層的に保持することで、実質的に例外を複数投げることができている

    // ===================================================================================
    //                                                                         Translation
    //                                                                         ===========
    /**
     * Execute this test and read exception message and write situation and cause on comment for non-programmer. <br>
     * テストを実行して例外メッセージを読んで、プログラマーでない人にもわかるように状況と原因をコメント上に書きましょう。
     */
    public void test_exception_translation_debugChallenge() {
        try {
            new SupercarClient().buySupercar();
            fail("always exception but none");
        } catch (RuntimeException e) {
            log("*No hint here for training.", e);
            // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
            // What happens? Write situation and cause here. (何が起きた？状況と原因をここに書いてみましょう)
            // - - - - - - - - - -
            // buySupercar()メソッドを呼び出した際に例外が起き、それがキャッチされて"*No hint here for training."と出力されている。
            // 原因は、スーパーカーを購入しようとした際に指定したボルトの種類がすでにサポートされていない形式だったから。
            // _/_/_/_/_/_/_/_/_/_/
        }
    }

    /**
     * Improve exception handling in supercar's classes to understand the situation
     * by only exception information as far as possible. <br>
     * できるだけ例外情報だけでその状況が理解できるように、Supercarのクラスたちの例外ハンドリングを改善しましょう。
     */
    public void test_exception_translation_improveChallenge() {
        try {
            new SupercarClient().buySupercar(); // you can fix the classes
            fail("always exception but none");
        } catch (RuntimeException e) {
            log("*No hint here for training.", e);
        }
        // #1on1: 例外の翻訳によるデバッグのしやすさの工夫 (2026/03/04)
        // done jflute 次回1on1ふぉろー、もうちょい説明 (2026/03/04)
        // 末端の人は全体情報を持ってない (今回だとネジ会社)
        // 「処理を止めた人は、全体を把握してないので、デバッグしやすい情報を出せない」
        /*
                 catch        catch        catch
     <---- 例外  /      例外  /     例外    /    例外
             \ /         \ /          \ /       |
   o          |           |            |         \
  /|\   ->    A     ->    B    ->     C      ->      D -> D'
  /\           |     |        PK           ^^v
               |     |                     も
               +設定ファイル (PK)
         */
        // A,B,Cなどの全体像を把握している人たちが持っている途中の情報を残したい。
        // 一つ落ちたというイベントに付き、複数の言い分(情報/例外メッセージ)がある。
        // 
        // エラーメッセージ読め読め大合唱
        // https://jflute.hatenadiary.jp/entry/20130522/errorsinging
        //
        // 「目撃者の証言を無視しますか？」
        // 「エラーメッセージは解読するものなんです！」

        // #1on1: 良い先輩に恵まれているようなので (2026/03/18)
        // こうはい extends せんぱい
        // https://jflute.hatenadiary.jp/entry/20131124/extendsmaster
        //
        // 聞いてるだけだと？吸収するためにやってることは？ by たばたさん
        // o 話を引き出す: 単純に相槌/表情、合いの手(それって、こういうことですか？)
        // o 深掘り質問: 気になったことを聞く
        // o その後ひとりで思考: 帰りの電車だったりお風呂だったり (振り返り)
        //   (自分の中で解釈する時間を作る)
        //
        // #1on1: 振り返りの話 (2026/03/18)
        // 体験を経験にするために。
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Fix terrible (YABAI in Japanese) exception handling. (you can modify exception class) <br>
     * (やばい例外ハンドリングがあるので修正しましょう (例外クラスを修正してOK))
     */
    public void test_exception_writing_constructorChallenge() {
        try {
            helpSurprisedYabaiCatch();
        } catch (St7ConstructorChallengeException e) {
            log("Thrown by help method", e); // should show also "Caused-by" information
        }
    }

    private void helpSurprisedYabaiCatch() {
        try {
            helpThrowIllegalState();
        } catch (IllegalStateException e) {
            throw new St7ConstructorChallengeException("Failed to do something.", e);
        }
    }

    private void helpThrowIllegalState() {
        if (true) { // simulate something illegal
            String importantValue = "dummy"; // important to debug
            throw new IllegalStateException("something illegal: importantValue=" + importantValue);
        }

    // 元の実装では、St7ConstructorChallengeExceptionがメッセージだけを受け取るようになっており、causeを渡せないようになっていた。
    // これだとhelpThrowIllegalState()で起きた本当の原因を消してしまっている。
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What is the concept difference between Exception and Error? Write it on comment. <br>
     * (ExceptionとErrorのコンセプトの違いはなんでしょうか？コメント上に書きましょう)
     */
    public void test_exception_zone_differenceExceptionError() {
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // Write here. (ここに書いてみましょう)
        // - - - - - - - - - -
        // そもそもErrorに関して理解していなかったので調べた。
        // ThrowableからErrorとExceptionの分岐があり、Errorはプログラムの外側で起きた問題を表しExceptionはプログラムの中で起きた問題を表す。
        // つまり、Exceptionはプログラム内で起きたことなのでプログラムで対処できる余地があるが、Errorはプログラム外の環境が原因で起きたものなのでプログラムで対処できる余地がない？
        // _/_/_/_/_/_/_/_/_/_/
    }
}
