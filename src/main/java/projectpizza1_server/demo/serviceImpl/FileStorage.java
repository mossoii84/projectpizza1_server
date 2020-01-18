package projectpizza1_server.demo.serviceImpl;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorage {
    private final Path rootDir = Paths.get("uploadFile"); //это путь к папке сохранения на сервере

    public Path store(MultipartFile file) {// стор - это метод сохранения
        try {
            Files.copy(file.getInputStream(), rootDir.resolve(file.getOriginalFilename()));

        } catch (IOException e){throw new RuntimeException("MultipartFile stream exception"); }
        return rootDir.resolve(file.getOriginalFilename());
    }


    // Path- путь, resolve - это метод в Spring(принимает строку, выдает путь к файлу)
    //Resource - обькт из спринга, содержит в себе ссылку на конкретный фаил из файловой папки
    public Resource getFile(String filename) {  // это выгрузить(получить фаил)
        Path file = rootDir.resolve(filename);//rootLocation - папка Upload Image где хроним фото
        try {
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Resource is not exist or readable");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Resource creation ex. Msg: " + e.getMessage());
        }

    }

    public void deleteAll() {
        try {
            FileSystemUtils.deleteRecursively(rootDir);
        } catch (IOException e) {
            throw new RuntimeException("Deletion files ex");
        }
    }

    public void init() {
        try {
            Files.createDirectory(rootDir);
        } catch (IOException e) {
            throw new RuntimeException("Dir creation ex");
        }
    }
}