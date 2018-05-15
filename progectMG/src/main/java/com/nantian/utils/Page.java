package com.nantian.utils;

/**
 * Created by xiongyiwu on 2016/4/18.
 */

public class Page {
    private int totalCount;//总的记录
    private int countOfPage;//总的页数
    private int currentPage;//当前页数
    private boolean hasNextPage;//是否有下一页
    private boolean hasPrePage;//是否有上一页
    private int rowOfPage;//每页显示的行数
    private String toolBar;//分页工具条
    private static final int MAXPAGE = 4;

    public Page(int rowOfPage) {
        this.totalCount = 0;
        this.currentPage = 1;
        this.rowOfPage = rowOfPage;
    }

    public Page(int totalCount, int currentPage, int rowOfPage) {

        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.rowOfPage = rowOfPage;
    }


    public int getCountOfPage() {

        int countOfPage = 1;

        if (totalCount % rowOfPage == 0)
            countOfPage = totalCount / rowOfPage;
        else
            countOfPage = totalCount / rowOfPage + 1;


        return countOfPage;
    }

    public String getToolBar() {
        StringBuffer toolBar = new StringBuffer();
        if (getCountOfPage() == 0) {
            toolBar.append(" <div style=\"text-align: center\">查询无结果</div>");
        } else {
            toolBar.append("<div class=\"page red\"  style=\"margin: auto;\">");
            toolBar.append("<div class=\"pbtn \" onclick=\"firstPage()\">首页</div>");
            toolBar.append("<div class=\"pbtn \" id=\"upperpage\" onclick=\"upperPage()\">上一页</div>");
            toolBar.append("<ul>");
            if (getCountOfPage() <= MAXPAGE) {
                for (int i = 1; i <= getCountOfPage(); i++) {
                    toolBar.append("<li><a onclick=\"getInfo(this)\" href=\"javascript:void(0)\" class=\"red normal\" id=\"" + i + "\">" + i + "</a></li>");
                }
            } else {
                if (currentPage <= MAXPAGE - 2) {
                    for (int i = 1; i <= currentPage; i++) {
                        toolBar.append("<li><a onclick=\"getInfo(this)\" href=\"javascript:void(0)\" class=\"red normal \" id=\"" + i + "\">" + i + "</a></li>");
                    }
                } else if (currentPage < getCountOfPage() - 2) {
                    for (int i = 0; i < MAXPAGE; i++) {
                        toolBar.append("<li><a onclick=\"getInfo(this)\" href=\"javascript:void(0)\" class=\"red normal\" id=\"" + (currentPage + i - 1) + "\">" + (currentPage + i - 1) + "</a></li>");
                    }
                } else {
                    for (int i = getCountOfPage() - MAXPAGE + 1; i <= getCountOfPage(); i++) {
                        toolBar.append("<li><a onclick=\"getInfo(this)\" href=\"javascript:void(0)\" class=\"red normal\" id=\"" + i + "\">" + i + "</a></li>");
                    }
                }
            }
            toolBar.append("</ul>");
            toolBar.append("<div class=\"pbtn\" id=\"nextpage\" onclick=\"nextPage()\">下一页</div>");
            toolBar.append("<div class=\"pbtn\" onclick=\"lastPage()\">尾页</div>");
            toolBar.append("<div class=\"short\"><input type=\"text\" value=\"1\" id=\"gopage\"><input class=\"pbtn\" value=\"跳转\"  type=\"button\" onclick=\"goPage()\"></div>");
            toolBar.append("</div>");
        }
        return toolBar.toString();
    }
}
