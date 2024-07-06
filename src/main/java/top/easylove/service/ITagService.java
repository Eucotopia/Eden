package top.easylove.service;

import org.springframework.stereotype.Service;
import top.easylove.pojo.Post;
import top.easylove.pojo.Tag;
import top.easylove.util.ResultResponse;

import java.util.List;

@Service
public interface ITagService {
    ResultResponse<String> addTags(List<Tag> tags);

    ResultResponse<Long> getTagCount();

    ResultResponse<List<Tag>> getTags();
}
