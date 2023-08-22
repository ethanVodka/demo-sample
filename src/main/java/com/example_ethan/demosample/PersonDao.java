package com.example_ethan.demosample;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

// DBアクセスのためのインターフェース
@Dao
@ConfigAutowireable // Domaの設定を自動的に組み込む
public interface PersonDao {
    // 全データを取得するメソッド
    @Select
    List<Person> selectAll();

    // データを追加するメソッド
    @Insert
    int insert(Person person);

    // データを削除するメソッド
    @Delete
    int delete(Person person);
}
