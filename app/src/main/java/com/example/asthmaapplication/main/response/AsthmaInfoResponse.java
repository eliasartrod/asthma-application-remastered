package com.example.asthmaapplication.main.response;

import java.util.List;

public class AsthmaInfoResponse {
    public int section;
    public List<SectionDetail> sectionDetails;

    public int getSection() { return section; }
    public void setSection(int value) { this.section = value; }

    public List<SectionDetail> getSectionDetails() { return sectionDetails; }
    public void setSectionDetails(List<SectionDetail> value) { this.sectionDetails = value; }

    public static class SectionDetail {
        public String title;
        public String description;
        public String imageView;

        public String getTitle() { return title; }
        public void setTitle(String value) { this.title = value; }

        public String getDescription() { return description; }
        public void setDescription(String value) { this.description = value; }

        public String getImageView() { return imageView; }
        public void setImageView(String value) { this.imageView = value; }
    }
}
