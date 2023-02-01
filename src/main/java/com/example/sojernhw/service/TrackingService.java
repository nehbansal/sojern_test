package com.example.sojernhw.service;

import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class TrackingService {
    public boolean fileExist() {
        URL url = TrackingService.class.getResource("/tmp/Ok.txt");
        return url!=null;
    }
}
