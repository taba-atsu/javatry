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
package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;

/**
 * パークへの入場チケットを表すクラスです。チケットが利用可能なのか判定するロジックを持ちます。購入するロジックは含みません。
 * チケットの値段、入場可能日数、夜限定チケットなのか、そのチケットであと何日パークに入場可能かなどの情報を保持します。
 * また、チケットが利用可能か判定するメソッドなどを持ちます。
 * @author jflute
 * @author taba-atsu
 */
public class Ticket {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final LocalTime nightPassStartTime = LocalTime.of(17,0);
    private static final LocalTime lastEntryTime = LocalTime.of(20,0);


    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // #1on1: インスタンス変数の定義順序の選択肢。
    // ticketDays と remainingDays が近いカテゴリの変数なので、一緒にしておくってのもGood。
    // 一方で、finalが付いてないものは、オブジェクトの状態を示すもので...
    // finalが付いているものは、オブジェクトの固定の属性を示すもの...
    // (インスタンス変数の定義順序というのもにも意識を)
    private final int displayPrice; // written on ticket, park guest can watch this
    private boolean alreadyIn; // true means this ticket is unavailable

    private final int ticketDays;
    private int remainingDays;

    private final boolean isNightOnlyPassport;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * チケットを作るコンストラクタ。
     * チケットで入場可能な残り日数を表すremainingDaysはticketDaysで初期化される。
     * @param displayPrice チケットの値段
     * @param ticketDays チケットの利用可能な日数
     * @param isNightOnlyPassport 夜限定パスポートだとtrueになる
     */
    public Ticket(int displayPrice, int ticketDays, boolean isNightOnlyPassport) {
        this.displayPrice = displayPrice;
        this.ticketDays = ticketDays;
        this.remainingDays = ticketDays;
        this.isNightOnlyPassport = isNightOnlyPassport;
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    /**
     * パークに入場するときに実行するメソッド。
     * 実行するたびに入場可能な残り日数が1日減る。また、残り日数が0の時や夜限定パスポートを夜以外に使おうとしたときは入場できない。
     */
    public void doInPark() {
        if (remainingDays == 0) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        if (this.isNightOnlyPassport){
            if (!isNightPassportAvailable()){
                throw new IllegalStateException(String.format("Night Pass is not available at this time.Night Pass is only valid between %s and %s.",nightPassStartTime,lastEntryTime));
            }
        }
        remainingDays--;
        alreadyIn = true;
    }
    // TODO done tabata このコメント、isNightPassportAvailable()の説明だと思うので空行なしでOK by jflute (2025/10/08)
    // doInParkメソッドにナイトパスの場合夜か判定して入場できるか判断する処理を追加する
    private boolean isNightPassportAvailable(){
        LocalTime currentTime = LocalTime.now();
        // TODO done tabata 17:00:01 〜 19:59:59 なら入れるという仕様になっているが意図しているか？ by jflute (2025/10/08)
        // 境界値が含むのか？含まないのか？意識して判定をしましょう。e.g. 17時ぴったりは入れていいのでは？
        // (DBのEND_TIME含むの？ややこしい話)
        // 17:00:00 〜 19:59:59なら入れる仕様にしました by taba-atsu
        return !currentTime.isBefore(nightPassStartTime) && currentTime.isBefore(lastEntryTime);
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    /**
     * @return 券面に表示される値段を返す
     */
    public int getDisplayPrice() {
        return displayPrice;
    }

    /**
     * @return すでにパークに入場したことがあるのかを返す
     */
    public boolean isAlreadyIn() {
        return alreadyIn;
    }

    // done tabata ここだけ突然の1行スタイル、悪くはないけど、であれば上もその方が良いしと統一を by jflute (2025/09/25)
    // IntelliJの機能で上2つのメソッドが自動的に1行スタイルに見えるようになっていました、、
    // #1on1: 自分が使ってるツール、オープンソースなのか？closedなのか？
    // オープンソースにしても、企業ベースでビジネス的なのか？コミュニティでボランティア的なのか？(個人でボランティア的なのか？)
    // DBFluteの例、Spring Frameworkの例
    /**
     * @return 何日間入場できるチケットなのか日数を返す。　入場可能な残り日数ではない。
     */
    public int getTicketDays(){
        return ticketDays;
    }

    /**
     * @return 夜限定のパスポートなのかを返す
     */
    public boolean isNightOnlyPassport(){
        return isNightOnlyPassport;
    }
}
