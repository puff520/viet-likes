package com.likes.common.model;

import java.util.ArrayList;

public class NoAuthInterface {

    /**
     * 不需要验证的权限接口
     *
     * @return
     */
    public static java.util.List<String> noAuthInterfaceList() {
        java.util.List<String> noAuthInterfaceList = new ArrayList<String>();
        noAuthInterfaceList.add("/cdn/refresh");
        // 上传单个图片
        noAuthInterfaceList.add("/video/awsupload/uploadSingleImageFile");
        // 获取APP官方账号
        noAuthInterfaceList.add("/getOperatorList");
        // 广告图片上传
        noAuthInterfaceList.add("/ad/awsupload/uploadAdImage");
        // 广告位详细
        noAuthInterfaceList.add("/ad/getBannerseatDetail");
        // 贴文上传视频保存,贴文上传视频编辑
        noAuthInterfaceList.add("/article/awsupload/uploadVideoFile");
        // 贴文详细
        noAuthInterfaceList.add("/article/getArticleDetail");
        // 贴文多图上传保存,贴文多图上传编辑
        noAuthInterfaceList.add("/article/awsupload/manyArticlePicFileUpload");
        noAuthInterfaceList.add("/order/awsupload/manyBusinessPicFileUpload");
        // 业务参数获取
        noAuthInterfaceList.add("/param/getChildBypcode");
        noAuthInterfaceList.add("/busparam/getChild");



        // 修改密码
        noAuthInterfaceList.add("/modpassword");
        // 业务参数列表树(包含子孙)
        noAuthInterfaceList.add("/busparam/listsub");
        // 业务参数详情
        noAuthInterfaceList.add("/busparam/getDetail");
        // 功能获取选择树
        noAuthInterfaceList.add("/permission/getSysSelectFunctionorgTree");
        // 后台用户角色关系删除
        noAuthInterfaceList.add("/permission/adminRroleDel");
        // 后台用户角色关系新增
        noAuthInterfaceList.add("/permission/adminRroleAdd");
        // 系统用户详细
        noAuthInterfaceList.add("/permission/getBdUserDetail");
        //用于系统用户新增/编辑角色
        noAuthInterfaceList.add("/permission/roleListForSystemUser");
        // 系统通知查询用户
        noAuthInterfaceList.add("/sys/findUsers");
        // 系统参数根据code获取详情
        noAuthInterfaceList.add("/sysparam/getByCode");
        // 系统参数根据id详情
        noAuthInterfaceList.add("/sysparam/getDetail");
        // 标签详细,话题详细
        noAuthInterfaceList.add("/tags/getSysTagsDetail");
        // 用户详细
        noAuthInterfaceList.add("/user/getUserDetail");
        // 短视讯上传视频保存,短视讯上传视频编辑
        noAuthInterfaceList.add("/video/awsupload/uploadVideoFile");
        // 短视讯上传视频封面保存,短视讯上传视频封面编辑
        noAuthInterfaceList.add("/video/awsupload/uploadImageFile");
        //短视讯详细用于更新
        noAuthInterfaceList.add("/video/getVideoDetailForUpdate");
        //短视讯详细
        noAuthInterfaceList.add("/video/getVideoDetail");
        //禁言强制（管理后台）
        noAuthInterfaceList.add("/talk/doNotalk");
        //关联主播批量新增
        //noAuthInterfaceList.add("/family/doSaveManyFamilymem");
        //棋牌游戏平台
        noAuthInterfaceList.add("/chess/getChessPlatform");

        //noAuthInterfaceList.add("/payaccount/doSaveSysThreepayset");
        //noAuthInterfaceList.add("/payaccount/doUpdateSysThreepayset");
        //noAuthInterfaceList.add("/payaccount/doDelSysThreepayset");
        //noAuthInterfaceList.add("/payaccount/doUpdateSysThreepaysetStatus");

        //家族
        noAuthInterfaceList.add("/family/allFamilyList");
        noAuthInterfaceList.add("/family/incarnate/incarnateHistoryOne");
        noAuthInterfaceList.add("/family/incarnate/getAnchorIncomeList");

        //彩票详细
        noAuthInterfaceList.add("/bet/getCaiDetail");
        noAuthInterfaceList.add("/payaccount/getAllsysPayproviderlist");
        noAuthInterfaceList.add("/chess/getRecordHandle");



        noAuthInterfaceList.add("/member/ranking");
        noAuthInterfaceList.add("/msg/InfSysnoticeList");
        noAuthInterfaceList.add("/param/getChildBypcode");
        noAuthInterfaceList.add("/taskApp/categoryList");
        noAuthInterfaceList.add("/ad/adList?seatcode=app");
        return noAuthInterfaceList;

    }

}
