package cn.yxisme.myrest.controller;

import cn.yxisme.myrest.common.ResultBean;
import cn.yxisme.myrest.core.upload.FileUtil;
import cn.yxisme.myrest.core.web.GlobalHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yangxiong on 2019/3/11.
 */
@RestController
@RequestMapping(value = "/file")
public class FileController extends GlobalHandler {

    @Autowired
    FileUtil util;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Object upload(@RequestParam(value = "file", required = false) MultipartFile file) {
       return new ResultBean(util.upload(file));
    }
}
