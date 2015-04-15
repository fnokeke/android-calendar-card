#android-calendar-card

This Android Calendar View is forked from [kenumir/android-calendar-card](https://github.com/kenumir/android-calendar-card).

With the lolipop theme published, a newly android-calendar-card shows here.

It's easy for you to make any custom style or events. Here I applied it to view like a calendar. Provided with json data,
the events in everyday can be marked out in that days. More details you can check the attached screenshot.

May this can help you!

##CalendarCard
The CalendarCard is the simplest prototype view for the calendar. It will show the Calendar of this month. Hightlight today's date
and set listener for what you chose from Calendar. Here is how to use it.

Show the calendar view in the layout.xml:

```
<com.wt.calendarcard.CalendarCard
    android:id="@+id/calendarCard1"
    android:layout_height="wrap_content"
    android:layout_width="match_parent"/>
```

SetOnDateSelectedListener in java code:

```
mCalendarCard = (CalendarCard) findViewById(R.id.calendarCard1);
mCalendarCard.setOnDateSelectedListener(new CalendarCard.OnDateSelectedListener() {
    @Override
    public void onDateSelected(View view, CardGridItem item) {
        mTextView.setText(getResources().getString(R.string.sel_date, new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(item.getDate().getTime())));
    }
});
```

##CalendarCardPager

The CalendarCardPager is actually a ViewPager, which contains many CalendarCards that can be swiped. Swiped next will be the 
next month, and before which is the last month. 

Show the CalendaCardPager in layout.xml

```
<com.wt.calendarcard.CalendarCardPager
    android:id="@+id/calendarCard1"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

You want to set listener for each element click. Tries the following method:

```
CalendarCardPager pager = (CalendarCardPager) findViewById(R.id.calendarCard2);
pager.setOnCellItemClick(new CalendarCard.OnDateSelectedListener() {
    @Override
    public void onDateSelected(View view, CardGridItem target) {
        // Do something to get the clicked cell with date
    }
});
```

## CalenderCardList

CalendarCardList is the View contains CalendarCardPager and ListView. So ListView will show the events in specific date.
We try to show the Calendar with other data, and it's a little same with Google Calendar.


## CalendarListAdapter

In some business requirement you may need a vertical scrollable Calendar, the ViewPager is not easy to do so. And you can try
to load the CalendarCard in a ListView. So that every list item will be one month Calendar item. The effect is also nice.
More details you can see CalendarListAdapter in the calendar-card-sample project.

## Design

Redesigned the Calendar UI based on the previous CalendarCard. The new UI is aimed for the Android New material design. You
may found some design is similar to Google Calendar, but that's what I want to build. Below is a part of CalendarCardPager.

[![CalendarCard Sample3 - Show Calendar][3]][3]

[3]: https://github.com/gongmingqm10/android-calendar-card/raw/master/calendar-card-sample/images/sample-3.png


## License 

```
Copyright 2013-2015 Michał Szwarc & gongmingqm10

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```