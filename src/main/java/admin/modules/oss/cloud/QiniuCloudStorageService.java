package admin.modules.oss.cloud;

import java.io.InputStream;

import admin.common.exception.BusinessException;

/**
 * 七牛云存储
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2017-03-25 15:41
 */
public class QiniuCloudStorageService extends CloudStorageService{
//    private UploadManager uploadManager;
    private String token;

    public QiniuCloudStorageService(CloudStorageConfig config){
        this.config = config;

        //初始化
        init();
    }

    private void init(){
//        uploadManager = new UploadManager(new Configuration(Zone.autoZone()));
//        token = Auth.create(config.getQiniuAccessKey(), config.getQiniuSecretKey()).
//                uploadToken(config.getQiniuBucketName());
    }

    @Override
    public String upload(byte[] data, String path) {
//        try {
//            Response res = uploadManager.put(data, path, token);
//            if (!res.isOK()) {
//                throw new RuntimeException("上传七牛出错：" + res.toString());
//            }
//        } catch (Exception e) {
            throw new BusinessException("上传文件失败，请核对七牛配置信息");
//        }

//        return config.getQiniuDomain() + "/" + path;
    }

    @Override
    public String upload(InputStream inputStream, String path) {
//        try {
//            byte[] data = IOUtils.toByteArray(inputStream);
//            return this.upload(data, path);
//        } catch (IOException e) {
            throw new BusinessException("上传文件失败");
//        }
    }

    @Override
    public String upload(byte[] data) {
        return upload(data, getPath(config.getQiniuPrefix()));
    }

    @Override
    public String upload(InputStream inputStream) {
        return upload(inputStream, getPath(config.getQiniuPrefix()));
    }
}
