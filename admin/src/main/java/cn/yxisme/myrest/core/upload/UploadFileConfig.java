package cn.yxisme.myrest.core.upload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangxiong on 2019/3/11.
 */
@Configuration
public class UploadFileConfig {

    @Value("${file.upload.folder}")
    private String uploadFolder;

    @Value("${file.upload.maxSize}")
    private int uploadMaxSize;

    public String getUploadFolder() {
        return uploadFolder;
    }

    public int getUploadMaxSize() {
        return uploadMaxSize;
    }
}
