package com.boleks.jobfair.controllers;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


@Named(value = "fileUploadBean")
@RequestScoped
public class FileUpload implements Serializable {

    private Part uploadedFile;
    private final String folder = "d:\\!!!Java\\NetBeans\\JobFair\\web\\resources\\slike\\";
    private final String folderPDF = "d:\\!!!Java\\NetBeans\\JobFair\\web\\resources\\pdf\\";

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public boolean saveFile(Part uploadedFile, String fileName) {
        String relativePath = "/resources/slike/";
        String absolutePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(relativePath);

        try (InputStream input = uploadedFile.getInputStream()) {
            
            Files.copy(input, new File(folder, fileName).toPath(), REPLACE_EXISTING);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveFilePDF(Part uploadedFile, String fileName) {
        String relativePath = "/resources/pdf/";
        String absolutePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(relativePath);

        try (InputStream input = uploadedFile.getInputStream()) {

            Files.copy(input, new File(folderPDF, fileName).toPath(), REPLACE_EXISTING);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public FileUpload() {
    }

}
