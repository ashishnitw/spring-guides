package com.ashishnitw.googleapis.service;

import org.springframework.stereotype.Service;

@Service
public class GoogleDriveService {

    public String getFiles() {
        return "Files";
    }

    public String getFiles(String id) {
        return "Files with id: " + id;
    }

    public String createFile() {
        return "File created";
    }

    public String updateFile(String id) {
        return "File updated with id: " + id;
    }

    public String deleteFile(String id) {
        return "File deleted with id: " + id;
    }

    public String downloadFile(String id) {
        return "File downloaded with id: " + id;
    }

    public String uploadFile() {
        return "File uploaded";
    }
}
