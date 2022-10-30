package upload.files.upload.service.mapper;

import org.mapstruct.Mapper;
import upload.files.upload.dao.domain.Document;
import upload.files.upload.service.dto.DocumentDto;

@Mapper(componentModel = "spring")
public interface DocumentMapper extends CommonMapper<DocumentDto, Document> {
}
