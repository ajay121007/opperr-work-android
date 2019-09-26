package com.expert.operrwork.view.dashboard.home

import android.R
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.DefaultValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.expert.operrwork.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_admin_browse.tvPath
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList




/**
 * Created by Akshay.
 */
class HomeFragment : BaseFragment() {

    private lateinit var xAxis: XAxis

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.expert.operrwork.R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        tvPath.text = HtmlCompat.fromHtml(
            getString(com.expert.operrwork.R.string.str_login) + " > " +
                    "<font color=black>" + getString(com.expert.operrwork.R.string.str_dashboard) + getString(com.expert.operrwork.R.string.tb_home)
                    + "</font>",HtmlCompat.FROM_HTML_MODE_LEGACY)

        tvDate.setText(getCurrentDay())


        setGraphView()
        setPieChartView()
        setPieChartData()
        setLineChartData()
    }

    fun setGraphView() {

        // no description text
        lineChart.getDescription().setEnabled(false)

        // enable touch gestures
        lineChart.setTouchEnabled(true)

        lineChart.setDragDecelerationFrictionCoef(0.9f)
     //   val MyMarkerView = MyMarkerView(activity, com.justcodenow.operrwork.R.layout.custom_marker_view)
        // enable scaling and dragging
        lineChart.setDragEnabled(true)
        lineChart.setScaleEnabled(true)
        lineChart.setDrawGridBackground(false)
        lineChart.setHighlightPerDragEnabled(true)
        //lineChart.setExtraBottomOffset(-50f)
        lineChart.setVisibleXRangeMaximum(4f)
        //lineChart.setMarker(MyMarkerView)

        // set an alternative background color
       // lineChart.setBackgroundColor(resources.getColor(R.color.white))
       // lineChart.setViewPortOffsets(0f, 8f, 0f, 0f)


        // get the legend (only possible after setting data)
        val l = lineChart.getLegend()
        l.setEnabled(false)
        xAxis = lineChart.getXAxis()
        xAxis.setLabelCount(4)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)
        xAxis.setTextSize(10f)
        xAxis.setTextColor(Color.GRAY)
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(true)
        //xAxis.setTextColor(Color.rgb(255, 255, 255))
        xAxis.setCenterAxisLabels(true)
        xAxis.setGranularity(3f) // 2 hour

        val leftAxis = lineChart.getAxisLeft()
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        leftAxis.setTextColor(Color.GRAY)
        leftAxis.setDrawGridLines(true)
        leftAxis.setGranularityEnabled(true)
        leftAxis.setAxisMinimum(0f)
        leftAxis.setAxisMaximum(300f)
        leftAxis.setYOffset(-9f)
        //leftAxis.setTextColor(Color.rgb(255, 255, 255))

        val rightAxis = lineChart.getAxisRight()
     //   rightAxis.setAxisMinimum(0f)
       // rightAxis.setAxisMaximum(200f)
        rightAxis.setEnabled(false)
    }

    private fun setPieChartView() {
        pieChart.setUsePercentValues(true)

        pieChart.getDescription().setEnabled(false)
        pieChart.setExtraOffsets(5F, 10F, 5F, 5F)
        pieChart.setDragDecelerationFrictionCoef(0.95f)
        pieChart.setRotationEnabled(true)
        pieChart.setHighlightPerTapEnabled(true)


        val l = pieChart.getLegend()
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER)
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT)
        l.setOrientation(Legend.LegendOrientation.VERTICAL)
        l.setDrawInside(false)
        l.setXEntrySpace(0f)
        l.setYEntrySpace(0f)
        l.setYOffset(0f)
        l.setTextColor(resources.getColor(com.expert.operrwork.R.color.white))
    }


    private fun setPieChartData() {
        var yvalues: ArrayList<PieEntry> = ArrayList()

        for (i in 0 until 10) {
            yvalues.add(
                PieEntry(
                    (Math.random() * 10 + 10 / 50.0).toFloat(),10)
                )

        }
        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.

        val dataSet = PieDataSet(yvalues, "")
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 0f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        val data = PieData(dataSet)

        // In percentage Term
        data.setValueFormatter(DefaultValueFormatter(2))

        pieChart.data = data
        pieChart.setEntryLabelTextSize(7f)
        pieChart.setEntryLabelColor(resources.getColor(R.color.transparent))
        //Set color
        dataSet.setColors(*ColorTemplate.MATERIAL_COLORS)
        dataSet.valueTextColor = resources.getColor(R.color.black)
        dataSet.valueTextSize = 14f


        //Disable Hole in the Pie Chart
        pieChart.isDrawHoleEnabled = false
        pieChart.invalidate()
        pieChart.notifyDataSetChanged()
    }


    private fun setLineChartData() {


        xAxis.valueFormatter = object : ValueFormatter() {
            private val mFormat = SimpleDateFormat("dd MMM HH:mm", Locale.ENGLISH)

            override fun getFormattedValue(value: Float): String {
                Log.d("valusses", "$value ")
                val date = Date(value.toLong())
                //     date.getTime();
                return mFormat.format(date.time)
            }
        }

        val values:ArrayList<Entry> = ArrayList()

        for (i in 0..10) {
            val floatValue:Float = (Math.random() * (10 / 2f)).toFloat() + 50

            values.add(Entry(i.toFloat(), floatValue)) // add one entry per hour
            values.add(Entry(i.toFloat(), floatValue)) // add one entry per hour
            values.add(Entry(i.toFloat(), floatValue)) // add one entry per hour

        }

        // create a dataset and give it a type
        val set1 = LineDataSet(values, "DataSet 1")
        set1.axisDependency = YAxis.AxisDependency.LEFT
        set1.color = ColorTemplate.getHoloBlue()
        set1.valueTextColor = ColorTemplate.getHoloBlue()
        set1.lineWidth = 3f
        set1.setDrawCircles(true)
        set1.setDrawValues(false)
        set1.fillColor = ColorTemplate.getHoloBlue()
        set1.highLightColor = resources.getColor(R.color.holo_blue_light)
        set1.isHighlightEnabled = false
        set1.highlightLineWidth = 2.0f
        set1.setDrawHighlightIndicators(true)
        set1.setDrawHorizontalHighlightIndicator(true)

        set1.setDrawCircleHole(true)
        set1.circleHoleColor=resources.getColor(R.color.holo_blue_light)
        set1.circleHoleRadius = 0f
        set1.circleRadius = 4f
        set1.mode = LineDataSet.Mode.CUBIC_BEZIER
        //to enable the cubic density : if 1 then it will be sharp curve
        set1.cubicIntensity = 0.2f

        //to fill the below of smooth line in graph
       // set1.setDrawFilled(true)
       // set1.fillColor = Color.BLACK
        //set the transparency
       // set1.fillAlpha = 80


        // create a data object with the data sets
        val data = LineData(set1)
        data.setValueTextColor(Color.GRAY)
        data.setValueTextSize(9f)

        // set data
        lineChart.data = data
        lineChart.invalidate()
        lineChart.notifyDataSetChanged()
    }

    protected fun getPercentageValue(value: Int, percentage: Int): Int {
        return (value * (percentage / 100.0f)).toInt()
    }


    @Throws(ParseException::class)
    protected fun getMillis(myDate: String): Long {
        val sdf = SimpleDateFormat("dd MM yyyy")
        val date = sdf.parse(myDate)
        return date.time
    }


    @SuppressLint("SimpleDateFormat")
    private fun getCurrentDay(): String {
        val currentDate = SimpleDateFormat("MMMM dd, yyyy").format(Calendar.getInstance().getTime())
        //Day of Name in full form like,"Saturday"
        val dayName = SimpleDateFormat("EEEE").format(Date()) + ", "
        return "$dayName $currentDate"
    }



}