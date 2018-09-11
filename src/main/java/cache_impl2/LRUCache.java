/*
 * Copyright [2018] [RAHUL RAJ]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cache_impl2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LRUCache<K, T> {
    private long timeToLive;
    private HashMap<K, T> cacheMap;

    protected class CrunchifyCacheObject {
        public long lastAccessed = System.currentTimeMillis();
        public String value;

        protected CrunchifyCacheObject(String value) {
            this.value = value;
        }
    }

    public LRUCache(long timeToLive, final long timeInterval, int max) {
        this.timeToLive = timeToLive * 2000;

        cacheMap = new HashMap<K, T>(max);

        if (timeToLive > 0 && timeInterval > 0) {

            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(timeInterval * 1000);
                        } catch (InterruptedException ex) {
                        }

                    }
                }
            });

            t.setDaemon(true);
            t.start();
        }
    }

    // PUT method
    public void put(K key, T value) {
        synchronized (cacheMap) {
            cacheMap.put(key, value);
        }
    }

    // GET method
    @SuppressWarnings("unchecked")
    public T get(K key) {
        synchronized (cacheMap) {
            CrunchifyCacheObject c = (CrunchifyCacheObject) cacheMap.get(key);

            if (c == null)
                return null;
            else {
                c.lastAccessed = System.currentTimeMillis();
                return (T) c.value;
            }
        }
    }

    // REMOVE method
    public void remove(String key) {
        synchronized (cacheMap) {
            cacheMap.remove(key);
        }
    }

    // Get Cache Objects Size()
    public int size() {
        synchronized (cacheMap) {
            return cacheMap.size();
        }
    }

    // CLEANUP method
    public void cleanup() {

        long now = System.currentTimeMillis();
        ArrayList<String> deleteKey = null;

        synchronized (cacheMap) {
            Iterator<?> itr = cacheMap.entrySet().iterator();

            deleteKey = new ArrayList<String>((cacheMap.size() / 2) + 1);
            CrunchifyCacheObject c = null;

            while (itr.hasNext()) {
                String key = (String) itr.next();
                c = (CrunchifyCacheObject) ((Map.Entry<?, ?>) itr).getValue();
                if (c != null && (now > (timeToLive + c.lastAccessed))) {
                    deleteKey.add(key);
                }
            }
        }

        for (String key : deleteKey) {
            synchronized (cacheMap) {
                cacheMap.remove(key);
            }

            Thread.yield();
        }
    }
}
