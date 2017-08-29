package com.hj.utils;

import javax.servlet.http.HttpServletRequest;



import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FileMethod {

	private static final String LEFTSTR = "/";

	public static String[] filePath(MultipartFile patch, HttpServletRequest req) throws Exception {
		String es = "";
		String newFileName = "";
		if (!patch.isEmpty()) {
			String fileName = patch.getOriginalFilename();
			String newName = UUIDKey.generate();
			String lastName = fileName.substring(fileName.indexOf('.'), fileName.length());
			newFileName = newName + lastName;
			// 得到文件
			String path = "upload";
			File picSaveDir = new File(req.getSession().getServletContext().getRealPath(LEFTSTR + path));
			if (!picSaveDir.exists()) {
				picSaveDir.mkdirs();
			}
			File file1 = new File(picSaveDir.getAbsolutePath() + "/" + newFileName);
			patch.transferTo(file1);
			// ImageCompressUtil.saveMinPhoto(file1.getAbsolutePath(),
			// picSaveDir.getAbsolutePath() + "/min_" + newFileName, 140, 1);
			es = path + LEFTSTR + newFileName;
		}
		return new String[] { es, newFileName };
	}

	public static String[] multFilePath(MultipartFile patch, HttpServletRequest req) throws Exception {
		String[] arr = null;
		if (!patch.isEmpty()) {
			String fileName = patch.getOriginalFilename();
			String newName = UUIDKey.generate();
			String lastName = fileName.substring(fileName.indexOf('.'), fileName.length());
			String newFileName = newName + lastName;
			// 得到文件
			String path = "upload";
			File picSaveDir = new File(req.getSession().getServletContext().getRealPath(LEFTSTR + path));
			if (!picSaveDir.exists()) {
				picSaveDir.mkdirs();
			}
			File file1 = new File(picSaveDir.getAbsolutePath() + "/" + newFileName);
			patch.transferTo(file1);
			String path1 = file1.getAbsolutePath();
			String path2 = picSaveDir.getAbsolutePath() + "/min_" + newFileName;
			ImageCompressUtil.saveMinPhoto(file1.getAbsolutePath(), path2, 140, 1);
			arr = new String[2];
			arr[0] = path1;
			arr[1] = path2;
		}
		return arr;
	}


/**
	 * 获取文件后缀名（不带点）.
	 *
	 * @return 如："jpg" or "".
	 */

	public static String getFileExt(String fileName) {
		if (StringUtils.isBlank(fileName) || !fileName.contains(".")) {
			return "";
		} else {
			return fileName.substring(fileName.lastIndexOf(".") + 1); // 不带最后的点
		}
	}

	public static String getRandomFileName() {

		SimpleDateFormat simpleDateFormat;

		simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

		Date date = new Date();

		String str = simpleDateFormat.format(date);

		Random random = new Random();

		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数

		return rannum + str;// 当前时间
	}

	public static File newFile(HttpServletRequest req, String newFileName) {
		String path = "upload3";
		File picSaveDir = new File(req.getSession().getServletContext().getRealPath(LEFTSTR + path));
		if (!picSaveDir.exists()) {
			picSaveDir.mkdirs();
		}
		File file1 = new File(picSaveDir.getAbsolutePath() + "/" + newFileName);
		return file1;
	}

	public static File newBooks(HttpServletRequest req, String newFileName) {
		String path = "uploadbooks";
		File picSaveDir = new File(req.getSession().getServletContext().getRealPath(LEFTSTR + path));
		if (!picSaveDir.exists()) {
			picSaveDir.mkdirs();
		}
		File file1 = new File(picSaveDir.getAbsolutePath() + "/" + newFileName);
		return file1;
	}

	public static File childBooks(HttpServletRequest req, String newFileName, String parentPath) {
		// String path=FileMethod.getRandomFileName()+childName;

		File picSaveDir = new File(req.getSession(true).getServletContext().getRealPath(LEFTSTR + parentPath));
		if (!picSaveDir.exists()) {
			picSaveDir.mkdirs();
		}
		R.info(picSaveDir.getAbsolutePath() + "/" + newFileName);
		File file1 = new File(picSaveDir.getAbsolutePath() + "/" + newFileName);
		return file1;
	}

	public static File createFile(String newFileName, String parentPath) {
		File picSaveDir = new File(parentPath);
		if (!picSaveDir.exists()) {
			picSaveDir.mkdirs();
		}
		R.info(picSaveDir.getAbsolutePath() + File.separator + newFileName);
		File file1 = new File(picSaveDir.getAbsolutePath() + File.separator + newFileName);
		return file1;
	}




}

