package com.csair.wxopen.core.service;

import com.csair.wxopen.constant.CacheConstant;
import com.csair.wxopen.demo.dto.WxSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description session服务
 * @author kelvin
 * @date 2016年10月2日 下午3:07:32
 */
@Component
public class SessionService {

    @Value("${session.expiry.time}")
    private String expiryTime;

	@Autowired
	CacheService cacheService;

	private WxSession initSession(String openid) {
		String key = CacheConstant.SESSION + openid;
		WxSession session = new WxSession();
		cacheService.put(key, session);
		return session;
	}

	public WxSession getSession(String openid) {
		String key = CacheConstant.SESSION + openid;
		WxSession session = null;
		session = cacheService.get(key, WxSession.class);
		return session == null ? initSession(openid) : session;
	}

    /**
     * 获取session缓存有效期
     * @return
     */
    public Integer getSessionExpiryTime()
    {
        Integer expiry = cacheService.get("session", Integer.class);

        if(expiry == null)
        {
            expiry = Integer.valueOf(expiryTime);
            cacheService.put("session", expiry);
        }

        return expiry;
    }
}
