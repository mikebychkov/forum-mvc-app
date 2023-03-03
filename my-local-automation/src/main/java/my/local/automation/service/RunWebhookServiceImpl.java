package my.local.automation.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class RunWebhookServiceImpl implements RunWebhookService {
    @Override
    public void initDeploy() {

        ProcessBuilder pb = new ProcessBuilder("automation-server-init-deploy.sh");
        pb.directory(new File("/home/vagrant/"));
        try {
            pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
