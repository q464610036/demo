package com.example.springcloudgateway.util;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.spring.util.BeanUtils;
import com.example.springcloudgateway.vo.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.util.Base64Util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * 自己写一个jwt
 */
public class JWTUtil {
    //密钥
    private final static String SECRET = "12312312312312312312312123212121";
    private final static String signAlgorithm = "HmacSHA256";

    /**
     * 生成jwt
     * @param user
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String getJWT(User user) throws NoSuchAlgorithmException, InvalidKeyException {
        String token = null;
        String header64 = getHeader64();
        String payLoad64 = getPayLoad64(user);
        //准备数据
        String data = header64+"."+payLoad64;
        token = header64 + "." + payLoad64 + "." + getSignature(data);
        return token;
    }

    private static String getHeader64(){
        String header="{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
        return Base64.encodeBase64URLSafeString(header.getBytes(StandardCharsets.UTF_8));
    }

    private static String getPayLoad64(User user){
        return Base64.encodeBase64URLSafeString(JSON.toJSONString(user).getBytes(StandardCharsets.UTF_8));
    }

    private static String getSignature(String data) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(signAlgorithm);//初始化算法类型
        //创建安全密钥
        SecretKeySpec spec = new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), signAlgorithm);
        //让mac使用指定密钥
        mac.init(spec);
        //生成签名
        byte[] bytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeBase64URLSafeString(bytes);
    }

    /**
     * 比较密钥
     * @param jwt
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String checkSign(String jwt) throws NoSuchAlgorithmException, InvalidKeyException {
        String data = jwt.substring(0,jwt.lastIndexOf("."));
        String signature = jwt.substring(jwt.lastIndexOf(".")+1);
        if (signature.equals(getSignature(data))) {
            return data;
        }
        return null;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
//        User user = new User();
//        user.setId(1);
//        user.setName("张三");
//        user.setRole("admin");
//        //生成jwt
//        String jwt = getJWT(user);
//        System.out.println(jwt);
//        //验证密钥
//        System.out.println(checkSign(jwt));

        jjwtTest2();


    }

    /**
     * jjwt例子 使用系统生成的key
     */
    public static void jjwtTest1(){
        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setRole("admin");
        String payLoadJson = JSON.toJSONString(user);
        //使用jjwt库
        //生成jwt
        //这个密钥是自动生成的，为了安全性，key必须满足JJWT的规范，生成key后持久化下来，将来就都用这个key了
        // 问题：怎么持久化？
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String jws = Jwts.builder().setSubject(payLoadJson).signWith(key).compact();

        //验证jwt
            Claims body =  Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws).getBody();
            String subject = body.getSubject();
            System.out.println(subject);
    }

    /**
     * jjwt例子 使用自定义key
     */
    public static void jjwtTest2(){
        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setRole("admin");
        Map<String, Object> map = BeanUtil.beanToMap(user);
        //使用jjwt库
        //生成jwt
        //这个key字符串必须是32位32*8=256，所以是HS256
        String SECRET = "12312312312312312312312123212121";
        Key key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        String jws = Jwts.builder()
                //public-payload部分
                .setSubject("employee") //表示给员工用的
                .setAudience("zhangsan") //给谁看
                .setExpiration(DateUtils.addMinutes(new Date(), 30))//过期时间
                .setNotBefore(new Date()) //生效时间
                .setIssuedAt(new Date()) //签发时间
                .setId(UUID.randomUUID().toString().replace("-",""))
                //private-payload部分
//                .setClaims(map) //如果用set会把public部分数据都覆盖掉
                .addClaims(map)
                //数据压缩
                .compressWith(CompressionCodecs.DEFLATE) //使用默认压缩
                //签名部分
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        //解析jwt
        try{
            JwtParserBuilder jwtParserBuilder =  Jwts.parserBuilder();
            jwtParserBuilder.setSigningKey(key);
            //创建parser对象
            JwtParser jwtParser = jwtParserBuilder.build();
            //解析数据
            Jws<Claims> claims = jwtParser.parseClaimsJws(jws);
            //获取数据
            Claims body = claims.getBody();
            System.out.println("subject:"+body.getSubject()+
                    " audience:"+body.getAudience()+
                    " name:"+body.get("name"));
        } catch (SignatureException e) {
            System.out.println("签名异常");
            e.printStackTrace();
        } catch (ExpiredJwtException e) {
            System.out.println("jwt过期异常");
            e.printStackTrace();
        }
    }

    /**
     * jjwt公私钥例子
     * 生成一对公私钥，公钥生成的key，只有私钥才能解析
     * 服务器生成公钥和私钥，公钥给ios和Android，服务器自己保留私钥，这样ios和android生成的jwt只有服务器能解析，起到保密的作用。
     * 问题：这样端上不就可以伪造jwt了吗？
     * 答：token不能用RSA
     */
    public static void jjwtTest3(){
        KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        //私钥对密码加密
        String jws = Jwts.builder().setSubject("123456").signWith(privateKey).compact();
        //公钥对密码解密
        String subject = Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(jws).getBody().getSubject();
        System.out.println(subject);
    }



}
