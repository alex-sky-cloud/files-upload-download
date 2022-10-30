package upload.files.upload.service.dto;

import java.util.Arrays;
import java.util.Objects;

public class DocumentDto {

    private String id;

    private String key;

    private String docName;

    private String pathToFile;

    private byte[] file;

    public DocumentDto() {
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentDto that = (DocumentDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(key, that.key) &&
                Objects.equals(docName, that.docName) &&
                Objects.equals(pathToFile, that.pathToFile) &&
                Arrays.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, key, docName, pathToFile);
        result = 31 * result + Arrays.hashCode(file);
        return result;
    }

    @Override
    public String toString() {
        return "DocumentDto{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", docName='" + docName + '\'' +
                ", pathToFile='" + pathToFile + '\'' +
                ", file=" + Arrays.toString(file) +
                '}';
    }
}
