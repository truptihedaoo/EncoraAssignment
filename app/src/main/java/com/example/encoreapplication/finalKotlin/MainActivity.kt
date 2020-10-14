package com.example.encoreapplication.finalKotlin

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import android.app.Activity
import android.content.Context
import android.os.Bundle

import com.example.encoreapplication.R


import com.example.encoreapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    private var mViewModel: MainVM? = null
    internal var mBinding: ActivityMainBinding? = null
    private var mContext: Context? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingOfWidgets()
    }

    private fun bindingOfWidgets() {
        mContext = this@MainActivity
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainVM(mContext as Activity, mBinding as ActivityMainBinding) as T
            }
        }
        mViewModel = ViewModelProviders.of(this, factory).get<MainVM>(MainVM::class.java!!)
        mBinding?.lifecycleOwner = this
        initUI()
    }

    private fun initUI() {
        mViewModel!!.setRecycler(this)
        mViewModel!!.parseJSON(this)


    }

}