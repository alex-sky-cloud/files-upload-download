package upload.files.upload.web.controllers.filesystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import upload.files.upload.dao.exceptions.StorageException;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("files")
@RestController
public class UploadToFileSystemRest {

    Logger LOGGER = LoggerFactory.getLogger(UploadToFileSystemRest.class);

    @Value("${root.Location.Upload}")
    String rootLocationUpload;


    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(Paths.get(rootLocationUpload));
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage location", e);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity uploadToLocalFileSystem(@RequestParam("file") MultipartFile file) {


        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        String fileBasePath = rootLocationUpload;

        Path path = Paths.get(fileBasePath + fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(fileName)
                .toUriString();
        return ResponseEntity.ok(fileDownloadUri);
    }


    @PostMapping("/multi-upload")
    public ResponseEntity multiUpload(@RequestParam("files") MultipartFile[] files) {

        List<Object> fileDownloadUrls = new ArrayList<>();

        Arrays.asList(files)
                .stream()
                .forEach(file -> fileDownloadUrls
                        .add(
                                uploadToLocalFileSystem(file).getBody()
                        ));

        return ResponseEntity.ok(fileDownloadUrls);
    }


    @PostMapping("/upload-extra-param")
    public ResponseEntity uploadWithExtraParams(@RequestParam("file") MultipartFile file,
                                                @RequestParam String extraParam) {
        LOGGER.info("Extra param " + extraParam);

        return uploadToLocalFileSystem(file);
    }
}
