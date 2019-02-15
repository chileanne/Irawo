package com.anthony.ekpei.irawo;

/**
 * Created by EKPEI on 6/2/2018.
 */

public class resp {
    public int pages,totalres,totalpagess;

    public resp(int pages, int totalres, int totalpagess) {
        this.pages = pages;
        this.totalres = totalres;
        this.totalpagess = totalpagess;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotalres() {
        return totalres;
    }

    public void setTotalres(int totalres) {
        this.totalres = totalres;
    }

    public int getTotalpagess() {
        return totalpagess;
    }

    public void setTotalpagess(int totalpagess) {
        this.totalpagess = totalpagess;
    }
}
