//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.springbootswagger.page;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageSerializable<T> implements Serializable {
    protected long total;
    protected List<T> list;
}
