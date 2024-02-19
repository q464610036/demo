package com.poi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Text {

    private String text;
    private UnderlinePatterns underlinePatterns;

}
