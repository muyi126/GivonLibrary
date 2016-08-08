package com.base.givon.baseobj.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;


/**
 * 文件操作的工具类
 * <p/>
 * Copyright 2015 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @十月 15/10/4 下午2:33 - Guzhu
 * @email:muyi126@163.com
 */

public class FileUtil {

    private static final String COOKIE_FILE = "cookie_file";

    /**
     * 存档到data/data/files
     * 保存文件
     */
    public static boolean writeLocalFile(Context context, String file,
                                         byte[] msg) throws IOException {
        boolean res = false;

        FileOutputStream stream = context.openFileOutput(file,
                Context.MODE_WORLD_WRITEABLE);
        stream.write(msg);
        stream.flush();
        stream.close();
        res = true;
        return res;
    }

    /**
     * 读取文件data/data/files
     */
    public static byte[] readLocalFile(Context context, String file)
            throws IOException {
        byte[] datas = null;
        if (checkFileIsExists(context, file)) {
            FileInputStream stream = context.openFileInput(file);
            BufferedInputStream bis = new BufferedInputStream(stream);
            byte[] buffer = new byte[1024];
            int ch = 0;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            while ((ch = bis.read(buffer)) != -1) {
                out.write(buffer, 0, ch);
            }
            datas = out.toByteArray();
            bis.close();
            out.close();
        }
        return datas;
    }

    /**
     * 从流内容读取文件
     */
    public static byte[] readStreamFile(InputStream is) throws IOException {
        byte[] datas = null;
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        int ch = 0;
        while ((ch = bis.read(buffer)) != -1) {
            baos.write(buffer, 0, ch);
        }
        datas = baos.toByteArray();
        baos.close();
        baos = null;
        bis.close();
        bis = null;

        return datas;
    }

    /**
     * 写文件数据  可以指定任意目录
     *
     * @param file 文件名称
     * @param msg  数据
     */
    public static void writeFile(String file, byte[] msg) {

        try {
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(msg);
            stream.flush();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件数据 可以指定任意目录
     *
     * @param file 文件名称
     * @return
     * @throws IOException
     */
    public static byte[] readFile(String file) {
        byte[] datas = null;
        try {
            byte[] buffer = new byte[1024];
            FileInputStream stream = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(stream);
            int ch = 0;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            while ((ch = bis.read(buffer)) != -1) {
                out.write(buffer, 0, ch);
            }
            datas = out.toByteArray();
            bis.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datas;
    }

    /**
     * 判断文件是否存在 data/data/files
     */
    public static boolean checkFileIsExists(Context mContext, String fileName) {
        File fileDir = mContext.getFilesDir();
        String sFileName = fileDir.getParent() + File.separator
                + fileDir.getName() + File.separator + fileName;
        File file = new File(sFileName);
        return file.exists();
    }

    /**
     * 取得文件最后修改日期
     */
    public static String getFileDatetime(Context mContext, String fileName) {
        String dt = "";
        File fileDir = mContext.getFilesDir();
        String sFileName = fileDir.getParent() + File.separator
                + fileDir.getName() + File.separator + fileName;
        File file = new File(sFileName);
        if (file.isFile()) {
            dt = TimeUtil.getTime2String(file.lastModified(), "");
        }
        return dt;
    }

    /**
     * 判断目录是否存在
     */
    public static boolean checkDirectoryIsExists(Context mContext,
                                                 String dirName) {
        File fileDir = mContext.getFilesDir();
        String sFileName = fileDir.getParent() + File.separator
                + fileDir.getName() + File.separator + dirName;
        File file = new File(sFileName);
        return file.isDirectory();
    }

    /**
     * 创建新的目录
     */
    public static boolean createDirectory(Context mContext, String dirName) {
        File fileDir = mContext.getFilesDir();
        String sFileName = fileDir.getParent() + File.separator
                + fileDir.getName() + File.separator + dirName;
        File file = new File(sFileName);
        boolean success = file.mkdir();
        return success;
    }

    /**
     * 通过本地图片路径返回bitmap
     *
     * @param context
     * @param fileName     图片文件名
     * @param defaultImage 未解析成功后显示的错误图片资源
     * @return
     */
    public static Bitmap loadBitmapFromFile(Context context, String fileName,
                                            int defaultImage) {
        Bitmap bm = null;
        String bmpPath = context.getFilesDir() + File.separator
                + fileName;
        bm = BitmapFactory.decodeFile(bmpPath);
        if (bm == null) {
            bm = BitmapFactory.decodeResource(context.getResources(),
                    defaultImage);
        }
        return bm;
    }

    /**
     * 检查sd卡是否存在如果存在
     *
     * @return 返回sd File卡路径
     */
    public static File getSDRootPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
        }
        return sdDir;
    }

    /**
     * 清除文件
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                for (File files : file.listFiles()) {
                    deleteFile(files);
                }
            }
            file.delete();
        }
    }

    /**
     * 删除小于给定时间的缓存数据
     *
     * @param dir
     * @param numDays
     * @return 清除文件数量
     */
    public static int clearCacheFolder(File dir, long numDays) {
        int deletedFiles = 0;
        if (dir != null && dir.isDirectory()) {
            try {
                for (File child : dir.listFiles()) {
                    if (child.isDirectory()) {
                        deletedFiles += clearCacheFolder(child, numDays);
                    }
                    if (child.lastModified() < numDays) {
                        if (child.delete()) {
                            deletedFiles++;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deletedFiles;
    }

    /**
     * 获取缓存文件大小（Data+sdcard目录的）
     * 格式化字符串
     *
     * @param context
     * @param cacheRoot 缓存目录
     * @return
     * @throws Exception
     */
    public static String getCacheSize(Context context, String cacheRoot) {
        File fileCache = context.getCacheDir();
        File temp = new File(FileUtil.getSDRootPath(), cacheRoot);//sdcard 目录缓存大小

        long size = 0;
        try {
            size = getFileSize(fileCache);
            size += getFileSize(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formetFileSize(size);
    }

    /**
     * 获取文件/文件夹大小
     *
     * @param f
     * @return
     * @throws Exception
     */
    public static long getFileSize(File f) throws Exception {
        long size = 0;
        if (f.exists()) {
            if (f.isFile()) {//文件
                size += f.length();
            } else {//文件夹
                File flist[] = f.listFiles();
                for (File file : flist) {
                    if (file.isDirectory()) {
                        size += getFileSize(file);
                    } else if (file.isFile()) {
                        size += file.length();
                    }
                }
            }
        }
        return size;
    }

    /**
     * 格式化文件大小
     *
     * @param filesize 文件大小
     * @return
     */
    public static String formetFileSize(long filesize) {
        DecimalFormat df = new DecimalFormat("0.00");
        String fileSizeString = "";
        if (filesize < 1024) {
            fileSizeString = df.format((double) filesize) + "B";
        } else if (filesize < 1048576) {
            fileSizeString = df.format((double) filesize / 1024) + "KB";
        } else if (filesize < 1073741824) {
            fileSizeString = df.format((double) filesize / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) filesize / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 递归求取目录文件个数
     *
     * @param f
     * @return
     */
    public static long getFileNum(File f) {
        long size = 0;
        File flist[] = f.listFiles();
        size = flist.length;
        int length = flist.length;
        for (int i = 0; i < length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileNum(flist[i]);
                size--;
            }
        }
        return size;
    }

    /**
     * 获取本地图片路径
     *
     * @param cachedir 缓存根目录
     * @param httpUrl  网络地址
     * @return 本地缓存文件
     */
    public static File getLocalFilePath(Context context, String cachedir, String httpUrl) {

        String bitmapName = getFileName(httpUrl);
        File cachefile = new File(getCachePath(context, cachedir), bitmapName);
        return cachefile;
    }

    /**
     * 返回缓存文件目录对象
     *
     * @param context
     * @param path    文件目录
     * @return file sdcard 或cache 目录dir
     */
    public static File getCachePath(Context context, String path) {
        File picfile = null;
        if (null == FileUtil.getSDRootPath()) {
            picfile = new File(context.getCacheDir() + path);
        } else {
            picfile = new File(FileUtil.getSDRootPath() + path);
        }
        if (!picfile.exists())
            picfile.mkdirs();
        return picfile;
    }

    /**
     * 根据文件绝对路径获取文件名
     *
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath) {
        if (filePath == null || "".equals(filePath))
            return "";
        return filePath.substring(filePath.lastIndexOf(File.separator) + 1);
    }

    /**
     * 写图片文件到存储卡中
     *
     * @param filePath 全路径名
     * @param bitmap   图片数据
     * @param quality  压缩比例
     * @throws IOException
     */
    public static void saveImageToStorage(String filePath, Bitmap bitmap, int quality) throws IOException {
        if (bitmap != null) {
            File bitmapFile = new File(filePath);
            FileOutputStream fos = new FileOutputStream(bitmapFile);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.JPEG, quality, stream);
            byte[] bytes = stream.toByteArray();
            fos.write(bytes);
            fos.close();
        }
    }

    /**
     * 文件拷贝
     *
     * @param fromFile
     * @param toFile
     */
    public static void copyfile(File fromFile, File toFile) {
        if (!fromFile.exists()) {
            return;
        }
        if (!fromFile.isFile()) {
            return;
        }
        if (!fromFile.canRead()) {
            return;
        }
        try {
            FileInputStream fosfrom = new FileInputStream(fromFile);
            FileOutputStream fosto = new FileOutputStream(toFile);
            byte bt[] = new byte[1024];
            int c;
            while ((c = fosfrom.read(bt)) > 0) {
                fosto.write(bt, 0, c); // 将内容写到新文件当中
            }
            fosfrom.close();
            fosto.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取apk或其他文件大小
     *
     * @throws Exception
     */
    public static String getApkSize(File apk) throws Exception {
        return formetFileSize(getFileSize(apk));
    }

    /**
     * 文件解压解压缩
     *
     * @param zipfile：需要解压缩的文件如：apk等zip包
     * @param descDir：解压后的目标目录
     * @return true 代表成功、false 失败
     * @throws IOException
     * @throws ZipException
     */
    public static void unCompress(File zipfile, String descDir) throws ZipException, IOException {
        ZipFile zf = new ZipFile(zipfile);
        for (Enumeration entries = zf.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zf.getInputStream(entry);
            File file = new File(descDir, zipEntryName);
            if (!file.exists()) {
                if (!file.isDirectory()) {//是文件
                    File dirFile = file.getParentFile();//获取文件的父目录
                    if (!dirFile.exists())
                        dirFile.mkdirs();
                } else {
                    file.mkdirs();
                }
            }
            //输出文件路径信息
            //System.out.println(file);
            OutputStream out = new FileOutputStream(file);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
    }

    //-----------------------------文件压缩begin----------------------------------------------
    public static final String EXT = ".zip";
    private static final String BASE_DIR = "";

    // 符号"/"用来作为目录标识判断符
    private static final String PATH = File.separator;
    private static final int BUFFER = 1024;

    /**
     * 默认压缩
     *
     * @param srcFile 源路径
     * @throws Exception
     */
    public static void compress(File srcFile) throws Exception {
        String name = srcFile.getName();
        String basePath = srcFile.getParent();
        String destPath = basePath + File.separator + name + EXT; //默认为当前被压缩的文件名
        compress(srcFile, destPath);
    }

    /**
     * 压缩
     *
     * @param srcFile  源路径
     * @param destFile 目标路径
     * @throws Exception
     */
    public static void compress(File srcFile, File destFile) throws Exception {

        // 对输出文件做CRC32校验
        CheckedOutputStream cos = new CheckedOutputStream(new FileOutputStream(
                destFile), new CRC32());

        ZipOutputStream zos = new ZipOutputStream(cos);

        compress(srcFile, zos, BASE_DIR);

        zos.flush();
        zos.close();
    }

    /**
     * 压缩文件
     *
     * @param srcFile
     * @param destPath
     * @throws Exception
     */
    public static void compress(File srcFile, String destPath) throws Exception {
        compress(srcFile, new File(destPath));
    }

    /**
     * 压缩
     *
     * @param srcFile  源路径
     * @param zos      ZipOutputStream
     * @param basePath 压缩包内相对路径
     * @throws Exception
     */
    private static void compress(File srcFile, ZipOutputStream zos,
                                 String basePath) throws Exception {
        if (srcFile.isDirectory()) {
            compressDir(srcFile, zos, basePath);
        } else {
            compressFile(srcFile, zos, basePath);
        }
    }

    /**
     * 压缩
     *
     * @param srcPath
     * @throws Exception
     */
    public static void compress(String srcPath) throws Exception {
        File srcFile = new File(srcPath);

        compress(srcFile);
    }

    /**
     * 文件压缩
     *
     * @param srcPath  源文件路径
     * @param destPath 目标文件路径
     */
    public static void compress(String srcPath, String destPath)
            throws Exception {
        File srcFile = new File(srcPath);

        compress(srcFile, destPath);
    }

    /**
     * 压缩目录
     *
     * @param dir
     * @param zos
     * @param basePath
     * @throws Exception
     */
    private static void compressDir(File dir, ZipOutputStream zos,
                                    String basePath) throws Exception {

        File[] files = dir.listFiles();

        // 构建空目录
        if (files.length < 1) {
            ZipEntry entry = new ZipEntry(basePath + dir.getName() + PATH);

            zos.putNextEntry(entry);
            zos.closeEntry();
        }

        for (File file : files) {

            // 递归压缩
            compress(file, zos, basePath + dir.getName() + PATH);

        }
    }

    /**
     * 文件压缩
     *
     * @param file 待压缩文件
     * @param zos  ZipOutputStream
     * @param dir  压缩文件中的当前路径
     * @throws Exception
     */
    private static void compressFile(File file, ZipOutputStream zos, String dir)
            throws Exception {

        /**
         * 压缩包内文件名定义
         *
         * <pre>
         * 如果有多级目录，那么这里就需要给出包含目录的文件名
         * 如果用WinRAR打开压缩包，中文名将显示为乱码
         * </pre>
         */
        ZipEntry entry = new ZipEntry(dir + file.getName());

        zos.putNextEntry(entry);

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
                file));

        int count;
        byte data[] = new byte[BUFFER];
        while ((count = bis.read(data, 0, BUFFER)) != -1) {
            zos.write(data, 0, count);
        }
        bis.close();

        zos.closeEntry();
    }
    //-----------------------------文件压缩end----------------------------------------------

    /**
     * 读取assets 文件内容
     *
     * @param context
     * @param fileName 文件名
     * @return
     */
    public static String getFromAssets(Context context, String fileName) {
        InputStreamReader inputReader = null;
        try {
            inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputReader != null)
                try {
                    inputReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return "";
    }


    /**
     * 应用程序内部缓存+外部缓存大小
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static String getTotalCacheSize(Context context) throws Exception {
        long cacheSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(context.getExternalCacheDir());
        }
        return getFormatSize(cacheSize);
    }

    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    /**
     * * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache) * *
     *
     * @param context
     */
    public static void cleanInternalCache(Context context) {
        deleteFilesByDirectory(context.getCacheDir());
    }

    /**
     * * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases) * *
     *
     * @param context
     */
    public static void cleanDatabases(Context context) {
        deleteFilesByDirectory(new File("/data/data/"
                + context.getPackageName() + "/databases"));
    }

    /**
     * * 按名字清除本应用数据库 * *
     *
     * @param context
     * @param dbName
     */
    public static void cleanDatabaseByName(Context context, String dbName) {
        context.deleteDatabase(dbName);
    }

    /**
     * * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs) *
     *
     * @param context
     */
    public static void cleanSharedPreference(Context context) {
        deleteFilesByDirectory(new File("/data/data/"
                + context.getPackageName() + "/shared_prefs"));
    }

    /**
     * * 清除/data/data/com.xxx.xxx/files下的内容 * *
     *
     * @param context
     */
    public static void cleanFiles(Context context) {
        deleteFilesByDirectory(context.getFilesDir());
    }

    /**
     * * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
     *
     * @param context
     */
    public static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(context.getExternalCacheDir());
        }
    }

    /**
     * * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除 * *
     *
     * @param filePath
     */
    public static void cleanCustomCache(String filePath) {
        deleteFilesByDirectory(new File(filePath));
    }

    /**
     * * 清除本应用所有的数据 * *
     *
     * @param context
     */
    public static void cleanApplicationData(Context context) {
        cleanInternalCache(context);
        cleanExternalCache(context);
        cleanDatabases(context);
        cleanSharedPreference(context);
        cleanFiles(context);
    }

    /**
     * * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * *
     *
     * @param directory
     */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }

    /**
     * Context.getExternalFilesDir() -->
     * SDCard/Android/data/你的应用的包名/files/目录，一般放一些长时间保存的数据
     * <p/>
     * Context.getExternalCacheDir() -->
     * SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 删除指定目录下文件及目录
     *
     * @param filePath
     * @param deleteThisPath
     */
    public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {// 如果下面还有文件
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除
                        file.delete();
                    } else {// 目录
                        if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }

}
