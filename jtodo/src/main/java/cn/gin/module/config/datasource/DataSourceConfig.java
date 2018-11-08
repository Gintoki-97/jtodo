package cn.gin.module.config.datasource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig {

    /**
     * Full classname of Database driver class
     */
    private String driverClassName;

    /**
     * JDBC Connection URL
     */
    private String url;

    /**
     * Database username
     */
    private String name;

    /**
     * Database password
     */
    private String password;


    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Bean definition of {@link DataSource}
     *
     * @return {@link DataSource}
     */
    @Bean
    public DataSource dataSource() {

        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.driverClassName(driverClassName);
        builder.url(url);
        builder.username(name);
        builder.password(password);

        return builder.build();
    }
}
