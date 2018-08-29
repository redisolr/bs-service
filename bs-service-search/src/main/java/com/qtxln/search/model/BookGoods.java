package com.qtxln.search.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author QT
 * 2018-07-31 10:42
 */
@Document(indexName = "book_goods", type = "book_goods_list")
@Data
public class BookGoods {
    @Id
    private Long id;

    private String goodsName;

    private BigDecimal goodsPrice;

    private Integer goodsNum;

    private String goodsBarcode;

    private String goodsImage;

    private String className;

    private Byte goodsStatus;

    private Date gmtCreate;
}
