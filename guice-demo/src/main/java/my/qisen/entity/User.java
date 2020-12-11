package my.qisen.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description: User实体类
 * @author: angbeats
 * @create: 2020-12-10 10:39
 **/

@Data
@Accessors(chain = true)
public class User {

    private String username;

    private String password;

}