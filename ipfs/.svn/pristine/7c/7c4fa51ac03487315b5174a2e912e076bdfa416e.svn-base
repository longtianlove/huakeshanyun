package com.tbr.crawler;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity {

    private List<News> newsList=new ArrayList<>();
    private void getNews(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    //获取虎扑新闻20页的数据，网址格式为：https://voice.hupu.com/nba/第几页
                    for(int i = 1;i<=10;i++) {

                        Document doc = Jsoup.connect("https://ethfans.org/?page=" + Integer.toString(i)).get();
                        Elements titleLinks = doc.select("div.post-info");    //解析来获取每条新闻的标题与链接地址
//                        Elements descLinks = doc.select("div.list-content");//解析来获取每条新闻的简介
                        Elements timeLinks = doc.select("div.mate");   //解析来获取每条新闻的时间与来源
                        for(int j = 0;j < titleLinks.size();j++){
                            String title = titleLinks.get(j).select("a").text();
                            String uri = titleLinks.get(j).select("a").attr("href");
                            Document doc1 = Jsoup.connect("https://ethfans.org/" +uri).userAgent("Mozilla/5.0 (Windows NT 6.2; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0").get();
                            String desc = doc1.html();
                            PrintWriter pw=new PrintWriter(new File("D:/tbr/"+System.currentTimeMillis()+".html"));
                            pw.println(desc);
                            pw.close();
                            News news = new News(title,uri,desc,null);
                            newsList.add(news);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            
        }).start();
     }
    	public List<News> getNewsList() {
		return newsList;
	}



	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}



		public static void main(String[] args)  {
			MainActivity entity= new MainActivity();
			entity.getNews();
    	}

}