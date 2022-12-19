package com.likes.common.util.file;

import com.likes.common.util.RandomUtil;
import org.apache.commons.lang.StringUtils;

import java.io.File;

public class UploadFile {
    /**
     * @return
     * @author abu
     * <p>
     * Description:<br>
     * 格式编号
     */
    public static String setNameId(String fileName) {
        String id = RandomUtil.getRandNo();
        String contentType = "";
        if (fileName.lastIndexOf(".") >= 0) {
            fileName = fileName.toLowerCase();
            fileName = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        if ("gif".equals(fileName)) {
            contentType = "01";
        } else if ("jpeg".equals(fileName)) {
            contentType = "02";
        } else if ("jpg".equals(fileName)) {
            contentType = "03";
        } else if ("png".equals(fileName)) {
            contentType = "04";
        } else if ("bmp".equals(fileName)) {
            contentType = "05";
        } else if ("amr".equals(fileName)) {
            contentType = "06";
        } else if ("mp3".equals(fileName)) {
            contentType = "07";
        }
        return id + contentType;
    }

    /**
     * @param id
     * @return
     * @author abu
     * <p>
     * Description:<br>
     */
    public static String getNameId(String id) {
        String suffix = "";
        if (!StringUtils.isEmpty(id)) {
            String type = id.substring(id.length() - 2);
            if ("01".equals(type)) {
                suffix = "gif";
            } else if ("02".equals(type)) {
                suffix = "jpeg";
            } else if ("03".equals(type)) {
                suffix = "jpg";
            } else if ("04".equals(type)) {
                suffix = "png";
            } else if ("05".equals(type)) {
                suffix = "bmp";
            } else if ("06".equals(type)) {
                id = "voice" + File.separator + id;
                suffix = "amr";
            } else if ("07".equals(type)) {
                id = "voice" + File.separator + id;
                suffix = "mp3";
            }
        }

        return id + "." + suffix;
    }

    /**
     * 根据路径删除指定的目录或文件，无论存在与否
     *
     * @param sPath 要删除的目录或文件
     * @return 删除成功返回 true，否则返回 false。
     */
    public static boolean DeleteFolder(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) { // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) { // 为文件时调用删除文件方法
                return deleteFile(sPath);
            } else { // 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            } // 删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {
            return false;
        }
        // 删除当前目录
        return dirFile.delete();
    }

}
