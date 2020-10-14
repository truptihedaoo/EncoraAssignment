package com.example.encoreapplication.finalKotlin

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.encoreapplication.databinding.ActivityMainBinding

import org.json.JSONException

import java.util.ArrayList

class MainVM(private val mContext: Activity, internal var mBinding: ActivityMainBinding) :
    ViewModel() {


    private var mExampleAdapter: Example6Adapter? = null
    private var mExampleList: ArrayList<ExampleItems>? = null
    private var mRequestQueue: RequestQueue? = null

    internal var db: DatabaseHandlerK

    init {

        db = DatabaseHandlerK(this.mContext)
    }

    fun parseJSON(mainActivity: MainActivity) {
        val url =
            "https://rss.itunes.apple.com/api/v1/in/itunes-music/top-albums/all/25/explicit.json"
        val request = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                try {
                    val jsonObject = response.getJSONObject("feed")
                    Log.d("jsonObject", jsonObject.toString())
                    val jsonArray = jsonObject.getJSONArray("results")
                    for (i in 0 until jsonArray.length()) {
                        val hit = jsonArray.getJSONObject(i)
                        val creatorName = hit.getString("name")
                        val imageUrl = hit.getString("artworkUrl100")
                        val likeCount = hit.getString("url")
                        //                                int likeCount = hit.getInt("likes");
                        mExampleList!!.add(ExampleItems(imageUrl, creatorName, likeCount))

                        db.addSongs(ExampleItems(imageUrl, creatorName, likeCount))

                    }

                    Log.d("Reading: ", "Reading all contacts..")
                    val contacts = db.allData

                    for (cn in contacts) {
                        val log = "Id: " + cn._id + " ,Name: " + cn.getmCreator() + " ,Phone: " +
                                cn.getmImageUrl()
                        // Writing Contacts to log
                        Log.d("NameName: ", log)
                    }

                    mExampleAdapter =
                        Example6Adapter(mContext, db.allData as ArrayList<ExampleItems>)
                    mBinding.recyclerView.adapter = mExampleAdapter
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error -> error.printStackTrace() })
        mRequestQueue!!.add(request)
    }


    fun setRecycler(mainActivity: MainActivity) {
        mBinding.recyclerView.setHasFixedSize(true)
        mBinding.recyclerView.layoutManager = LinearLayoutManager(mainActivity)
        mExampleList = ArrayList()
        mRequestQueue = Volley.newRequestQueue(mainActivity)

    }
}
