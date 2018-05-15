package com.jinhui.mapper;

import com.jinhui.domain.Attachment;

import java.util.List;

/**
 * Created by jinhui on 2018/3/16.
 */
public interface ExtraMapper {

    void saveAttachment(Attachment attachment);
    List<Attachment> findAttachments(List<Long> ids);
}
