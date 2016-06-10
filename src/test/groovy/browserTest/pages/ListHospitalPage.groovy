package browserTest.pages
import geb.Page

/**
 * Created by flocktonj on 27/04/16.
 */
class ListHospitalPage extends Page{
    static url = "/api/all_games/"

    static at = { title == "All Games" }

    static content = {
        gamesList { $("div.games-list") }
    }
}
