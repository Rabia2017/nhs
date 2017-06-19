package com.cgi.odsc.nhs.config;

import com.cgi.odsc.nhs.patient.dao.IPatientDao;
import com.cgi.odsc.nhs.patient.dao.PatientDao;
import com.cgi.odsc.nhs.patient.domain.Patient;
import com.cgi.odsc.nhs.patient.service.IPatientService;
import com.cgi.odsc.nhs.patient.service.PatientService;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by rabia on 17/06/17.
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class DBConfig {
    @Bean
    public IPatientDao patientDao(){
        return new PatientDao();
    }

    //@Bean
    //public PatientController PatientController() {return new PatientController();}


    @Bean
   public IPatientService patientService(){
        return new PatientService();
    }

    @Bean
    public HibernateTemplate hibernateTemplate(){
        return  new HibernateTemplate(sessionFactory());
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new LocalSessionFactoryBuilder(getDataSource())
                .addAnnotatedClasses(Patient.class)
                .buildSessionFactory();
    }
    @Autowired
    private Environment env;


    @Bean
    public DataSource getDataSource(){
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(env.getProperty("database.driverClassName"));
        dataSource.setUrl(env.getProperty("database.url"));
        dataSource.setUsername(env.getProperty("database.username"));
        dataSource.setPassword(env.getProperty("database.password"));
        return dataSource;
    }
    @Bean
    public HibernateTransactionManager hibTransMan(){
        return new HibernateTransactionManager(sessionFactory());
    }
}
