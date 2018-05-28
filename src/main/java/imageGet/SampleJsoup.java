package imageGet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SampleJsoup {
	public void getHtml() {
		//String url = "http://ypage.tennis365.net/ypage/index_detail.html?ypage_id=4127";
		String url = "https://item.rakuten.co.jp/cerise-sweets/rai10dis/?iasid=07_10095__129366754_ven-jha60gu6-dp04";

		try {
			Document doc = Jsoup.connect(url).get();
			//System.out.println(doc);
			Elements imgs = doc.select("img");

			for(Element img : imgs) {
				System.out.println(img.attr("src"));
				//System.out.println(img);
			}

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	public static void getImageByUrl(String imageUrl , String filePath) {
		BufferedImage image;
		try {
			image = ImageIO.read(
				    new ByteArrayInputStream(
				        Jsoup
				        .connect(imageUrl)
				        .ignoreContentType(true)
				        .execute()
				        .bodyAsBytes()
				    )
				);
			String imageType = "jpg";
			ImageIO.write(image, imageType, new File(filePath));
			System.out.println("GET:"+filePath);

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

}
