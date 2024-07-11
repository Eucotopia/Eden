package top.easylove.util;

import java.util.*;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import top.easylove.constant.RedisConstants;

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
     * @return true if successful, false otherwise
     */
    public boolean delete(String key) {
        try {
            return Boolean.TRUE.equals(redisTemplate.delete(key));
        } catch (Exception e) {
            log.error("Error deleting key: {}", key, e);
            return false;
        }
    }

    /**
     * Delete multiple keys from Redis.
     *
     * @param keys the collection of keys to delete
     * @return the number of keys that were deleted
     */
    public long delete(Collection<String> keys) {
        try {
            Long count = redisTemplate.delete(keys);
            return count != null ? count : 0;
        } catch (Exception e) {
            log.error("Error deleting multiple keys", e);
            return 0;
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
}