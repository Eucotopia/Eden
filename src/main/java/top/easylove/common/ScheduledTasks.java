package top.easylove.common;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import top.easylove.constant.RedisConstants;
import top.easylove.pojo.Post;
import top.easylove.repository.PostRepository;
import top.easylove.util.RedisUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ScheduledTasks {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private PostRepository postRepository;

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    @Transactional
    public void syncAndResetPostViews() {

        String pattern = RedisConstants.POST_VIEW_KEY_PREFIX + "*";

        Set<String> keys = redisUtil.keys(pattern);

        if (keys.isEmpty()) {
            return;
        }

        Map<String, Integer> postViewsMap = keys.stream()
                .collect(Collectors.toMap(
                        key -> key.substring(RedisConstants.POST_VIEW_KEY_PREFIX.length()),
                        key -> redisUtil.get(key, Integer.class).orElse(0)
                ));

        log.error(postViewsMap.toString());

        List<Post> postsToUpdate = postRepository.findAllById(postViewsMap.keySet());

        postsToUpdate.forEach(post -> {
            Integer additionalViews = postViewsMap.get(post.getId());
            if (additionalViews != null) {
                post.setViews(post.getViews() + additionalViews);
            }
        });

        postRepository.saveAll(postsToUpdate);

        redisUtil.delete(keys);

        log.info("Synced and reset views for {} posts", postsToUpdate.size());

    }
}