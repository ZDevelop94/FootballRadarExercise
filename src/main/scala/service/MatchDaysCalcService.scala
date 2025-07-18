package service

import cats.effect.IO
import domain.{LeagueTable, Match}

class MatchDaysCalcService {

  def calculateTable(matches: Vector[Match]): IO[LeagueTable] = {
    val distinctHomeTeams = matches.groupBy(_.homeTeam)
    val distinctAwayTeams = matches.groupBy(_.awayTeam)

    val allTeamsMatchesWon = distinctHomeTeams.map(calculateHomeGamesWon) ++ distinctAwayTeams.map(calculateAwayGamesWon)
    val allTeamsMatchesLost = distinctHomeTeams.map(calculateHomeGamesLost) ++ distinctAwayTeams.map(calculateAwayGamesLost)
    val allTeamsMatchesDrawn = distinctHomeTeams.map(calculateGamesDrawn) ++ distinctAwayTeams.map(calculateGamesDrawn)

    ???
  }

  private def calculateHomeGamesWon(teamName: String, matches: Vector[Match]): (String, Int) = {
    (teamName,
      matches.count { `match` =>
        `match`.homeGoals > `match`.awayGoals
      }
    )
  }

  private def calculateAwayGamesWon(teamName: String, matches: Vector[Match]): (String, Int) = {
    (teamName,
      matches.count { `match` =>
        `match`.awayGoals > `match`.homeGoals
      }
    )
  }

  private def calculateHomeGamesLost(teamName: String, matches: Vector[Match]): (String, Int) = {
    (teamName,
      matches.count { `match` =>
        `match`.homeGoals < `match`.awayGoals
      }
    )
  }

  private def calculateAwayGamesLost(teamName: String, matches: Vector[Match]): (String, Int) = {
    (teamName,
      matches.count { `match` =>
        `match`.awayGoals < `match`.homeGoals
      }
    )
  }

  private def calculateGamesDrawn(teamName: String, matches: Vector[Match]): (String, Int) = {
    (teamName,
      matches.count { `match` =>
        `match`.homeGoals == `match`.awayGoals
      }
    )
  }
}