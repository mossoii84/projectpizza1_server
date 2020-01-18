package projectpizza1_server.demo.security.service;



//это для Вызагрузки фото(image), чтобы сделать например Get allImage
public class UploadFileUtil {

    public static String getStoragePath(String fileName) {
        String path = "http://localhost:8080/api/files/";
        return path + fileName;
    }
}
