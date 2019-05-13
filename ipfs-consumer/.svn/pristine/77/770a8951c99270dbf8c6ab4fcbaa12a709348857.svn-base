package com.stys.ipfs.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTest {
	public static void main(String[] args) throws IOException {
		// 获取编辑推荐页
		Document document = Jsoup.connect(
				"http://www.ipfs.cn/Ajax/AjaxCommon.ashx?act=Home_NewsList&pageindex=1&pagesize=15&Search=%27|%27&random="
						+ Math.random())
				// 模拟火狐浏览器
				// .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
				.get();

		Elements contents = document.select("div.news-item");

		System.out.println("為什麼沒有" + contents.size());

		// System.out.println("文件大小"+url.size()+url.html());
		for (Element question : contents) {

			// 输出href后的值，即主页上每个关注问题的链接
			
			String title  = question.select("div[class=new-intro]").select("h3").select("a").html();
			String link  = question.select("div[class=new-intro]").select("h3").select("a").attr("href");
			
			Document sunDocument = Jsoup.connect(
					"http://www.ipfs.cn/"+link)
					// 模拟火狐浏览器
					// .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
					.get();
			
			Elements sunElements= sunDocument.select("div.wrapper").select("section:gt(0)");
	 
			
			
			//System.out.println(sunElements.html());
			
			
			String detail  = question.select("div[class=new-intro]").select("p").select("a").html();	
			String date  = question.select("div[class=new-intro]").select("div[class=nrb-state]").select("span[class=nrb-time]").html();
			String imgPath = question.select("div[class=new-img]").select("img").attr("src");

			date=date.substring(28);
//            System.out.println("\n"+"链接："+link
//                    +"\n"+"标题："+title
//                    +"\n"+"问题描述："+detail
//                    +"\n"+"時間："+date
//                    +"\n"+"圖片："+imgPath);
		}
	}
}
