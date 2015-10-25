package sl.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class OtherUtil {

	public static void main(String[] args) {
		try {
			File file = new File("D:/");
			File[] files = file.listFiles();
			File xmlfile = new File("D:/fileInfo.xml");
			if (xmlfile.exists()) {
				xmlfile.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(xmlfile);
			OutputFormat format = OutputFormat.createPrettyPrint();
			//format.setEncoding("gbk");
			format.setEncoding("utf-8");
			XMLWriter writer = new XMLWriter(fos, format);
			Document doc = DocumentHelper.createDocument();
			Element rootElement = DocumentHelper.createElement("root");
			rootElement.addAttribute("version", "2.0");
			doc.setRootElement(rootElement);
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					Element fileElement = rootElement.addElement("file");
					Element nameElement = fileElement.addElement("name");
					nameElement.addText(files[i].getName().substring(0,
							files[i].getName().indexOf(".")));
					Element lengthElement = fileElement.addElement("length");
					lengthElement.addText(Long.toString(files[i].length()));
					Element cssElement = fileElement.addElement("css");
					cssElement.addText(files[i].getName().substring(
							files[i].getName().indexOf(".") + 1));

				}
			}
			writer.write(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
