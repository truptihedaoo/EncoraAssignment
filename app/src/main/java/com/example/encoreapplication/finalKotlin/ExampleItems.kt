package com.example.encoreapplication.finalKotlin



class ExampleItems {
    var _id: Int = 0
    private var mImageUrl: String? = null
    private var mCreator: String? = null
    private var mLikes: String? = null

    constructor() {}
    constructor(imageUrl: String, creator: String, likes: String) {
        mImageUrl = imageUrl
        mCreator = creator
        mLikes = likes
    }

    constructor(id: Int, creator: String, imageUrl: String, url: String) {
        this._id = id
        mImageUrl = imageUrl
        mCreator = creator
        mLikes = url
    }

    fun getmImageUrl(): String? {
        return mImageUrl
    }

    fun setmImageUrl(mImageUrl: String) {
        this.mImageUrl = mImageUrl
    }

    fun getmCreator(): String? {
        return mCreator
    }

    fun setmCreator(mCreator: String) {
        this.mCreator = mCreator
    }

    fun getmLikes(): String? {
        return mLikes
    }

    fun setmLikes(mLikes: String) {
        this.mLikes = mLikes
    }

    //    public String getImageUrl() {
    //        return mImageUrl;
    //    }
    //    public String getCreator() {
    //        return mCreator;
    //    }
    //    public String getLikeCount() {
    //        return mLikes;
    //    }
}
