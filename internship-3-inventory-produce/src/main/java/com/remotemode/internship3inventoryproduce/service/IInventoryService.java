package com.remotemode.internship3inventoryproduce.service;

import org.springframework.web.multipart.MultipartFile;

public interface IInventoryService {
    void parseAndSend(MultipartFile file);
}
