package com.psx.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.psx.entity.Book;
import com.psx.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    //TODO: 查询特定书
    public Page<Book> getBooksByPage(int current, int size) {
        return bookMapper.selectPage(new Page<>(current, size), null);
    }

    //TODO:查询所有书
    public Page<Book> getBooksByPage(int current, int size, String name, String author) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(name)) {
            queryWrapper.like("name", name);
        }
        if (StringUtils.hasText(author)) {
            queryWrapper.like("author", author);
        }
        return bookMapper.selectPage(new Page<>(current, size), queryWrapper);
    }

    //TODO: 添加书
    public void insert(Book book) {
        bookMapper.insert(book);
    }

    //TODO: 修改书的状态
    public void updateStatus(Integer id) {
        Book book = bookMapper.selectById(id);
        if (book != null) {
            book.setStatus(book.getStatus().equals("已借出") ? "可借阅" : "已借出");
            bookMapper.updateById(book);
        }

    }
}
