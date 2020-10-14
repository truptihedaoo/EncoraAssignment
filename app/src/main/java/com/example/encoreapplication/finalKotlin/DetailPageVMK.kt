package com.example.encoreapplication.finalKotlin


import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Toast

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.encoreapplication.databinding.ActivityImageDetailBinding
import com.squareup.picasso.Picasso

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList

class DetailPageVMK(
    private val mContext: Activity,
    internal var mBinding: ActivityImageDetailBinding
) : ViewModel() {

    internal var db: DatabaseHandlerK


    init {

        db = DatabaseHandlerK(this.mContext)
    }

    fun setRecycler(detailPageView: DetailPageK) {

        Log.d("Reading: ", mContext.intent.getStringExtra("id")!!)
        val id = Integer.parseInt(mContext.intent.getStringExtra("id")!!)
        val contacts = db.getContact(id)

        mBinding.authorName.text = contacts.getmCreator()
        Picasso.with(mContext).load(contacts.getmImageUrl()).resize(250, 250).into(mBinding.image)

        mBinding.button2.setOnClickListener {
            try {
                val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(contacts.getmLikes()))
                mContext.startActivity(myIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    mContext,
                    "No application can handle this request",
                    Toast.LENGTH_LONG
                ).show()
                e.printStackTrace()
            }

            //                MediaPlayer mp=new MediaPlayer();
            //
            //                Log.d("contas",contacts.getmLikes());
            //                try{
            //                    mp.setDataSource("https://music.apple.com/in/album/nct-resonance-pt-1-the-2nd-album/1534864462?app=itunes");//Write your location here
            //                    mp.prepare();
            //                    mp.start();
            //
            //                }catch(Exception e){e.printStackTrace();}
        }
    }
}