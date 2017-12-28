package com.zxp.java.utils;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

/**
 * Created by zxp on 2017/12/26.
 * desc:
 */
public class FolderUtils {
    /**
     * 创建完整路径
     *
     * @param path
     *            a {@link java.lang.String} object.
     */
    public static final void mkdirs(final String... path) {
        for (String foo : path) {
            final String realPath = FilenameUtils.normalizeNoEndSeparator(foo, true);
            final File folder = new File(realPath);
            if (!folder.exists() || folder.isFile()) {
                folder.mkdirs();
            }
        }
    }
}
