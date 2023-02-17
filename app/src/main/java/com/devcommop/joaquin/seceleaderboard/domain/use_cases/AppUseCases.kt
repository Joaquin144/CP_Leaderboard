package com.devcommop.joaquin.seceleaderboard.domain.use_cases

/**
 * As our app grows so does our total number of UseCases.
 *
 * We don't want all our ViwModels to have dozens of UseCases. So we wrap all the use cases into one single class and inject only that class into our ViewModels. Hence boilerplate code is reduced.
 */
data class AppUseCases(
    val getCfHanlesUseCase: GetCfHandlesUseCase,
    val getPartiesScoreUseCase: GetPartiesScoreUseCase,
    val getScoreboardUseCase: GetScoreboardUseCase,
    val getContestsListUseCase: GetContestsListUseCase,
    val getUpcomingContestsListUseCase: GetUpcomingContestsUseCase
)
