package com.example.message.module.message.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 文件与文件夹表
 * </p>
 *
 * @author chenmengfei
 * @since 2023-04-23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageSendDTO implements Serializable {

private static final long serialVersionUID = 1L;

	private String title;

	private String content;

	private String type;
}
