/*
 * Copyright (c) 2023, Glassdoor Inc.
 *
 * Licensed under the Glassdoor Inc Hiring Assessment License.
 * You may not use this file except in compliance with the License.
 * You must obtain explicit permission from Glassdoor Inc before sharing or distributing this file.
 * Mention Glassdoor Inc as the source if you use this code in any way.
 */

package com.glassdoor.intern.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.glassdoor.intern.presentation.IMainViewModel
import com.glassdoor.intern.presentation.MainIntent.HideErrorMessage
import com.glassdoor.intern.presentation.MainIntent.RefreshScreen
import com.glassdoor.intern.presentation.MainUiState
import com.glassdoor.intern.presentation.model.ItemUiModel
import com.glassdoor.intern.presentation.model.HeaderUiModel
import com.glassdoor.intern.presentation.theme.InternTheme
import com.glassdoor.intern.presentation.ui.component.ContentComponent
import com.glassdoor.intern.presentation.ui.component.ErrorMessageComponent
import com.glassdoor.intern.presentation.ui.component.TopBarComponent
import java.time.Instant

@Composable
internal fun MainScreen(
    viewModel: IMainViewModel,
    modifier: Modifier = Modifier,
) {
    /**
     * DONE: [Consume UI state safely from the ViewModel](https://developer.android.com/codelabs/jetpack-compose-advanced-state-side-effects#3)
     */
    val uiState: MainUiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopBarComponent(
                modifier = Modifier.fillMaxWidth(),
                isLoading = uiState.isLoading,
                progressClickAction = { viewModel.acceptIntent(RefreshScreen) },
            )
        },
        snackbarHost = {
            ErrorMessageComponent(
                modifier = Modifier.fillMaxWidth(),
                errorMessage = uiState.errorMessage,
                hideErrorMessageAction = { viewModel.acceptIntent(HideErrorMessage) },
            )
        },
        content = { paddingValues ->
            ContentComponent(
                modifier = Modifier.padding(paddingValues),
                header = uiState.header,
                items = uiState.items,
            )
        },
    )
}

@Preview
@Composable
private fun MainScreenPreview() = InternTheme {
//    DONE("Define UI state for preview purposes")
    val uiState = MainUiState(
        errorMessage = "An error occurred",
        header = HeaderUiModel(
            id = 1,
            title = "Sample Title",
            description = "Sample Description",
            timestamp = "2023-06-23T01:23:40.887Z"
        ),
        isLoading = false,
        items = listOf(
            ItemUiModel(
                title = "Libra",
                description = "Description 1",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/76/Libra_IAU.svg/2560px-Libra_IAU.svg.png",
                timestamp = "2023-06-23T01:23:40.887Z",
            ),
            ItemUiModel(
                title = "Sagittarius",
                description = "Description 2",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/Terebellum_asterism.png/1920px-Terebellum_asterism.png",
                timestamp = "2023-06-23T01:23:40.887Z",
            )
        )
    )

    MainScreen(viewModel = uiState.asDummyViewModel)
}
