package com.example.sojernhw.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MathService {
    public List<Integer> getKMax(List<Integer> list, int k) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Integer> minHeap
                = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            minHeap.add(list.get(i));
        }

        for (int i = k; i < list.size(); i++) {
            if (minHeap.peek() < list.get(i)) {
                minHeap.poll();
                minHeap.add(list.get(i));
            }
        }
        Iterator<Integer> iterator = minHeap.iterator();

        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return result;
    }
    
    public List<Integer> getKMin(List<Integer> list, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
        for (int i = 0; i < k; i++) {
            maxHeap.add(list.get(i));
        }
        for (int i = k; i < list.size(); i++) {
            if (maxHeap.peek() > list.get(i)) {
                maxHeap.poll();
                maxHeap.add(list.get(i));
            }
        }

        List<Integer> topKList = new ArrayList<>(maxHeap);
        Collections.reverse(topKList);

        return topKList;
    }

    public Double average(List<Integer> list) {
        return list.stream()
                .mapToDouble(d -> d)
                .average()
                .orElse(0.0);
    }

    public Double median(List<Integer> list) {
        Collections.sort(list);
        Double median;
        int size = list.size();
        if (size % 2 == 0)
            median = ((double)list.get(size/2) + (double)list.get(size/2 - 1))/2;
        else
            median = (double) list.get(size/2);
        return median;
    }

    public static long percentile(List<Integer> numbers, int percentile) {
        Collections.sort(numbers);
        int index = (int) Math.ceil(percentile / 100.0 * numbers.size());
        return numbers.get(index-1);
    }

}
