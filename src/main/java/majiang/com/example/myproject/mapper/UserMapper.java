package majiang.com.example.myproject.mapper;
import majiang.com.example.myproject.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified) values (#{name},#{account_id},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id")Integer id);
}
