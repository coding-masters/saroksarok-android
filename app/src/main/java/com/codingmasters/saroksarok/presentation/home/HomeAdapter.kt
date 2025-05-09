package com.codingmasters.saroksarok.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.codingmasters.saroksarok.data.Content
import com.codingmasters.saroksarok.databinding.ItemContentBinding

class HomeAdapter(
    private val showDetail:(Content)->Unit,
):RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private val contentList = mutableListOf<Content>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int = contentList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(contentList[position], position)
    }

    fun getList(list:List<Content>){
        contentList.clear()
        contentList.addAll(list)
        notifyDataSetChanged()
    }

    inner class HomeViewHolder(private val binding:ItemContentBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data:Content, position:Int){
            with(binding){
                ivImage.load(data.image){
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

                binding.ivBadge.visibility=(if(data.certified) View.VISIBLE else View.GONE)
                clickCard(data)

                val layoutParams = root.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.topMargin = if (position == 0) dpToPx(20) else 0
                layoutParams.bottomMargin = if (position == contentList.lastIndex) dpToPx(20) else 0
                root.layoutParams = layoutParams
            }
        }

        private fun clickCard(data: Content){
            binding.cvContent.setOnClickListener{
                showDetail(data)
            }
        }

        private fun dpToPx(dp: Int): Int {
            val density = binding.root.context.resources.displayMetrics.density
            return (dp * density).toInt()
        }

    }
}