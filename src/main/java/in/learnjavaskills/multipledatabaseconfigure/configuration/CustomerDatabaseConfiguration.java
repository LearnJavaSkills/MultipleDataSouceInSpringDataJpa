package in.learnjavaskills.multipledatabaseconfigure.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import in.learnjavaskills.multipledatabaseconfigure.customer.entity.Customer;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "customerEntityManagerFactory", transactionManagerRef = "customerTransactionManager", 
		basePackages = {"in.learnjavaskills.multipledatabaseconfigure.customer.repository"})
public class CustomerDatabaseConfiguration
{
	@Primary
	@Bean(name = "customerDataSourceProperties")
	@ConfigurationProperties("spring.datasource-customer")
	public DataSourceProperties customerDataSourceProperties()
	{
		return new DataSourceProperties();
	}
	
	@Primary
	@Bean(name = "customerDataSource")
	public DataSource customerDataSource()
	{
		return customerDataSourceProperties()
				.initializeDataSourceBuilder()
				.type(HikariDataSource.class)
				.build();
	}
	
	@Primary
	@Bean(name = "customerEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean customerEntityManager(EntityManagerFactoryBuilder entityManagerFactoryBuilder)
	{
		return entityManagerFactoryBuilder.dataSource(customerDataSource())
			.packages(Customer.class)
			.build();
	}
	
	@Primary
	@Bean(name = "customerTransactionManager")
	public PlatformTransactionManager customerTransactionManager(@Qualifier("customerEntityManagerFactory") EntityManagerFactory customerEntityManagerFactory)
	{
		return new JpaTransactionManager(customerEntityManagerFactory);
	}
}
