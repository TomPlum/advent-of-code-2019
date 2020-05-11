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

#### Most Fun

## What I Learned

### Kotlin & Kotlin Gradle DSL

## Answer Table

| Day 	| Part 1 	| Part 2 	        | Name                                      |
|-------|-----------|-------------------|----------------------------------------   |
| 01   	| 3184233  	| 4773483           | The Tyranny of the Rocket Equation        |
| 02   	| 10566835 	| 2347              | 1202 Program Alarm                        |                               |
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
| 19  	| 181      	| -             	| Tractor Beam                              |
| 20  	| -       	| -             	| Donut Maze                                |
| 21  	| -       	| -             	| Springdroid Adventure                     |
| 22  	| -       	| -             	| Slam Shuffle                              |
| 23  	| -       	| -             	| Category Six                              |
| 24  	| -       	| -             	| Planet of Discord                         |
| 25  	| -       	| -             	| Cryostasis                                |