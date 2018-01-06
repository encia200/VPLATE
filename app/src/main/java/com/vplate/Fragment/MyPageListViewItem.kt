package com.vplate.Fragment

/**
 * Created by chosoomin on 2018. 1. 6..
 */
class MyPageListViewItem {

    private var type: Int = 0

    private var title: String? = null

    private var section: String? = null

    private var title_toggle: String? = null

    private var item: String? = null

    fun setItem(m_item: String) {
        this.item = m_item
    }

    fun setType(m_type: Int) {
        this.type = m_type
    }

    fun setTitle(m_title: String) {
        this.title = m_title
    }

    fun setSection(m_section: String) {
        this.section = m_section
    }

    fun setTitle_toggle(m_titlet: String) {
        this.title_toggle = m_titlet
    }

    fun getType(): Int {
        return this.type
    }

    fun getTitle(): String? {
        return this.title
    }


    fun getSection(): String? {
        return this.section
    }

    fun getTitle_t(): String? {
        return this.title_toggle
    }

    fun getItem(): String? {
        return this.item
    }
}