package upload.files.upload.dao.repositoty.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import upload.files.upload.dao.domain.Document;
import upload.files.upload.dao.repositoty.UploadRepositoryDocument;

import javax.annotation.PostConstruct;


@Repository
@Transactional
public class UploadRepositoryImpl implements UploadRepositoryDocument {

    private HashOperations<String, String, Document> hashOperations;

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public UploadRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(String key, Document entity) {

        hashOperations.put(key, entity.getId(), entity);
    }

    @Override
    public Document find(String key, String id) {
        return hashOperations.get(key, id);
    }
}
