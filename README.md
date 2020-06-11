# :christmas_tree: Advent of Code (2019)

[![GitHub Issues](https://img.shields.io/github/issues/TomPlum/advent-of-code-2019.svg)](https://github.com/TomPlum/advent-of-code-2019/issues)
![GitHub closed issues](https://img.shields.io/github/issues-closed/TomPlum/advent-of-code-2019?color=brightgreen)
![GitHub](https://img.shields.io/github/license/TomPlum/advent-of-code-2019?color=informational)

## About
Working through it while learning [Kotlin](https://kotlinlang.org/). Acts as a nice format for personal development. 
I'm treating the code-base a little more _enterprise-esque_, for lack of a better term.
Meaning I'm focusing more on design, readability, and ensuring code is test-driven with full coverage etc. 
This doesn't mean, however, that I'm not considering performance. Any solutions that don't meet a respectable order of
runtime complexity will be refactored and improved upon in a later pass of the days.

## Contents
* [The Days](#the-days)
  * [Favourite Days](#favourite-days)
    * [Most Challenging](#most-challenging-day-18)
    * [Most Fun](#most-fun)
* [What I Learned](#what-i-learned)
  * [Kotlin](#kotlin--kotlin-gradle-dsl)
  * [Data Structures & Algorithms](#data-structures--algorithms)
  * [Performance & Runtime Complexity](#performance)
* [Answer Table](#answer-table)

## The Days

### Most Challenging (Day 18)
This day was add one. Usually, the implementation is the 'easy' bit, and figuring out the theory behind the
solution is the harder bit that can take a while. However, Day 18s theory was simple. It was a maze
filled with doors with corresponding keys, and you had the find the shortest path to collect all keys. So it was just
a case of using an exhaustive [DFS (Depth First Search)](https://en.wikipedia.org/wiki/Depth-first_search) Algorithm
that would graph all the keys and their respective weights to any other accessible ones. Then running
[Dijkstra Algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm) on the weighted graph to find the shortest path. 
I just couldn't figure out how to implement it. I spent so many hours trying different things until I eventually managed 
to get 4 of the 5 examples working. Example 5, however, just ran indefinitely and so did my puzzle input. I needed to 
improve the performance of my graphing algorithm, so my solution input didn't take a literal eternity to run.

### Favourite Days

#### Technically

#### Most Fun (Day 8)
This day was most fun because I didn't understand what was actually going on until I'd successfully implemented a
solution. It was only shortly after re-reading the documentation that I understand what was happening. The output
below was the result flattening a three-dimensional array of integers that represented pixel colours. After traversing
the array vertically and exposing the upper-most opaque pixels, it produced a bitmap that represented an image of
the spaceships' registration number.

    1 0 0 1 0 0 1 1 0 0 1 1 1 0 0 0 1 1 0 0 1 1 1 1 0
    1 0 0 1 0 1 0 0 1 0 1 0 0 1 0 1 0 0 1 0 1 0 0 0 0
    1 1 1 1 0 1 0 0 0 0 1 1 1 0 0 1 0 0 0 0 1 1 1 0 0
    1 0 0 1 0 1 0 1 1 0 1 0 0 1 0 1 0 0 0 0 1 0 0 0 0
    1 0 0 1 0 1 0 0 1 0 1 0 0 1 0 1 0 0 1 0 1 0 0 0 0
    1 0 0 1 0 0 1 1 1 0 1 1 1 0 0 0 1 1 0 0 1 0 0 0 0
    
After I realised what my output was, I parsed the black pixels as octothorps and the transparent as spaces and got the
below output. This was interesting to me as it meant you couldn't programmatically test your solution and therefore
required human interpretation. I suppose you could use an OCR (Optical Character Recognition) software.


    #     #     # #     # # #       # #     # # # #  
    #     #   #     #   #     #   #     #   #        
    # # # #   #         # # #     #         # # #    
    #     #   #   # #   #     #   #         #        
    #     #   #     #   #     #   #     #   #        
    #     #     # # #   # # #       # #     #        

## What I Learned

### Kotlin & Kotlin Gradle DSL
A friend of mine had been raving about Kotlin for months at work, and I finally decided to start learning it when I
started the Advent of Code. Having written in nothing but Java 10 hours a day for 2 years, I was amazed by how easy it
was to pick the language up. The first thing I noticed was how succinct the language was syntactically. Most simple
functions could be one-liners; return types were mostly implicit; and data classes turned huge Java bean classes into
one line too.

### Data Structures & Algorithms
Graphs, trees and mazes were a common theme and meant that path-finding algorithms were common.

* [DFS (Depth First Search)](https://en.wikipedia.org/wiki/Depth-first_search)
* [BFS (Breadth First Search)](https://en.wikipedia.org/wiki/Breadth-first_search)
* [Dijkstra Algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm)
* [Flooding Algorithm](https://en.wikipedia.org/wiki/Flooding_algorithm)

Where there are Data Structures & Algorithms, there are always performance considerations...

### Performance
Implementing the aforementioned data structures and algorithms means that some solutions needed refactoring for 
performance optimisation due to the vast puzzle input sizes. This meant that careful consideration was needed when 
choosing data structures and designing the algorithms. It even meant that in some cases, a brute-force approach was 
impossible due to it taking an eternity to run (Looking at you, Day 12... simulating the repetition of time).

For the most part, only runtime-complexity was a concern. There were few occasions where space-complexity was an issue.

## Answer Table

| Day 	| Part 1 	| Part 2 	        | Name                                      |
|-------|-----------|-------------------|----------------------------------------   |
| 01   	| 3184233  	| 4773483           | The Tyranny of the Rocket Equation        |
| 02   	| 10566835 	| 2347              | 1202 Program Alarm                        |
| 03   	| 529      	| 20386         	| Crossed Wires                             |                    
| 04   	| 466      	| 292     	        | Secure Container                          |                                 
| 05   	| 5044655  	| 7408802 	        | Sunny with a Chance of Asteroids          |                                         
| 06   	| 314702   	| 439            	| Universal Orbit Map                       |                                
| 07   	| 21860    	| 2645740  	        | Amplification Circuit                     |
| 08   	| 2904     	| HGBCF         	| Space Image Format                        |
| 09   	| 3100786347| 87023    	        | Sensor Boost                              |
| 10  	| 263      	| 1110     	        | Monitoring Station                        |
| 11  	| 1564     	| RFEPCFEB 	        | Space Police                              |
| 12  	| 7722     	| 292653556339368  	| The N-Body Problem                        |
| 13  	| 247     	| 12954    	        | Care Package                              |
| 14  	| 1037742  	| 1572358  	        | Space Stoichiometry                       |
| 15  	| 212      	| 358             	| Oxygen System                             |
| 16  	| 77038830 	| 28135104         	| Flawed Frequency Transmission             |
| 17  	| 7404    	| 929045           	| Set and Forget                            |
| 18  	| -       	| -             	| Many-Worlds Interpretation                |
| 19  	| 181      	| 4240964          	| Tractor Beam                              |
| 20  	| 526     	| 6292             	| Donut Maze                                |
| 21  	| -       	| -             	| Springdroid Adventure                     |
| 22  	| -       	| -             	| Slam Shuffle                              |
| 23  	| -       	| -             	| Category Six                              |
| 24  	| -       	| -             	| Planet of Discord                         |
| 25  	| -       	| -             	| Cryostasis                                |