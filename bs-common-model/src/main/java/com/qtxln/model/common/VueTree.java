package com.qtxln.model.common;

import lombok.Data;

import java.util.List;

/**
 * @author QT
 * 2018-07-25 20:54
 */
@Data
public class VueTree {
    private long id;
    private String title;
    private boolean expand;
    private List<VueTree> children;
    private boolean isParent;
    private long parentId;
}
