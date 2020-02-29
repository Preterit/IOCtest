package com.xiangxue.ioctest

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.xiangxue.ioctest.util.ContentView
import com.xiangxue.ioctest.util.OnClick
import com.xiangxue.ioctest.util.ViewInject
import java.util.*

@ContentView(R.layout.activity_main)
class MainActivity : BaseActivity() {

    @ViewInject(R.id.tv1)
    var tv1: TextView? = null
    @ViewInject(R.id.tv2)
    var tv2: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tv1?.text = "第1个文本-----1"
        tv2?.text = "第2个文本-----2"
//        tv2.setOnClickListener(object :View.OnClickListener{
//            override fun onClick(v: View?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//        })
    }

    @OnClick(R.id.tv3)
    fun click(view: View?) {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
    }

}
