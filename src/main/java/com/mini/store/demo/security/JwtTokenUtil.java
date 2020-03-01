package com.mini.store.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtTokenUtil {

    public static final String AUTH_HEADER_KEY = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String ATTR_NAME = "UID";

    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (ExpiredJwtException eje) {
            log.error("===== Token过期 =====", eje);
            throw new JwtException("jwt expired");
        } catch (Exception e) {
            log.error("===== token解析异常 =====", e);
            throw new JwtException("jwt error");
        }
    }

    public static String createJWT(String id, String name, Audience audience) {
        try {
            // 使用HS256加密算法
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            //生成签名密钥
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(audience.getBase64Secret());
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
            //添加构成JWT的参数
            JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                    // 可以将基本不重要的对象信息放到claims
                    .claim("id", id)
                    .claim("name", name)
                    .setSubject(id)           // 代表这个JWT的主体，即它的所有人
                    .setIssuer(audience.getClientId())              // 代表这个JWT的签发主体；
                    .setIssuedAt(new Date())        // 是一个时间戳，代表这个JWT的签发时间；
                    .setAudience(audience.getName())          // 代表这个JWT的接收对象；
                    .signWith(signatureAlgorithm, signingKey);
            //添加Token过期时间
            long TTLMillis = audience.getExpiresSecond();
            if (TTLMillis >= 0) {
                long expMillis = nowMillis + TTLMillis;
                Date exp = new Date(expMillis);
                builder.setExpiration(exp)  // 是一个时间戳，代表这个JWT的过期时间；
                        .setNotBefore(now); // 是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的
            }
            //生成JWT
            return builder.compact();
        } catch (Exception e) {
            log.error("签名失败", e);
            throw new JwtException("jwt sign error");
        }
    }

    public static String getId(String token, String base64Security) {
        return parseJWT(token, base64Security).getSubject();
    }

//    public static WxMaUserInfo getUserInfo(String token, String base64Security) {
//        Claims claims = parseJWT(token, base64Security);
//        String openId = claims.get("openId", String.class);
//        String avatarUrl = claims.get("avatarUrl", String.class);
//        String nickName = claims.get("nickName", String.class);
//        WxMaUserInfo wxMaUserInfo = new WxMaUserInfo();
//        wxMaUserInfo.setOpenId(openId);
//        wxMaUserInfo.setAvatarUrl(avatarUrl);
//        wxMaUserInfo.setNickName(nickName);
//        return wxMaUserInfo;
//    }

    public static boolean isExpiration(String token, String base64Security) {
        return parseJWT(token, base64Security).getExpiration().before(new Date());
    }
}

