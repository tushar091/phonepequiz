package com.example.phonepequiz.repository

import com.example.phonepequiz.Constants.LOGOS
import com.example.phonepequiz.model.Logos
import com.example.phonepequiz.utils.parseListResponse

class LogoRepository {

    fun fetchLogoList(): Array<Logos>? {
        return parseListResponse(LOGOS, Logos::class.java)
    }
}