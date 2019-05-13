package com.stys.ipfs.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUtils {

	public static void downLoadFile(File file, HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		// 如果文件存在，则进行下载
		if (file.exists()) {
			// 下载文件能正常显示中文
			response.setHeader("Content-Disposition",
					"attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
			response.setContentType("application/vnd.android.package-archive");
			response.setContentLength((int) file.length());

			// 实现文件下载
			byte[] buffer = new byte[8192];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
				System.out.println("Download the file successfully!");
			} catch (Exception e) {
				System.out.println("Download the file failed!");
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
