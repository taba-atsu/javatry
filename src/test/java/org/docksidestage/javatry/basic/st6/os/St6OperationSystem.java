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
package org.docksidestage.javatry.basic.st6.os;

/**
 * @author jflute
 * @author tabaatsu
 */
public abstract class St6OperationSystem {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // done tabata サブクラスで隠蔽変数になるくらいなら、privateで隠しちゃった方が良い by jflute (2026/01/07)
    // もし継承(参照)させるのであれば、サブクラスで隠蔽を発生させないようにしましょう。
    // (隠蔽は、コンストラクターの狭い範囲でクラス内で閉じてるものなら良いけど、クラス跨っての隠蔽は紛らわしいので避けようs)
    // done tabata そもそも、osTypeをクラスで表現するようになったわけなので、文字列のosTypeは無くてもいいかも by jflute (2026/01/07)
    // TODO tabata Javaだと、可視性なしの場合は、packageスコープになる。意図しているか？してないなら何か付けたい by jflute (2026/02/18)
    // 今、サブクラスがprotectedスコープで参照しているわけではなく、packageスコープで(たまたま)参照できているだけ。
    // packageが分かれたとしてもサブクラスに参照させて良いのであれば、protectedにしましょう。
    // #1on1: jfluteがpackageスコープをほとんど使わない話 (一部(UnitTest)は使うけど) (2026/02/18) 
    // ファイル構成などの物理構成に依存するから。
    // Javaでは、packageとディレクトリ構成が一致させないといけないので、
    // 「packageスコープに依存する == コードが物理構成に依存する」と言っても過言ではない。
    // 一方で、Javaのpackageとディレクトリ構成が一致はわかりやすいので好き。(レールが敷かれてて無難)
    final String loginId;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public St6OperationSystem( String loginId) {
        this.loginId = loginId;
    }

    // ===================================================================================
    //                                                                      User Directory
    //                                                                      ==============
    public String buildUserResourcePath(String relativePath) {
        String fileSeparator = getFileSeparator();
        String userDirectory = getUserDirectory();
        String resourcePath = userDirectory + fileSeparator + relativePath;
        return resourcePath.replace("/", fileSeparator);
    }

    protected abstract String getFileSeparator();

    protected abstract String getUserDirectory();
}
