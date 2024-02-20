package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/admin/common")
public class CommonController {

    private AliOssUtil aliOssUtil;
    public CommonController(AliOssUtil aliOssUtil1){
        this.aliOssUtil=aliOssUtil1;
    }

    /**
     * 文件上传
     * @param file
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file){
        log.info("文件上传,{}",file);
        String originName = file.getOriginalFilename();
        String extension = originName.substring(originName.lastIndexOf("."));
        String objectName = UUID.randomUUID()+extension;
        String filePath = null;
        try {
            filePath =  aliOssUtil.upload(file.getBytes(),objectName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.info("文件上传失败，{}",file);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
