package com.poi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    private String index;
    private String company;
    private String name;
    private String phoneNo;
    private String number;
    private String names;
    private String nature;
    private String class1;
    private String classroom;

}
