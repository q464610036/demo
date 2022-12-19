package com.lambda;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * <p>
 *     填充名字
 * </p>
 * @author 晾、小生
 * @since 2022/12/15 18:52
 */
@Data
@Builder
@AllArgsConstructor
public class FillNameGroup implements Serializable {

    /**
    * 编码
    **/
    private String code;

    /**
    * 填充名字
    **/
    private Consumer<String> fillName;
}
