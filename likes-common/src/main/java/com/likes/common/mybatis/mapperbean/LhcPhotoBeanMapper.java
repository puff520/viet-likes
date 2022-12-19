package com.likes.common.mybatis.mapperbean;

import com.likes.common.model.vo.lottery.LhcPhotoCategoryVO;
import com.likes.common.model.vo.lottery.LhcPhotoVO;
import com.likes.common.mybatis.mapperbean.provider.lotterymanage.LotteryDynaSqlProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface LhcPhotoBeanMapper {

    /**
     * 根据父级id获取六合彩图库的分类
     *
     * @param parentId
     * @return
     */
    @Select("SELECT l.id,l.name,l.parent_id as parentId,l.sort FROM lhc_photo_category l WHERE l.deleted = 0 and l.parent_id = #{parentId} order by sort desc")
    List<LhcPhotoCategoryVO> findLhcPhotoCategoryByParentId(@Param("parentId") Integer parentId);

    /**
     * 根据条件获取六合彩图库的数量
     *
     * @param issue
     * @param oneId
     * @param twoId
     * @return
     */
    @SelectProvider(type = LotteryDynaSqlProvider.class, method = "countLhcPhoto")
    int countLhcPhoto(@Param("issue") String issue, @Param("oneId") Integer oneId, @Param("twoId") Integer twoId);

    @SelectProvider(type = LotteryDynaSqlProvider.class, method = "listLhcPhoto")
    List<LhcPhotoVO> listLhcPhoto(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("issue") String issue, @Param("oneId") Integer oneId, @Param("twoId") Integer twoId);

    @Select("SELECT l.id, l.issue, l.url, c.id as twoCategoryId, c.parent_id as oneCategoryId FROM lhc_photo l LEFT JOIN lhc_photo_category c ON l.category_id = c.id  WHERE l.deleted = 0 and l.id = #{photoId}")
    LhcPhotoVO findLhcPhotoById(@Param("photoId") Integer photoId);

    /**
     * 根据条件获取六合彩图库分类的数量
     *
     * @param parentId
     * @param name
     * @return
     */
    @SelectProvider(type = LotteryDynaSqlProvider.class, method = "countLhcPhotoCategory")
    int countLhcPhotoCategory(@Param("parentId") Integer parentId, @Param("name") String name);

    @SelectProvider(type = LotteryDynaSqlProvider.class, method = "listLhcPhotoCategory")
    List<LhcPhotoCategoryVO> listLhcPhotoCategory(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("parentId") Integer parentId, @Param("name") String name);
}
