//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.common.page;

import lombok.Data;

@Data
public class PageInfo<T> extends PageSerializable<T> {
    private int pageNum;
    private int pageSize;
    private int size;
    private long startRow;
    private long endRow;
    private int pages;
    private int prePage;
    private int nextPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private int navigatePages;
    private int[] navigatepageNums;
    private int navigateFirstPage;
    private int navigateLastPage;
}
