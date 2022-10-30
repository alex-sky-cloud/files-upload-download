package upload.files.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import upload.files.upload.dao.storage.config.StorageProperties;

@EnableConfigurationProperties(StorageProperties.class)
@SpringBootApplication
public class FilesUploadDownloadApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilesUploadDownloadApplication.class, args);
    }

}
