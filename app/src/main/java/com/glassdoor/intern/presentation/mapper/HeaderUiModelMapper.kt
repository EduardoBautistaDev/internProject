/*
 * Copyright (c) 2023, Glassdoor Inc.
 *
 * Licensed under the Glassdoor Inc Hiring Assessment License.
 * You may not use this file except in compliance with the License.
 * You must obtain explicit permission from Glassdoor Inc before sharing or distributing this file.
 * Mention Glassdoor Inc as the source if you use this code in any way.
 */

package com.glassdoor.intern.presentation.mapper

import com.glassdoor.intern.domain.model.HeaderInfo
import com.glassdoor.intern.presentation.model.HeaderUiModel
import java.time.Instant
import java.time.format.DateTimeFormatter
import javax.inject.Inject

internal class HeaderUiModelMapper @Inject constructor() {

    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy")
//        DONE("Define date formatting pattern")

    fun toUiModel(headerInfo: HeaderInfo): HeaderUiModel = with(headerInfo) {
//        DONE("Convert domain model to UI model")
        HeaderUiModel(
            id = id,
            title = title,
            description = description,
            timestamp = timestamp,
        )
    }
}
