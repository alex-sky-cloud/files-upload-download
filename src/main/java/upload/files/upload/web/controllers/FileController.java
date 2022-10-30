package upload.files.upload.web.controllers;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import upload.files.upload.dao.storage.repository.StorageService;
import upload.files.upload.service.dto.FileResponseDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FileController {

    private StorageService storageService;

    public FileController(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * получение списка всех файлов, которые можно загрузить из сервера
     * storageService.loadAll() - получает список всех путей, к располжениям
     * файлов в хранилище, нашего сервера.
     * @param model
     * @return возрвращаем имя модели страницы, на которую Spring поместит полученный
     * список
     */
    @GetMapping("/")
    public String listAllFiles(Model model) {

        model.addAttribute("files", storageService.loadAll().map(
                path -> ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/download/")
                        .path(path.getFileName().toString())
                        .toUriString())
                .collect(Collectors.toList()));

        return "listFiles";
    }

    /**
     * Получение списка файла по имени
     * @param filename
     * @return
     */
    @GetMapping("/download/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {

        Resource resource = storageService.loadAsResource(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    /**
     * загрузка одного файла
     * @param file
     * @return
     */
    @PostMapping("/upload-file")
    @ResponseBody
    public FileResponseDto uploadFile(@RequestParam("file") MultipartFile file) {

        String name = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(name)
                .toUriString();

        return new FileResponseDto(name, uri, file.getContentType(), file.getSize());
    }

    /**
     * загрузка нескольких файлов
     * @param files
     * @return
     */
    @PostMapping("/upload-multiple-files")
    @ResponseBody
    public List<FileResponseDto> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        List<FileResponseDto> collect = Arrays.stream(files)
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());

        return collect;
    }
}
