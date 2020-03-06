package com.ticketsys.mgmt.util;

import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Objects;

/**
 * @author mdoss
 */
public class Base64Util {
    private Base64Util() {}
    public static String encodeToString(File file) {
       try {
            if(Objects.nonNull(file) && file.exists() && file.isFile()) {
                String encodedString = null;
                byte[] fileContent = FileUtils.readFileToByteArray(file);
                encodedString = Base64.getEncoder().encodeToString(fileContent);
                return encodedString;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static File fileContentasBytes(String imgAsEncoded) {
        String separator = ",";
        if(!StringUtils.isEmpty(imgAsEncoded) && imgAsEncoded.contains(separator)) {
            try {
                String splitArr[] = imgAsEncoded.split(separator);
                String encodeImg = splitArr[1];
                byte[] decodeImg = Base64.getDecoder().decode(encodeImg.getBytes(StandardCharsets.UTF_8));
                String fileExtension = splitArr[0].split(";")[0].split("/")[1];
                File dir = new File("path/to/imageDir");
                if(!dir.exists()) {
                    dir.mkdirs();
                }
                Path destinationFile = Paths.get("path/to/imageDir", "decodeImg." + fileExtension);
                Path path = Files.write(destinationFile, decodeImg);
                FileStore store = path.getFileSystem().getFileStores().iterator().next();
                File file = new File("path/to/imageDir/decodeImg.png");
                System.out.println("absolutepath:----------- "+file.getAbsolutePath());

                return file;
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    /**
     * https://stackoverflow.com/questions/28584080/base64-java-lang-illegalargumentexception-illegal-character
     * String partSeparator = ",";
     * if (data.contains(partSeparator)) {
     *   String encodedImg = data.split(partSeparator)[1];
     *   byte[] decodedImg = Base64.getDecoder().decode(encodedImg.getBytes(StandardCharsets.UTF_8));
     *   Path destinationFile = Paths.get("/path/to/imageDir", "myImage.jpg");
     *   Files.write(destinationFile, decodedImg);
     * }
     */
}
