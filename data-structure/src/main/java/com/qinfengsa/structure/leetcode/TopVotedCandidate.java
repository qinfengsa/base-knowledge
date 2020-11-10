package com.qinfengsa.structure.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * 911. 在线选举
 *
 * <p>在选举中，第 i 张票是在时间为 times[i] 时投给 persons[i] 的。
 *
 * <p>现在，我们想要实现下面的查询函数： TopVotedCandidate.q(int t) 将返回在 t 时刻主导选举的候选人的编号。
 *
 * <p>在 t 时刻投出的选票也将被计入我们的查询之中。在平局的情况下，最近获得投票的候选人将会获胜。
 *
 * <p>示例：
 *
 * <p>输入：["TopVotedCandidate","q","q","q","q","q","q"],
 * [[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]] 输出：[null,0,1,1,0,0,1] 解释：
 * 时间为 3，票数分布情况是 [0]，编号为 0 的候选人领先。 时间为 12，票数分布情况是 [0,1,1]，编号为 1 的候选人领先。 时间为 25，票数分布情况是
 * [0,1,1,0,0,1]，编号为 1 的候选人领先（因为最近的投票结果是平局）。 在时间 15、24 和 8 处继续执行 3 个查询。
 *
 * <p>提示：
 *
 * <p>1 <= persons.length = times.length <= 5000 0 <= persons[i] <= persons.length times
 * 是严格递增的数组，所有元素都在 [0, 10^9] 范围中。 每个测试用例最多调用 10000 次 TopVotedCandidate.q。 TopVotedCandidate.q(int t)
 * 被调用时总是满足 t >= times[0]。
 *
 * @author qinfengsa
 * @date 2020/11/10 10:31
 */
@Slf4j
public class TopVotedCandidate {

    private int[] times;

    // 获胜人员数组
    private int[] winPersons;

    public TopVotedCandidate(int[] persons, int[] times) {
        this.times = times;
        this.winPersons = new int[persons.length];
        int max = 0;

        int[] votes = new int[persons.length + 1];
        int winPerson = persons[0];
        for (int i = 0; i < persons.length; i++) {
            votes[persons[i]]++;
            if (votes[persons[i]] >= max) {
                max = votes[persons[i]];
                winPerson = persons[i];
            }
            winPersons[i] = winPerson;
        }
    }

    private int getPerson(int time) {
        int left = 0, right = times.length - 1;
        if (time >= times[right]) {
            return winPersons[right];
        }
        int index = 0;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (times[mid] <= time) {
                left = mid + 1;
                index = mid;
            } else {
                right = mid;
            }
        }
        return winPersons[index];
    }

    public int q(int t) {

        return getPerson(t);
    }

    public static void main(String[] args) {
        int[] persons = {0, 1, 1, 0, 0, 1, 0};
        int[] times = {0, 5, 10, 15, 20, 25, 30};

        TopVotedCandidate votedCandidate = new TopVotedCandidate(persons, times);
        int val1 = votedCandidate.q(3);
        log.debug("val1:{}", val1);
        int val2 = votedCandidate.q(12);
        log.debug("val2:{}", val2);
        int val3 = votedCandidate.q(25);
        log.debug("val3:{}", val3);
        int val4 = votedCandidate.q(15);
        log.debug("val4:{}", val4);
        int val5 = votedCandidate.q(24);
        log.debug("val5:{}", val5);
        int val6 = votedCandidate.q(8);
        log.debug("val6:{}", val6);
    }
}
