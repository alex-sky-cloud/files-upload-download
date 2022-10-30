package upload.files.upload.service.read;

import upload.files.upload.service.dto.DocumentDto;

public interface DocumentReaderService {

   DocumentDto readFileForDownload(DocumentDto documentDto);
}
