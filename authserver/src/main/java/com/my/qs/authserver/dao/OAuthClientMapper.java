package com.my.qs.authserver.dao;

import com.my.qs.authserver.entity.OAuthClientDetail;
import org.apache.ibatis.annotations.Param;

public interface OAuthClientMapper {

	OAuthClientDetail loadClientDetailByClientId(@Param("clientId")String clientId);

	int createClientDetail(@Param("param") OAuthClientDetail oAuthClientDetail);

}
