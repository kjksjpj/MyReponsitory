package majiang.com.example.myproject.mapper;

import java.util.List;
import majiang.com.example.myproject.model.Question;
import majiang.com.example.myproject.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface QuestionExtMapper {
    int incView(Question record);
}