package rezaei.mohammad.ecoa.ui
import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.transition.TransitionManager
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.ViewCompat
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.hsalf.smilerating.BaseRating
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import rezaei.mohammad.ecoa.EstimateCostAndTime
import rezaei.mohammad.ecoa.R
import rezaei.mohammad.ecoa.objects.Settings
import java.lang.StringBuilder
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    val typeface by lazy {
        ResourcesCompat.getFont(this,R.font.iran_sans)
    }
    private var programmerLevel: Settings.ProgrammerLevel = Settings.ProgrammerLevel.Intern
    private var appHardness: Settings.AppHardness = Settings.AppHardness.VerySimple
    private var activities = 0
    private var services = 0
    private var graphic: Settings.Graphic = Settings.Graphic.None
    private var needSource: Boolean = false
    private var needSupport: Boolean = false
    private val HoursOfDay = 8.0
    private val DaysOfWeek = 5.0
    private val WeeksOfMonth = 4.0
    private lateinit var costAndTime: Pair<Long,Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        initAppbar()
        initViews()
    }

    private fun initAppbar(){
        val param = appBar.layoutParams
        val screenX = resources.displayMetrics.widthPixels
        val height = screenX.div(1.8f)
        param.height = height.toInt()
        appBar.layoutParams = param

        scrollView.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if(scrollView.canScrollVertically(-1)){
                ViewCompat.setElevation(appBar,8f)
            } else {
                ViewCompat.setElevation(appBar,0f)
            }
        }
    }

    private fun initViews(){
        checkboxSource.typeface = typeface
        checkboxSupport.typeface = typeface
        checkboxSource.setOnCheckedChangeListener { buttonView, isChecked ->
            needSource = isChecked
            showResult()
        }
        checkboxSupport.setOnCheckedChangeListener { buttonView, isChecked ->
            needSupport = isChecked
            showResult()
        }

        txtPrice.setDecimalFormat(DecimalFormat("###,###,###"))
        txtPrice.setAnimationDuration(1000)
        txtTime.setAnimationDuration(1000)

        layTime.setOnClickListener {
            TransitionManager.beginDelayedTransition(layResult)
            if(layTimeDetail.visibility == View.VISIBLE) {
                layTimeDetail.visibility = View.GONE
                imgCollapse.setImageResource(R.drawable.arrow_down)
            }
            else {
                layTimeDetail.visibility = View.VISIBLE
                imgCollapse.setImageResource(R.drawable.arrow_up)
            }
        }

        help.setOnClickListener {
            AlertDialog.Builder(this)
                    .setView(R.layout.time_tip)
                    .setPositiveButton("خب") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
        }

        pickerActivites.setListener {
            activities = it
            showResult()
        }
       pickerServices.setListener {
           services = it
           showResult()
       }

        viewProgrammerLevel.setNameForSmile(BaseRating.TERRIBLE,"کارآموز")
        viewProgrammerLevel.setNameForSmile(BaseRating.BAD,"مبتدی")
        viewProgrammerLevel.setNameForSmile(BaseRating.OKAY,"نیمه حرفه ای")
        viewProgrammerLevel.setNameForSmile(BaseRating.GOOD,"حرفه ای")
        viewProgrammerLevel.setNameForSmile(BaseRating.GREAT,"فول استک")
        viewProgrammerLevel.setTypeface(typeface)
        viewProgrammerLevel.setSelectedSmile(BaseRating.TERRIBLE,false)

        viewAppHardness.setNameForSmile(BaseRating.TERRIBLE,"خیلی ساده")
        viewAppHardness.setNameForSmile(BaseRating.BAD,"ساده")
        viewAppHardness.setNameForSmile(BaseRating.OKAY,"متوسط")
        viewAppHardness.setNameForSmile(BaseRating.GOOD,"حدودا سخت")
        viewAppHardness.setNameForSmile(BaseRating.GREAT,"سخت")
        viewAppHardness.setTypeface(typeface)
        viewAppHardness.setSelectedSmile(BaseRating.TERRIBLE,false)

        viewGraphic.setNameForSmile(BaseRating.TERRIBLE,"بدون گرافیک")
        viewGraphic.setNameForSmile(BaseRating.BAD,"مبتدی")
        viewGraphic.setNameForSmile(BaseRating.OKAY,"قابل قبول")
        viewGraphic.setNameForSmile(BaseRating.GOOD,"خوب")
        viewGraphic.setNameForSmile(BaseRating.GREAT,"عالی")
        viewGraphic.setTypeface(typeface)
        viewGraphic.setSelectedSmile(BaseRating.TERRIBLE,false)

        viewProgrammerLevel.setOnSmileySelectionListener { smiley, reselected ->
            when(smiley){
                BaseRating.TERRIBLE -> programmerLevel = Settings.ProgrammerLevel.Intern
                BaseRating.BAD -> programmerLevel = Settings.ProgrammerLevel.Beginner
                BaseRating.OKAY -> programmerLevel = Settings.ProgrammerLevel.SemiProfessional
                BaseRating.GOOD -> programmerLevel = Settings.ProgrammerLevel.Professional
                BaseRating.GREAT -> programmerLevel = Settings.ProgrammerLevel.FullStack
            }
            showResult()
        }

        viewAppHardness.setOnSmileySelectionListener { smiley, reselected ->
            when(smiley){
                BaseRating.TERRIBLE -> appHardness = Settings.AppHardness.VerySimple
                BaseRating.BAD -> appHardness = Settings.AppHardness.Simple
                BaseRating.OKAY -> appHardness = Settings.AppHardness.Medium
                BaseRating.GOOD ->appHardness = Settings.AppHardness.SemiHard
                BaseRating.GREAT -> appHardness = Settings.AppHardness.Hard
            }
            showResult()
        }

        viewGraphic.setOnSmileySelectionListener { smiley, reselected ->
            when(smiley){
                BaseRating.TERRIBLE -> graphic = Settings.Graphic.None
                BaseRating.BAD -> graphic = Settings.Graphic.Beginner
                BaseRating.OKAY -> graphic = Settings.Graphic.Acceptable
                BaseRating.GOOD -> graphic = Settings.Graphic.Good
                BaseRating.GREAT -> graphic = Settings.Graphic.Best
            }
            showResult()
        }

        showResult()

    }

    @SuppressLint("SetTextI18n")
    private fun showResult(){
        activities = pickerActivites.value
        services = pickerServices.value

        costAndTime = EstimateCostAndTime(programmerLevel
                , appHardness
                , graphic
                , activities
                , services
                , needSource
                , needSupport).getCostAndTime()

        txtPrice.countAnimation(txtPrice.text.toString().filter { it.isDigit() }.toInt(),costAndTime.first.toInt())
        txtTime.countAnimation(txtTime.text.toString().toInt(),costAndTime.second)

        val month = Math.floor(costAndTime.second.div(HoursOfDay).div(DaysOfWeek).div(WeeksOfMonth))
        val week = Math.floor((costAndTime.second.div(HoursOfDay).div(DaysOfWeek)).minus(month.times(WeeksOfMonth)))
        val day = Math.floor((costAndTime.second.div(HoursOfDay)).minus(month.times(WeeksOfMonth).times(DaysOfWeek) + week.times(DaysOfWeek)))

        txtTimeDetail.text = spanTimeText(month.toInt().toString(),"ماه و").append("\n" +
                spanTimeText(week.toInt().toString(),"هفته و").append("\n" +
                        spanTimeText(day.toInt().toString(),"روز")))
    }

    private fun spanTimeText(time: String, text: String): SpannableStringBuilder {
        val result = SpannableStringBuilder("$time $text")
        result.setSpan(StyleSpan(Typeface.BOLD),
                0, time.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        return result
    }

    private fun shareText(){
        val result = StringBuilder()
        result.appendln("نتیجه تخمین هزینه برای اپلیکیشن اندرویدی شما به شرح زیر است")
        result.appendln("قیمت تقریبی: ${NumberFormat.getNumberInstance(Locale.US).format(costAndTime.first)} تومان")
        result.appendln("زمان تقریبی: ${costAndTime.second} ساعت")
        result.appendln("محاسبه بالا توسط فاکتورهای زیر انجام گرفته است")
        result.appendln("سطح برنامه نویس: ${programmerLevel.getName()}")
        result.appendln("سختی برنامه: ${appHardness.getName()}")
        result.appendln("تعداد اکتیویتی، فرگمنت: $activities")
        result.appendln("تعداد سرویس: $services")
        result.appendln("سطح گرافیک برنامه: ${graphic.getName()}")
        result.appendln("تحویل سورس: ${if(needSource) "بله" else "خیر"}")
        result.appendln("پشتیبانی یکساله: ${if(needSupport) "بله" else "خیر"}")
        result.appendln("*تخمین هزینه و زمان توسط اپلیکیشن ecoa*")
        result.appendln("دریافت اپلیکیشن ecoa")
        result.appendln("market://details?id=$packageName")

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/html"
        intent.putExtra(Intent.EXTRA_TEXT,result.toString())
        startActivity(Intent.createChooser(intent,"Share as..."))
    }

    private fun shareImage(){
        val header = getBitmapFromView(appBar)
        val body = getBitmapFromScrollView(scrollView,scrollView.getChildAt(0).height,scrollView.width)
        val result = mergeBitmaps(header,body)
        val bitmapPath = MediaStore.Images.Media.insertImage(contentResolver,result,"title",null)
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_STREAM,Uri.parse(bitmapPath))
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.putExtra(Intent.EXTRA_TEXT,"تخمین هزینه و زمان توسط اپلیکیشن ecoa")
        startActivity(Intent.createChooser(intent,"Share as..."))
    }

    private fun getBitmapFromView(view: View): Bitmap {
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache(true)
        val bitmap = Bitmap.createBitmap(view.drawingCache)
        view.isDrawingCacheEnabled = false
       return bitmap
    }

    private fun getBitmapFromScrollView(view: View,height: Int,width: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val bgDrawable = view.background
        if (bgDrawable != null)
            bgDrawable.draw(canvas)
        else
            canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return bitmap
    }

    private fun mergeBitmaps(bitmap1: Bitmap, bitmap2: Bitmap): Bitmap{
        val width = bitmap1.width
        val height = bitmap1.height + bitmap2.height
        val result = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888)
        val canvas = Canvas(result)
        canvas.drawBitmap(bitmap1,0f,0f,null)
        canvas.drawBitmap(bitmap2,0f,bitmap1.height.toFloat(),null)
        return result
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_info -> {
                val intent = Intent(this@MainActivity,AboutActivity::class.java)
                startActivity(intent)
            }
            R.id.action_share_text -> shareText()
            R.id.action_share_image -> {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1 &&
                        checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                    checkPermission()
                else
                    shareImage()
            }
        }
        return true
    }

    private fun checkPermission(){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1)
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),1)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1 && grantResults.isNotEmpty())
            shareImage()
    }
}
