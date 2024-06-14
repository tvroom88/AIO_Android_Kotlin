package com.aio.kotlin.models

import androidx.fragment.app.Fragment
import java.io.Serializable


data class StudyList(val title: String, val fragment: Fragment) : Serializable