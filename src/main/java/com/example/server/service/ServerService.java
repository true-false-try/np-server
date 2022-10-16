package com.example.server.service;

import com.example.server.data.Document;

import java.util.List;

public interface ServerService extends Runnable{
    void save(Document document);
    String getData();

}
