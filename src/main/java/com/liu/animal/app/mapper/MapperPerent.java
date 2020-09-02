package com.liu.animal.app.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MapperPerent<T>{
    public List<T> selectAll(@Param("page") Integer page,@Param("num") Integer num);
    public int insertInfo(T t);
    public int updateById(T t);
    public int deleteById(String id);
    public T selectById(String id);
    public String count();
}
