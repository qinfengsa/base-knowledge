package com.qinfengsa.structure.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import lombok.extern.slf4j.Slf4j;

/**
 * 5430. 设计浏览器历史记录
 *
 * <p>你有一个只支持单个标签页的 浏览器 ，最开始你浏览的网页是 homepage ，你可以访问其他的网站 url ，也可以在浏览历史中后退 steps 步或前进 steps 步。
 *
 * <p>请你实现 BrowserHistory 类：
 *
 * <p>BrowserHistory(string homepage) ，用 homepage 初始化浏览器类。 void visit(string url) 从当前页跳转访问 url 对应的页面
 * 。执行此操作会把浏览历史前进的记录全部删除。 string back(int steps) 在浏览历史中后退 steps 步。如果你只能在浏览历史中后退至多 x 步且 steps > x
 * ，那么你只后退 x 步。请返回后退 至多 steps 步以后的 url 。 string forward(int steps) 在浏览历史中前进 steps 步。如果你只能在浏览历史中前进至多
 * x 步且 steps > x ，那么你只前进 x 步。请返回前进 至多 steps步以后的 url 。
 *
 * <p>示例：
 *
 * <p>输入：
 * ["BrowserHistory","visit","visit","visit","back","back","forward","visit","forward","back","back"]
 * [["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[1],[1],[1],["linkedin.com"],[2],[2],[7]]
 * 输出：
 * [null,null,null,null,"facebook.com","google.com","facebook.com",null,"linkedin.com","google.com","leetcode.com"]
 *
 * <p>解释： BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
 * browserHistory.visit("google.com"); // 你原本在浏览 "leetcode.com" 。访问 "google.com"
 * browserHistory.visit("facebook.com"); // 你原本在浏览 "google.com" 。访问 "facebook.com"
 * browserHistory.visit("youtube.com"); // 你原本在浏览 "facebook.com" 。访问 "youtube.com"
 * browserHistory.back(1); // 你原本在浏览 "youtube.com" ，后退到 "facebook.com" 并返回 "facebook.com"
 * browserHistory.back(1); // 你原本在浏览 "facebook.com" ，后退到 "google.com" 并返回 "google.com"
 * browserHistory.forward(1); // 你原本在浏览 "google.com" ，前进到 "facebook.com" 并返回 "facebook.com"
 * browserHistory.visit("linkedin.com"); // 你原本在浏览 "facebook.com" 。 访问 "linkedin.com"
 * browserHistory.forward(2); // 你原本在浏览 "linkedin.com" ，你无法前进任何步数。 browserHistory.back(2); // 你原本在浏览
 * "linkedin.com" ，后退两步依次先到 "facebook.com" ，然后到 "google.com" ，并返回 "google.com"
 * browserHistory.back(7); // 你原本在浏览 "google.com"， 你只能后退一步到 "leetcode.com" ，并返回 "leetcode.com"
 *
 * <p>提示：
 *
 * <p>1 <= homepage.length <= 20 1 <= url.length <= 20 1 <= steps <= 100 homepage 和 url 都只包含 '.'
 * 或者小写英文字母。 最多调用 5000 次 visit， back 和 forward 函数。
 *
 * @author qinfengsa
 * @date 2020/06/07 11:21
 */
@Slf4j
public class BrowserHistory {
    // 两个栈实现
    Deque<String> back = new LinkedList<>();

    Deque<String> forward = new LinkedList<>();

    public BrowserHistory(String homepage) {
        back.push(homepage);
    }

    public void visit(String url) {
        back.push(url);
        forward.clear();
    }

    public String back(int steps) {

        while (back.size() > 1 && steps > 0) {
            String url = back.pop();
            forward.push(url);
            steps--;
        }
        return back.peek();
    }

    public String forward(int steps) {
        while (!forward.isEmpty() && steps > 0) {
            back.push(forward.pop());
            steps--;
        }
        return back.peek();
    }

    public static void main(String[] args) {
        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        // 你原本在浏览 "leetcode.com" 。访问 "google.com"
        browserHistory.visit("google.com");
        // 你原本在浏览 "google.com" 。访问 "facebook.com"
        browserHistory.visit("facebook.com");
        // 你原本在浏览 "facebook.com" 。访问 "youtube.com"
        browserHistory.visit("youtube.com");
        // 你原本在浏览 "youtube.com" ，后退到 "facebook.com" 并返回 "facebook.com"
        String url1 = browserHistory.back(1);
        log.debug("url1:{}", url1);
        // 你原本在浏览 "facebook.com" ，后退到 "google.com" 并返回 "google.com"
        String url2 = browserHistory.back(1);
        log.debug("url2:{}", url2);
        // 你原本在浏览 "google.com" ，前进到 "facebook.com" 并返回 "facebook.com"
        String url3 = browserHistory.forward(1);
        log.debug("url3:{}", url3);
        browserHistory.visit("linkedin.com"); // 你原本在浏览 "facebook.com" 。 访问 "linkedin.com"
        String url4 = browserHistory.forward(2); // 你原本在浏览 "linkedin.com" ，你无法前进任何步数。
        log.debug("url4:{}", url4);
        // 你原本在浏览 "linkedin.com" ，后退两步依次先到 "facebook.com" ，然后到 "google.com" ，并返回 "google.com"
        String url5 = browserHistory.back(2);
        log.debug("url5:{}", url5);

        // 你原本在浏览 "google.com"， 你只能后退一步到 "leetcode.com" ，并返回 "leetcode.com"
        String url6 = browserHistory.back(7);
        log.debug("url6:{}", url6);
    }
}
