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

package cache_impl3;

public class TestLRUCache {

	public static void main(String[] args) {
		LRUCache cache = new LRUCache(4, 0.75f);
		cache.put(1, "Nokia");
		cache.put(2, "Samsung");
		cache.put(3, "Blackberry");
		cache.put(4, "Apple");
		System.out.println(cache);
		cache.get(2);
		System.out.println(cache);
		cache.put(5, "Lenovo");
		System.out.println(cache);
		cache.get(4);
		System.out.println(cache);
		cache.put(6, "Sony");
		System.out.println(cache);
	}
}