package im.heart.core.support.upload;

import lombok.Data;

/**
 *
 * @author gg
 * @desc  处理进度Model
 *
 */
@Data
public class ProgressModel {

    public static final String UPLOAD_STATUS_KEY = "upload.status";
    /**
     * 到目前为止读取文件的比特数
     */
    private long bytesRead = 0L;
    /**
     * /文件总大小
     */
    private long contentLength = 0L;
    /**
     * 目前正在读取第几个文件
     */
    private int items;
}
