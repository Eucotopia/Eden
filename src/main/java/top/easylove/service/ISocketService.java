package top.easylove.service;

import org.springframework.stereotype.Service;
import top.easylove.pojo.MessageType;
import top.easylove.util.ResultResponse;

import java.util.List;

@Service
public interface ISocketService {
    ResultResponse<List<MessageType>> getUnapprovedUsers();
}
