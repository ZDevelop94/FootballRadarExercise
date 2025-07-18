import cats.effect.IO
import domain.LeagueTable
import munit.CatsEffectSuite
import service.MatchDaysCalcService

class MatchDaysCalcServiceTest extends CatsEffectSuite {

  val service = new MatchDaysCalcService
  
  test("") {
    val teams = Vector(???)
    
    val result: IO[LeagueTable] = service.calculateMatches(Vector.empty)
    val expections = Vector(
      
    )
    result.assertEquals(expections)
  }
}
