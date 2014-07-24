package com.inet.cloud.service.horae.statistic.db.mongo

import com.gmongo.GMongoClient
import groovy.transform.CompileStatic
import org.springframework.retry.RetryCallback
import org.springframework.retry.RetryContext
import org.springframework.retry.policy.SimpleRetryPolicy
import org.springframework.retry.support.RetryTemplate

/**
 * MongoService
 *
 * @author Dzung Nguyen
 * @version $Id MongoService 2014-07-25 00:15:30z dungvnguyen $
 * @since 1.0
 */
class MongoService {
  //~ class properties ========================================================
  private final MongoConnectionFactory connectionFactory
  private final int maxAttempts

  //~ class members ===========================================================
  MongoService(MongoConnectionFactory connectionFactory, int maxAttempts = 3) {
    this.connectionFactory = connectionFactory
    this.maxAttempts = maxAttempts
  }

  @CompileStatic
  def withDb(String name, Closure closure) {
    GMongoClient connection = makeConnection()
    try {
      closure(connection.getDB(name))
    } finally {
      // connection.close()
    }
  }

  @CompileStatic
  def withMongo(Closure closure) {
    GMongoClient connection = makeConnection()
    try {
      closure(connection)
    } finally {
      // connection.close()
    }
  }

  // make the connection.
  private GMongoClient makeConnection() {
    Map<Class<? extends Throwable>, Boolean> retryableExceptions = [:]
    retryableExceptions[NullPointerException.class] = Boolean.TRUE
    SimpleRetryPolicy policy = new SimpleRetryPolicy(maxAttempts, retryableExceptions)

    // create retry template.
    RetryTemplate retryTemplate = new RetryTemplate()
    retryTemplate.setRetryPolicy(policy)

    // make the connection.
    retryTemplate.execute(new RetryCallback<GMongoClient, NullPointerException>() {
      @Override
      GMongoClient doWithRetry(RetryContext context) throws NullPointerException {
        def connection = connectionFactory.makeConnection()
        if (connection == null) throw new NullPointerException()
        connection
      }
    })
  }
}
