# Converter

This tries to replicate one of the screens from the Revolut app, namely the converter screen.

The screen shows a list of currencies. The currency at the top is considered the base, i.e. it's amount is converted to all other currencies on the screen.
The conversion rates (with that base) are updated once every 1 seconds.

The user can change the amount for the top currency (base) and she will see it converted to all other currencies.

The user can change the base currency by clicking in the amount field of a non-base currency.

If the back-end on [master] does not work, there is a [mock] branch that mocks the back-end response for 4 currencies.