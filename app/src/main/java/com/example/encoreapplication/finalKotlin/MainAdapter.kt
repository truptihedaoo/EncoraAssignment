package com.example.encoreapplication.finalKotlin


import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
//import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

import android.content.Context

import android.content.Intent
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.example.encoreapplication.R
import com.example.encoreapplication.databinding.ExampleItemBinding
import com.squareup.picasso.Picasso

import java.util.ArrayList

class Example6Adapter(
    private val mContext: Context,
    private val mExampleList: ArrayList<ExampleItems>
) : RecyclerView.Adapter<Example6Adapter.ExampleViewHolder>() {
    internal var mBinding: ExampleItemBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        //        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item, parent, false);
        //        return new ExampleViewHolder(v);
        mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.example_item,
            parent,
            false
        )
        return ExampleViewHolder(mBinding!!)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = mExampleList[position]
        Log.d("currentItem", currentItem.toString())
        val imageUrl = currentItem.getmImageUrl()
        val creatorName = currentItem.getmCreator()
        //        String likeCount = currentItem.getCreator();
        //        int likeCount = currentItem.getLikeCount();
        holder.mBinding.textViewCreator.text = creatorName

        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mBinding.imageView)

        holder.mBinding.cardMain.setOnClickListener {
            mContext.startActivity(
                Intent(
                    mContext,
                    DetailPageK::class.java
                ).putExtra("id", currentItem._id.toString())
            )
        }
    }

    override fun getItemCount(): Int {
        return mExampleList.size
    }

    inner class ExampleViewHolder(
        //        public ImageView mImageView;
        //        public TextView mTextViewCreator;
        //        CardView cardMain;
        //        public ExampleViewHolder(ExampleItemBinding itemView) {
        //            super(itemView);
        //            mImageView = itemView.findViewById(R.id.image_view);
        //            mTextViewCreator = itemView.findViewById(R.id.text_view_creator);
        //            cardMain = itemView.findViewById(R.id.cardMain);
        //
        //        }

        internal var mBinding: ExampleItemBinding
    ) : RecyclerView.ViewHolder(mBinding.root) {

        fun bind(obj: Any) {
            //            mBinding.setVariable(BR.orderWrapper, obj);
            mBinding.executePendingBindings()
        }
    }
}

