package domain


case class TableOfMatches(matches: List[Match])

case class Match(gameId: GameID,
                 homeTeam: String,
                 awayTeam: String,
                 kickoff: String,
                 seasonId: Int,
                 homeGoals: Int,
                 awayGoals: Int)

case class GameID(value: Int)

case class LeagueTable()

case class Team(gamesPlayed: Int, name: String, gamesWon: Int, 
                gamesDrawn: Int, gamesLost: Int, totalScored: Int, totalAgainst: Int, totalPoint: Int)
