package com.lambdaschool.gdp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * LogConsumer is a Spring service set to listen for messages sent to the log queue.
 */
@Slf4j
@Service
public class LogConsumer {
  /**
   * Log messages found in the Log queue.
   *
   * @param msg A RabbitMQ message
   */
  @RabbitListener(queues = GdpApplication.QUEUE)
  public void consumeMsg(final Message msg) {
    log.info("Received Message: {}", msg.toString());
  }
}
