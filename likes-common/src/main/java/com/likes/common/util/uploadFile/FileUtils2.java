package com.likes.common.util.uploadFile;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传工具类
 * 
 * @author jack
 *
 */
public class FileUtils2 {

	/**
	 * 文件上传
	 * 
	 * @param file     文件
	 * @param putPath     文件存放路径
	 * @param fileName 原文件名
	 * @param folder   添加文件夹路径：例如日期形式的文件夹(为空不添加)
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> upload(MultipartFile file, String putPath, String fileName, String folder)
			throws IOException {

		// 在原有的路径中添加文件夹 /opt/static/live/liveBackground/20190815/ssfsd.jsp
		if (!folder.equals("")) {
			putPath = putPath + "/" + folder;
		}
		// 生成新的文件名
		String newName = FileNameUtils.getFileName(fileName);
		String realPath = putPath + "/" + newName;
		Map<String, String> map = new HashMap<String, String>();

		// 使用原文件名 small.jpg
		map.put("newName", newName);// 新的文件名字
		map.put("putPath", realPath);// 上传的最终路径+新的文件名
		File dest = new File(realPath);
		// 判断文件父目录是否存在
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();// 创建父级文件路径
			dest.createNewFile();// 创建文件
			System.out.println(dest.exists());
		}
		try {
			// 保存文件
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;

	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath 被删除文件的文件路径+文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
			System.out.println("目标文件----" + sPath + "：删除成功！！！");
			return flag;
		} else {
			System.out.println("删除单个文件：删除失败，目标文件路径不存在！！！");
			return flag;
		}

	}
}
