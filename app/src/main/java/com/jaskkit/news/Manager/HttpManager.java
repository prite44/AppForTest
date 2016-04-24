package com.jaskkit.news.Manager;

import com.jaskkit.news.Manager.Http.ApiService;
import com.jaskkit.news.R;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by jaskkit on 4/21/16.
 */
public class HttpManager {

    private static HttpManager instance;

    public static HttpManager getInstance() {
        if (instance == null)
            instance = new HttpManager();
        return instance;
    }

    private ApiService service;

    private HttpManager() {
        try {
            service = createAdapter().create(ApiService.class);
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private RestAdapter createAdapter() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        RestAdapter rest = new RestAdapter.Builder()
                .setEndpoint("https://mobilemagic.de")
                .setClient(new OkClient(UnsafeOkHttpClient.getInstance().getUnsafeOkHttpClient(R.raw.mobliecert)))
                .build();
        return rest;
    }

    public ApiService getService(){
        return service;
    }

}
