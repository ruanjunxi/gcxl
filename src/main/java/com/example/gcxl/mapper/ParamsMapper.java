package com.example.gcxl.mapper;

import com.example.gcxl.domain.Params;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

/**
 * @ClassName: paramMapper
 * @author: Eason
 * @data: 2022/4/24 14:14
 */
@Service
public interface ParamsMapper {
    @Update("UPDATE param SET param_description=#{paramDescription} ,account=#{account},modified_time=now() ")
    boolean updateParams(int paramDescription,String account);

    @Select("select * from param")
    Params getParams();

}
