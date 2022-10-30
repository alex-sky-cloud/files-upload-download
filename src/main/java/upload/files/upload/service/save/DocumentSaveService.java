package upload.files.upload.service.save;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentSaveService {

    /**
     * после выгрузки файла на сервер и сохранения его в базу, формируется адрес,
     * по которому можно выгрузить загружаемый файл
     * @param file
     * @return
     */
    String saveUploadedFile(MultipartFile file);
}
