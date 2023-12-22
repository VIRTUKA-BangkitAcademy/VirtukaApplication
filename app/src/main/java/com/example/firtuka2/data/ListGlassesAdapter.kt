package com.example.firtuka2.data

import android.animation.ArgbEvaluator
import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.util.Pair
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.firtuka2.R
import com.example.firtuka2.data.repo.DetailData
import com.example.firtuka2.data.response.FramesItem
import com.example.firtuka2.databinding.ItemCardBinding
import com.example.firtuka2.ui.deskription.DescriptionActivity
import com.squareup.picasso.Picasso

class ListGlassesAdapter
    : PagingDataAdapter<FramesItem, ListGlassesAdapter.ListViewHolder>(DIFF_CALLBACK)
{

    private lateinit var binding: ItemCardBinding

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FramesItem>(){
            override fun areItemsTheSame(oldItem: FramesItem, newItem: FramesItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: FramesItem,
                newItem: FramesItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ListViewHolder(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {

        val imageGlasses : ImageView = binding.imgItemPhoto
        val nameGlasses : TextView = binding.textName
        val gender : TextView = binding.textGender

        fun bind(review: FramesItem){
            Glide.with(binding.root)
                .load(review.image)
                .into(imageGlasses)

            nameGlasses.text = review.name
            gender.text = review.gender
//            binding.textName.text = "${review.name}"
//            binding.textGender.text = "${review.gender}"
//            Picasso.get().load(review.image).into(binding.imgItemPhoto)


            val clickedGlasses = binding.root.context

            itemView.setOnClickListener {
                val intent = Intent(clickedGlasses, DescriptionActivity::class.java)
                intent.putExtra(DescriptionActivity.EXTRA_ID, review.id)
                intent.putExtra(DescriptionActivity.EXTRA_NAME, review.name)
                intent.putExtra(DescriptionActivity.EXTRA_GENDER, review.gender)
                intent.putExtra(DescriptionActivity.EXTRA_IMG, review.image)


                clickedGlasses.startActivity(intent)
            }

            binding.cardView.setOnClickListener{
                val intentDetail = Intent(itemView.context, DescriptionActivity::class.java)
                intentDetail.putExtra(DescriptionActivity.EXTRA_ID, DetailData(nama = review.name!!, image = review.image!!, gender = review.gender!! ))

                itemView.context.startActivity(intentDetail)
            }

//            Glide.with(binding.root)
//                .load(review.image)
//                .placeholder(gradient)
//                .listener(
//                    object : RequestListener<Drawable> {
//                        override fun onLoadFailed(
//                            e: GlideException?,
//                            model: Any?,
//                            target: Target<Drawable>,
//                            isFirstResource: Boolean
//                        ) : Boolean {
//                            return false
//                        }
//
//                        override fun onResourceReady(
//                            resource: Drawable,
//                            model: Any,
//                            target: Target<Drawable>?,
//                            dataSource: DataSource,
//                            isFirstResource: Boolean
//                        ): Boolean {
//                            return false
//                        }
//
//                    }
//                )
//                .into(binding.imgItemPhoto)



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
//        val view: View =
//            LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
//        return ListViewHolder(view)

        binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)

//        val from = ContextCompat.getColor(binding.root.context, R.color.cream)
//        val to = ContextCompat.getColor(binding.root.context, R.color.navy)
//        binding.imgItemPhoto.setImageDrawable(
//            GradientDrawable( GradientDrawable.Orientation.LEFT_RIGHT,
//                intArrayOf(
//                    to, from
//                ),
//            ))
//        val gradient = binding.imgItemPhoto.drawable as GradientDrawable
//
//        val evaluator = ArgbEvaluator()
//        return ListViewHolder(binding)
    }

//    override fun getItemCount(): Int = listGlasses.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val glasses = getItem(position)

        if (glasses != null) {
            holder.bind(glasses)
        }

//        holder.bind(getItem(position)!!)
    }


}