package upload.files.upload.service.save.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import upload.files.upload.dao.domain.Document;
import upload.files.upload.dao.repositoty.UploadRepositoryDocument;
import upload.files.upload.service.mapper.DocumentMapper;
import upload.files.upload.service.save.DocumentSaveService;

import java.io.IOException;

import static upload.files.upload.service.enums.ConstantForKeys.UPLOAD_SINGLE_KEY;

@Service
public class DocumentSaveServiceImpl implements DocumentSaveService {

    Logger LOGGER = LoggerFactory.getLogger(DocumentSaveServiceImpl.class);

    private DocumentMapper mapper;

    private UploadRepositoryDocument repository;

    @Autowired
    public DocumentSaveServiceImpl(DocumentMapper mapper, UploadRepositoryDocument repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public String saveUploadedFile(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Document document = createDocument(file, fileName);
        document.setKey(UPLOAD_SINGLE_KEY.getField());

        String id = fileName;

        document.setId(id);

        String fileDownloadUri = getFileDownloadUri(fileName);
        document.setPathToFile(fileDownloadUri);

        repository.save(UPLOAD_SINGLE_KEY.getField(), document);

        return fileDownloadUri;
    }

    private String getFileDownloadUri(String fileName){

       return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(fileName)
                .path("/db")
                .toUriString();

    }

    private Document createDocument(MultipartFile file, String fileName)  {

        Document document = new Document();

        byte[] bytes = new byte[0];

        document.setDocName(fileName);

        try {
          bytes = file.getBytes();
        } catch (IOException e) {
            LOGGER.error("Failed get an array bytes from a file.");
        }

        document.setFile(bytes);

        return document;

    }
}
