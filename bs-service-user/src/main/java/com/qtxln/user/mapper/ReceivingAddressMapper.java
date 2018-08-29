package com.qtxln.user.mapper;

import com.qtxln.model.user.ReceivingAddress;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QT
 * 2018-08-28 15:30
 */
@Mapper
@Repository
public interface ReceivingAddressMapper {

    /**
     * 添加收货地址
     *
     * @param receivingAddress
     */
    @Insert("INSERT INTO u_receiving_address (user_id,contact_name,contact_phone,address_detail,is_default,gmt_create) VALUES" +
            " (#{userId},#{contactName},#{contactPhone},#{addressDetail},#{isDefault},now())")
    void insert(ReceivingAddress receivingAddress);

    /**
     * 查询收货地址列表
     *
     * @param userId Long
     * @return List
     */
    @Select("SELECT id,user_id,contact_name,contact_phone,address_detail,is_default,gmt_create FROM u_receiving_address  WHERE user_id = #{userId}")
    List<ReceivingAddress> findAll(Long userId);

    /**
     * 根据id查询收货地址
     *
     * @param id Long
     * @return ReceivingAddress
     */
    @Select("SELECT id,user_id,contact_name,contact_phone,address_detail,is_default,gmt_create FROM u_receiving_address WHERE id = #{id}")
    ReceivingAddress findById(Long id);

    /**
     * 编辑收货地址
     *
     * @param receivingAddress ReceivingAddress
     */
    @Update("UPDATE u_receiving_address SET contact_name = #{contactName},contact_phone = #{contactPhone},address_detail = #{addressDetail}" +
            ",is_default = #{isDefault},gmt_update = now() WHERE id = #{id}")
    void update(ReceivingAddress receivingAddress);

    /**
     * 根据用户id设置默认值
     * @param userId Long
     */
    @Update("UPDATE u_receiving_address SET is_default = false,gmt_update = now() WHERE user_id = #{userId} AND is_default = true")
    void updateNoDefault(Long userId);

    /**
     * 删除收货地址
     *
     * @param id long
     */
    @Delete("DELETE FROM u_receiving_address WHERE id = #{id}")
    void delete(Long id);

}
