package com.viewt.rest.data;

import com.alibaba.fastjson.JSON;
import com.viewt.rest.BaseBootstrap;
import com.viewt.rest.data.bean.DpCommentBean;
import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.util.Cons;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tangyu
 * @since 2017-11-07 20:50
 */
public class DpCommentBootstrap extends BaseBootstrap {


    public static void main(String[] args) {
        DpCommentBootstrap bootstrap = new DpCommentBootstrap();
        bootstrap.start(args);
    }

    private static Pattern totalPagePattern = Pattern.compile("网友点评</a><em class=\"col-exp\">\\((\\d+)\\)");
    private static Pattern shopNamePattern = Pattern.compile("<a title=\"([^\"]+)\" target=\"_blank\" href=\"/shop/\\d+\">[^<]+</a>");

    private static Pattern sectionPattern = Pattern.compile("rev_\\d+[\\s\\S]+?</div>\\s+</li>");
    private static Pattern starPattern = Pattern.compile("comment-rst\">[\\s\\S]+?</div>");
    private static Pattern smellPattern = Pattern.compile("<span class=\"rst\">口味(\\d+)<em class=\"col-exp\">");
    private static Pattern envPattern = Pattern.compile("<span class=\"rst\">环境(\\d+)<em class=\"col-exp\">");
    private static Pattern servicePattern = Pattern.compile("<span class=\"rst\">服务(\\d+)<em class=\"col-exp\">");
    private static Pattern commentPattern = Pattern.compile("<div class=\"J_brief-cont\">([\\s\\S]+?)</div>");
    private static Pattern commentDatePattern = Pattern.compile("<span class=\"time\">([\\s\\S]+?)</span>");
    private static Pattern userNamePattern = Pattern.compile("<a target=\"_blank\" title=\"\" href=\"/member/\\d+\">([^<]+)</a>");
    private static Pattern starNumPattern = Pattern.compile("item-rank-rst irr-star(\\d+)");

    @Override
    protected void crawl(String[] args) {
        String[] dpIds = { "80990733","66315573", "73584375", "75172598", "91678145", "95169584", "92090965", "69137634", "92531877", "92088993"};
//        String[] dpIds = {"80990733"};
        String dpCommentUrl = Cons.Dianping.DP_COMMENT_URL;
        logger.info("{},{},{},{},{},{},{}","店名","用户","口味","环境","服务","星级","评论时间");
        for (int i = 0; i < dpIds.length; i++) {
            String dpId = dpIds[i];
            int page = 1;
            int totalPage = 0;
            String shopName = "";
            do {
                reqHeader.put(Cons.USER_AGENT, Cons.MOBILE_USER_AGENT);
                reqHeader.put(Cons.COOKIE, "_hc.v=5e2ef585-e032-7e64-0ac7-ce146e828a4a.1500731291; _lxsdk_cuid=15d74d982cdc8-085875325275ed-3067780b-13c680-15d74d982cdc8; _lxsdk=15d74d982cdc8-085875325275ed-3067780b-13c680-15d74d982cdc8; PHOENIX_ID=0a010918-15d74d7054f-6e92eba; aburl=1; cy=" + 9 + "; cye=chongqing; __mta=223812900.1500904004251.1500904004251.1500904004251.1; cityid=" + 9 + "; msource=default; default_ab=shopList%3AA%3A1; _lxsdk_s=15d751a2f1e-111-750-fda%7C%7C5");
                String url = MessageFormat.format(dpCommentUrl, dpId, page);
                RespBean contentBean = urlService.getContentByUrl(url, reqHeader);
                String content = contentBean.getContent();
                if (totalPage == 0) {
                    Matcher matcher = totalPagePattern.matcher(content);
                    if (matcher.find()) {
                        String group = matcher.group(1);
                        if (StringUtils.isNumeric(group)) {
                            totalPage = (int) Math.ceil(((double) Integer.parseInt(group)) / 20);
                        }
                    }
                }
                Matcher shopMatcher = shopNamePattern.matcher(content);
                if (shopMatcher.find()) {
                    shopName = shopMatcher.group(1);
                }

                Matcher matcher = sectionPattern.matcher(content);
                while (matcher.find()) {
                    String group = matcher.group();
                    DpCommentBean bean = new DpCommentBean();
                    bean.setDpId(dpId);
                    bean.setPage(page + "");
                    bean.setTotalPage(totalPage + "");
                    bean.setShopName(shopName);
                    fillBean(group, bean);
                    logger.info("{},{},{},{},{},{},{}",bean.getShopName(),bean.getUsername(),bean.getSmell(),bean.getEnv(),bean.getService(),bean.getStarNum(),bean.getCommentDate());
                }
            } while (page++ < totalPage + 1);
        }
    }

    private void fillBean(String content, DpCommentBean bean) {
        getStar(content, bean);
        getComment(content, bean);
    }

    /**
     * @param content
     * @param bean
     */
    private void getComment(String content, DpCommentBean bean) {
//        Matcher matcher = commentPattern.matcher(content);
//        if (matcher.find()) {
//            String group = matcher.group(1);
//            if (Objects.nonNull(group)) {
//                group = group.trim();
//            }
//            bean.setComment(group);
//        }

        Matcher matcher1 = commentDatePattern.matcher(content);
        if (matcher1.find()) {
            String group = matcher1.group(1);
            bean.setCommentDate(group);
        }
        Matcher matcher2 = userNamePattern.matcher(content);
        if (matcher2.find()) {
            String group = matcher2.group(1);
            bean.setUsername(group.trim().replace("\"",""));
        }

    }

    /**
     * 获取星级
     *
     * @param content
     * @param bean
     */
    private void getStar(String content, DpCommentBean bean) {
        Matcher matcher = starPattern.matcher(content);
        if (matcher.find()) {
            String starContent = matcher.group();
            Matcher smellMatcher = smellPattern.matcher(starContent);
            if (smellMatcher.find()) {
                String group = smellMatcher.group(1);
                bean.setSmell(group);

            }
            Matcher envMatcher = envPattern.matcher(starContent);
            if (envMatcher.find()) {
                String group = envMatcher.group(1);
                bean.setEnv(group);
            }
            Matcher serviceMatcher = servicePattern.matcher(starContent);
            if (serviceMatcher.find()) {
                String group = serviceMatcher.group(1);
                bean.setService(group);
            }
        }

        Matcher matcher1 = starNumPattern.matcher(content);
        if (matcher1.find()) {
            bean.setStarNum(matcher1.group(1));
        }
    }


}
