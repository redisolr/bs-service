package com.qtxln.model.user;

import lombok.Data;

@Data
public class Permission {
    private Long id;

    private String name;

    private Long parentId;

    private String path;

    private Boolean isParent;
}