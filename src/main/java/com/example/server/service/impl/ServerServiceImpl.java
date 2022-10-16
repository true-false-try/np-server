package com.example.server.service.impl;


import com.example.server.data.Document;
import com.example.server.repository.TemplateRepository;
import com.example.server.service.ServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ServerServiceImpl implements ServerService {
    private final RestTemplate restTemplate;
    private final Document document;
    @Autowired
    public ServerServiceImpl(@Nullable Document document, RestTemplate restTemplate) {
        this.document = document;
        this.restTemplate = restTemplate;
    }

    @Override
    public void save(Document document) {
        TemplateRepository.documents.add(document);
    }

    @Override
    public String getData() {
        return TemplateRepository.documents.toString();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(60000);
            restTemplate.postForObject(document.getCallbackUrl(),
                    Document.builder()
                            .number(document.getNumber())
                            .type(document.getType())
                            .callbackUrl(document.getCallbackUrl())
                            .build(),
                    String.class);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        log.info("Finished");
    }

}
