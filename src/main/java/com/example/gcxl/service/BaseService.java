package com.example.gcxl.service;

import com.example.gcxl.domain.AttachmentFile;
import com.example.gcxl.domain.ModifyLogWithDetails;

import java.util.List;

/**
 * @ClassName: BaseMapper
 * @author: Eason
 * @data: 2022/4/19 14:46
 */
public interface BaseService {
    /**
     * 通过type即前端中的标题列来得到修改日志
     * @param typeID
     * @return
     */
    List<ModifyLogWithDetails> getModifyWithDetailByTypeID(String typeID);

    void insertFile(AttachmentFile attachmentFile);

    void deleteFile(int id);

    List<AttachmentFile> getFilesByTypeID(String typeID);

    void updateNumberAttachments(int numberAttachments, String contractID);

    void updateInvoiceNumberAttachments(int numberAttachments, String invoiceID);
}
