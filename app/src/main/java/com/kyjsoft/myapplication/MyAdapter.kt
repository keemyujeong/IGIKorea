package com.kyjsoft.myapplication

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kyjsoft.myapplication.databinding.TourRecyclerItemBinding
import java.text.SimpleDateFormat
import java.util.*


class MyAdapter(val context: Context, var items : MutableList<Item>): RecyclerView.Adapter<MyAdapter.VH>() {

    data class VH (val itemView : View) : RecyclerView.ViewHolder(itemView){
        val binding: TourRecyclerItemBinding = TourRecyclerItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView = LayoutInflater.from(context).inflate(R.layout.tour_recycler_item, parent, false)
        return VH(itemView)
    }


    override fun onBindViewHolder(holder: VH, position: Int) {

        Glide.with(context).load("file:///android_asset/${items[position].imgUrl}").into(holder.binding.iv)
        holder.binding.tvTourName.text = items[position].tourName
        holder.binding.tvTime.text =items[position].time

        val currentDate = Date(System.currentTimeMillis()) // 날짜 포맷 맞춰주기

        val startDate = SimpleDateFormat("yyyy.MM.dd").parse(items[position].time.substring(0,10))
        val endDate = SimpleDateFormat("yyyy.MM.dd").parse("2023." + items[position].time.substring(13))
        // TODO 다른 로직도 고민해보기
        if (currentDate.time < startDate.time){
            holder.binding.tvSchedule.visibility = View.VISIBLE
            holder.binding.tvEnd.visibility = View.GONE
            holder.binding.tvOngoing.visibility = View.GONE
            // 여행 일정 시작 전
        } else if (currentDate.time >= startDate.time && currentDate.time <= endDate.time){
            holder.binding.tvOngoing.visibility = View.VISIBLE
            holder.binding.tvEnd.visibility = View.GONE
            holder.binding.tvSchedule.visibility = View.VISIBLE
            // 여행 일정 종료 후
        } else {
            holder.binding.tvEnd.visibility = View.VISIBLE
            holder.binding.tvOngoing.visibility = View.GONE
            holder.binding.tvSchedule.visibility = View.VISIBLE
            // 여행 일정 중
        }


    } // TODO 해야할 일 -> 사진 / 늦은 순




    override fun getItemCount(): Int = items.size

}