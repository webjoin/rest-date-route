package com.viewt.massage.day;

import com.viewt.rest.BaseBootstrap;
import com.viewt.rest.data.bean.MassageActivityBean;
import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.service.UrlService;
import com.viewt.rest.data.service.impl.UrlServiceImpl;
import com.viewt.rest.data.util.Cons;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tangyu
 * @since 2018-01-09 09:58
 * area=massage-activety
 */
public class MassageActivityApplication extends BaseBootstrap {

    public static void main(String[] args) {
        MassageActivityApplication bootstrap = new MassageActivityApplication();
        try {
            long stime = System.currentTimeMillis();
            bootstrap.logger.error("{} , start...", bootstrap.dc_index);
            bootstrap.start(args);
            long etime = System.currentTimeMillis();
            bootstrap.logger.error("{} , end...it tooks {}s", bootstrap.dc_index, (etime - stime) / 1000);
        } catch (Exception e) {
            bootstrap.logger.error(e.getMessage(), e);
            bootstrap.logger.error("end...");
        }
    }

    UrlService urlService;
    int qty = 0;
    int dc_index = -1;

    @Override
    protected void crawl(String[] args) {
        String cookie = "_lxsdk_cuid=1609609b3abc8-0ec349de52e4e68-49566d-13c680-1609609b3abc8; _lxsdk=1609609b3abc8-0ec349de52e4e68-49566d-13c680-1609609b3abc8; _hc.v=\"\\\"f21d88bb-1c97-40ec-8046-549b1af9070b.1514345590\\\"\"; PHOENIX_ID=0a45d1c9-160d6383ebc-181e705; cy=1; cye=shanghai; _tr.u=36Gnj6oGAqP4s3Na; JSESSIONID=C5FF9D30D4F84C0D75DB21BF2FCD01C6; cityid=1; default_ab=shop%3AA%3A1%7CshopList%3AA%3A1; source=m_browser_test_22; pvhistory=6L+U5ZuePjo8L3NzbmV3P2tleXdvcmQ9JUU1JUJBJUI3JUU5JTgxJTkzJUU2JThDJTg5JUU2JTkxJUE5Jl89MTUxNTQyMzYwODM0OSZjYWxsYmFjaz1aZXB0bzE1MTU0MjMyNzg4NDk+OjwxNTE1NDIzNDY4Nzc4XV9b; m_flash2=1; Hm_lvt_185e211f4a5af52aaffe6d9c1a2737f4=1515423625; Hm_lpvt_185e211f4a5af52aaffe6d9c1a2737f4=1515423625; tg_list_scroll=0";
        reqHeader.put(Cons.COOKIE, cookie);
        reqHeader.put(Cons.USER_AGENT, Cons.PC_USER_AGENT);

        urlService = new UrlServiceImpl();
        String[] massageDistricts = Cons.Dianping.MASSAGE_DISTRICTS;
        int end = massageDistricts.length;
//        end = 1;
        for (int i = 0; i < end; i++) {

            String massageDistrict = massageDistricts[i];
            String url = Cons.Dianping.MASSAGE_DISTRICTS_BASE + massageDistrict;

            RespBean contentBean = urlService.getContentByUrl(url, reqHeader);
            String content = contentBean.getContent();
            int totalPage = getTotalPage(content);
            List<MassageActivityBean> massageActivityBeans = fetchBrandNameAndActivityId(content,url);
            saveData(massageActivityBeans);
            for (int j = 1; j < totalPage; j++) {
                String pageUrl = url + "?pageIndex=" + j;
                RespBean contentByUrl = urlService.getContentByUrl(pageUrl, reqHeader);
                String content1 = contentByUrl.getContent();
                List<MassageActivityBean> massageActivityBeans1 = fetchBrandNameAndActivityId(content1,pageUrl);
                saveData(massageActivityBeans1);
            }
        }
    }

    private void saveData(List<MassageActivityBean> datas) {
        datas.forEach(x -> logger.info("{}", x));
    }

    String pageIndexReg = "pageIndex=(\\d+)";
    Pattern pageIndexReqPattern = Pattern.compile(pageIndexReg);

    private int getTotalPage(String content) {
        Matcher matcher = pageIndexReqPattern.matcher(content);
        List<Integer> list = new ArrayList<>();
        while (matcher.find()) {
            String group = matcher.group(1);
            if (StringUtils.isNumeric(group)) {
                list.add(Integer.valueOf(group));
            }
        }
        if (list.isEmpty()) {
            return 1;
        }
        Collections.sort(list);
        return list.get(list.size() - 1);
    }

    String outReg = "<li class=\"tg-floor-item[\\s\\S]+?</div>\\s*</li>";
    String brandNameReg = "<h3>([^<]+)</h3>";
    String activityIdReg = "href=\"/deal/([0-9]+)";

    Pattern outReqPattern = Pattern.compile(outReg);
    Pattern brandNameRegPattern = Pattern.compile(brandNameReg);
    Pattern activityIdReqPattern = Pattern.compile(activityIdReg);

    private List<MassageActivityBean> fetchBrandNameAndActivityId(String content,String url) {
        List<MassageActivityBean> rs = new ArrayList<>();
        MassageActivityBean bean;
        Matcher outRegMatcher = outReqPattern.matcher(content);
        while (outRegMatcher.find()) {
            bean = new MassageActivityBean(url, LocalDate.now());
            String outRegGroup = outRegMatcher.group();
            boolean aNull = Objects.nonNull(outRegGroup);
            if (aNull && !outRegGroup.isEmpty()) {
                Matcher brandNameMatcher = brandNameRegPattern.matcher(outRegGroup);
                if (brandNameMatcher.find()) {
                    String nameGroup = brandNameMatcher.group(1);
                    bean.setBrandName(nameGroup);
                }
                Matcher activityIdMatcher = activityIdReqPattern.matcher(outRegGroup);
                if (activityIdMatcher.find()) {
                    String activityIdgroup = activityIdMatcher.group(1);
                    bean.setActivityId(activityIdgroup);
                }
            }
            rs.add(bean);
        }
        return rs;
    }

    @Override
    protected void start(String[] args) {
        super.start(args);
    }

    @Override
    protected void initSqlSessionFactory(String env) {
        super.initSqlSessionFactory(env);
    }

    @Override
    protected int insert(String statement, Object parameter) {
        return super.insert(statement, parameter);
    }
}
