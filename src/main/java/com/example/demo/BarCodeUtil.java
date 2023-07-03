package com.example.demo;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.krysalis.barcode4j.BarcodeDimension;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.impl.HeightVariableBarcodeBean;
import org.krysalis.barcode4j.impl.codabar.CodabarBean;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.impl.datamatrix.DataMatrixBean;
import org.krysalis.barcode4j.impl.fourstate.RoyalMailCBCBean;
import org.krysalis.barcode4j.impl.int2of5.Interleaved2Of5Bean;
import org.krysalis.barcode4j.impl.pdf417.PDF417Bean;
import org.krysalis.barcode4j.impl.postnet.POSTNETBean;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.impl.upcean.UPCABean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Calendar;

/**
 * @Author zhoujing
 * @Date 2022/8/10 18:18
 * @Desciption:
 */
public class BarCodeUtil {
    public static void getBarCode(String msg,String path){
        try {
            File file=new File(path);
            OutputStream ous=new FileOutputStream(file);
            if(StringUtils.isEmpty(msg) || ous==null)
                return;
            //选择条形码类型(好多类型可供选择)
            Code128Bean  bean=new Code128Bean ();
            //设置长宽
            final double moduleWidth=0.3;
            final int resolution=150;
            bean.setModuleWidth(moduleWidth);
            bean.setBarHeight(10.0);
            bean.doQuietZone(true);
            bean.setQuietZone(4);
            String format = "image/png";
            // 输出流
            /**
             * out- 要写入的输出流
             * mime- 所需输出格式的MIME类型（例如“图像/ png”）
             * resolution- 所需的图像分辨率（每英寸点数）
             * imageType- 所需的图像类型（值：BufferedImage.TYPE_*）
             * antiAlias- 如果应启用抗锯齿，则为 true
             */
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format,
                    resolution, BufferedImage.TYPE_BYTE_BINARY, true, 0);
            //生成条码
            bean.generateBarcode(canvas,msg);
            canvas.finish();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void generateBarCode(String msg, boolean hideText, OutputStream ous) {
        generateBarCode(msg,hideText,ous, new CodabarBean());
    }
    public static void generateBarCode39(String msg, boolean hideText, OutputStream ous) {
        generateBarCode(msg,hideText,ous, new Code39Bean());
    }
    public static void generateBarCode128(String msg, boolean hideText, OutputStream ous) {
        generateBarCode(msg,hideText,ous, new Code128Bean());
    }
    /**
     * 已生成code128条形码为例
     * @param msg           要生成的文本
     * @param hideText      隐藏可读文本
     * @param ous
     */
    public static void generateBarCode(String msg, boolean hideText, OutputStream ous, AbstractBarcodeBean bean) {
        try {
            if (StringUtils.isEmpty(msg) || ous == null) {
                return;
            }

            // 如果想要其他类型的条码(CODE 39, EAN-8...)直接获取相关对象Code39Bean...等等
//            AbstractBarcodeBean bean = new Code128Bean();
            // 分辨率：值越大条码越长，分辨率越高。
            int dpi = 200;
            // 设置两侧是否加空白
            bean.doQuietZone(true);
            // 设置条码每一条的宽度
            // UnitConv 是barcode4j 提供的单位转换的实体类，用于毫米mm,像素px,英寸in,点pt之间的转换
            bean.setModuleWidth(UnitConv.in2mm(3.0f / dpi));

            // 设置文本位置（包括是否显示）
            if (hideText) {
                bean.setMsgPosition(HumanReadablePlacement.HRP_NONE);
            }
            // 设置图片类型
            String format = "image/png";

            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi,
                    BufferedImage.TYPE_BYTE_BINARY, false, 0);

            // 生产条形码
            bean.generateBarcode(canvas, msg);

            // 结束
            canvas.finish();
            ous.close();
        } catch (IOException ie) {
            ie.getStackTrace();
        }
    }


    /**
     * 生成条码文件
     * @param msg
     * @param hideText
     * @param path
     * @return
     */
    public static File generateFile(String msg, boolean hideText,String path) {
        File file = new File(path);
        try {
            generateBarCode128(msg, hideText, new FileOutputStream(file));
        } catch (FileNotFoundException fe) {
            throw new RuntimeException(fe);
        }
        return file;
    }

    /**
     *  生成条码字节
     * @param msg
     * @param hideText
     * @return
     */
    public static byte[] generateByte(String msg, boolean hideText) {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        generateBarCode128(msg, hideText, ous);
        return ous.toByteArray();
    }

    public static void main(String[] args) {
        String msg = "156330G20151016F097";
        String path = "D:\\test\\barcode.jpg";
//        BarCodeUtil.getBarCode(msg,path);

        BarCodeUtil.generateFile(msg, false, path);

    }

}
