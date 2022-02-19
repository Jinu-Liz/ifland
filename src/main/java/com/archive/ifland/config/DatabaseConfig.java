package com.archive.ifland.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(value="com.archive.ifland.dao", sqlSessionTemplateRef = "iflandSessionTemplate")
@RequiredArgsConstructor
public class DatabaseConfig {

  private final MybatisProperties mybatisProperties;

  @Bean(name = "hikariCP")
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource.ifland")
  public HikariConfig hikariConfig() { return new HikariConfig();}

  @Bean(name = "iflandDataSource")
  public DataSource iflandDataSource() {
    return new HikariDataSource(hikariConfig());
  }

  @Bean(name = "iflandSessionFactory")
  @Primary
  public SqlSessionFactory iflandSessionFactory(@Qualifier("iflandDataSource") DataSource dataSource) throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(mybatisProperties.getConfigLocation()));
    sqlSessionFactoryBean.setMapperLocations(mybatisProperties.resolveMapperLocations());

    return sqlSessionFactoryBean.getObject();
  }

  @Bean(name = "transactionManager")
  @Primary
  public DataSourceTransactionManager iflandTransactionManager(@Qualifier("iflandDataSource") DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean(name = "iflandSessionTemplate")
  @Primary
  public SqlSessionTemplate iflandSessionTemplate(@Qualifier("iflandSessionFactory") SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }

//  @Bean(name = "transactionManager")
//  public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//    return new JpaTransactionManager(entityManagerFactory);
//  }
}
