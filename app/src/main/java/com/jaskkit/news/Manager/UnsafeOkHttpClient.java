package com.jaskkit.news.Manager;

import android.content.Context;

import com.jaskkit.news.Manager.Http.OkHttpClientService;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by jaskkit on 4/21/16.
 */
public class UnsafeOkHttpClient  implements OkHttpClientService {

    private static UnsafeOkHttpClient instance;

    public static UnsafeOkHttpClient getInstance() {
        if (instance == null)
            instance = new UnsafeOkHttpClient();
        return instance;
    }

    private Context mContext;

    private UnsafeOkHttpClient() {
        mContext = Contextor.getInstance().getContext();
    }
    @Override
    public OkHttpClient getUnsafeOkHttpClient(int Certificate) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, KeyManagementException {
        OkHttpClient okHttpClient = new OkHttpClient();
        java.security.cert.Certificate ca = null;
        // loading CAs from an InputStream
        CertificateFactory cf = null;
        try {
            cf = CertificateFactory.getInstance("X.509");
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        InputStream cert = mContext.getResources().openRawResource(Certificate);

        try {
            ca = cf.generateCertificate(cert);
        } catch (CertificateException e) {
            e.printStackTrace();
        } finally {
            cert.close();
        }
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, null);
        keyStore.setCertificateEntry("ca", ca);
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keyStore);
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);
        okHttpClient.setSslSocketFactory(sslContext.getSocketFactory());
        return  okHttpClient;
    }
}
