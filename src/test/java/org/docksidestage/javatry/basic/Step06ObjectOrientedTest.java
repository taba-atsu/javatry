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

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketBuyResult;
import org.docksidestage.bizfw.basic.objanimal.*;
import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.loud.AlarmClock;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;
import org.docksidestage.bizfw.basic.objanimal.runner.FastRunner;
import org.docksidestage.javatry.basic.st6.dbms.St6MySql;
import org.docksidestage.javatry.basic.st6.dbms.St6PostgreSql;
import org.docksidestage.javatry.basic.st6.os.St06Mac;
import org.docksidestage.javatry.basic.st6.os.St06OldWindows;
import org.docksidestage.javatry.basic.st6.os.St06Windows;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of object-oriented. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author taba-atsu
 */
public class Step06ObjectOrientedTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        About Object
    //                                                                        ============
    // -----------------------------------------------------
    //                                        Against Object
    //                                        --------------
    /**
     * Fix several mistakes (except simulation) in buying one-day passport and in-park process. <br>
     * (OneDayPassportを買って InPark する処理の中で、(simulationを除いて)間違いがいくつかあるので修正しましょう)
     */
    public void test_objectOriented_aboutObject_againstObject() {
        //
        // [ticket booth info]
        //
        // simulation: actually these variables should be more wide scope
        int oneDayPrice = 7400;
        int quantity = 10;
        int salesProceeds = 0;
        // 売り上げがない時nullを返すのは不自然だと考えて、int型に変更し初期値を0にした

        // done tabata 間違い、あと2つ by jflute (2025/10/22)
        // TODO tabata あと1つ、この辺に、それぞれの行自体は間違ってない系のバグが一つ by jflute (2025/11/26)
        //
        // [buy one-day passport]
        //
        // simulation: actually this money should be from customer
        int handedMoney = 10000;
        if (quantity <= 0) {
            throw new IllegalStateException("Sold out");
        }
        if (handedMoney < oneDayPrice) {
            throw new IllegalStateException("Short money: handedMoney=" + handedMoney);
        }
        --quantity; // 所持金が足りない場合でも、quantityを減らしてしまっていたので位置を修正した
        salesProceeds += oneDayPrice;
        //売上を保持する変数に、所持金を代入してしまっていたので、チケットの値段を足すように変更した

        //
        // [ticket info]
        //
        // simulation: actually these variables should be more wide scope
        int displayPrice = oneDayPrice;
        // displayPriceに在庫の数を渡してしまっていたので修正
        boolean alreadyIn = false;

        // other processes here...
        // ...
        // ...

        //
        // [do in park now!!!]
        //
        // simulation: actually this process should be called by other trigger
        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayPrice=" + oneDayPrice);
           // displayPriceを返すはずが、quantityを返していたので修正
        }
        alreadyIn = true;

        //
        // [final process]
        //
        saveBuyingHistory(quantity, salesProceeds, displayPrice, alreadyIn);
        // 渡す引数の順番が間違っていた

        // done jflute 次回1on1で間違えない話をする (2025/10/22)
        // #1on1: 間違えない話
        //
        // 作り手として: 極力、こういうメソッドを作らないように努力する。
        // o 個別個別にクラス化、Value Objectみたいなものを導入する。
        // o BoothとかTicket単位でクラス化する。(オブジェクト利用)
        // o 引数の順序を工夫する (100%ではないけど: 軽減策)
        // o JavaDocおおげさに書く (100%ではないけど: 軽減策)
        //
        // 呼び手として: チェックする(by たばたさん)、指差し確認する。
        // o したら、1行1行ぜんぶ指差し確認するか？
        // o 間違えポイントだけで指差し確認するにはどうしたら？
        // o 重み付け、クリティカルなところだけ指差し確認(by たばたさん)
        // o 経験から、間違えポイント(の思い出)を積み重ねてるかどうか？ ☆☆☆
        // o 同じ体験をしてても、積み重ねてないと意味がない。
        // o さらに、自分ならではな間違えポイントも積み重ねてるかどうか？ ☆☆☆
        // o jfluteならではな間違えポイントも積み重ねて思い出にしている
        //
        // 技術スキルというよりかは、開発者スキル？ものづくりスキル？
    }

    private void saveBuyingHistory(int quantity, int salesProceeds, int displayPrice, boolean alreadyIn) {
        // simulation: only logging here (normally e.g. DB insert)
        showTicketBooth(quantity, salesProceeds);
        showYourTicket(displayPrice, alreadyIn);
        // alreadyInのif文があると、チケットを購入しているのに入場していない場合、チケットの情報を表示できないと考えて修正。
        // 渡す引数が間違っていたので修正。quantityとdisplayPriceの位置が逆になっていました。
    }

    private void showTicketBooth(int quantity, int salesProceeds) {
        log("Ticket Booth: quantity={}, salesProceeds={}", quantity, salesProceeds);
    }

    private void showYourTicket(int displayPrice, boolean alreadyIn) {
        log("Ticket: displayPrice={}, alreadyIn={}", displayPrice, alreadyIn);
    }

    // -----------------------------------------------------
    //                                          Using Object
    //                                          ------------
    /**
     * Read (analyze) this code and compare with the previous test method, and think "what is object?". <br>
     * (このコードを読んで(分析して)、一つ前のテストメソッドと比べて、「オブジェクトとは何か？」を考えてみましょう)
     */
    public void test_objectOriented_aboutObject_usingObject() {
        //
        // [ticket booth info]
        //
        TicketBooth booth = new TicketBooth();

        // *booth has these properties:
        //int oneDayPrice = 7400;
        //int quantity = 10;
        //Integer salesProceeds = null;

        //
        // [buy one-day passport]
        //
        // if step05 has been finished, you can use this code by jflute (2019/06/15)
        TicketBuyResult ticket = booth.buyOneDayPassport(10000);
        //booth.buyOneDayPassport(10000); // as temporary, remove if you finished step05
        //Ticket ticket = new Ticket(7400,1,false); // also here

        // *buyOneDayPassport() has this process:
        //if (quantity <= 0) {
        //    throw new TicketSoldOutException("Sold out");
        //}
        //if (handedMoney < oneDayPrice) {
        //    throw new TicketShortMoneyException("Short money: handedMoney=" + handedMoney);
        //}
        //--quantity;
        //salesProceeds = handedMoney;

        // *ticket has these properties:
        //int displayPrice = oneDayPrice;
        //boolean alreadyIn = false;

        // other processes here...
        // ...
        // ...

        //
        // [do in park now!!!]
        //
        ticket.getPurchasedTicket().doInPark();

        // *doInPark() has this process:
        //if (alreadyIn) {
        //    throw new IllegalStateException("Already in park by this ticket: displayPrice=" + displayPrice);
        //}
        //alreadyIn = true;

        //
        // [final process]
        //
        saveBuyingHistory(booth, ticket.getPurchasedTicket());
    }

    private void saveBuyingHistory(TicketBooth booth, Ticket ticket) {
        if (ticket.isAlreadyIn()) {
            // only logging here (normally e.g. DB insert)
            doShowTicketBooth(booth);
            doShowYourTicket(ticket);
        }
    }

    private void doShowTicketBooth(TicketBooth booth) {
        log("Ticket Booth: quantity={}, salesProceeds={}", booth.getOneDayPassPortQuantity(), booth.getSalesProceeds());
    }

    private void doShowYourTicket(Ticket ticket) {
        log("Your Ticket: displayPrice={}, alreadyIn={}", ticket.getDisplayPrice(), ticket.isAlreadyIn());
    }

    // write your memo here:
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // what is object?
    // オブジェクトを使用することで、特定の責務ごとに処理がまとまりそのコードが何をしているのかとても読みやすくなった。
    // また、責務ごとに分離していることで動作のテストも非常にやりやすそう。
    // クラスを用いて、private変数でパスポートの値段や売上を管理することで、その数字を安全に管理しやすくなっていると感じた。
    // private変数にしていると外部からその値を変更されるリスクがなく、複雑な処理を追う必要がなくなりそう。
    // さらに、クラスを作成することで特定の処理を再利用しやすくなると感じた。特にパスポートを購入する処理は何回か繰り返される可能性が高いが、
    // クラスを作成しているとそのクラスに所属しているメソッドを呼び出すだけで購入できる。
    // まとめると、オブジェクトとは処理をまとめて整理することで複雑度合いを下げられ、開発を行いやすくしてくれる仕組みなのではないかと考えた。
    // _/_/_/_/_/_/_/_/_/_/
    // #1on1: オブジェクトの意味をちゃんと見出して、クラスにしていることが大切 (適当にまとめればいいわけではない) (2025/10/22)

    // ===================================================================================
    //                                                              Polymorphism Beginning
    //                                                              ======================
    /**
     * What string is sea and land variable at the method end? <br>
     * (メソッド終了時の変数 sea, land の中身は？)
     */
    public void test_objectOriented_polymorphism_1st_concreteOnly() {
        Dog dog = new Dog();
        BarkedSound sound = dog.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        // getBarkWordメソッドは、そのインスタンスのbarkWordを返すだけのgetメソッドなので、soundのbarkWordになにが入っているのかに着目した。
        // barkメソッドはBarkSoundというオブジェクトを返し、doBarkメソッドの引数に入るものが、今回log(sea)で出力される。doBarkメソッドの引数には
        // getBarkWordメソッドによって取得された文字列が入る。getBarkWordメソッドには、abstractがついていたのでサブクラスでオーバーライドされていると考え
        // てDogクラスを見てみた。そこではwanという文字列が返されていたので、log(sea)で出力されるのはwan。
        int land = dog.getHitPoint();
        log(land); // your answer? => 7
        // ここではhitPointの数が出力されるのでその処理を追った。初めに初期値が10に設定されており、barkメソッドの中でhitPointをデクリメントする
        // 処理を呼び出している関数が3つあったので、log(land)は7が出力される。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_2nd_asAbstract() {
        Animal animal = new Dog();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
        // Animal型の変数animalにDogクラスのインスタンスが入れられているが、DogクラスはAnimalクラスを継承しているので、型のエラーは起きない？暗黙的にAnimal型と扱われる？
        // また、今回の出力に関しては一つ前の問題と同じ処理を辿ると考えて、出力されるものも同じになると予想した。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_3rd_fromMethod() {
        Animal animal = createAnyAnimal();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
        // createAnyAnimalメソッド以外前回の問題と同じと考えた。createAnyAnimalメソッドは新しいDogオブジェクトを返すだけなので
        // 前回の問題と出力は同じになると考えた。
    }

    private Animal createAnyAnimal() {
        return new Dog();
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_4th_toMethod() {
        Dog dog = new Dog();
        doAnimalSeaLand_for_4th(dog);
    }

    private void doAnimalSeaLand_for_4th(Animal animal) {
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
        // 前回の問題と比較してみると、メソッドが分かれているがドックオブジェクトが渡されているので、前回の問題と出力は同じになると考えた。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_5th_overrideWithSuper() {
        Animal animal = new Cat();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => nya-
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
        // catクラスで親クラスであるanimalクラスのメソッドをオーバーライドしている。superは親のバージョンを呼びたい時に使用する。
        // 今回はgetHitPointメソッドはcat側のものが使用される。したがって、landは７ではなく５になる。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_6th_overriddenWithoutSuper() {
        Animal animal = new Zombie();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => uooo
        int land = animal.getHitPoint();
        log(land); // your answer? => -1
    }

    /**
     * What is happy if you can assign Dog or Cat instance to Animal variable? <br>
     * (Animal型の変数に、DogやCatなどのインスタンスを代入できると何が嬉しいのでしょう？)
     */
    public void test_objectOriented_polymorphism_7th_whatishappy() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // what is happy?
        // メソッドをオーバーライドすることで簡単に処理を分けられる。他の動物を追加したいときとても簡単になりそうだと感じた。
        // また、鳴き声を取得するときに、どの場合でも同じ処理で呼び出せるので混乱が少なく再利用しやすいと感じた。
        // 新しい共通メソッドを作成するときもかなり簡単に追加できるところも嬉しいと感じた。
        // _/_/_/_/_/_/_/_/_/_/
        // #1on1: ポリモーフィズム的な考え方、実は日常生活でも使っている!? (2025/11/26)
        // 便利なので、プログラミングでもやりたい。
        // (日常の中でのポリモーフィズムを見つけてみてください。抽象概念を見つける)
    }
    // done jflute 次回1on1ここから (2025/11/26)

    // ===================================================================================
    //                                                              Polymorphism Interface
    //                                                              ======================
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_dispatch() {
        Loudable loudable = new Zombie();
        String sea = loudable.soundLoudly();
        log(sea); // your answer? => uooo
        String land = ((Zombie) loudable).bark().getBarkWord();
        log(land); // your answer? => uooo
        // AnimalクラスはLoudableの実装クラスであり、soundLoudlyメソッドはbarkWordが返されるので、seaもlandもuoooになる
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_hierarchy() {
        Loudable loudable = new AlarmClock();
        String sea = loudable.soundLoudly();
        log(sea); // your answer? => jiri jiri jiri---
        boolean land = loudable instanceof Animal;
        log(land); // your answer? => false
        // AlarmClockクラスはLoudableクラスの実装クラスであり、seaの出力はAlarmClockクラスで実装されているのでjiri jiri jiri---になる
        // また、AlarmClockクラスはLoudableクラスの実装クラスであり、Animalクラスのサブクラスではないのでfalseになる
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_partImpl() {
        Animal seaAnimal = new Cat();
        Animal landAnimal = new Zombie();
        boolean sea = seaAnimal instanceof FastRunner;
        log(sea); // your answer? => true
        boolean land = landAnimal instanceof FastRunner;
        log(land); // your answer? => false
        // CatクラスはFastRunnerの実装クラスなのでtrueになるがZombieクラスは実装していないのでfalseになる
    }

    /**
     * Make Dog class implement FastRunner interface. (the method implementation is same as Cat class) <br>
     * (DogもFastRunnerインターフェースをimplementsしてみましょう (メソッドの実装はCatと同じで))
     */
    public void test_objectOriented_polymorphism_interface_runnerImpl() {
        // your confirmation code here
        Animal animal = new Dog();
        boolean sea = animal instanceof FastRunner;
        log(sea);
    }

    /**
     * What is difference as concept between abstract class and interface? <br>
     * (抽象クラスとインターフェースの概念的な違いはなんでしょう？)
     */
    public void test_objectOriented_polymorphism_interface_whatisdifference() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // what is difference?
        // インターフェースは特定の実装が強制されるので、特定の機能を保証したい時に使用するのではないか。
        // それに対して、抽象クラスは振る舞いだけでなく変数などの状態を持てるので、共通の処理をまとめる時に使用するという違いがあるのではないかと考えた。
        // _/_/_/_/_/_/_/_/_/_/
        // #1on1: 抽象クラスのインターフェースに。 (2025/12/03)
        //
        // そもそもコンセプトの違い:
        // インターフェース: 機能にフォーカスを当てて整理する
        // オブジェクト指向: オブジェクト(その物)にフォーカスを当てて整理する
        //
        // 抽象クラスのインターフェースにはない機能はGood。
        // 一方で、特定の実装が強制に関しては、抽象クラスもできる。
        // これだけの論理だと、抽象クラスが機能を包含しているので、インターフェースなくても良いのでは？話になる。
        //
        // インターフェース: ポリモーフィズム
        // 抽象クラス: ポリモーフィズム + 実装継承(具象クラスの形付け)
        //
        // 一瞬、抽象クラスが機能を包含しているように見えるが...
        //
        // インターフェース: 複数定義できる (implements)
        // 抽象クラス(継承): 単一のみ (extends)
        //
        // Javaのオブジェクト指向は、多重継承を排除している。(あえてしている)
        // Dog, Cat が Animal でもあり、Pet でもあり...ができない。
        // C++ が多重継承OK。話に聞くと、多重継承でだいぶカオスになった。
        // 間違った多重継承しちゃって辛いもあるだろうし、正しくでもまあまあ辛い。
        //
        // Javaは多重継承やめた。(ようである)
        // でも、Pet継承できないじゃん。困った。
        // ってところでインターフェースが補完している。
        // でも、インターフェースで多重継承できちゃったら同じ話じゃん。
        // インターフェースは、あくまでポリモーフィズムだけなので、実装は継承できない。
        // ある意味、多重ポリモーフィズム (造語) ができるようになる。
        // 多重の実装継承はできない。ので、そこまでカオスにならないでしょう、という話。
        //
        // 多重の実装継承 (再利用) は、別の方法である程度は代替もできる。(newして委譲するだけとか)
        // 多重ポリモーフィズムは、代替がしづらいのでこれはさすがにできて欲しい。
        //
        // 具象クラスと抽象クラスは、変数の継承などできるから、依存度が高い by たばたさん
        // その感想すばらしい by jflute
        // あとまあ、インターフェースはフレームワークとかライブラリとかでよく使われるので自分で作る機会が少ない by jflute
        // (レイヤー隠蔽のための機械的なinterfaceは作るかもだけど、複数具象クラスのinterfaceはなかなかない)
        
        // #1on1: インターフェースの応用的利用 ColorBox (2025/12/03)
        // e.g. abstract class AbstractColorBox implements ColorBox
        // メリットは何だ？
        //
        // 1. ポリモーフィズムできるメソッドの一覧がわかりやすい
        // 2. 内部用publicメソッドがあったときにそれを隠しやすい
        // 3. 抽象クラスの実装を継承したくない別の具象クラスを作りたいとき 
        //    (ラップオブジェクトとか e.g. ListResultBean)
        //
        // 抽象クラスは、内政(実装継承)に集中して、外交(ポリモーフィズム)はインターフェースにお任せ。
        // 王道なポリモーフィズムはインターフェースに任せる。
        // 一方で、オブジェクト指向のポリモーフィズムは、内部の初期化処理でのポリモーフィズムとか、
        // 具象と具象のポリモーフィズムとかで一部ちゃんと使われる。
        //
        // #1on1: プログラミング言語の互換性の運用の話 (2025/12/17)
        // フレームワークも同じ。DBFluteの互換モードの話も。
    }

    // ===================================================================================
    //                                                                 Polymorphism Making
    //                                                                 ===================
    /**
     * Make concrete class of Animal, which is not FastRunner, in "objanimal" package. (implementation is as you like) <br>
     * (FastRunnerではないAnimalクラスのコンクリートクラスをobjanimalパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeConcrete() {
        // your confirmation code here
        Animal cow = new Cow();
        String sea = cow.soundLoudly();
        log(sea);
    }

    /**
     * Make interface implemented by part of Animal concrete class in new package under "objanimal" package. (implementation is as you like) <br>
     * (Animalクラスの一部のコンクリートクラスだけがimplementsするインターフェースをobjanimal配下の新しいパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeInterface() {
        // your confirmation code here
        Cow cow = new Cow();
        cow.sleep();
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Extract St6MySql, St6PostgreSql (basic.st6.dbms)'s process to abstract class (as super class and sub-class) <br>
     * (St6MySql, St6PostgreSql (basic.st6.dbms) から抽象クラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */
    public void test_objectOriented_writing_generalization_extractToAbstract() {
        // your confirmation code here
        St6MySql mySql = new St6MySql();
        log(mySql.buildPagingQuery(1, 2));
        St6PostgreSql postgreSql = new St6PostgreSql();
        log(postgreSql.buildPagingQuery(1,2));
        // 振る舞いを出し分けるという点でinterfaceを使いたくなった。
        // 共通しているロジックとしてoffsetの計算があったので、それを親クラスでまとめた。戻り値はそれぞれの形式を返せるようになっている。
    }

    /**
     * Extract St6OperationSystem (basic.st6.os)'s process to concrete classes (as super class and sub-class) <br>
     * (St6OperationSystem (basic.st6.os) からコンクリートクラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */
    public void test_objectOriented_writing_specialization_extractToConcrete() {
        // your confirmation code here
        St06Mac mac = new St06Mac("mac");
        St06Windows windows = new St06Windows("windows");
        St06OldWindows oldWindows = new St06OldWindows("oldWindows");

        log(mac.getFileSeparator(),mac.getUserDirectory());
        log(windows.getFileSeparator(),windows.getUserDirectory());
        log(oldWindows.getFileSeparator(),oldWindows.getUserDirectory());
        
        // TODO jflute 次回以降1on1にて、思考トレーニングを二つやれたら (2026/01/07)
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Extract Animal's bark() process as BarkingProcess class to also avoid large abstract class. <br>
     * (抽象クラス肥大化を抑制するためにも、Animalのbark()のプロセス(処理)をBarkingProcessクラスとして切り出しましょう)
     */
    public void test_objectOriented_writing_withDelegation() {
        // your confirmation code here
        Animal dog = new Dog();
        BarkedSound sound = dog.bark();
        log(sound.getBarkWord());
        // barkメソッドの内容を切り出したBarkingProcessクラス内にbarkメソッドを定義し、その引数にAnimalクラスを入れた。それによってbarkメソッド以外のメソッドを
        // BarkingProcessクラス内で簡単に使用できるようにした。
    }

    /**
     * Put barking-related classes, such as BarkingProcess and BarkedSound, into sub-package. <br>
     * (BarkingProcessやBarkedSoundなど、barking関連のクラスをサブパッケージにまとめましょう)
     * <pre>
     * e.g.
     *  objanimal
     *   |-barking
     *   |  |-BarkedSound.java
     *   |  |-BarkingProcess.java
     *   |-loud
     *   |-runner
     *   |-Animal.java
     *   |-Cat.java
     *   |-Dog.java
     *   |-...
     * </pre>
     */
    public void test_objectOriented_writing_withPackageRefactoring() {
        // your confirmation code here
        Animal dog = new Dog();
        BarkedSound sound = dog.bark();
        log(sound.getBarkWord());
        // Animalクラスに記述されているメソッドがprotectedになっており別パッケージからの呼び出しができなかったので、publicに修正した。
    }

    // TODO jflute 次回1on1にて、Zombie話で盛り上がろう (2026/01/07)
    /**
     * Is Zombie correct as sub-class of Animal? Analyze it in thirty seconds. (thinking only) <br>
     * (ゾンビは動物クラスのサブクラスとして適切でしょうか？30秒だけ考えてみましょう (考えるだけでOK))
     */
    public void test_objectOriented_zoo() {
        // write your memo here:
        // 現状、Animalクラスを継承してZombieクラスが適切に仕様を表している。しかし、HPが無限であったり概念的には動物としてまとめるには違和感がある。
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // is it correct?
        // 共通化されているAnimalクラスの仕組みを利用することを重視するなら今のサブクラスとして適切であると考える。
        // しかし、HPがdownHitPointメソッドで減らなかったり、動物とは言いにくい特徴を持っていることから、意味的な正しさを重視するならサブクラスとして適切ではないと考えた。
        // _/_/_/_/_/_/_/_/_/_/
    }
}
