package com.alexdev.app.springbootdatajpa.util;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Paginator <T> {
    private final String url;

    private final Page<T> page;

    private final int totalPages;

    private final int countElementsByPage;

    private final int currentIndexPage;

    private final List<PageItem> pages;

    public Paginator(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.pages = new ArrayList<>();

        countElementsByPage = page.getSize() != 0? page.getSize() : 5;
        totalPages = page.getTotalPages();
        currentIndexPage = page.getNumber() + 1;

        int from, to;
        if (totalPages <= countElementsByPage) {
            from = 1;
            to = totalPages;
        } else {
            if (currentIndexPage <= countElementsByPage / 2) {
                from = 1;
                to = countElementsByPage;
            } else if (currentIndexPage >= totalPages - countElementsByPage / 2) {
                from = totalPages - countElementsByPage + 1;
                to = countElementsByPage;
            } else {
                from = currentIndexPage - countElementsByPage / 2;
                to = countElementsByPage;
            }
        }

        for (int i = 0; i < to; i++) {
            pages.add(new PageItem(from + i, currentIndexPage == from + i));
        }
    }

    //Funcionalidades para movernos con el paginator en Thymeleaf:
    public boolean isFirst() {
        return page.isFirst();
    }

    public boolean isLast() {
        return page.isLast();
    }

    public boolean isHasNext() {
        return page.hasNext();
    }

    public boolean isHasPrevious() {
        return page.hasPrevious();
    }
}