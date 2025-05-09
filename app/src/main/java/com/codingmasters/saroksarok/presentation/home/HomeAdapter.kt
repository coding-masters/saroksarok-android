package com.codingmasters.saroksarok.presentation.home

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
import retrofit2.Response
import timber.log.Timber

class HomeAdapter(
    private val showDetail:(ResponseAllDto.Data, Int)->Unit,
):RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private val contentList = mutableListOf<ResponseAllDto.Data>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int = contentList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(contentList[position], position, imageList[position])
    }

    fun getList(list:List<ResponseAllDto.Data>){
        contentList.clear()
        contentList.addAll(list)
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

    inner class HomeViewHolder(private val binding:ItemContentBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data:ResponseAllDto.Data, position:Int, image:Int){
            with(binding){
                val url = data.fileUrl
                if (url.endsWith(".pdf", ignoreCase = true)) {
                    ivImage.setImageResource(image) // PDF용 아이콘
                } else {
                    ivImage.load(url) {
                        transformations(
                            RoundedCornersTransformation(
                                topLeft = 60f,
                                topRight = 60f,
                                bottomLeft = 0f,
                                bottomRight = 0f
                            )
                        )
                    }
                }
                tvTitle.text=data.title
                tvId.text= binding.root.context.getString(R.string.id, data.id)
                tvPrice.text= data.price
                Timber.d("price: ${data.price.toString()}")
                tvType.text=data.type

                binding.ivBadge.visibility=(if(data.certified) View.VISIBLE else View.GONE)
                clickCard(data, image)

                val layoutParams = root.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.topMargin = if (position == 0) dpToPx(20) else 0
                layoutParams.bottomMargin = if (position == contentList.lastIndex) dpToPx(20) else 0
                root.layoutParams = layoutParams
            }
        }

        private fun clickCard(data: ResponseAllDto.Data, image:Int){
            binding.cvContent.setOnClickListener{
                showDetail(data, image)
            }
        }

        private fun dpToPx(dp: Int): Int {
            val density = binding.root.context.resources.displayMetrics.density
            return (dp * density).toInt()
        }

    }
}