package com.mark.sdklib.okhttp.https;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/**
 * @Desc https 工具类
 * @作者 Mark
 * @时间 2019-12-10
 * @EMail 2285581945@qq.com
 */
public class HttpsUtils {
    //支持所有类型的证书
    public static SSLSocketFactory getSslSocketFactory() {
        //1.生成一个信任管理器类
        X509TrustManager mTrustManager = getTrustManager();

        //2.创建加密上下文
        SSLContext sslContext = null;

        try {
            //SSL与服务器要保持一致的算法类型
            sslContext = SSLContext.getInstance("SSL");
            X509TrustManager[] xTrustManager = new X509TrustManager[]
                    {mTrustManager};
            sslContext.init(null, xTrustManager, new SecureRandom());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return sslContext.getSocketFactory();
    }

    //生成一个信任管理器类
    public static X509TrustManager getTrustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s)
                    throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s)
                    throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }
}
