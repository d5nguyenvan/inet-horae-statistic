package com.inet.cloud.service.horae.statistic.config

import com.inet.cloud.service.horae.statistic.db.mongo.DefaultMongoConnectionFactory
import com.inet.cloud.service.horae.statistic.db.mongo.MongoConnectionFactory
import com.inet.cloud.service.horae.statistic.db.mongo.MongoService
import com.mongodb.ServerAddress
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


/**
 * DbConfig
 *
 * @author Dzung Nguyen
 * @version $Id DbConfig 2014-07-25 02:23:30z dungvnguyen $
 * @since 1.0
 */
@Configuration
class DbConfig {
  //~ class members ===========================================================
  @Bean
  @Autowired
  MongoService mongoService(MongoConnectionFactory mongoConnectionFactory) {
    new MongoService(mongoConnectionFactory)
  }

  @Bean(destroyMethod = 'destroy')
  MongoConnectionFactory mongoConnectionFactory() {
    // create connection factory.
    MongoConnectionFactory connectionFactory = new DefaultMongoConnectionFactory(
        serverAddresses: [new ServerAddress('10.201.0.43', 27017)]
    )

    // init the connection.
    connectionFactory.afterPropertiesSet()

    // return the connection factory.
    connectionFactory
  }

  /**
   * @return the exception list.
   */
  @Bean(name='exceptList')
  List<String> exceptList() {
    [
        '00.96.H29', '00.95.H29', '00.98.H29', '00.99.H29', '00.97.H29', '00.00.W29',
        '08.98.H29', '01.98.H29', '02.98.H29', '03.98.H29', '04.98.H29', '05.98.H29',
        '99.98.H29', '07.98.H29'
    ]
  }
}
