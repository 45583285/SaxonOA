package sl.test;

import java.awt.image.BufferedImage;  
import java.io.FileOutputStream;  
import org.jbarcode.JBarcode;  
import org.jbarcode.encode.Code128Encoder;
import org.jbarcode.encode.EAN8Encoder;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.EAN8TextPainter;
import org.jbarcode.paint.WidthCodedPainter;  
import org.jbarcode.util.ImageUtil;  
/** 
 * 2012-05-28 
 * @author 郏高阳 
 * 支持EAN13, EAN8, UPCA, UPCE, Code 3 of 9, Codabar, Code 11, Code 93, Code 128, MSI/Plessey, Interleaved 2 of PostNet等
 * 利用jbarcode生成各种条形码！测试成功！分享给大家！
 */  
public class OneBarcodeUtil {  

    public static void main(String[] paramArrayOfString) {  
        try {  
            JBarcode localJBarcode = new JBarcode(Code128Encoder.getInstance(),WidthCodedPainter.getInstance(),BaseLineTextPainter.getInstance());  
            String str = "221964423222";  
            BufferedImage localBufferedImage = localJBarcode.createBarcode(str);  

            saveToJPEG(localBufferedImage, "Code128.jpg");
        }  
        catch (Exception localException) {  
            localException.printStackTrace();  
        }  
    }  

    static void saveToJPEG(BufferedImage paramBufferedImage, String paramString) {  
        saveToFile(paramBufferedImage, paramString, "jpeg");  
    }  

    static void saveToFile(BufferedImage paramBufferedImage, String paramString1, String paramString2) {  
        try {  
            FileOutputStream localFileOutputStream = new FileOutputStream("C:\\Users\\Administrator\\Desktop/" + paramString1);  
            ImageUtil.encodeAndWrite(paramBufferedImage, paramString2, localFileOutputStream, 96, 96);  
            localFileOutputStream.close();  
        }  
        catch (Exception localException) {  
            localException.printStackTrace();  
        }  
    }  
}
