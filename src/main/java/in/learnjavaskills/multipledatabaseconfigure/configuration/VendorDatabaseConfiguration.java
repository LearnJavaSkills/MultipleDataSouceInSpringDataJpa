package in.learnjavaskills.multipledatabaseconfigure.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import in.learnjavaskills.multipledatabaseconfigure.vendor.entity.Vendor;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "vendorEntityManagerFactory", transactionManagerRef = "vendorTransactionManager",
 basePackages = {"in.learnjavaskills.multipledatabaseconfigure.vendor.repository"})
public class VendorDatabaseConfiguration 
{
	@Bean(name = "vendorDataSourceProperties")
	@ConfigurationProperties("spring.datasource-vendor")
	public DataSourceProperties vendorDataSourceProperties()
	{
		return new DataSourceProperties();
	}
	
	@Bean(name = "vendorDataSource")
	public DataSource vendorDataSource()
	{
		return vendorDataSourceProperties()
				.initializeDataSourceBuilder()
				.type(HikariDataSource.class)
				.build();
	}
	
	@Bean(name = "vendorEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean vendorEntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder)
	{
		return entityManagerFactoryBuilder.dataSource(vendorDataSource())
			.packages(Vendor.class)
			.build();

	}
	
	@Bean(name = "vendorTransactionManager")
	public PlatformTransactionManager vendorTransactionManager(@Qualifier("vendorEntityManagerFactory") EntityManagerFactory vendorEntityManagerFactory)
	{
		return new JpaTransactionManager(vendorEntityManagerFactory);
	}
}
