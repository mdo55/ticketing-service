package com.ticketsys.mgmt.util;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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
}
