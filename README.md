# Match Without Current Value Example

A `MatchWithoutCurrentValue` example for Lift 2.6.

## Scenario

We have a `Menu.param` matching _/product/{id}_.

There is one known product ID: `1`.

## Expected Behaviour

- A request of _/product/1_ should produce a "product information" page which simply echos the product ID.

- A request of _/product/2_ should redirect the user to the home page.

## Try it...

    $ sbt
    > container:start

Try the above URLs:

- http://127.0.0.1:8080/ (home page).
- http://127.0.0.1:8080/product/1 (product detail page).
- http://127.0.0.1:8080/product/2 (should redirect to home page).

## Code

The `Menu.param` is created and added to site map in _Boot.scala_.  This is also where our "database" lives.

The `ProductInfo` and rendering class is in _code.snippet/products.scala_.

The HTML page is _src/main/webapp/product/star.html_
