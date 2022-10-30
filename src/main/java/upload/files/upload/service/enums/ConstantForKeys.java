package upload.files.upload.service.enums;

public enum ConstantForKeys {

    UPLOAD_SINGLE_KEY("UP-S");


    private final String field;

    ConstantForKeys(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
