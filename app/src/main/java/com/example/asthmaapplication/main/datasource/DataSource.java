package com.example.asthmaapplication.main.datasource;

import com.example.asthmaapplication.main.response.AsthmaInfoResponse;

import java.util.List;

import io.reactivex.Single;

public interface DataSource {

    Single<List<AsthmaInfoResponse>> getInformation(String sectionNumber);
}
