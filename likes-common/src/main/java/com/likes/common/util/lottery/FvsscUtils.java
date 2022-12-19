package com.likes.common.util.lottery;
import com.likes.common.constant.LotteryInformationType;
import com.likes.common.model.CQSSCPlayRule;
import com.likes.common.model.vo.MapListVO;
import com.likes.common.mybatis.entity.FivesscLotterySg;
import com.likes.common.util.ArrayUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 重庆时时彩功能类
 *
 * @author lzy
 * @create 2018-07-28 11:46
 **/
public class FvsscUtils {

    /**
     * 今日统计
     *
     * @param sgList
     * @return
     */
    public static Map<String, Object> todayCount(List<FivesscLotterySg> sgList) {
        if (CollectionUtils.isEmpty(sgList)) {
            return null;
        }

        int[] arr1 = new int[10];
        int[] arr2 = new int[10];
        int[] arr3 = new int[10];
        int[] arr4 = new int[10];
        int[] arr5 = new int[10];

        int[] total = new int[10];
        int[] sanxing = new int[3]; //组六,豹子,组三
        int[] erxing = new int[2]; //对子,连号

        int totalCount = 0;
        for (FivesscLotterySg sg : sgList) {
            Integer wan = sg.getWan();
            if (wan == null) {
                continue;
            }
            totalCount ++;
            Integer qian = sg.getQian();
            Integer bai = sg.getBai();
            Integer shi = sg.getShi();
            Integer ge = sg.getGe();
            arr1[wan] += 1;
            arr2[qian] += 1;
            arr3[bai] += 1;
            arr4[shi] += 1;
            arr5[ge] += 1;
            total[wan] += 1;
            total[qian] += 1;
            total[bai] += 1;
            total[shi] += 1;
            total[ge] += 1;
            if (bai.equals(shi) && shi.equals(ge)) {
                sanxing[1] += 1;
            } else if (bai.equals(shi) || shi.equals(ge) || bai.equals(ge)) {
                sanxing[2] += 1;
            } else {
                sanxing[0] += 1;
            }
            if (shi.equals(ge)) {
                erxing[0] += 1;
            } else if (shi - ge == 1 || ge - shi == 1) {
                erxing[1] += 1;
            }
        }

        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> mapList = new HashMap<>();
            List<Integer> list1 = new ArrayList<>();
            list1.add(totalCount - arr1[i]);
            list1.add(totalCount - arr2[i]);
            list1.add(totalCount - arr3[i]);
            list1.add(totalCount - arr4[i]);
            list1.add(totalCount - arr5[i]);

            List<Integer> list2 = new ArrayList<>();
            list2.add(arr1[i]);
            list2.add(arr2[i]);
            list2.add(arr3[i]);
            list2.add(arr4[i]);
            list2.add(arr5[i]);

            mapList.put("num", i);
            mapList.put("noOpen", list1); //号码未开次数
            mapList.put("open", list2); //号码已开次数
            list.add(mapList);
        }
        result.put("list", list);
        result.put("numTotal", ArrayUtils.toList(total)); //号码出现次数统计
        result.put("sanxing", ArrayUtils.toList(sanxing));
        result.put("erxing", ArrayUtils.toList(erxing));

        return result;
    }

    /**
     * 今日统计【统计0-9号码个 十 百 千 万出现的次数、大小单双个 十 百 千 万出现的次数】
     *
     * @param sgList
     * @return
     */
    public static Map<String, Object> todayCount1(List<FivesscLotterySg> sgList) {
        if (sgList == null || sgList.size() == 0) {
            return null;
        }

        int[] arr1 = new int[10];
        int[] arr2 = new int[10];
        int[] arr3 = new int[10];
        int[] arr4 = new int[10];
        int[] arr5 = new int[10];

        int[] big = new int[5];
        int[] small = new int[5];
        int[] single = new int[5];
        int[] two = new int[5];

        int bigCount = 0;
        int smallCount = 0;
        int singleCount = 0;
        int twoCount = 0;

        //统计个十百千万 总次数
        int[] total = new int[10];

        int totalIssue = sgList.size();
        for (int i = 0; i < totalIssue; i++) {
            FivesscLotterySg sg = sgList.get(i);
            Integer wan = sg.getWan();
            Integer qian = sg.getQian();
            Integer bai = sg.getBai();
            Integer shi = sg.getShi();
            Integer ge = sg.getGe();


            arr1[wan] += 1;
            arr2[qian] += 1;
            arr3[bai] += 1;
            arr4[shi] += 1;
            arr5[ge] += 1;
            total[wan] += 1;
            total[qian] += 1;
            total[bai] += 1;
            total[shi] += 1;
            total[ge] += 1;


            if (wan % 2 == 0 && wan > 4) {//统计大小单双
                big[0] += 1;
                bigCount++;
                two[0] += 1;
                twoCount++;
            }
            if (wan % 2 != 0 && wan > 4) {
                big[0] += 1;
                bigCount++;
                single[0] += 1;
                singleCount++;
            }
            if (wan % 2 == 0 && wan < 4) {
                two[0] += 1;
                twoCount++;
                small[0] += 1;
                smallCount++;
            }
            if (wan % 2 != 0 && wan < 4) {
                single[0] += 1;
                singleCount++;
                small[0] += 1;
                smallCount++;
            }


            if (qian % 2 == 0 && qian > 4) {
                big[1] += 1;
                bigCount++;
                two[1] += 1;
                twoCount++;
            }
            if (qian % 2 != 0 && qian > 4) {
                big[1] += 1;
                bigCount++;
                single[1] += 1;
                singleCount++;
            }
            if (qian % 2 == 0 && qian < 4) {
                two[1] += 1;
                twoCount++;
                small[1] += 1;
                smallCount++;
            }
            if (qian % 2 != 0 && qian < 4) {
                single[1] += 1;
                singleCount++;
                small[1] += 1;
                smallCount++;
            }

            if (bai % 2 == 0 && bai > 4) {
                big[2] += 1;
                bigCount++;
                two[2] += 1;
                twoCount++;
            }
            if (bai % 2 != 0 && bai > 4) {
                big[2] += 1;
                bigCount++;
                single[2] += 1;
                singleCount++;
            }
            if (bai % 2 == 0 && bai < 4) {
                two[2] += 1;
                twoCount++;
                small[2] += 1;
                smallCount++;
            }
            if (bai % 2 != 0 && bai < 4) {
                single[2] += 1;
                singleCount++;
                small[2] += 1;
                smallCount++;
            }

            if (shi % 2 == 0 && shi > 4) {
                big[3] += 1;
                bigCount++;
                two[3] += 1;
                twoCount++;
            }
            if (shi % 2 != 0 && shi > 4) {
                big[3] += 1;
                bigCount++;
                single[3] += 1;
                singleCount++;
            }
            if (shi % 2 == 0 && shi < 4) {
                two[3] += 1;
                twoCount++;
                small[3] += 1;
                smallCount++;
            }
            if (shi % 2 != 0 && shi < 4) {
                single[3] += 1;
                singleCount++;
                small[3] += 1;
                smallCount++;
            }

            if (ge % 2 == 0 && ge > 4) {
                big[4] += 1;
                bigCount++;
                two[4] += 1;
                twoCount++;
            }
            if (ge % 2 != 0 && ge > 4) {
                big[4] += 1;
                bigCount++;
                single[4] += 1;
                singleCount++;
            }
            if (ge % 2 == 0 && ge < 4) {
                two[4] += 1;
                twoCount++;
                small[4] += 1;
                smallCount++;
            }
            if (ge % 2 != 0 && ge < 4) {
                single[4] += 1;
                singleCount++;
                small[4] += 1;
                smallCount++;
            }


        }

        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>(10);
        for (int j = 0; j < 10; j++) {
            Map<String, Object> mapList = new HashMap<>();
            ArrayList<Integer> list2 = new ArrayList<>(10); //0-9的号码
            list2.add(arr1[j]);  //第几位
            list2.add(arr2[j]);
            list2.add(arr3[j]);
            list2.add(arr4[j]);
            list2.add(arr5[j]);
            mapList.put("num", j);
            mapList.put("open", list2); //号码已开次数
            list.add(mapList);
        }

        result.put("list", list);
        result.put("numTotal", ArrayUtils.toList(total)); //号码出现次数统计
        result.put("bigCount", bigCount);
        result.put("smallCount", smallCount);
        result.put("singleCount", singleCount);
        result.put("twoCount", twoCount);
        result.put("big", big);
        result.put("single", single);
        result.put("small", small);
        result.put("two", two);

        return result;
    }


    /**
     * 历史开奖之开奖
     *
     * @param sgList 开奖结果列表
     * @return
     */
    public static List<Map<String, Object>> lishiKaiJiang(List<FivesscLotterySg> sgList) {
        // 创建结果容器
        List<Map<String, Object>> result = new ArrayList<>();

        // 判空
        if (CollectionUtils.isEmpty(sgList)) {
            return result;
        }

        // 遍历重新组装数据
        List<String> list;
        Map<String, Object> map;
        for (FivesscLotterySg fvsscSg : sgList) {
            list = new ArrayList<>();
            map = new HashMap<>();
            map.put("issue", fvsscSg.getIssue().substring(8));
            list.add(fvsscSg.getWan() == null ? "" : fvsscSg.getWan() + "," + fvsscSg.getQian() + "," + fvsscSg.getBai() + "," + fvsscSg.getShi() + "," + fvsscSg.getGe());
            list.add(CQSSCPlayRule.daxiaoDanShuang(fvsscSg.getShi()));
            list.add(CQSSCPlayRule.daxiaoDanShuang(fvsscSg.getGe()));
            list.add(CQSSCPlayRule.houSan(fvsscSg.getBai(), fvsscSg.getShi(), fvsscSg.getGe()));
            map.put("list", list);
            result.add(map);
        }
        return result;
    }

    /**
     * 历史开奖
     *
     * @param sgList
     * @return
     */
    public static List<Map<String, Object>> lishiSg(List<FivesscLotterySg> sgList) {
        if (sgList == null || sgList.size() == 0) {
            return new ArrayList<>(0);
        }
        int totalIssue = sgList.size();
        ArrayList<Map<String, Object>> result = new ArrayList<>();
        for (int i = 0; i < totalIssue; i++) {
            FivesscLotterySg fvsscSg = sgList.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("issue", fvsscSg.getIssue());
            map.put("time", fvsscSg.getTime());
            map.put("number", new StringBuffer().append(fvsscSg.getWan()).append(fvsscSg.getQian()).append(fvsscSg.getBai())
                    .append(fvsscSg.getShi()).append(fvsscSg).toString());
            map.put("numberstr", new StringBuffer().append(fvsscSg.getWan()).append(fvsscSg.getQian()).append(fvsscSg.getBai())
                    .append(fvsscSg.getShi()).append(fvsscSg.getGe()).toString());
            map.put("he", fvsscSg.getWan() + fvsscSg.getQian() + fvsscSg.getBai() + fvsscSg.getShi() + fvsscSg.getGe());
            result.add(map);
        }
        return result;
    }

    /**
     * 期次详情
     *
     * @param sg
     * @return
     */
    public static Map<String, Object> sgDetails(FivesscLotterySg sg) {
        if (sg == null) {
            return null;
        }
        Integer wan = sg.getWan();
        Integer qian = sg.getQian();
        Integer bai = sg.getBai();
        Integer shi = sg.getShi();
        Integer ge = sg.getGe();
        String wuxing = wan + "," + qian + "," + bai + "," + shi + "," + ge;
        String wuxingdxds = CQSSCPlayRule.DAXIAO_DANSHUANG[wan] + "," + CQSSCPlayRule.DAXIAO_DANSHUANG[qian] + "," + CQSSCPlayRule.DAXIAO_DANSHUANG[bai] + "," + CQSSCPlayRule.DAXIAO_DANSHUANG[shi] + "," + CQSSCPlayRule.DAXIAO_DANSHUANG[ge];
        int wuxingzh = wan + qian + bai + shi + ge;
        Map<String, Object> map = new HashMap<>();
        map.put("issue", sg.getIssue());
        map.put("number", "" + wan + qian + bai + shi + ge);
        map.put("he", wan + qian + bai + shi + ge);
        map.put("wuxing", wuxing);
        map.put("qiansi", wuxing.substring(0, 7));
        map.put("housi", wuxing.substring(2));
        map.put("qiansan", wuxing.substring(0, 5));
        map.put("zhongsan", wuxing.substring(2, 7));
        map.put("housan", wuxing.substring(4));
        map.put("qianer", wuxing.substring(0, 3));
        map.put("houer", wuxing.substring(6));
        map.put("wuxingdxds", wuxingdxds);
        String wuxingzhdx = wuxingzh > 22 ? "大" : "小";
        map.put("wuxingzhdx", wuxingzhdx);
        String wuxingzhdxds = wuxingzhdx + (wuxingzh % 2 == 1 ? "单" : "双");
        map.put("wuxingzhdxds", wuxingzhdxds);
        map.put("qiansidxds", wuxingdxds.substring(0, 11));
        map.put("housidxds", wuxingdxds.substring(3));
        map.put("qiansandxds", wuxingdxds.substring(0, 8));
        map.put("housandxds", wuxingdxds.substring(6));
        map.put("qianerdxds", wuxingdxds.substring(0, 5));
        map.put("houerdxds", wuxingdxds.substring(9));
        return map;
    }

    /**
     * 历史开奖之冷热
     *
     * @param
     * @return
     */
    public static List<MapListVO> lishiLengRe(List<FivesscLotterySg> fvsscSgs, String type) {
        if (fvsscSgs == null || fvsscSgs.size() == 0) {
            return new ArrayList<>(0);
        }
        int totalIssue = fvsscSgs.size();
        ArrayList<MapListVO> result = new ArrayList<>();
        int[] count30 = new int[10];
        int[] count50 = new int[10];
        int[] count100 = new int[10];
        Integer[] countNo = new Integer[10];

        if (LotteryInformationType.CQSSC_LSKJ_LRT_TWO.equals(type)) {
            // 二星的统计
            for (int i = 0; i < totalIssue; i++) {
            	FivesscLotterySg fvsscSg = fvsscSgs.get(i);
                Integer shi = fvsscSg.getShi();
                Integer ge = fvsscSg.getGe();
                if (i < 30) {
                    count30[shi] += 1;
                    count30[ge] += 1;
                } else if (i < 50) {
                    count50[shi] += 1;
                    count50[ge] += 1;
                } else if (i < 100) {
                    count100[shi] += 1;
                    count100[ge] += 1;
                } else {
                    break;
                }
                if (countNo[shi] == null) {
                    countNo[shi] = i;
                }
                if (countNo[ge] == null) {
                    countNo[ge] = i;
                }
            }

        } else if (LotteryInformationType.CQSSC_LSKJ_LR_THERE.equals(type)) {
            // 三星的统计
            for (int i = 0; i < totalIssue; i++) {
                FivesscLotterySg fvsscSg = fvsscSgs.get(i);
                Integer bai = fvsscSg.getBai();
                Integer shi = fvsscSg.getShi();
                Integer ge = fvsscSg.getGe();
                if (i < 30) {
                    count30[bai] += 1;
                    count30[shi] += 1;
                    count30[ge] += 1;
                } else if (i < 50) {
                    count50[bai] += 1;
                    count50[shi] += 1;
                    count50[ge] += 1;
                } else if (i < 100) {
                    count100[bai] += 1;
                    count100[shi] += 1;
                    count100[ge] += 1;
                } else {
                    break;
                }
                if (countNo[bai] == null) {
                    countNo[bai] = i;
                }
                if (countNo[shi] == null) {
                    countNo[shi] = i;
                }
                if (countNo[ge] == null) {
                    countNo[ge] = i;
                }
            }
        } else {
            // 一星的统计
            for (int i = 0; i < totalIssue; i++) {
                FivesscLotterySg fvsscSg = fvsscSgs.get(i);
                Integer ge = fvsscSg.getGe();
                if (i < 30) {
                    count30[ge] += 1;
                } else if (i < 50) {
                    count50[ge] += 1;
                } else if (i < 100) {
                    count100[ge] += 1;
                } else {
                    break;
                }
                if (countNo[ge] == null) {
                    countNo[ge] = i;
                }
            }

        }
        for (int j = 0; j < 10; j++) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(count30[j]);
            list.add(count30[j] + count50[j]);
            list.add(count30[j] + count50[j] + count100[j]);
            list.add(countNo[j] == null ? totalIssue : countNo[j]);
            result.add(new MapListVO("" + j, list));
        }
        return result;
    }


}