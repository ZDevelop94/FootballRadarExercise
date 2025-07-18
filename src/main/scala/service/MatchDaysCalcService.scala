package service

import domain.Match

class MatchDaysCalcService {

  def calculateMatches(matches: Vector[Int]) = {


  }

  private def calculateHomeGamesWon(teamName: String, matches: Vector[Match]): Int = {

    val gamesWon: Map[String, Vector[Match]] = matches.groupBy(_.homeTeam)

    val fulhamGamesWon = gamesWon.filter(_._1 == "Fulham")

    val matchesCollected = fulhamGamesWon.flatMap(_._2).toVector

    matchesCollected.map {
      _.homeGoals
    }.sum
  }
}