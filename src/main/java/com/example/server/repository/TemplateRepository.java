package com.example.server.repository;

import com.example.server.data.Document;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TemplateRepository {
    public List<Document> documents = new ArrayList<>();
}
