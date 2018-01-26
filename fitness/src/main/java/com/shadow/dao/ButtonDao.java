package com.shadow.dao;


import com.shadow.entity.ButtonEntity;

import java.util.List;

public interface ButtonDao {

    List<ButtonEntity> getButton(int mid);

}
