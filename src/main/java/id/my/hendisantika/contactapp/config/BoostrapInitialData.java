package id.my.hendisantika.contactapp.config;

import com.github.javafaker.Faker;
import id.my.hendisantika.contactapp.entity.Contact;
import id.my.hendisantika.contactapp.entity.Status;
import id.my.hendisantika.contactapp.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Random;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : contact-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/20/24
 * Time: 13:22
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BoostrapInitialData implements CommandLineRunner {
    private final ContactRepository contactRepository;
    private final Faker faker = new Faker(Locale.getDefault());

    @Override
    public void run(String... args) {
        for (int i = 0; i < 50; i++) {
            log.info("Add new Data ....");
            contactRepository.save(new Contact(UUID.randomUUID().toString(), faker.name().fullName(), faker.internet().emailAddress(),
                    faker.name().title(), faker.phoneNumber().cellPhone(), faker.address().fullAddress(), Status.values()[new Random().nextInt(Status.values().length)], faker.avatar().image()));
        }
    }

}
