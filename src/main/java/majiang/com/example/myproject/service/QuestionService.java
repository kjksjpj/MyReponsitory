package majiang.com.example.myproject.service;

import majiang.com.example.myproject.dto.PaginationDTO;
import majiang.com.example.myproject.dto.QuestionDTO;
import majiang.com.example.myproject.exception.CustomizeErrorCode;
import majiang.com.example.myproject.exception.CustomizeException;
import majiang.com.example.myproject.mapper.QuestionMapper;

import majiang.com.example.myproject.mapper.UserMapper;
import majiang.com.example.myproject.model.Question;


import majiang.com.example.myproject.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        Integer totalPage;
        Integer totalCount = questionMapper.count();//条目总数
        PaginationDTO paginationDTO = new PaginationDTO();
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1) {
            page = 1;
        } else if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);
        Integer offset = (page - 1) * size;//偏移量计算
        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        Integer totalPage;
        Integer totalCount = questionMapper.countByUserId(userId);//条目总数
        PaginationDTO paginationDTO = new PaginationDTO();
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1) {
            page = 1;
        } else if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);
        Integer offset = (page - 1) * size;//偏移量计算
        List<Question> questions = questionMapper.listByUserId(userId, offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;

    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            questionMapper.create(question);
        } else {
            //更新
            int updated = questionMapper.update(question);
            //没有更新的时候也需要抛出异常
            if (updated != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }
}
