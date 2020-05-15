package com.aoc.intcode.tractorbeam

import java.lang.IllegalArgumentException

class ShipNotFound(areaSize: Int) : IllegalArgumentException("Could not find beam square of size $areaSize")