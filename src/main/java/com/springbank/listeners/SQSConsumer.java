package com.springbank.listeners;

import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SQSConsumer {

	@SqsListener(value = { "${aws.services.sqs.queue-url}" })
	public void onMessage(final String message) {
		log.info("message received is {}.", message);
	}

}
