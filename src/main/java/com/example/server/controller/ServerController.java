package com.example.server.controller;


import com.example.server.data.Document;
import com.example.server.service.ServerService;
import com.example.server.service.impl.ServerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ServerController {
    private final ServerService service;
    private final RestTemplate restTemplate;

    @PostMapping("document/create")
    public ResponseEntity<String> getCreateDocument(@RequestBody Document document) {
        log.info(String.format("[%s]: start method getCreateDocumentServer()", ServerController.class.getName()));
        new Thread(new ServerServiceImpl(document, restTemplate)).start();
        service.save(document);
        log.info("HTTP Response Code ".concat(HttpStatus.ACCEPTED.toString()));
        return ResponseEntity.accepted().build();
        // If you want to watch all have saved document you should use service.getData();

    }


}
