package com.example_ethan.demosample;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.PostgresDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import javax.sql.DataSource;

// Domaの設定をSpring Bootで自動的に認識させるための設定クラス
@Configuration
public class DomaConfig {

    private final DataSource dataSource;

    // コンストラクタでDataSourceを受け取り、トランザクション対応のプロキシにラップする
    public DomaConfig(DataSource dataSource) {
        this.dataSource = new TransactionAwareDataSourceProxy(dataSource);
    }

    // PostgreSQL用の方言をBeanとして登録
    @Bean
    public Dialect dialect() {
        return new PostgresDialect();
    }

    // Domaの設定をBeanとして登録
    @Bean
    public Config config() {
        return new org.seasar.doma.jdbc.Config() {
            @Override
            public DataSource getDataSource() {
                // トランザクション対応のDataSourceを返す
                return dataSource;
            }

            @Override
            public Dialect getDialect() {
                // PostgreSQL用の方言を返す
                return dialect();
            }
        };
    }
}
