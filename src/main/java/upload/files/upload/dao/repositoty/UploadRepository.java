package upload.files.upload.dao.repositoty;

public interface UploadRepository <T> {

    void save(String key, T entity);

    T find(String key, String id);
}
