package code.snippet

import net.liftweb.util.Helpers._

case class ProductInfo(id: Long)

class ProductRender(info: ProductInfo) {
   def render = ".id *" #> info.id
}