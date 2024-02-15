package com.alexdev.app.springbootdatajpa.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageItem {
    private int index;
    private boolean current;
}