package com.expert.operrwork.view.oldDashboard.requestOffTime

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.expert.operrwork.R
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Akshay.
 */
@Suppress("NAME_SHADOWING")
class RequesHistoryOffAdapter(private val _context: Context, private val _listDataHeader: ArrayList<String>, private val _listDataChild: HashMap<String, List<String>>, private val mItemClick: ItemClick) : BaseExpandableListAdapter() {

    override fun getChild(groupPosition: Int, childPosititon: Int): Any {
        return this._listDataChild[this._listDataHeader[groupPosition]]!![childPosititon]
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val childText = getChild(groupPosition, childPosition) as String
        if (convertView == null) {
            val infalInflater = this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = infalInflater.inflate(R.layout.item_request_off_history_off_first, null)
        }
        val txtListChild = convertView!!.findViewById<View>(R.id.txt_history_dates) as AppCompatTextView
        txtListChild.text = childText
        txtListChild.setOnClickListener {
            mItemClick.buttonClick(groupPosition, childPosition)
        }
        return convertView
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return this._listDataChild[this._listDataHeader[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return this._listDataHeader[groupPosition]
    }

    override fun getGroupCount(): Int {
        return this._listDataHeader.size
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val headerTitle = getGroup(groupPosition) as String
        if (convertView == null) {
            val infalInflater = this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = infalInflater.inflate(R.layout.item_time_sheet_header, null)
        }
        val lblListHeader = convertView!!.findViewById<View>(R.id.txt_header) as TextView
        lblListHeader.setTypeface(null, Typeface.BOLD)
        lblListHeader.text = headerTitle

        return convertView
    }



    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    public interface ItemClick {
        fun buttonClick(groupPosition: Int, childPosition: Int)
    }
}
