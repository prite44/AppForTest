package com.jaskkit.news.Manager.Http;

import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * Created by jaskkit on 4/20/16.
 */
public interface OkHttpClientService {
    public OkHttpClient getUnsafeOkHttpClient(int Certificate) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, KeyManagementException;
}
