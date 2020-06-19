package cc.xzwb.bookstore.config;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 正方教务系统的config
 */
@Configuration
public class ZFJWConfig {

    // HttpClient连接池
    @Bean
    public PoolingHttpClientConnectionManager createHttpClientPool() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // 设置最大连接数
        cm.setMaxTotal(100);
        // 设置每个主机的最大连接数
        cm.setDefaultMaxPerRoute(10);

        return cm;
    }

}
