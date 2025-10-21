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
import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.docksidestage.bizfw.basic.objanimal.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.Cat;
import org.docksidestage.bizfw.basic.objanimal.Dog;
import org.docksidestage.bizfw.basic.objanimal.Zombie;
import org.docksidestage.bizfw.basic.objanimal.loud.AlarmClock;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;
import org.docksidestage.bizfw.basic.objanimal.runner.FastRunner;
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

        //
        // [buy one-day passport]
        //
        // simulation: actually this money should be from customer
        int handedMoney = 10000;
        if (quantity <= 0) {
            throw new IllegalStateException("Sold out");
        }
        --quantity;
        if (handedMoney < oneDayPrice) {
            throw new IllegalStateException("Short money: handedMoney=" + handedMoney);
        }
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
    }

    private void saveBuyingHistory(int quantity, int salesProceeds, int displayPrice, boolean alreadyIn) {

        // simulation: only logging here (normally e.g. DB insert)
        showTicketBooth(displayPrice, salesProceeds);
        showYourTicket(quantity, alreadyIn);
        // alreadyInのif文があると、チケットを購入しているのに入場していない場合、チケットの情報を表示できないと考えて修正。
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
    }

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
    }

    /**
     * Make interface implemented by part of Animal concrete class in new package under "objanimal" package. (implementation is as you like) <br>
     * (Animalクラスの一部のコンクリートクラスだけがimplementsするインターフェースをobjanimal配下の新しいパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeInterface() {
        // your confirmation code here
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
    }

    /**
     * Extract St6OperationSystem (basic.st6.os)'s process to concrete classes (as super class and sub-class) <br>
     * (St6OperationSystem (basic.st6.os) からコンクリートクラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */
    public void test_objectOriented_writing_specialization_extractToConcrete() {
        // your confirmation code here
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
    }

    /**
     * Is Zombie correct as sub-class of Animal? Analyze it in thirty seconds. (thinking only) <br>
     * (ゾンビは動物クラスのサブクラスとして適切でしょうか？30秒だけ考えてみましょう (考えるだけでOK))
     */
    public void test_objectOriented_zoo() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // is it correct?
        //
        // _/_/_/_/_/_/_/_/_/_/
    }
}
