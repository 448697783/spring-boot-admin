package com.dll;

import com.arcsoft.face.*;
import com.arcsoft.face.enums.ImageFormat;

import javax.imageio.ImageIO;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FaceEngineTest {

    public static void main(String[] args) {
        new FaceEngineTest().faceEngineTest();
    }


    public void faceEngineTest() {
        String appId = "";
        String sdkKey = "";

        FaceEngine faceEngine = new FaceEngine();
        //激活引擎
        faceEngine.active("EPYPDAeuoGdzbfzGdhpaLuVq912t9V1Z7Zqvuruyw4UD", "CSy8MCHWFeASU8oWZPH2FDRMpMf9wuz4BErHQCXYMvfr");
        EngineConfiguration engineConfiguration = EngineConfiguration.builder().functionConfiguration(
                FunctionConfiguration.builder()
                        .supportAge(true)
                        .supportFace3dAngle(true)
                        .supportFaceDetect(true)
                        .supportFaceRecognition(true)
                        .supportGender(true)
                        .supportLiveness(true)
                        .build()).build();
        //初始化引擎
        faceEngine.init(engineConfiguration);

//        ImageInfo imageInfo = getRGBData(new File("d:\\pic3.png"));
        ImageInfo imageInfo = getRGBData(new File("d:\\pic7.jpg"));
//        ImageInfo imageInfo2 = getRGBData(new File("d:\\shujuan4.jpg"));
//        ImageInfo imageInfo2 = getRGBData(new File("d:\\冷志向.jpg"));
//        ImageInfo imageInfo2 = getRGBData(new File("d:\\wang1.jpg"));
        ImageInfo imageInfo2 = getRGBData(new File("d:\\pic8.jpg"));
        //人脸检测
        List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
         faceEngine.detectFaces(imageInfo.getRgbData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList);
        
         
         List<FaceInfo> faceInfoList2 = new ArrayList<FaceInfo>();
         faceEngine.detectFaces(imageInfo2.getRgbData(), imageInfo2.getWidth(), imageInfo2.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList2);
         
         

        //提取人脸特征
        FaceFeature faceFeature = new FaceFeature();
        faceEngine.extractFaceFeature(imageInfo.getRgbData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList.get(0), faceFeature);

        FaceFeature faceFeature2 = new FaceFeature();
        faceEngine.extractFaceFeature(imageInfo2.getRgbData(), imageInfo2.getWidth(), imageInfo2.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList2.get(0), faceFeature2);
        
        //人脸对比
        FaceFeature targetFaceFeature = new FaceFeature();
        targetFaceFeature.setFeatureData(faceFeature.getFeatureData());
        

        FaceFeature sourceFaceFeature = new FaceFeature();
        sourceFaceFeature.setFeatureData(faceFeature2.getFeatureData());

        FaceSimilar faceSimilar = new FaceSimilar();
        float compareFaceFeature = faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);
        System.out.println(faceSimilar.getScore());

        int processResult = faceEngine.process(imageInfo.getRgbData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList, FunctionConfiguration.builder().supportAge(true).supportFace3dAngle(true).supportGender(true).supportLiveness(true).build());
        System.out.println(processResult);

        //性别提取
        List<GenderInfo> genderInfoList = new ArrayList<GenderInfo>();
        int genderCode = faceEngine.getGender(genderInfoList);
        System.out.println(genderCode);

        //年龄提取
        List<AgeInfo> ageInfoList = new ArrayList<AgeInfo>();
        int ageCode = faceEngine.getAge(ageInfoList);
        System.out.println(ageCode);

        //3D信息提取
        List<Face3DAngle> face3DAngleList = new ArrayList<Face3DAngle>();
        int face3dCode = faceEngine.getFace3DAngle(face3DAngleList);
        System.out.println(face3dCode);

        //活体信息
        List<LivenessInfo> livenessInfoList = new ArrayList<LivenessInfo>();
        int livenessCode = faceEngine.getLiveness(livenessInfoList);
        System.out.println(livenessCode);

        System.out.println();
    }

    public ImageInfo getRGBData(File file) {
        if (file == null)
            return null;
        ImageInfo imageInfo;
        try {
            //将图片文件加载到内存缓冲区
            BufferedImage image = ImageIO.read(file);
            imageInfo = bufferedImage2ImageInfo(image);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return imageInfo;
    }

    private ImageInfo bufferedImage2ImageInfo(BufferedImage image) {
        ImageInfo imageInfo = new ImageInfo();
        int width = image.getWidth();
        int height = image.getHeight();
        // 使图片居中
        width = width & (~3);
        height = height & (~3);
        imageInfo.setWidth(width);
        imageInfo.setHeight(height);
        //根据原图片信息新建一个图片缓冲区
        BufferedImage resultImage = new BufferedImage(width, height, image.getType());
        //得到原图的rgb像素矩阵
        int[] rgb = image.getRGB(0, 0, width, height, null, 0, width);
        //将像素矩阵 绘制到新的图片缓冲区中
        resultImage.setRGB(0, 0, width, height, rgb, 0, width);
        //进行数据格式化为可用数据
        BufferedImage dstImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        if (resultImage.getType() != BufferedImage.TYPE_3BYTE_BGR) {
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_LINEAR_RGB);
            ColorConvertOp colorConvertOp = new ColorConvertOp(cs, dstImage.createGraphics().getRenderingHints());
            colorConvertOp.filter(resultImage, dstImage);
        } else {
            dstImage = resultImage;
        }
        //获取rgb数据
        imageInfo.setRgbData(((DataBufferByte) (dstImage.getRaster().getDataBuffer())).getData());
        return imageInfo;
    }


    class ImageInfo {
        public byte[] rgbData;
        public int width;
        public int height;

        public byte[] getRgbData() {
            return rgbData;
        }

        public void setRgbData(byte[] rgbData) {
            this.rgbData = rgbData;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
