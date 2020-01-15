package projectpizza1_server.demo.security.service;


public class UploadFileUtil {

    public static String getStoragePath(String fileName) {
        String path = "http://localhost:8080/files/";
        return path + fileName;
    }
}
