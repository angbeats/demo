package com.my.qs.authserver.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OAuthClientDetail {
	private String clientId;
	private String ResourceIds;
	private String clientSecret;
	private String scope;
	private String authorizedGrantTypes;
	private String webServerRedirectUri;
	private String authorities;
	private String accessTokenValidity;
	private String refreshTokenValidity;
	private String additionalInformation;
	private Boolean enable;
	private String autoapprove;


}
