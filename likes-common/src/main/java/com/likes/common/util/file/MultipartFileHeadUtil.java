package com.likes.common.util.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class MultipartFileHeadUtil {

	// 缓存文件头信息-文件头信息
	public static final HashMap<String, String> mFileTypes = new HashMap<String, String>();
	public static final HashMap<String, String> imagesTypes = new HashMap<String, String>();
	public static final HashMap<String, String> videoTypes = new HashMap<String, String>();
	static {
		// images
		imagesTypes.put("FFD8FF", "jpg");
		imagesTypes.put("89504E47", "png");
		imagesTypes.put("47494638", "gif");
		imagesTypes.put("49492A00", "tif");
		imagesTypes.put("424D", "bmp");

		//video
		// 1、视频格式主要有rm,rmvb,mpeg1-4 mov mtv dat wmv avi 3gp amv dmv flv,
		videoTypes.put("00000018", "mp4");
		videoTypes.put("464C5601", "flv");
		videoTypes.put("41564920", "avi");
		videoTypes.put("000001BA", "mpg");
		videoTypes.put("000001B3", "mpg");
		videoTypes.put("2E524D46", "rm");
		videoTypes.put("6D6F6F76", "mov");

		//
		mFileTypes.put("41433130", "dwg"); // CAD
		mFileTypes.put("38425053", "psd");
		mFileTypes.put("7B5C727466", "rtf"); // 日记本
		mFileTypes.put("3C3F786D6C", "xml");
		mFileTypes.put("68746D6C3E", "html");
		mFileTypes.put("44656C69766572792D646174653A", "eml"); // 邮件
		mFileTypes.put("D0CF11E0", "doc");
		mFileTypes.put("5374616E64617264204A", "mdb");
		mFileTypes.put("252150532D41646F6265", "ps");
		mFileTypes.put("255044462D312E", "pdf");
		mFileTypes.put("504B0304", "docx");
		mFileTypes.put("52617221", "rar");
		mFileTypes.put("57415645", "wav");
		mFileTypes.put("3026B2758E66CF11", "asf");
		mFileTypes.put("4D546864", "mid");
		mFileTypes.put("1F8B08", "gz");
		mFileTypes.put("4D5A9000", "exe/dll");
		mFileTypes.put("75736167", "txt");

	}

	public static String getFileHeader(MultipartFile file) {
		InputStream is = null;
		String value = null;
		try {
			is = file.getInputStream();
			byte[] b = new byte[4];
			is.read(b, 0, b.length);
			value = bytesToHexString1(b);
		} catch (Exception e) {
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
		return value;
	}

	private static String bytesToHexString1(byte[] src) {
		StringBuilder builder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		String hv;
		for (int i = 0; i < src.length; i++) {
			hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
			if (hv.length() < 2) {
				builder.append(0);
			}
			builder.append(hv);
		}
		System.out.println(builder);
		return builder.toString();
	}

	//====================================================================

	/**
	 * 根据文件路径获取文件头信息
	 *
	 * @param filePath 文件路径
	 * @return 文件头信息
	 */
	/*public static String getFileType(String filePath) {
		//System.out.println(getFileHeader(filePath));
		//System.out.println(mFileTypes.get(getFileHeader(filePath)));
		return mFileTypes.get(getFileHeader(filePath));
	}
	*/
	/**
	 * 根据文件路径获取文件头信息
	 *
	 * @param filePath 文件路径
	 * @return 文件头信息
	 */
	public static String getFileHeader(String filePath) {
		FileInputStream is = null;
		String value = null;
		try {
			is = new FileInputStream(filePath);
			byte[] b = new byte[4];
			/*
			* int read() 从此输入流中读取一个数据字节。 int read(byte[] b) 从此输入流中将最多 b.length
			* 个字节的数据读入一个 byte 数组中。 int read(byte[] b, int off, int len)
			* 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。
			*/
			is.read(b, 0, b.length);
			value = bytesToHexString2(b);

			//System.out.println("*:"+bytesToHexString2(b));
		} catch (Exception e) {
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
		return value;
	}

	/**
	 * 将要读取文件头信息的文件的byte数组转换成string类型表示
	 *
	 * @param src 要读取文件头信息的文件的byte数组
	 * @return 文件头信息
	 */
	private static String bytesToHexString2(byte[] src) {
		StringBuilder builder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		String hv;
		for (int i = 0; i < src.length; i++) {
// 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
			hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
			if (hv.length() < 2) {
				builder.append(0);
			}
			builder.append(hv);
		}
		System.out.println(builder);
		return builder.toString();
	}

	public static void main(String[] args) throws Exception {
		String filePath = "C:" + File.separator + "YIHUI" + File.separator + "yasuo.flv";
		final String fileType = getFileHeader(filePath);
		System.out.println(fileType);

		//mFileTypes.put("464C5601", "flv");
		//String
		String fileSuffix = filePath.substring(filePath.lastIndexOf("."));
		System.out.println(fileSuffix);

	}


}
