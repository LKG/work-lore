package im.heart.core.support.upload;

import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 *
 * @author gg
 * @desc 文件上传监听
 * https://blog.csdn.net/zmx729618/article/details/51881084
 */
@Component
public class UploadProgressListener implements ProgressListener {
    private HttpSession session;

    public void setSession(HttpSession session){
        this.session = session;
        ProgressModel status = new ProgressModel();
        session.setAttribute(ProgressModel.UPLOAD_STATUS_KEY, status);
    }

    /**
     * 更新状态
     * @param bytesRead 到目前为止读取文件的比特数 p
     * @param contentLength 文件总大小
     * @param items 目前正在读取第几个文件
     */
    @Override
    public void update(long bytesRead, long contentLength, int items) {
        ProgressModel status = (ProgressModel) this.session.getAttribute(ProgressModel.UPLOAD_STATUS_KEY);
        status.setBytesRead(bytesRead);
        status.setContentLength(contentLength);
        status.setItems(items);
    }
}
