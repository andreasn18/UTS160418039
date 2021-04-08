package com.example.uts160418039.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.uts160418039.R
import com.example.uts160418039.model.Friend
import com.example.uts160418039.util.loadImage
import de.hdodenhof.circleimageview.CircleImageView

class ExpandAdapter(
    var context: Context,
    var header: MutableList<String>,
    var body: MutableList<List<Friend>>
) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return header.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return body[groupPosition].size
    }

    override fun getGroup(groupPosition: Int): String {
        return header[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Friend {
        return body[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        var convertView = convertView
        if (convertView == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.layout_group, null)
        }
        val title = convertView?.findViewById<TextView>(R.id.txtTitle)
        title?.text = getGroup(groupPosition) + " " + getChildrenCount(groupPosition)
        return convertView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        var convertView = convertView
        if (convertView == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.layout_child, null)
        }
        convertView?.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeToOption(getChild(groupPosition, childPosition).name.toString(), getChild(groupPosition, childPosition).image_url.toString())
            Navigation.findNavController(it).navigate(action)
        }
        val title = convertView?.findViewById<TextView>(R.id.txtTitle)
        val img = convertView?.findViewById<CircleImageView>(R.id.profile_image)
        val progressBar = convertView?.findViewById<ProgressBar>(R.id.progressBar2)
        title?.text = getChild(groupPosition, childPosition).name
        img?.loadImage(getChild(groupPosition, childPosition).image_url, progressBar!!)
        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}