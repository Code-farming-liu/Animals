package com.liu.animal.app.service;

import java.util.List;

public interface ServiceParent<T> {
    public List<T> findAll(Integer page,Integer num);
    public int addInfo(T t);
    public int updateById(T t);
    public int deleteById(String id);
    public T findById(String id);
    public String count();
}
