package com.my.qs.authserver;

import com.my.qs.authserver.dao.OAuthClientMapper;
import com.my.qs.authserver.entity.OAuthClientDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;

public class OAuth2ApprovalHandler extends TokenStoreUserApprovalHandler {

	@Autowired
	private OAuthClientMapper oAuthClientMapper;

	@Override
	public boolean isApproved(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
		if (super.isApproved(authorizationRequest, userAuthentication)){
			return true;
		}

		if (!userAuthentication.isAuthenticated()){
			return false;
		}

		OAuthClientDetail oAuthClientDetail = oAuthClientMapper.loadClientDetailByClientId(authorizationRequest.getClientId());

		return oAuthClientDetail != null && oAuthClientDetail.getEnable();
	}
}
