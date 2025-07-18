package service

import cats.effect.IO
import domain.{LeagueTable, Match}

class MatchDaysCalcService {

  private lazy val homeGamesWon: Match => Boolean = `match` => `match`.homeGoals > `match`.awayGoals
  private lazy val awayGamesWon: Match => Boolean = `match` => `match`.homeGoals > `match`.awayGoals

  lazy val homeGamesLost: Match => Boolean = `match` => `match`.homeGoals < `match`.awayGoals
  lazy val awayGamesLost: Match => Boolean = `match` => `match`.awayGoals < `match`.homeGoals

  lazy val gamesDrawn: Match => Boolean = `match` => `match`.homeGoals == `match`.awayGoals

  def calculateTable(matches: Vector[Match]): IO[LeagueTable] = {
    val distinctHomeTeams = matches.groupBy(_.homeTeam)
    val distinctAwayTeams = matches.groupBy(_.awayTeam)

    val allTeamsMatchesWon = distinctHomeTeams.map { (teamName, matches) =>
      calculateGame(teamName, matches)(homeGamesWon)
    } ++ distinctAwayTeams.map { (teamName, matches) =>
      calculateGame(teamName, matches)(awayGamesWon)
    }

    val allTeamsMatchesLost = distinctHomeTeams.map { (teamName, matches) =>
      calculateGame(teamName, matches)(homeGamesLost)
    } ++ distinctAwayTeams.map(calculateAwayGamesLost)

    val allTeamsMatchesDrawn = (distinctHomeTeams ++ distinctAwayTeams).map { (teamName, matches) =>
      calculateGame(teamName, matches)(gamesDrawn)
    }

    ???
  }

  private def calculateGame(teamName: String, matches: Vector[Match])(f: Match => Boolean ): (String, Int) = {
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