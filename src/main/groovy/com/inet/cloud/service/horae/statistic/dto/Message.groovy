package com.inet.cloud.service.horae.statistic.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.CompileStatic

/**
 * Message
 *
 * @author Dzung Nguyen
 * @version $Id Message 2014-07-25 04:07:30z dungvnguyen $
 * @since 1.0
 */
@CompileStatic
@JsonInclude(JsonInclude.Include.NON_NULL)
abstract class Message {
  //~ class properties ========================================================
  @JsonProperty('request_id')
  String requestId
}

@CompileStatic
@JsonInclude(JsonInclude.Include.NON_NULL)
class OkMessage<T> extends Message {
  String action
  T result
}

@CompileStatic
@JsonInclude(JsonInclude.Include.NON_NULL)
class Messages<T> extends Message {
  //~ class properties ========================================================
  @JsonProperty('items')
  List<T> items
}

/**
 * ErrorMessage.
 *
 * @author Dzung Nguyen
 * @version $Id Message 2014-07-25 04:07:30z dungvnguyen $
 *
 * @since 1.0
 */
@CompileStatic
@JsonInclude(JsonInclude.Include.NON_NULL)
class ErrorMessage extends Message {
  //~ class properties ========================================================
  @JsonProperty('code')
  String code

  @JsonProperty('message')
  String message

  @JsonProperty('resource')
  String resource

  //~ class members ===========================================================
  /**
   * Creates {@link ErrorMessage error message} from the code, message, resource
   * and request identifier.
   *
   * @param code the given error code.
   * @param message the given error message.
   * @param resource the given resource.
   * @param requestId the request identifier.
   */
  ErrorMessage(String code = null, String message = null, String resource = null, String requestId = null) {
    this.code = code
    this.message = message
    this.resource = resource
    this.requestId = requestId
  }
}

