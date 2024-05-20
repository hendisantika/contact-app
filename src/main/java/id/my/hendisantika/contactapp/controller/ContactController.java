package id.my.hendisantika.contactapp.controller;

import id.my.hendisantika.contactapp.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : contact-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/20/24
 * Time: 11:55
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;
}
