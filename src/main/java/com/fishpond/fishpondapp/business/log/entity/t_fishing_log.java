package com.fishpond.fishpondapp.business.log.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

/**
 * 钓鱼日志表 实体
 *
 * @author zhucj
 * @since 2025-08-25
*/
@Data
@Accessors(chain = true)
@Schema(description = "钓鱼日志表")
@TableName("t_fishing_log")
public class t_fishing_log {
    /** 日志ID */
    @TableId
    //@NotBlank(message = "日志ID不能为空")
    @Schema(description = "日志ID")
    private String id;

    /** 是否删除: 0未删除 1已删除 */
    @NotNull(message = "是否删除: 0未删除 1已删除不能为null")
    @Schema(description ="是否删除: 0未删除 1已删除")
    private Integer deleted;

    /** 使用装备 */
    @Schema(description ="使用装备")
    private String equipment;

    /** 鱼种 */
    @Schema(description ="鱼种")
    private String fishType;

    /** 钓点标记 */
    @Schema(description ="钓点标记")
    private String location;

    /** 鱼获照片URL */
    @Schema(description ="鱼获照片URL")
    private String picture;

    /** 用户ID */
    @NotBlank(message = "用户ID不能为空")
    @Schema(description ="用户ID")
    private String userId;

    /** 重量(kg) */
    @Schema(description ="重量(kg)")
    private Double weight;
}