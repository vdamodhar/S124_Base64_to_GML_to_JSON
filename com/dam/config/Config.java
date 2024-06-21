package com.dam.config;

import java.util.logging.Logger;

public class Config {

    Logger logger = Logger.getLogger(getClass().getName());
    
    String folder = "D:\\NC_S100\\Sample-datasets\\S124\\";    
    String fileName = "S124_SECOM.txt";
    String filePrefix = "sample_";

    public String getFolder() {
        return folder;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePrefix() {
        return filePrefix;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFilePrefix(String filePrefix) {
        this.filePrefix = filePrefix;
    }

}
