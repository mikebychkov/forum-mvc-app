package my.local.automation.controller;

import lombok.extern.log4j.Log4j2;
import my.local.automation.service.RunWebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
@Log4j2
public class WebhookController {

    @Autowired
    private RunWebhookService runWebhookService;

    @GetMapping("/push-event")
    public ResponseEntity<String> getPushEventByGetRequest() {

        log.info("GET: GIT PUSH EVENT ({})", LocalDateTime.now());

        runWebhookService.initDeploy();

        return ResponseEntity.ok().build();
    }

    @PostMapping("/push-event")
    public ResponseEntity<String> getPushEventByPostRequest() {

        log.info("POST: GIT PUSH EVENT ({})", LocalDateTime.now());

        runWebhookService.initDeploy();

        return ResponseEntity.ok().build();
    }
}
