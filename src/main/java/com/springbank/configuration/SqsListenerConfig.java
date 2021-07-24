package com.springbank.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.springbank.aws.basics.configurations.AWSProperties;

@Configuration
public class SqsListenerConfig {

	@Bean
	public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(
			@Qualifier("amazonSqsAsync") final AmazonSQSAsync amazonSqsAsync) {
		final SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory = new SimpleMessageListenerContainerFactory();
		simpleMessageListenerContainerFactory.setAutoStartup(true);
		simpleMessageListenerContainerFactory.setAmazonSqs(amazonSqsAsync);
		return simpleMessageListenerContainerFactory;
	}

	@Primary
	@ConditionalOnProperty(value = "${aws.services.sqs.async-enabled}", havingValue = "true", matchIfMissing = true)
	@Bean
	public AmazonSQSAsync amazonSqsAsync(final AWSProperties awsProperties, final AWSCredentials awsCredentials) {
		return AmazonSQSAsyncClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.withRegion(awsProperties.getCredentials().getRegion()).build();
	}

}
