package com.poi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String index;
    private String contractNO;
    private String company;
    private String address;
    private String name;
    private String phoneNo;
    private String dutyParagraph;

}
