package com.codingmasters.saroksarok.presentation.my

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.codingmasters.saroksarok.data.Content
import com.codingmasters.saroksarok.databinding.ItemContentBinding

class MyAdapter(
    private val showDetail:(Content)->Unit,
):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private val myList = mutableListOf<Content>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=ItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = myList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(myList[position])
    }

    fun getList(list:List<Content>){
        myList.clear()
        myList.addAll(list)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(private val binding: ItemContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Content) {
            with(binding) {
                ivImage.load(data.image) {
                    transformations(
                        RoundedCornersTransformation(
                            topLeft = 60f,
                            topRight = 60f,
                            bottomLeft = 0f,
                            bottomRight = 0f
                        )
                    )
                }
                tvTitle.text=data.title
                tvId.text=data.id
                tvPrice.text=data.price
                tvType.text=data.type

                ivBadge.visibility=(if(data.certified) View.VISIBLE else View.GONE)

                cvContent.setOnClickListener{
                    showDetail(data)
                }
            }
        }
    }
}