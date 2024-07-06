package top.easylove.service.impl;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.easylove.enums.ResultEnum;
import top.easylove.pojo.Post;
import top.easylove.pojo.Tag;
import top.easylove.repository.TagRepository;
import top.easylove.service.ITagService;
import top.easylove.util.ResultResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TagServiceImpl implements ITagService {

    @Resource
    private TagRepository tagRepository;

    @Override
    public ResultResponse<String> addTags(List<Tag> tags) {

        if (CollectionUtils.isEmpty(tags)) {
            log.warn("Attempt to add empty tag list");
            return ResultResponse.error(ResultEnum.INVALID_PARAMS);
        }

        List<Tag> validTags = tags.stream()
                .filter(tag -> !tagRepository.existsTagByName(tag.getName()))
                .collect(Collectors.toList());

        if (validTags.isEmpty()) {
            log.warn("No valid tags to add");
            return ResultResponse.error(ResultEnum.INVALID_PARAMS);
        }

        try {
            List<Tag> savedTags = tagRepository.saveAllAndFlush(validTags);
            log.info("Successfully added {} tags", savedTags.size());
            return ResultResponse.success(ResultEnum.SUCCESS, "Successfully added " + savedTags.size() + " tags");
        } catch (DataIntegrityViolationException e) {
            log.error("Error while adding tags: {}", e.getMessage());
            return ResultResponse.error(ResultEnum.DATABASE_ERROR);
        } catch (Exception e) {
            log.error("Unexpected error while adding tags", e);
            return ResultResponse.error(ResultEnum.DATABASE_ERROR);
        }
    }

    @Override
    public ResultResponse<Long> getTagCount() {
        return ResultResponse.success(ResultEnum.SUCCESS, tagRepository.count());
    }

    @Override
    public ResultResponse<List<Tag>> getTags() {
        return ResultResponse.success(ResultEnum.SUCCESS,tagRepository.findAll());
    }
}
