package com.test.test_soletanche.model;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;


/*
 * Improve Idea
 * Modification Date
 * Creation Date
 */

public class FilesDetail {

    private int id;
    private static int count = 0;
    private String fileName;
    private String contentType;
    private Boolean canRead;
    private Boolean canWrite;
    private Boolean isDirectory;
    private long size;
    private URI fileUri;
    private Path path;
    private MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();

    public FilesDetail() {
    }

    public FilesDetail(File file) {
        this.id = count++;
        this.contentType = mimeTypesMap.getContentType(file.getName());
        this.fileName = file.getName();
        this.canRead = file.canRead();
        this.canWrite = file.canWrite();
        this.isDirectory = file.isDirectory();
        this.size = file.length();
        this.fileUri = file.toURI();
        this.path = Paths.get(file.getAbsolutePath());
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Boolean getCanRead() {
        return canRead;
    }

    public void setCanRead(Boolean canRead) {
        this.canRead = canRead;
    }

    public Boolean getCanWrite() {
        return canWrite;
    }

    public void setCanWrite(Boolean canWrite) {
        this.canWrite = canWrite;
    }

    public Boolean getDirectory() {
        return isDirectory;
    }

    public void setDirectory(Boolean directory) {
        isDirectory = directory;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public URI getFileUri() {
        return fileUri;
    }

    public void setFileUri(URI fileUri) {
        this.fileUri = fileUri;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "FilesDetail{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", contentType='" + contentType + '\'' +
                ", canRead=" + canRead +
                ", canWrite=" + canWrite +
                ", isDirectory=" + isDirectory +
                ", size=" + size +
                ", fileUri=" + fileUri +
                ", path=" + path +
                '}';
    }
}