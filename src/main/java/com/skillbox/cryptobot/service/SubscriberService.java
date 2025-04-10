package com.skillbox.cryptobot.service;

import com.skillbox.cryptobot.entity.Subscriber;
import com.skillbox.cryptobot.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Service
@Slf4j
public class SubscriberService {

    private final SubscriberRepository subscriberRepository;

    @Autowired
    public SubscriberService(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    public Subscriber createNewSubscriber(Long id, Double price) {
        Optional<Subscriber> existingSubscriber = Optional.ofNullable(subscriberRepository.findById(id));
        if (existingSubscriber.isPresent()){
            return existingSubscriber.get();
        }

        Subscriber subscriber = new Subscriber();
        subscriber.setId(id);
        subscriber.setPrice(price);
        log.info("Создан новый пользователь: {}", subscriber);
        return subscriberRepository.save(subscriber);
    }
}