package com.hj.utils;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.*;


public class FastDFSClient {
    private static final String CONF_FILENAME = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "fdfs_client.conf";
    private static StorageClient1 storageClient1 = null;


/**
     * 只加载一次.
     */

    static {
        try {
            R.info("=== CONF_FILENAME:" + CONF_FILENAME);
            ClientGlobal.init(CONF_FILENAME);
            TrackerClient trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
            TrackerServer trackerServer = trackerClient.getConnection();
            if (trackerServer == null) {
                R.error("getConnection return null");
            }
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            if (storageServer == null) {
                R.error("getStoreStorage return null");
            }
            storageClient1 = new StorageClient1(trackerServer, storageServer);
        } catch (Exception e) {
            R.error(e.toString());
        }
    }


/**
     * @param file     文件
     * @param fileName 文件名
     * @return 返回Null则为失败
     */

    public static String uploadFile(File file, String fileName) {
        R.debug("=== CONF_FILENAME:" + CONF_FILENAME);
        FileInputStream fis = null;
        try {
            NameValuePair[] meta_list = null; // new NameValuePair[0];
            fis = new FileInputStream(file);
            byte[] file_buff = null;
            if (fis != null) {
                int len = fis.available();
                file_buff = new byte[len];
                fis.read(file_buff);
            }

            String fileid = storageClient1.upload_file1(file_buff, FileUtils.getFileExt(fileName), meta_list);
            return fileid;
        } catch (Exception ex) {
            R.error(ex.toString());
            return null;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    R.error(e.toString());
                }
            }
        }
    }


/**
     * 文件下载
     *
     * @param fileId
     * @return 返回一个流
     */

    public static byte[] download(String fileId) {
        try {
            byte[] bytes = storageClient1.download_file1(fileId);
            return bytes;
        } catch (Exception ex) {
            R.error(ex.toString());
            return null;
        }
    }


/**
     * 文件下载
     *
     * @param fileId
     * @return 返回一个流
     */

    public static InputStream downloadFile(String fileId) {
        try {
            byte[] bytes = storageClient1.download_file1(fileId);
            InputStream inputStream = new ByteArrayInputStream(bytes);
            return inputStream;
        } catch (Exception ex) {
            R.error(ex.toString());
            return null;
        }
    }

}
