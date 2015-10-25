package sl.test;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

public class MatrixToImageWriterDo {

	/**
	 * @param args
	 */
	 public final static class MatrixToImageWriter {
		 
		   private static final int BLACK = 0xFF000000;
		   private static final int WHITE = 0xFFFFFFFF;
		 
		   private MatrixToImageWriter() {}
		 
		   
		   public static BufferedImage toBufferedImage(BitMatrix matrix) {
		     int width = matrix.getWidth();
		     int height = matrix.getHeight();
		     BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		     for (int x = 0; x < width; x++) {
		       for (int y = 0; y < height; y++) {
		         image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
		       }
		     }
		     return image;
		   }
		 
		   
		   public static void writeToFile(BitMatrix matrix, String format, File file)
		       throws IOException {
		     BufferedImage image = toBufferedImage(matrix);
		     if (!ImageIO.write(image, format, file)) {
		       throw new IOException("Could not write an image of format " + format + " to " + file);
		     }
		   }
		 
		   
		   public void writeToStream(BitMatrix matrix, String format, OutputStream stream)
		       throws IOException {
		     BufferedImage image = toBufferedImage(matrix);
		     if (!ImageIO.write(image, format, stream)) {
		       throw new IOException("Could not write an image of format " + format);
		     }
		   }
		 
		 }
	 
	 
	 
	 //生成二维码end
	 
	 
	 public final static class BufferedImageLuminanceSource extends LuminanceSource {
		 
		   private final BufferedImage image;
		   private final int left;
		   private final int top;
		 
		   public BufferedImageLuminanceSource(BufferedImage image) {
		     this(image, 0, 0, image.getWidth(), image.getHeight());
		   }
		 
		   public BufferedImageLuminanceSource(BufferedImage image, int left, int top, int width, int height) {
		     super(width, height);
		 
		     int sourceWidth = image.getWidth();
		     int sourceHeight = image.getHeight();
		     if (left + width > sourceWidth || top + height > sourceHeight) {
		       throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
		     }
		 
		     for (int y = top; y < top + height; y++) {
		       for (int x = left; x < left + width; x++) {
		         if ((image.getRGB(x, y) & 0xFF000000) == 0) {
		           image.setRGB(x, y, 0xFFFFFFFF); // = white
		         }
		       }
		     }
		 
		     this.image = new BufferedImage(sourceWidth, sourceHeight, BufferedImage.TYPE_BYTE_GRAY);
		     this.image.getGraphics().drawImage(image, 0, 0, null);
		     this.left = left;
		     this.top = top;
		   }
		 
		   @Override
		   public byte[] getRow(int y, byte[] row) {
		     if (y < 0 || y >= getHeight()) {
		       throw new IllegalArgumentException("Requested row is outside the image: " + y);
		     }
		     int width = getWidth();
		     if (row == null || row.length < width) {
		       row = new byte[width];
		     }
		     image.getRaster().getDataElements(left, top + y, width, 1, row);
		     return row;
		   }
		 
		   @Override
		   public byte[] getMatrix() {
		     int width = getWidth();
		     int height = getHeight();
		     int area = width * height;
		     byte[] matrix = new byte[area];
		     image.getRaster().getDataElements(left, top, width, height, matrix);
		     return matrix;
		   }
		 
		   @Override
		   public boolean isCropSupported() {
		     return true;
		   }
		 
		   @Override
		   public LuminanceSource crop(int left, int top, int width, int height) {
		     return new BufferedImageLuminanceSource(image, this.left + left, this.top + top, width, height);
		   }
		 
		   @Override
		   public boolean isRotateSupported() {
		     return true;
		   }
		 
		   @Override
		   public LuminanceSource rotateCounterClockwise() {
		 
		       int sourceWidth = image.getWidth();
		     int sourceHeight = image.getHeight();
		 
		     AffineTransform transform = new AffineTransform(0.0, -1.0, 1.0, 0.0, 0.0, sourceWidth);
		 
		     BufferedImage rotatedImage = new BufferedImage(sourceHeight, sourceWidth, BufferedImage.TYPE_BYTE_GRAY);
		 
		     Graphics2D g = rotatedImage.createGraphics();
		     g.drawImage(image, transform, null);
		     g.dispose();
		 
		     int width = getWidth();
		     return new BufferedImageLuminanceSource(rotatedImage, top, sourceWidth - (left + width), getHeight(), width);
		   }
		 
		 }
	 
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*		try {
            
		     String content = "120605181003;http://www.cnblogs.com/jtmjx";
		     String path = "D://";
		     
		     MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		     
		     Map hints = new HashMap();
		     hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		     BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400,hints);
		     File file1 = new File(path,"test.png");
		     MatrixToImageWriter.writeToFile(bitMatrix, "png", file1);
		     
		 } catch (Exception e) {
		     e.printStackTrace();
		 }*/
		//--------------------------生成二维码 end
		
		String filePath = "D://test.png";  
        BufferedImage image;  
        try {  
            image = ImageIO.read(new File(filePath));  
            LuminanceSource source = new BufferedImageLuminanceSource(image);  
            Binarizer binarizer = new HybridBinarizer(source);  
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);  
            Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();  
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");  
            Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码  
            JSONObject content = JSONObject.parseObject(result.getText());  
            System.out.println("图片中内容：  ");  
            System.out.println("author： " + content.getString("author"));  
            System.out.println("zxing：  " + content.getString("zxing"));  
            System.out.println("图片中格式：  ");  
            System.out.println("encode： " + result.getBarcodeFormat());  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (NotFoundException e) {  
            e.printStackTrace();  
        }  
	}

}
