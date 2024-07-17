package top.easylove.util;

import java.util.*;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * Utility class for Redis operations.
 * This class provides a simplified interface for common Redis operations.
 */
@Slf4j
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * Set a key-value pair in Redis.
     *
     * @param key   the key
     * @param value the value
     * @param <T>   the type of the value
     * @return true if successful, false otherwise
     */
    public <T> boolean set(String key, T value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("Error setting value for key: {}", key, e);
            return false;
        }
    }

    /**
     * Set a key-value pair in Redis with an expiration time.
     *
     * @param key      the key
     * @param value    the value
     * @param timeout  the timeout value
     * @param timeUnit the unit of the timeout
     * @param <T>      the type of the value
     * @return true if successful, false otherwise
     */
    public <T> boolean set(String key, T value, long timeout, TimeUnit timeUnit) {
        try {
            redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
            return true;
        } catch (Exception e) {
            log.error("Error setting value with expiration for key: {}", key, e);
            return false;
        }
    }

    /**
     * Get the value associated with the given key.
     *
     * @param key the key
     * @return the value associated with the key, or null if the key does not exist
     */
    public <T> Optional<T> get(String key, Class<T> clazz) {
        try {
            Object value = redisTemplate.opsForValue().get(key);
            if (value == null) {
                return Optional.empty();
            }
            if (clazz.isInstance(value)) {
                return Optional.of(clazz.cast(value));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            log.error("Error getting value for key: {}", key, e);
            return Optional.empty();
        }
    }

    /**
     * Increment the integer value of a key by one.
     *
     * @param key the key
     */
    public void increment(String key) {
        try {
            redisTemplate.opsForValue().increment(key);
        } catch (Exception e) {
            log.error("Error incrementing value for key: {}", key, e);
        }
    }


    /**
     * Set the expiration time for a key.
     *
     * @param key     the key
     * @param timeout the timeout value
     * @param unit    the unit of the timeout
     * @return true if successful, false otherwise
     */
    public boolean expire(String key, long timeout, TimeUnit unit) {
        try {
            return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, unit));
        } catch (Exception e) {
            log.error("Error setting expiration for key: {}", key, e);
            return false;
        }
    }

    /**
     * Check if a key exists in Redis.
     *
     * @param key the key
     * @return true if the key exists, false otherwise
     */
    public boolean hasKey(String key) {
        try {
            return Boolean.TRUE.equals(redisTemplate.hasKey(key));
        } catch (Exception e) {
            log.error("Error checking existence of key: {}", key, e);
            return false;
        }
    }

    /**
     * Delete a key from Redis.
     *
     * @param key the key to delete
     */
    public void delete(String key) {
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            log.error("Error deleting key: {}", key, e);
        }
    }

    /**
     * Delete multiple keys from Redis.
     *
     * @param keys the collection of keys to delete
     */
    public void delete(Collection<String> keys) {
        try {
            Long count = redisTemplate.delete(keys);
        } catch (Exception e) {
            log.error("Error deleting multiple keys", e);
        }
    }


    /**
     * Push multiple values to the right of a list.
     *
     * @param key    the key of the list
     * @param values the values to push
     * @param <T>    the type of the values
     * @return the length of the list after the push operation
     */
    public <T> long rightPushAll(String key, List<T> values) {
        try {
            Long count = redisTemplate.opsForList().rightPushAll(key, values.toArray());
            return count != null ? count : 0;
        } catch (Exception e) {
            log.error("Error pushing values to list: {}", key, e);
            return 0;
        }
    }


    /**
     * Set multiple hash fields to multiple values.
     *
     * @param key the key of the hash
     * @param map the map of field-value pairs to set
     * @param <T> the type of the values
     */
    public <T> void putAll(String key, Map<String, T> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
        } catch (Exception e) {
            log.error("Error putting all entries to hash: {}", key, e);
        }
    }

    /**
     * Set the value of a hash field.
     *
     * @param key     the key of the hash
     * @param hashKey the field of the hash
     * @param value   the value to set
     */
    public void put(String key, String hashKey, Object value) {
        try {
            redisTemplate.opsForHash().put(key, hashKey, value);
        } catch (Exception e) {
            log.error("Error putting entry to hash: {} with hashKey: {}", key, hashKey, e);
        }
    }

    /**
     * Get the value of a hash field.
     *
     * @param key     the key of the hash
     * @param hashKey the field of the hash
     * @param <T>     the type of the value
     * @return the value of the hash field, or null if the field or key does not exist
     */
    public <T> T get(String key, String hashKey) {
        try {
            @SuppressWarnings("unchecked")
            T value = (T) redisTemplate.opsForHash().get(key, hashKey);
            return value;
        } catch (Exception e) {
            log.error("Error getting entry from hash: {} with hashKey: {}", key, hashKey, e);
            return null;
        }
    }

    /**
     * Find all keys matching the given pattern.
     *
     * @param pattern the pattern to match against
     * @return a set of keys matching the pattern
     */
    public Set<String> keys(String pattern) {
        try {
            return redisTemplate.keys(pattern);
        } catch (Exception e) {
            log.error("Error getting keys with pattern: {}", pattern, e);
            return Set.of();
        }
    }


    /**
     * 向 Redis 中追加 Set 类型数据。
     *
     * @param key    键名
     * @param values 要追加的值集合
     * @param <T>    值的类型
     */
    public <T> void appendToSet(String key, Set<T> values) {
        try {
            // 判断 key 是否存在，如果不存在，则直接添加
            if (Boolean.FALSE.equals(redisTemplate.hasKey(key))) {
                redisTemplate.opsForSet().add(key, values.toArray());
            } else {
                // 如果 key 存在，则将新的值追加到现有的 Set 中
                for (T value : values) {
                    redisTemplate.opsForSet().add(key, value);
                }
            }
        } catch (Exception e) {
            log.error("Error appending values to set: {}", key, e);
        }
    }


    /**
     * 获取 Redis 中存储的 Set 类型数据。
     *
     * @param key  键名
     * @param type 集合中元素的类型
     * @param <T>  集合中元素的类型
     * @return 存储在 Redis 中的 Set 数据
     */
    public <T> Set<T> getSet(String key, Class<T> type) {
        try {

            Set<?> rawSet = redisTemplate.opsForSet().members(key);
            if (rawSet == null) {
                return null;
            }

            Set<T> typedSet = new HashSet<>();
            for (Object element : rawSet) {
                if (type.isInstance(element)) {
                    typedSet.add(type.cast(element));
                } else {
                    return null;
                }
            }
            return typedSet;
        } catch (Exception e) {
            return null;
        }
    }

}