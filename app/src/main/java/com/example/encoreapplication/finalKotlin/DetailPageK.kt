package com.example.encoreapplication.finalKotlin

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.encoreapplication.R
import com.example.encoreapplication.databinding.ActivityImageDetailBinding

import com.squareup.picasso.Picasso

import java.util.concurrent.TimeUnit
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.encoreapplication.finalKotlin.DetailPageVMK

import org.json.JSONException
import org.json.JSONObject


class DetailPageK : AppCompatActivity() {

    internal var mBinding: ActivityImageDetailBinding? = null

    private var mViewModel: DetailPageVMK? = null
    private var mContext: Context? = null

    internal var db = DatabaseHandlerK(this)

    private val b1: Button? = null
    private val b2: Button? = null
    private val b3: Button? = null
    private val b4: Button? = null
    private val iv: ImageView? = null
    private val mediaPlayer: MediaPlayer? = null

    private var startTime = 0.0

    private val myHandler = Handler()
    private val seekbar: SeekBar? = null
    private val tx1: TextView? = null

    private val UpdateSongTime = object : Runnable {
        override fun run() {
            startTime = mediaPlayer!!.currentPosition.toDouble()
            tx1!!.text = String.format(
                "%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(startTime.toLong()),
                TimeUnit.MILLISECONDS.toSeconds(startTime.toLong()) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(startTime.toLong())
                )
            )
            seekbar!!.progress = startTime.toInt()
            myHandler.postDelayed(this, 100)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingOfWidgets()
    }

    private fun bindingOfWidgets() {
        mContext = this@DetailPageK
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_image_detail)
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DetailPageVMK(mContext as Activity, mBinding as ActivityImageDetailBinding) as T
            }
        }
        mViewModel = ViewModelProviders.of(this, factory).get(DetailPageVMK::class.java)
        mBinding!!.lifecycleOwner = this
        initUI()
    }

    private fun initUI() {
        mViewModel!!.setRecycler(this)

    }

    companion object {
        var oneTimeOnly = 0
    }
}
