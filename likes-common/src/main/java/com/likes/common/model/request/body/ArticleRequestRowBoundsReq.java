package com.likes.common.model.request.body;

import com.likes.common.model.request.ArticleRequest;
import org.apache.ibatis.session.RowBounds;

/**
 * @ClassName
 * @Description ArticleRequestRowBoundsReq
 * @Version 1.0
 **/
public class ArticleRequestRowBoundsReq {

    private ArticleRequest articleRequest;

    private RowBounds rowBounds;

    public ArticleRequestRowBoundsReq() {
    }

    public ArticleRequest getArticleRequest() {
        return articleRequest;
    }

    public void setArticleRequest(ArticleRequest articleRequest) {
        this.articleRequest = articleRequest;
    }

    public RowBounds getRowBounds() {
        return rowBounds;
    }

    public void setRowBounds(RowBounds rowBounds) {
        this.rowBounds = rowBounds;
    }
}
