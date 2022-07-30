package com.example.demo.fetchers;

import java.util.List;

public interface Database<T, DTO> {
   public T create(DTO input);
   public List<T> getAll();
   public T get(Long id);
}
