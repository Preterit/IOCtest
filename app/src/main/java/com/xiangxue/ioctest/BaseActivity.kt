package com.xiangxue.ioctest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xiangxue.ioctest.util.InjectUtil

/**
 * Date:2020-02-23
 * author:lwb
 * Desc:
 */
open class BaseActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        InjectUtil.inject(this)
    }
}