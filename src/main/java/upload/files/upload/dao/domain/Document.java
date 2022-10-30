package upload.files.upload.dao.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Document implements Serializable {

    private String id;

    private String key;

    private String docName;

    private String pathToFile;

    private byte[] file;

    public Document() {
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
        Document document = (Document) o;
        return Objects.equals(id, document.id) &&
                Objects.equals(key, document.key) &&
                Objects.equals(docName, document.docName) &&
                Objects.equals(pathToFile, document.pathToFile) &&
                Arrays.equals(file, document.file);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, key, docName, pathToFile);
        result = 31 * result + Arrays.hashCode(file);
        return result;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", docName='" + docName + '\'' +
                ", pathToFile='" + pathToFile + '\'' +
                ", file=" + Arrays.toString(file) +
                '}';
    }
}
