//package com.likes.common.config;
//
//import com.alibaba.druid.support.http.StatViewServlet;
//import com.alibaba.druid.support.http.WebStatFilter;
//import org.apache.ibatis.mapping.DatabaseIdProvider;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.scripting.LanguageDriver;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.type.TypeHandler;
//import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
//import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
//import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Druid的DataResource配置类
// *
// * @author Raye
// * @since 2016年10月7日14:14:18
// */
//@Configuration
//@EnableTransactionManagement
//public class DataBaseConfiguration extends MybatisAutoConfiguration {
//
//    @Value("${spring.datasource.type}")
//    private Class<? extends DataSource> dataSourceType;
//
//    public DataBaseConfiguration(MybatisProperties properties, ObjectProvider<Interceptor[]> interceptorsProvider, ObjectProvider<TypeHandler[]> typeHandlersProvider, ObjectProvider<LanguageDriver[]> languageDriversProvider, ResourceLoader resourceLoader, ObjectProvider<DatabaseIdProvider> databaseIdProvider, ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
//        super(properties, interceptorsProvider, typeHandlersProvider, languageDriversProvider, resourceLoader, databaseIdProvider, configurationCustomizersProvider);
//    }
//
//
//    @Bean(name = "masterDataSource")
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource.master")
//    public DataSource masterDataSource() {
//        return DataSourceBuilder.create().type(dataSourceType).build();
//    }
//
//    @Bean(name = "slaveDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.slave")
//    public DataSource slaveDataSource() {
//        return DataSourceBuilder.create().type(dataSourceType).build();
//    }
//
//    @Bean
//    @Override
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        return super.sqlSessionFactory(dataSource());
//    }
//
//
//    @Bean(name = "dataSource")
//    public AbstractRoutingDataSource dataSource() {
//        MasterSlaveRoutingDataSource proxy = new MasterSlaveRoutingDataSource();
//        Map<Object, Object> targetDataResources = new HashMap<>();
//        targetDataResources.put(DbContextHolder.DbType.MASTER, masterDataSource());
//        targetDataResources.put(DbContextHolder.DbType.SLAVE, slaveDataSource());
//        proxy.setDefaultTargetDataSource(masterDataSource());
//        proxy.setTargetDataSources(targetDataResources);
//        proxy.afterPropertiesSet();
//        return proxy;
//    }
//
//
//    @Bean
//    public ServletRegistrationBean statViewServlet() {
//        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
//        Map<String, String> initParams = new HashMap<>();
//        initParams.put("loginUsername", "admin"); //后台管理界面的登录账号
//        initParams.put("loginPassword", "123456"); //后台管理界面的登录密码
//        initParams.put("enabled", "true");
//        //后台允许谁可以访问
//        //initParams.put("allow", "localhost")：表示只有本机可以访问
//        initParams.put("allow", "");
//        //设置初始化参数
//        bean.setInitParameters(initParams);
//        return bean;
//    }
//
//
//    /**
//     * 向容器中添加 WebStatFilter
//     * 开启内置监控中的 Web-jdbc 关联监控的数据
//     *
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean druidWebStatFilter() {
//        WebStatFilter webStatFilter = new WebStatFilter();
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(webStatFilter);
//        // 监控所有的访问
//        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
//        // 监控访问不包括以下路径
//        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//        filterRegistrationBean.addInitParameter("connection-properties", "druid.stat.mergeSql=true;druid.stat.slowSqlMillis=1500");
//        return filterRegistrationBean;
//    }
//
//}
