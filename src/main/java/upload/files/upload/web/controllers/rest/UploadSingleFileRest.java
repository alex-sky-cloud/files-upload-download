package upload.files.upload.web.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import upload.files.upload.service.save.DocumentSaveService;

@RequestMapping
@RestController
public class UploadSingleFileRest {

    private DocumentSaveService documentSaveService;

    @Autowired
    public UploadSingleFileRest(DocumentSaveService documentSaveService) {
        this.documentSaveService = documentSaveService;
    }

    @PostMapping("/upload/db")
    public ResponseEntity uploadToDB(@RequestParam("file") MultipartFile file) {

        String fileDownloadUri = documentSaveService.saveUploadedFile(file);

        return ResponseEntity.ok(fileDownloadUri);
    }
}
