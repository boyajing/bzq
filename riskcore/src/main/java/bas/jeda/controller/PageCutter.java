/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.controller;

import java.util.List;

/**
 *  内存分页
 * @author menghui
 */
public class PageCutter {
    private int pageSize;
    private int pageIndex;
    private List us;
    
    //add by yyl
    private int rowCnt;
    private int beginIndex;
    private int endIndex;
    
    public int getBeginIndex() {
        beginIndex=(getPageIndex()-1)*getPageSize() + 1;

		return beginIndex;
	}

	public int getEndIndex() {
		beginIndex=(getPageIndex()-1)*getPageSize() + 1;
		endIndex = (beginIndex+getPageSize())>rowCnt?rowCnt:(beginIndex+getPageSize()-1);

		return endIndex;
	}

	public int getRowCnt() {
		return rowCnt;
	}
	public void setRowCnt(int rowCnt) {
		this.rowCnt = rowCnt;
	}
	public int pageCount(){
		if (getUs() != null) {
			return getUs().size()/getPageSize()+(getUs().size()%getPageSize()==0?0:1);
		}else {
			return this.rowCnt/getPageSize()+(this.rowCnt%getPageSize()==0?0:1);
		}
    }
    public List  cutList(){
        int pc=this.pageCount();
        if(getPageIndex()<1){
            setPageIndex(1);
        }
        if(getPageIndex()>pc){
            setPageIndex(pc);
        }
        
        int beginIndex=(getPageIndex()-1)*getPageSize();
        List L=getUs().subList(beginIndex, (beginIndex+getPageSize())>getUs().size()?getUs().size():(beginIndex+getPageSize()));
        
        return L;
        
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the pageIndex
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * @param pageIndex the pageIndex to set
     */
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * @return the us
     */
    public List getUs() {
        return us;
    }

    /**
     * @param us the us to set
     */
    public void setUs(List us) {
        this.us = us;
    }
}
