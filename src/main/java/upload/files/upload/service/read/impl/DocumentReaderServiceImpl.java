package upload.files.upload.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upload.files.upload.dao.domain.Document;
import upload.files.upload.dao.repositoty.UploadRepositoryDocument;
import upload.files.upload.service.dto.DocumentDto;
import upload.files.upload.service.mapper.DocumentMapper;
import upload.files.upload.service.read.DocumentReaderService;

@Service
public class DocumentReaderServiceImpl implements DocumentReaderService {

    private DocumentMapper mapper;

    private UploadRepositoryDocument repository;

    @Autowired
    public DocumentReaderServiceImpl(DocumentMapper mapper, UploadRepositoryDocument repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public DocumentDto readFileForDownload(DocumentDto documentDto) {

        String key = documentDto.getKey();
        String id = documentDto.getId();

        Document document = this.repository.find(key, id);

        DocumentDto dto = mapper.toDto(document);

        return dto;
    }
}
