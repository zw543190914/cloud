package com.zw.cloud.tools.controller.zing;

import cn.hutool.core.codec.Base64;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.zw.cloud.common.utils.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author zhengwei
 * @date 2022/4/15 14:25
 */
@RequestMapping("/zing/bar")
@RestController
@Slf4j
public class ZingBarCodeController {

    @GetMapping
    //http://localhost:9040/zing/bar?content=13122
    public WebResult<String> createBarCode(@RequestParam String content) throws Exception {
        log.info("[ZingBarCodeController][createBarCode]content is {}",content);

        if (StringUtils.isBlank(content)) {
            throw new RuntimeException("请输入内容");
        }
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            MultiFormatWriter writer = new MultiFormatWriter();
            BitMatrix matrix = writer.encode(content, BarcodeFormat.CODE_128, 550, 250, null);
            // 将位图矩阵BitMatrix保存为图片
            ImageIO.write(MatrixToImageWriter.toBufferedImage(matrix), "png", os);
            return WebResult.build("data:image/png;base64," + Base64.encode(os.toByteArray()));
        }
    }

    @PostMapping
    //http://localhost:9040/app/zing/bar
    public Result parseBarCode(MultipartFile file) throws Exception {
        if (Objects.isNull(file)) {
            throw new RuntimeException("请选择要解析的文件");
        }
        BufferedImage image = ImageIO.read(file.getInputStream());
        if (Objects.isNull(image)) {
            throw new RuntimeException("文件不支持");
        }
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, "utf8");
        hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        return new MultiFormatReader().decode(bitmap, hints);
    }
}
