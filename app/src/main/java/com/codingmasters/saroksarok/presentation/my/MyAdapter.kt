package com.codingmasters.saroksarok.presentation.my

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.codingmasters.saroksarok.R
import com.codingmasters.saroksarok.data.Content
import com.codingmasters.saroksarok.data.response_dto.ResponseAllDto
import com.codingmasters.saroksarok.databinding.ItemContentBinding

class MyAdapter(
    private val showDetail:(ResponseAllDto.Data)->Unit,
):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private val myList = mutableListOf<ResponseAllDto.Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=ItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = myList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(myList[position], imageList[position])
    }

    fun getList(list:List<ResponseAllDto.Data>){
        myList.clear()
        myList.addAll(list)
        notifyDataSetChanged()
    }

    private val imageList = listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5,
        R.drawable.image6,
        R.drawable.image7,
        R.drawable.image8,
        R.drawable.image9,
        R.drawable.image10,
    )

    inner class MyViewHolder(private val binding: ItemContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ResponseAllDto.Data, image:Int) {
            with(binding) {
                ivImage.load(data.thumnailURL) {
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
                tvId.text=binding.root.context.getString(R.string.id, data.tokenId)
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