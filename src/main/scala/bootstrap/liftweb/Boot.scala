package bootstrap.liftweb

import net.liftweb._
import util._
import Helpers._

import common._
import http._
import sitemap._
import Loc._
import net.liftmodules.JQueryModule
import net.liftweb.http.js.jquery._

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot {
    // where to search snippet
    LiftRules.addToPackages("code")

    import code.snippet._

    // Our "database". It contains only an entry for product id of "1":
    val lookup: String => Box[ProductInfo] =
      _ match {
        case "1" => Full(ProductInfo(1L))
        case _   => Empty
      }

    // If the value has to be defined; otherwise, show the home page:
    val FallbackToSearchPage =
      IfValue[ProductInfo](_.isDefined, () => RedirectResponse("/"))

    // /product/1 produces product info
    // /product/2 should take us to the home page
    lazy val product = Menu.param[ProductInfo]("product", "Product Detail Page",
      id   => lookup(id),
      info => info.id.toString
      ) / "product" >> MatchWithoutCurrentValue >> FallbackToSearchPage


    // Build SiteMap
    lazy val home = Menu.i("Home") / "index"
    LiftRules.setSiteMap(SiteMap(home, product))

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))
  }
}