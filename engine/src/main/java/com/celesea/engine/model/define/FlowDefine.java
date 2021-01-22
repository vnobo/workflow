package com.celesea.engine.model.define;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;

/**
 * com.celesea.engine.core.flow.FlowDinef
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/18
 */
@Data
@Table("wf_define")
public class FlowDefine {

    @Id
    private Long id;
    private String version;
    private String name;
    private String describe;
    private ByteBuffer source;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

}
