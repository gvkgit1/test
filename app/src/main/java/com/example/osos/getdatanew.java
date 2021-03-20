package com.example.osos;

import android.net.Uri;

public class getdatanew {
    private Uri imageurl;
    public getdatanew(Uri imageurl) {
        this.imageurl=imageurl;
    }

    public Uri getImageurl() {
        return imageurl;
    }

    public void setImageurl(Uri imageurl) {
        this.imageurl = imageurl;
    }
}
