package com.ysm.demo.config.mybatis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class MybatisConfiguration {

  @Autowired ApplicationContext applicationContext;

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.hikari")
  public HikariConfig hikariConfig(){
    return new HikariConfig();
  }

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource dataSource(){
    DataSource dataSource = new HikariDataSource(hikariConfig());
    return dataSource;
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
    ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
    Resource[] resources = resourcePatternResolver.getResources("classpath:/mappers/**/*.xml");

    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setMapperLocations(resources);
    return sqlSessionFactoryBean.getObject();
  }

  @Bean
  public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
    return new SqlSessionTemplate(sqlSessionFactory);
  }

  /*
  @Bean
  public EntityManagerFactory entityManagerFactory(){
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(dataSource());
    entityManagerFactoryBean.setPersistenceUnitName("persistence-unit");
    entityManagerFactoryBean.setPersistenceXmlLocation("classpath:/META-INF/persistence.xml");
    entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    entityManagerFactoryBean.afterPropertiesSet();

    return entityManagerFactoryBean.getObject();
  }

  @Bean
  public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
    return new JpaTransactionManager(entityManagerFactory);
  }
*/
}
