package com.inet.cloud.service.horae.statistic.db.mongo

import com.gmongo.GMongoClient
import com.mongodb.MongoClientOptions
import com.mongodb.ReadPreference
import com.mongodb.ServerAddress
import com.mongodb.WriteConcern
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean

/**
 * MongoConnectionFactory
 *
 * @author Dzung Nguyen
 * @version $Id MongoConnectionFactory 2014-07-25 00:10:30z dungvnguyen $
 * @since 1.0
 */
@CompileStatic
interface MongoConnectionFactory {
  /**
   * @return the connection to MongoDB.
   */
  GMongoClient makeConnection()
}

/**
 * DefaultMongoConnectionFactory
 *
 * @author: Dzung Nguyen
 * @version: $Id DefaultMongoConnectionFactory 2014-07-25 00:30:30z nguyen_dv $
 *
 * @since 1.0
 */
@Slf4j('LOG')
class DefaultMongoConnectionFactory implements MongoConnectionFactory, InitializingBean, DisposableBean {
  //~ class properties ========================================================
  private MongoClientOptions clientOptions
  int connectionsPerHost = 10
  int threadsAllowedToBlockForConnectionMultiplier = 10
  int maxWaitTime = 15000
  int connectTimeout = 10000
  int socketTimeout = 60000
  boolean autoConnectRetry = false
  int maxAutoConnectRetryTime = 60000
  ReadPreference readPreference = ReadPreference.secondary()
  WriteConcern writeConcern = WriteConcern.ACKNOWLEDGED
  List<ServerAddress> serverAddresses
  private GMongoClient client = null

  //~ class members ===========================================================
  @Override
  GMongoClient makeConnection() {
    if (client == null) {
      client = new GMongoClient(serverAddresses.size() == 1 ? serverAddresses[0] : serverAddresses, clientOptions)
    }

    // return the client.
    client
  }

  @CompileStatic
  @Override
  void destroy() throws Exception {
    serverAddresses.clear()
    serverAddresses = null

    // dispose the client option.
    clientOptions = null

    // close the client.
    if (client != null) client.close()
    client = null
  }

  @CompileStatic
  @Override
  void afterPropertiesSet() throws Exception {
    // create client options.
    clientOptions = MongoClientOptions.Builder.newInstance()
        .connectionsPerHost(connectionsPerHost)
        .threadsAllowedToBlockForConnectionMultiplier(threadsAllowedToBlockForConnectionMultiplier)
        .maxWaitTime(maxWaitTime)
        .connectTimeout(connectTimeout)
        .socketTimeout(socketTimeout)
        .autoConnectRetry(autoConnectRetry)
        .maxAutoConnectRetryTime(maxAutoConnectRetryTime)
        .readPreference(readPreference)
        .writeConcern(writeConcern)
        .build()

    if (LOG.isInfoEnabled()) {
      LOG.info("Client connection options: ${clientOptions}...")
    }

    // the mongo hosts.
    if (serverAddresses == null) {
      serverAddresses = [new ServerAddress()]
    }

    if (LOG.isInfoEnabled()) {
      LOG.info("List of server address ${serverAddresses}")
    }
  }
}