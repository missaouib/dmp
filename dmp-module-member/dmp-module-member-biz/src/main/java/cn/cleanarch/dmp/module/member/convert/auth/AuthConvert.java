package cn.cleanarch.dmp.module.member.convert.auth;

import cn.cleanarch.dmp.module.member.controller.app.auth.vo.*;
import cn.cleanarch.dmp.module.member.controller.app.social.vo.AppSocialUserUnbindReqVO;
import cn.cleanarch.dmp.module.system.api.oauth2.dto.OAuth2AccessTokenRespDTO;
import cn.cleanarch.dmp.module.system.api.sms.dto.code.SmsCodeSendReqDTO;
import cn.cleanarch.dmp.module.system.api.sms.dto.code.SmsCodeUseReqDTO;
import cn.cleanarch.dmp.module.system.api.social.dto.SocialUserBindReqDTO;
import cn.cleanarch.dmp.module.system.api.social.dto.SocialUserUnbindReqDTO;
import cn.cleanarch.dmp.module.system.enums.sms.SmsSceneEnum;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthConvert {

    AuthConvert INSTANCE = Mappers.getMapper(AuthConvert.class);

    SocialUserBindReqDTO convert(Long userId, Integer userType, AppAuthSocialLoginReqVO reqVO);
    SocialUserUnbindReqDTO convert(Long userId, Integer userType, AppSocialUserUnbindReqVO reqVO);

    SmsCodeSendReqDTO convert(AppAuthSmsSendReqVO reqVO);
    SmsCodeUseReqDTO convert(AppAuthResetPasswordReqVO reqVO, SmsSceneEnum scene, String usedIp);
    SmsCodeUseReqDTO convert(AppAuthSmsLoginReqVO reqVO, Integer scene, String usedIp);

    AppAuthLoginRespVO convert(OAuth2AccessTokenRespDTO bean);

}
