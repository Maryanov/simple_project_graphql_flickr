package com.andreymaryanov.myapplication.activities

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.andreymaryanov.myapplication.R
import com.andreymaryanov.myapplication.adapters.RecyclerViewAdapter
import com.andreymaryanov.myapplication.common.AppConst.MESSAGE_ERROR
import com.andreymaryanov.myapplication.contracts.RelayCommand
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import com.andreymaryanov.myapplication.adapters.RecyclerViewClickListener

class MainActivity : BaseActivity() {

    private val viewModel by lazy { viewModelResolver.resolveMainViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupLayout()
    }

    private fun setupLayout() {

        buttonLoad.setOnClickListener { getFeed() }

        viewModel.receiveLoadDataCommand = RelayCommand({ data ->
            mainListView.visibility = View.VISIBLE
            buttonLoad.visibility = View.VISIBLE
            val layoutManager = LinearLayoutManager(this@MainActivity)
            val adapter = RecyclerViewAdapter(this@MainActivity, data!!, object : RecyclerViewClickListener {
                override fun onItemClick(view: View) {
                    getFeed()
                }
            })
            mainListView.adapter = adapter
            mainListView.layoutManager = layoutManager
            progressBarLoad.visibility = View.GONE
            mainListView.visibility = View.VISIBLE

        })

        viewModel.showMessageResultCommand = RelayCommand({textMessage->
            showMessage(textMessage!!)
        })
    }

    private fun showMessage(testMessage: String) {
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Внимание!")
                .setMessage(testMessage)
                .setCancelable(false)
                .setNegativeButton("Let's try again!",
                        { dialog, _ ->
                               getFeed()
                            dialog.cancel() })
        val alert = builder.create()
        alert.show()
    }

    private fun getFeed(){
        if (isConnectInternet()) {
            mainListView.visibility = View.GONE
            progressBarLoad.visibility = View.VISIBLE
            buttonLoad.visibility = View.GONE
            viewModel.goToGetFeedCommand.execute()
        } else showMessage("NO Connect!")
    }

    private fun isConnectInternet(): Boolean {

        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
        if (activeNetworkInfo == null) {
            Toast.makeText(this, MESSAGE_ERROR, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}

