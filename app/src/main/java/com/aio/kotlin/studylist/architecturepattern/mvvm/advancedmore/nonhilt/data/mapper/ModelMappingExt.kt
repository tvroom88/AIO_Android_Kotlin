package com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.mapper

import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.entity.remote.RemoteGithubModel
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.ui.model.GithubModel

/**
 * Data model mapping extension functions. There are three model types:
 *
 * - GithubModel: External model exposed to other layers in the architecture.
 * Obtained using `toExternal`.
 *
 * - RemoteGithubModel: Internal model used to represent a task from the network. Obtained using
 * `toNetwork`.
 *
 * - LocalTask: Internal model used to represent a task stored locally in a database. Obtained
 * using `toLocal`.
 *
 */

// Network to External
fun RemoteGithubModel.toExternal() = GithubModel(
    avatarUrl = avatarUrl
)