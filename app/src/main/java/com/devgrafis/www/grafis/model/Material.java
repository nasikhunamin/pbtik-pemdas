package com.devgrafis.www.grafis.model;

public class Material {
    private String MaterialTitle;
    private String MaterialSubtitle;
    private String MaterialContent;
    private int ImgSource;


    public Material(String MaterialTitle, String MaterialSubtitle, String MaterialContent, int Img){
        this.MaterialSubtitle = MaterialSubtitle;
        this.MaterialTitle = MaterialTitle;
        this.MaterialContent = MaterialContent;
        this.ImgSource = Img;
    }

    public String getMaterialSubtitle() {
        return MaterialSubtitle;
    }
    public String getMaterialTitle() { return MaterialTitle; }
    public int getImgSource() { return ImgSource; }
    public String getMaterialContent() { return MaterialContent; }
}
