# ecoa (estimate cost of android app)

این برنامه برحسب چندین پارامتر مختلف مبلغ و زمان مورد نیاز برای انجام یک پروژه اندرویدی را به صورت تقریبی محاسبه میکند

[screen shot](http://url/to/img.png)

## نحوه محاسبه هزینه

هزینه طبق فرمول زیر محاسبه میشود
```
هزینه کل = هزینه پایه + هزینه اکتیویتی و فرگمنت ها + هزینه سرویس ها + هزینه تحویل سورس + هزینه پشتیبانی یکساله
```

### هزینه پایه

هزینه پایه بر اساس سطح مهارت برنامه نویس به شرح زیر در نظر گرفته شده است
* مبتدی 100 هزار تومان
* نیمه حرفه ای 300 هزار تومان
* حرفه ای 500 هزار تومان

### هزینه اکتیویتی فرگمنت ها

برای محاسبه هزینه کل اکتیویتی و فرگمنت ها از فرمول زیر استفاده شده است
```
هزینه اکتیویتی فرگمنت = تعداد * هزینه پایه هر اکتیویتی * نرخ سختی برنامه * نرخ کیفیت گرافیک برنامه
```
> هزینه پایه برای هر اکتیویتی 50 هزار تومان در نظر گرفته شده است

### هزینه سرویس ها

برای محاسبه هزینه کل سرویس ها از فرمول زیر استفاده شده است
```
هزینه سرویس = تعداد * هزینه پایه سرویس * نرخ سختی برنامه
```
> هزینه پایه برای هر سرویس 30 هزار تومان در نظر گرفته شده است

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
مدت زمان = (تعداد اکتیویتی ها + تعداد سرویس ها) * نرخ زمان
```

#### نرخ سختی برنامه
* خیلی ساده = 1
* ساده = 1.2
* متوسط = 1.5
* نیمه سخت = 2
* سخت = 2.5
* خیلی سخت = 3

#### نرخ نحویل سورس
* خیلی ساده = 10
* ساده = 15
* متوسط = 25
* نیمه سخت = 30
* سخت = 40
* خیلی سخت = 50

#### نرخ زمان
* خیلی ساده = 5
* ساده = 10
* متوسط = 15
* نیمه سخت = 20
* سخت = 35
* خیلی سخت = 40
> منظور از هر عدد ساعت است.

#### نرخ کیفیت گرافیک
* ساده = 1
* معمولی = 1.2
* خوب = 1.5
* عالی = 2
