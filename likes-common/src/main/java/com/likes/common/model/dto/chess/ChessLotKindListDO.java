package com.likes.common.model.dto.chess;

import java.util.List;

public class ChessLotKindListDO {
    private String name;

    private List<ChessLotKindDO> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChessLotKindDO> getList() {
        return list;
    }

    public void setList(List<ChessLotKindDO> list) {
        this.list = list;
    }
}
