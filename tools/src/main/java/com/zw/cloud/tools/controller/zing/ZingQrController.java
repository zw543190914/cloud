package com.zw.cloud.tools.controller.zing;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson2.JSON;
import com.baidu.aip.ocr.AipOcr;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.zw.cloud.common.entity.vo.OcrQrVO;
import com.zw.cloud.common.utils.zing.QrCodeUtils;
import com.zw.cloud.global.response.wrapper.entity.WebResult;
import com.zw.cloud.tools.entity.PostCard;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhengwei
 * @date 2022/4/15 13:48
 */
@RequestMapping("/zing/qr")
@RestController
@Slf4j
public class ZingQrController {

    @Autowired
    private AipOcr client;

    /*@GetMapping
    //http://localhost:9040/zing/qr?content=13422发
    public WebResult<String> createSimpleQRCode(@RequestParam String content) throws Exception {
        log.info("[ZingQrController][createSimpleQRCode]content is {}",content);

        if (StringUtils.isBlank(content)) {
            throw new RuntimeException("请输入内容");
        }
        BufferedImage bufferedImage = QrCodeUtils.encodeAndReturnStream(content, null);
        // 转为base64
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", os);
        return WebResult.build("data:image/png;base64," + Base64.encode(os.toByteArray()));
    }*/

    @GetMapping
    //http://localhost:9040/zing/qr?content=13422发
    //http://8.130.80.181/web/zing/qr?content=13422发
    public void createSimpleQRCodeForWeb(@RequestParam String content, HttpServletResponse response) throws Exception {
        log.info("[ZingQrController][createSimpleQRCodeForWeb]content is {}",content);

        if (StringUtils.isBlank(content)) {
            throw new RuntimeException("请输入内容");
        }
        BufferedImage bufferedImage = QrCodeUtils.encodeAndReturnStream(content, null);
        ServletOutputStream outputStream = response.getOutputStream();
        response.setHeader("Content-Type", "image/png");
        ImageIO.write(bufferedImage, "png", outputStream);
    }

    @PutMapping
    //http://localhost:9040/zing/qr
    public WebResult<String> createPostCard(@RequestBody PostCard postCard) throws Exception {
        log.info("[ZingQrController][createPostCard]postCard is {}",JSON.toJSONString(postCard));
        StringBuilder content = new StringBuilder("MECARD:");
        content.append("N:").append(postCard.getName())
                .append(";TEL:").append(postCard.getTel())
                .append(";VOICE:").append(postCard.getVoice())
                .append(";EMAIL:").append(postCard.getEmail())
                .append(";TITLE:").append(postCard.getTitle())
                .append(";ORG:").append(postCard.getOrg())
                .append(";ADR:").append(postCard.getAddress())
                .append(";NOTE:").append(postCard.getNote())
                .append(";;");
        BufferedImage bufferedImage = QrCodeUtils.encodeAndReturnStream(content.toString(), null);
        // 转为base64
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", os);
        return WebResult.success("data:image/png;base64," + Base64.encode(os.toByteArray()));
    }

    @PostMapping
    public WebResult<Map<String,Object>> parseQRCode(MultipartFile file) {
        if (Objects.isNull(file)) {
            throw new RuntimeException("请选择要解析的文件");
        }
        Map<String,Object> resultMap = new HashMap<>();
        try {
            MultiFormatReader reader = new MultiFormatReader();
            //File f=new File("D:\\QrCode\\05.png");
            BufferedImage image = ImageIO.read(file.getInputStream());
            BinaryBitmap bb = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            HashMap<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
            hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
            Result result = reader.decode(bb, hints);
            System.out.println("解析结果：" + result.toString());
            System.out.println("二维码格式类型：" + result.getBarcodeFormat());
            System.out.println("二维码文本内容：" + result.getText());
            resultMap.put("text",result.getText());
        } catch (IOException e) {
            throw new RuntimeException("文件读取失败");
        } catch (NotFoundException e) {
            try {
                JSONObject res = client.qrcode(file.getBytes(), new HashMap<>(4));
                OcrQrVO ocrQrVO = JSON.parseObject(res.toString(), OcrQrVO.class);
                List<OcrQrVO.Text> codesResult = ocrQrVO.getCodes_result();
                String result = codesResult.stream().map(text -> {
                    List<String> strings = text.getText();
                    return strings.stream().collect(Collectors.joining(","));
                }).collect(Collectors.joining(","));
                resultMap.put("text",result);
            } catch (IOException ex) {
                throw new RuntimeException("二维码图片无法识别,请重新上传");
            }
        }
        return WebResult.success(resultMap);
    }
}
