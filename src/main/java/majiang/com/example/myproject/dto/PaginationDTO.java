package majiang.com.example.myproject.dto;


import java.util.ArrayList;
import java.util.List;

public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevoous;//是否有向前按钮
    private boolean showFirstPage;//是否有第一页按钮
    private boolean showNext;//是否有后一页按钮
    private boolean showEndPage;//是否有最后页按钮
    private Integer page;//当前页面
    private Integer totalPage;//总页面数
    private List<Integer> pages = new ArrayList<>();

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    public boolean isShowPrevoous() {
        return showPrevoous;
    }

    public void setShowPrevoous(boolean showPrevoous) {
        this.showPrevoous = showPrevoous;
    }

    public boolean isShowFirstPage() {
        return showFirstPage;
    }

    public void setShowFirstPage(boolean showFirstPage) {
        this.showFirstPage = showFirstPage;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public boolean isShowEndPage() {
        return showEndPage;
    }

    public void setShowEndPage(boolean showEndPage) {
        this.showEndPage = showEndPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        this.page = page;
        //所有显示的页面将会加到pages里
        pages.add(page);
        for (int i = 1; i <= 2; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);//头部插入
            }
            if (page + i <= totalPage) {
                pages.add(page + i);//尾部插入
            }
        }
        //是否展示上一页按钮
        if (page == 1) {
            showPrevoous = false;
        } else {
            showPrevoous = true;
        }
        //是否展示下一页按钮
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }
        //是否展示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        //是否展示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
