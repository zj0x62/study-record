package com.example.demo.doc_analysis;

import com.spire.doc.Document;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.TextSelection;
import com.spire.doc.fields.TextRange;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author: zhoujing
 * @Date: 2022/11/18 10:46
 * @Description: 解析word文档内容
 */
public class DocAnalysisDemo {

    public static void main(String[] args) throws IOException {

        String filename="D:\\Download\\日报(08-24).docx";
        InputStream is = new BufferedInputStream(Files.newInputStream(Paths.get(filename)));
        String textContent = "";

        try {
//            textContent = readDoc(filename);
            read(filename);
//            readWord(filename);
//            System.out.println(read(is));
//            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(textContent);
    }

    public static String readDoc(String path) throws IOException {
        String resullt = "";
        //首先判断文件中的是doc/docx
        try {
            if (path.endsWith(".doc")) {
                InputStream is = new FileInputStream(new File(path));
                WordExtractor re = new WordExtractor(is);
                resullt = re.getText();
                re.close();
            } else if (path.endsWith(".docx")) {
                OPCPackage opcPackage = POIXMLDocument.openPackage(path);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                resullt = extractor.getText();
                extractor.close();
            } else {
                System.out.println("此文件不是word文件");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return resullt;
    }

    public static void read(String fileName) throws IOException{
        //加载Word源文档
        Document doc = new Document();
        doc.loadFromFile(fileName);

        //获取段落数量
        int count = doc.getSections().get(0).getParagraphs().getCount();
        System.out.println("总共含有段落数:" + count);
        for(int i = 0; i < count; i++) {
            System.out.println("段落" + (i + 1) + "--" + doc.getSections().get(0).getParagraphs().get(i).getText());
        }

        //查找指定文本
        TextSelection textSelections = doc.findString("今日工作", false, true);
        //获取字体名称
        String fontname = textSelections.getAsOneRange().getCharacterFormat().getFontName();
        //获取字体大小
        float fontsize = textSelections.getAsOneRange().getCharacterFormat().getFontSize();
        System.out.println("字体名称:" + fontname +"\n"
                +"字体大小："+fontsize);

        //获取第二段
        Paragraph paragraph2 = doc.getSections().get(0).getParagraphs().get(1);
        //获取段落行距
        float linespage = paragraph2.getFormat().getLineSpacing();
        System.out.println("段落行距：" + linespage);

        //遍历段落中的子对象
        for (int z = 0; z < paragraph2.getChildObjects().getCount(); z++)
        {
            Object obj2 = paragraph2.getChildObjects().get(z);

            //判定是否为文本
            if (obj2 instanceof TextRange)
            {
                TextRange textRange2 = (TextRange) obj2;

                //获取文本颜色
                Color textcolor = textRange2.getCharacterFormat().getTextColor();
                if (!(textcolor.getRGB() == 0))
                {
                    System.out.println("文本颜色：" + textRange2.getText() + textcolor.toString());
                }

                //获取字体加粗效果
                boolean isbold = textRange2.getCharacterFormat().getBold();
                if (isbold == true)
                {
                    System.out.println("加粗文本：" + textRange2.getText());
                }

                //获取字体倾斜效果
                boolean isitalic = textRange2.getCharacterFormat().getItalic();
                if (isitalic == true)
                {
                    System.out.println("倾斜文本：" + textRange2.getText());
                }

                //获取文本背景
                String text = textRange2.getText();
                Color highlightcolor = textRange2.getCharacterFormat().getHighlightColor();//获取文本的高亮颜色（即突出显示颜色）
                if (!(highlightcolor.getRGB() == 0 ))
                {
                    System.out.println("文本高亮：" + text + highlightcolor.toString());//输出高亮的文本和颜色
                }

                Color textbackgroundcolor = textRange2.getCharacterFormat().getTextBackgroundColor();//获取文字背景（底纹）
                if (!(textbackgroundcolor.getRGB()==0))
                {
                    System.out.println("文本背景：" + text + textbackgroundcolor.toString());//输出有背景的文本和颜色
                }
            }
        }
    }

    static String read(InputStream is) throws Exception {
        System.out.println(FileMagic.valueOf(is));
        String text = "";
        if (FileMagic.valueOf(is) == FileMagic.OLE2) {
            WordExtractor ex = new WordExtractor(is);
            text = ex.getText();
            ex.close();
        } else if (FileMagic.valueOf(is) == FileMagic.OOXML) {
            XWPFDocument doc = new XWPFDocument(is);
            XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
            text = extractor.getText();
            extractor.close();
        }
        return text;
    }
}
