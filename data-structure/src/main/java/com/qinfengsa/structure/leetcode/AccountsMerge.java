package com.qinfengsa.structure.leetcode;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 721. 账户合并
 *
 * <p>给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails
 * 表示该帐户的邮箱地址。
 *
 * <p>现在，我们想合并这些帐户。如果两个帐户都有一些共同的邮件地址，则两个帐户必定属于同一个人。请注意，即使两个帐户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的帐户，但其所有帐户都具有相同的名称。
 *
 * <p>合并帐户后，按以下格式返回帐户：每个帐户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。accounts 本身可以以任意顺序返回。
 *
 * <p>例子 1:
 *
 * <p>Input: accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John",
 * "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary",
 * "mary@mail.com"]] Output: [["John", 'john00@mail.com', 'john_newyork@mail.com',
 * 'johnsmith@mail.com'], ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]] Explanation:
 * 第一个和第三个 John 是同一个人，因为他们有共同的电子邮件 "johnsmith@mail.com"。 第二个 John 和 Mary 是不同的人，因为他们的电子邮件地址没有被其他帐户使用。
 * 我们可以以任何顺序返回这些列表，例如答案[['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
 * ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']]仍然会被接受。
 *
 * <p>注意：
 *
 * <p>accounts的长度将在[1，1000]的范围内。 accounts[i]的长度将在[1，10]的范围内。 accounts[i][j]的长度将在[1，30]的范围内。
 *
 * @author qinfengsa
 * @date 2020/07/21 07:51
 */
public class AccountsMerge {

    private int[] parents;

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int len = accounts.size();
        parents = new int[10001];
        for (int i = 0; i < 10001; i++) {
            parents[i] = i;
        }
        List<List<String>> result = new ArrayList<>();
        Map<Integer, List<String>> map = new HashMap<>();
        Map<String, Integer> idMap = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        int id = 0;
        for (List<String> list : accounts) {
            String firstEmail = "";
            String name = list.get(0);
            for (int j = 1; j < list.size(); j++) {
                String email = list.get(j);
                if (firstEmail.length() == 0) {
                    firstEmail = email;
                }
                emailToName.put(email, name);
                if (!idMap.containsKey(email)) {
                    idMap.put(email, id++);
                }
                union(idMap.get(firstEmail), idMap.get(email));
            }
        }
        emailToName.forEach(
                (k, v) -> {
                    int parentId = findParent(idMap.get(k));
                    map.computeIfAbsent(parentId, key -> new ArrayList<>()).add(k);
                });

        map.forEach(
                (k, v) -> {
                    v.sort(String::compareTo);
                    v.add(0, emailToName.get(v.get(0)));
                    result.add(v);
                });

        return result;
    }

    private void union(int child, int parent) {
        child = findParent(child);
        parent = findParent(parent);
        parents[child] = parent;
    }

    private int findParent(int child) {
        int parent = parents[child];
        if (parent == child) {
            return parent;
        }
        return findParent(parent);
    }

    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("David", "David0@m.co", "David1@m.co"));
        accounts.add(Arrays.asList("David", "David3@m.co", "David4@m.co"));
        accounts.add(Arrays.asList("David", "David4@m.co", "David5@m.co"));
        accounts.add(Arrays.asList("David", "David2@m.co", "David3@m.co"));
        accounts.add(Arrays.asList("David", "David1@m.co", "David2@m.co"));

        /*accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));*/

        /* accounts.add(Arrays.asList("Ethan", "Ethan1@m.co", "Ethan2@m.co", "Ethan0@m.co"));
        accounts.add(Arrays.asList("David", "David1@m.co", "David2@m.co", "David0@m.co"));
        accounts.add(Arrays.asList("Lily", "Lily0@m.co", "Lily0@m.co", "Lily4@m.co"));
        accounts.add(Arrays.asList("Gabe", "Gabe1@m.co", "Gabe4@m.co", "Gabe0@m.co"));
        accounts.add(Arrays.asList("Ethan", "Ethan2@m.co", "Ethan1@m.co", "Ethan0@m.co"));*/

        AccountsMerge merge = new AccountsMerge();
        List<List<String>> result = merge.accountsMerge(accounts);
        for (List<String> list : result) {
            logResult(list);
        }
    }
}
