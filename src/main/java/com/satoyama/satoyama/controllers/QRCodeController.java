package com.satoyama.satoyama.controllers;

import com.satoyama.satoyama.utils.QRCodeService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@RestController
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;


    // QrCodeDto
    // QrCodeServiceInterface // es una interfaz que va a tener los metodos: regresarQRparaFront generate(String email)
    // QrCodeServiceImplements // Aqui si va el codigo

    // Select user where MD5(email) = encryptedEmail;
    // Leer el QR generado

    @PostMapping("/v1/qrcode")
    public void generateQRCode(HttpServletResponse response,
                               @RequestBody String text,
                               @RequestParam(defaultValue = "350") int width,
                               @RequestParam(defaultValue = "350") int height) throws Exception {
        BufferedImage image = qrCodeService.generateQRCode(text, width, height);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);
        outputStream.flush();
        outputStream.close();
    }
}