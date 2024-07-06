package top.easylove.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.easylove.enums.ResultEnum;
import top.easylove.pojo.Post;
import top.easylove.pojo.Tag;
import top.easylove.pojo.dto.PostDto;
import top.easylove.repository.PostRepository;
import top.easylove.service.IPostService;
import top.easylove.util.ResultResponse;

import java.util.Set;

@Service
@Slf4j
public class PostServiceImpl implements IPostService {

    @Resource
    private PostRepository postRepository;

    @Override
    public ResultResponse<String> addPost(PostDto postDto) {

        if (StrUtil.hasBlank(postDto.getTitle(), postDto.getSummary(), postDto.getContent())) {
            return ResultResponse.error(ResultEnum.ERROR);
        }

        Post post = BeanUtil.copyProperties(postDto, Post.class);

        log.info(String.valueOf(post));

        postRepository.saveAndFlush(post);

        return ResultResponse.error(ResultEnum.SUCCESS);
    }
}
