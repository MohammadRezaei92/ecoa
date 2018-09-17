# ecoa (estimate cost of an android app)
<img src="https://github.com/MohammadRezaei92/ecoa/blob/master/app/src/main/ic_launcher-web.png" width="100" height="100" />
این برنامه برحسب چندین پارامتر مختلف مبلغ و زمان مورد نیاز برای انجام یک پروژه اندرویدی را به صورت تقریبی محاسبه میکند

![screenshot](https://github.com/MohammadRezaei92/ecoa/blob/master/photo5834570212870041242.jpg)

## تغییرات
### نسخه 2.0.0
* طراحی مجدد ظاهر برنامه از صفر
* محاسبه به صورت آنی و به محض تغییر پارامترها
* تغییرات قیمت ها و فرمول ها
* اصلاح فرمول های محاسبه و رفع مشکلات نسخه قبل
* افزوده شدن صفحه درباره


## نحوه محاسبه هزینه

هزینه طبق فرمول زیر محاسبه میشود
```
هزینه کل = هزینه پایه + هزینه اکتیویتی و فرگمنت ها + هزینه سرویس ها + هزینه تحویل سورس + هزینه پشتیبانی یکساله
```

### هزینه پایه

هزینه پایه بر اساس سطح مهارت برنامه نویس به شرح زیر در نظر گرفته شده است
* کارآموز 100 هزار تومان
* مبتدی 300 هزار تومان
* نیمه حرفه ای 500 هزار تومان
* حرفه ای 800 هزار تومان
* فول استک 1 میلیون تومان


### هزینه اکتیویتی فرگمنت ها

برای محاسبه هزینه کل اکتیویتی و فرگمنت ها از فرمول زیر استفاده شده است
```
هزینه اکتیویتی فرگمنت = تعداد * هزینه پایه هر اکتیویتی * نرخ سختی برنامه * نرخ کیفیت گرافیک برنامه
```
> هزینه پایه برای هر اکتیویتی 70 هزار تومان در نظر گرفته شده است

### هزینه سرویس ها

برای محاسبه هزینه کل سرویس ها از فرمول زیر استفاده شده است
```
هزینه سرویس = تعداد * هزینه پایه سرویس * نرخ سختی برنامه
```
> هزینه پایه برای هر سرویس 50 هزار تومان در نظر گرفته شده است

### هزینه تحویل سورس

برای محاسبه هزینه تحویل سورس از فرمول زیر استفاده شده است
```
هزینه تحویل سورس = (قیمت کل برنامه * نرخ تحویل سورس) / 100
```

### هزینه پشتیبانی یکساله

برای محاسبه هزینه پشتیبانی یکساله از فرمول زیر استفاده شده است
```
هزینه پشتیبانی یکساله = (قیمت کل برنامه * نرخ پشتیبانی) / 100
```
> نرخ پشتیبانی برای یکسال 25 در نظر گرفته شده است

## نحوه محاسبه زمان

برای محاسبه مدت زمان از فرمول زیر استفاده شده است
```
مدت زمان =  ((تعداد اکتیویتی * نرخ زمان گرافیک)+ (تعداد اکتیویتی ها + تعداد سرویس ها) * نرخ زمان سختی برنامه) * نرخ زمان برنامه نویس
```

#### نرخ سختی برنامه
* خیلی ساده = 1
* ساده = 1.5
* متوسط = 2.5
* نیمه سخت = 3.5
* سخت = 4

#### نرخ نحویل سورس
* خیلی ساده = 10
* ساده = 15
* متوسط = 25
* نیمه سخت = 30
* سخت = 40

#### نرخ زمان
* خیلی ساده = 5
* ساده = 10
* متوسط = 15
* نیمه سخت = 20
* سخت = 35
> منظور از هر عدد ساعت است.

#### نرخ کیفیت گرافیک
* بدون گرافیک = 1
* مبتدی = 1.5
* قابل قبول = 2.5
* خوب = 3.5
* عالی = 4

## نیازمند همکاری
برای کامل شدن هرچه بیشتر برنامه نیازمند نظرات و همکاری های شما هستیم
لطفا تمام نظرات و پیشنهادات خود و مشکلات برنامه را از طریق لینک زیر برای ما بنویسید
[ارسال نظرات و مشکلات برنامه](https://github.com/MohammadRezaei92/ecoa/issues)

## License

    The MIT License (MIT)

    Copyright (c) 2014 Pedant(http://pedant.cn)

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.


