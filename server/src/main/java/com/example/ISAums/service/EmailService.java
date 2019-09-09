package com.example.ISAums.service;

import com.example.ISAums.model.User;
import com.example.ISAums.model.VerificationToken;
import com.example.ISAums.repository.VerificationTokenRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class EmailService {
    private final VerificationTokenRepository verificationTokenRepository;
    private final JavaMailSenderImpl mailSender;
    private final TemplateEngine templateEngine;

    public EmailService(VerificationTokenRepository verificationTokenRepository, JavaMailSenderImpl mailSender, TemplateEngine templateEngine) {
        this.verificationTokenRepository = verificationTokenRepository;
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Transactional(rollbackFor = Exception.class)
    public void sendConfirmation(String appUrl, User user)  throws MessagingException, IOException {
        String token = UUID.randomUUID().toString();
        VerificationToken regToken = new VerificationToken(user, token);
        verificationTokenRepository.save(regToken);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

        String subject = "Complete your account registration";
        String confirmationUrl = "http://localhost:8080" + appUrl + "/confirm?token=" + token;

        Context context = new Context();
        context.setVariable("firstName", user.getFirstName());
        context.setVariable("lastName", user.getLastName());
        context.setVariable("confirmationUrl", confirmationUrl);
        context.setVariable("imgHotel", "imgHotel");
        context.setVariable("imgAirplane", "imgAirplane");
        context.setVariable("imgCar", "imgCar");
        context.setVariable("imgFb", "imgFb");
        context.setVariable("imgIg", "imgIg");
        context.setVariable("imgTw", "imgTw");
        context.setVariable("imgYt", "imgYt");
        context.setVariable("imgLogo", "imgLogo");

        String html = templateEngine.process("email", context);

        helper.setSubject(subject);
        helper.setFrom(mailSender.getUsername());
        helper.setTo(mailSender.getUsername());
        helper.setText(html,true);
        loadImages(helper, user);

        mailSender.send(message);
    }

    private void loadImages(MimeMessageHelper helper, User user) throws MessagingException, IOException {
        InputStreamSource imgLogo = new ByteArrayResource(IOUtils.toByteArray(getClass().getResourceAsStream("/templates/images/umslogo.png")));
        InputStreamSource imgHotel = new ByteArrayResource(IOUtils.toByteArray(getClass().getResourceAsStream("/templates/images/hotel.jpg")));
        InputStreamSource imgAirplane = new ByteArrayResource(IOUtils.toByteArray(getClass().getResourceAsStream("/templates/images/airplane.png")));
        InputStreamSource imgCar = new ByteArrayResource(IOUtils.toByteArray(getClass().getResourceAsStream("/templates/images/car.png")));
        InputStreamSource imgFb = new ByteArrayResource(IOUtils.toByteArray(getClass().getResourceAsStream("/templates/images/facebook.png")));
        InputStreamSource imgIg = new ByteArrayResource(IOUtils.toByteArray(getClass().getResourceAsStream("/templates/images/instagram.png")));
        InputStreamSource imgTw = new ByteArrayResource(IOUtils.toByteArray(getClass().getResourceAsStream("/templates/images/twitter.png")));
        InputStreamSource imgYt = new ByteArrayResource(IOUtils.toByteArray(getClass().getResourceAsStream("/templates/images/youtube.png")));

        helper.addInline("imgLogo", imgLogo,"image/png");
        helper.addInline("imgHotel", imgHotel,"image/png");
        helper.addInline("imgAirplane", imgAirplane,"image/png");
        helper.addInline("imgCar", imgCar,"image/png");
        helper.addInline("imgFb", imgFb,"image/png");
        helper.addInline("imgIg", imgIg,"image/png");
        helper.addInline("imgTw", imgTw,"image/png");
        helper.addInline("imgYt", imgYt,"image/png");
    }
}
