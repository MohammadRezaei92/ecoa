package rezaei.mohammad.ecoa.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.content_result.*
import rezaei.mohammad.ecoa.objects.Cons
import rezaei.mohammad.ecoa.R
import java.text.NumberFormat
import java.util.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setSupportActionBar(toolbar)

        val cost = intent.extras.getFloat(Cons.COST)
        val time = intent.extras.getInt(Cons.TIME)
        setDataToView(cost,time)

    }

    @SuppressLint("SetTextI18n")
    private fun setDataToView(cost: Float, time: Int){
        txtCost.text = moneyFormat(cost.toLong())
        txtTime.text = "$time ساعت "
    }

    private fun moneyFormat(price: Long): String {
        return (NumberFormat
                .getNumberInstance(Locale.US)
                .format(java.lang.Double.parseDouble(price.toString()))
                + " " + Cons.CURRENCY)
    }

}
