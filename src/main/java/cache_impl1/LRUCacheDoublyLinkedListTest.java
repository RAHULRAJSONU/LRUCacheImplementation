package cache_impl1;

import java.util.concurrent.TimeUnit;

public class LRUCacheDoublyLinkedListTest {

  public static void main(String[] args) {
    LRUCacheDoublyLinkedList<String, Integer> lru = new LRUCacheDoublyLinkedList<String, Integer>(5, TimeUnit.SECONDS);
    for (int i = 1; i < 10; i++) {
      lru.put(String.valueOf(i), i * 2);
    }
  }

}