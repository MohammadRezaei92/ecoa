package rezaei.mohammad.ecoa.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_result.*
import rezaei.mohammad.ecoa.BuildConfig
import rezaei.mohammad.ecoa.R
import android.content.Intent
import android.net.Uri


class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        app_version.text = BuildConfig.VERSION_NAME

        author.setOnClickListener {
            val url = "https://t.me/Syrix"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        fork_on_github.setOnClickListener {
            val url = "https://github.com/MohammadRezaei92/ecoa"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

    }

}
