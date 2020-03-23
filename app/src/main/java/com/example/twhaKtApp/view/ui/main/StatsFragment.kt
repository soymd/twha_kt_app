package com.example.twhaKtApp.view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twhaKtApp.R
import com.example.twhaKtApp.TwhaRepository
import com.example.twhaKtApp.model.TwhaAnswerModel

class StatsFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private val tRepository = TwhaRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.recyclerView = view.findViewById(R.id.container_recycler_view)

        this.recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = StatsViewAdapter(
                generateItemList(),
                object : StatsViewAdapter.ListListener {
                    override fun onClickItem(tappedView: View, twhaAnswerModel: TwhaAnswerModel) {
                        this@StatsFragment.onClickItem(tappedView, twhaAnswerModel)
                    }
                }
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.recyclerView?.adapter = null
        this.recyclerView = null
    }

    //RecyclerViewの生成時に一度だけ動く
    private fun generateItemList(): MutableList<TwhaAnswerModel> {
        val context = activity
        var answerModelList = mutableListOf<TwhaAnswerModel>()

        if (context != null) {
            //userIdは暫定で1
            answerModelList = tRepository.readAnswers(context, 1)
        }
        return answerModelList
    }

    //RecyclerView内のアイテムがクリックされたときに動く
    private fun onClickItem(tappedView: View, twhaAnswerModel: TwhaAnswerModel) {
    }
}