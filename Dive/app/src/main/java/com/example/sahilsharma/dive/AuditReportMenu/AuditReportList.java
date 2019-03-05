package com.example.sahilsharma.dive.AuditReportMenu;

import java.util.ArrayList;

/**
 * Created by sahilsharma on 3/5/19.
 */

public class AuditReportList {

    private ArrayList<String> tagNames = new ArrayList<>();

    public void save(String tag){
        tagNames.add(tag);
    }
    public ArrayList<String> getTagNames(){
        return tagNames;
    }
}
