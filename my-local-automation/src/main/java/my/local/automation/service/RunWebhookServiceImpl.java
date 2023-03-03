package my.local.automation.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Log4j2
public class RunWebhookServiceImpl implements RunWebhookService {

    private final ExecutorService threadPool = Executors.newFixedThreadPool(2);

    @Override
    public void initDeploy() {

        threadPool.submit(this::runJob);
    }

    private void runJob() {

        List<String> logs = new LinkedList<>();

        logs.add("AUTOMATION SERVER CALL START: " + LocalDateTime.now());

        ProcessBuilder pb = new ProcessBuilder("/home/vagrant/automation-server-init-deploy.sh");

        try {

            Process process = pb.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    logs.add(line);
                }
            }

            int exitVal = process.waitFor();

            if (exitVal == 0) {
                logs.add("AUTOMATION SERVER CALL SUCCESS: " + LocalDateTime.now());
            } else {
                logs.add("AUTOMATION SERVER CALL ERROR: " + LocalDateTime.now());
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        writeLogToFile(logs);
    }

    private void writeLogToFile(List<String> logs) {

        File file = new File("/home/vagrant/as-logs");

        String lineSeparator = System.getProperty("line.separator");

        try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
            for (var s : logs) {
                br.append(s).append(lineSeparator);
                br.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
