package com.qtxln.model.manager.dto;

import lombok.Data;

import java.util.List;

/**
 * @author QT
 * 2018-08-03 18:04
 */
@Data
public class PanelDTO {

    private Long id;

    private String panelName;

    private Long typeId;

    private String typeName;

    private Long sort;

    private Byte status;

    private String remark;

    private Integer maxPicture;

    private List<PanelContentDTO> panelContents;
}
