# :christmas_tree: Advent of Code (2019)

[![GitHub Issues](https://img.shields.io/github/issues/TomPlum/advent-of-code-2019.svg)](https://github.com/TomPlum/advent-of-code-2019/issues)
![GitHub closed issues](https://img.shields.io/github/issues-closed/TomPlum/advent-of-code-2019?color=brightgreen)
![GitHub](https://img.shields.io/github/license/TomPlum/advent-of-code-2019?color=informational)

## What is Advent of Code?

_Excerpt from the Advent of Code [website](https://adventofcode.com/2019/about);_

    "Advent of Code is an Advent calendar of small programming puzzles for a variety of skill sets
    and skill levels that can be solved in any programming language you like. People use them as a
    speed contest, interview prep, company training, university coursework, practice problems, or
    to challenge each other."

## About
Working through it while learning [Kotlin](https://kotlinlang.org/). Acts as a nice format for personal development. 
I'm treating the code-base a little more _enterprise-esque_, for lack of a better term.
Meaning I'm focusing more on design, readability, and ensuring code is test-driven with full coverage etc. 
This doesn't mean, however, that I'm not considering performance. Any solutions that don't meet a respectable order of
runtime complexity will be refactored and improved upon in a later pass of the days.

## Contents
* [Getting Started](#getting-started)
  * [Gradle Tasks](#gradle-tasks)
* [The Codebase](#the-codebase)
  * [Package Structure](#package-structure)
  * [Design](#design)
  * [Testing](#testing)
* [The Days](#the-days)
  * [Most Challenging](#most-challenging-day-18)
  * [Mathematically Challenging](#mathematically-challenging-day-22)
  * [Most Fun](#most-fun-day-8)
* [What I Learned](#what-i-learned)
  * [Kotlin](#kotlin--kotlin-gradle-dsl)
  * [Data Structures & Algorithms](#data-structures--algorithms)
  * [Performance & Runtime Complexity](#performance)
* [Answer Table & Puzzle Documentation](#answer-table)

## Getting Started
If you wanted to clone the repository and run the solutions, you need only clone/download, import it as a Gradle project
in your IDE, and run the respective solution file.

Annotation Processing must be enabled in your IDE for the JMH tests to run.
In IntelliJ; \
`Preferences -> Build, Execution, Deployment -> Compiler -> Annotation Processors`

### Gradle Tasks
| Task               | Description                                                               |
|--------------------|---------------------------------------------------------------------------|
| `test`             | Runs the unit tests for the `implementation` and `common` sub-projects.   |
| `testCoverage`     | Runs the unit tests, calculates the coverage and verifies that its > 90%. |       
| `benchmark`        | Runs the JMH tests for the `implementation` sub-project.                  |

## The Codebase
### Package Structure
The package structure was something that changed almost every day. My goal was "package-by-feature". For the first few
days it seemed like I'd just end up having 25 different packages whose names were relevant keywords from that day.
However, towards the latter half of the days, there were consistencies in the naming that started to make the language
a little more ubiquitous. This allowed me to start grouping packages together and start abstracting out common code.

I created two Gradle root projects, `implementation` and `solutions`. With the former having a sub-project, `common`, for
behaviour that is commonly used across multiple days.

    -implementation
        -common
    -solutions

### Design
-Design Patterns

### Testing

#### JMH (Java Micro-Benchmarking Harness)
Due to the nature of the puzzle inputs, lots of the second parts scaled the input size significantly.
The shear size of these inputs made the solutions impossible to brute-force or to even wait for with a runtime
complexity of O(1). I used [VisualVM](#visualvm-sampling--profiling) to manually investigate crippling performance
issues, but I wanted an automated solution to run across the whole codebase.

From the [OpenJDK](https://openjdk.java.net/projects/code-tools/jmh/) website;

    JMH is a Java harness for building, running, and analysing nano/micro/milli/macro benchmarks
    written in Java and other languages targeting the JVM.


#### Junit5 & AssertK
#### Test-Driven Development
#### VisualVM Sampling & Profiling

## The Days
### Most Challenging (Day 18)
This day was an odd one. Usually the implementation is the 'easy' bit, and figuring out the theory behind the
solution is the harder bit that can take a while. However, Day 18s theory was simple. It was a maze
filled with doors with corresponding keys, and you had the find the shortest path to collect all keys. So it was just
a case of using an exhaustive [DFS (Depth First Search)](https://en.wikipedia.org/wiki/Depth-first_search) Algorithm
that would graph all the keys and their respective weights to any other accessible ones. Then running
[Dijkstra's Algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm) on the weighted graph to find the shortest path. 
I just couldn't figure out how to implement it. I spent so many hours trying different things until I eventually managed 
to get 4 of the 5 examples working. Example 5, however, just ran indefinitely and so did my puzzle input. I needed to 
improve the performance of my graphing algorithm, so my solution input didn't take a literal eternity to run.

### Mathematically Challenging (Day 22)

### Most Fun (Day 8)
This day was the most fun because I didn't understand what was actually going on until I'd successfully implemented a
solution. It was only shortly after re-reading the documentation that I understand what was happening. The output
below is the result of flattening a three-dimensional array of integers that represented pixel colours. After traversing
the array vertically and exposing the upper-most opaque pixels, it produced a bitmap that represented an image of
the spaceships' registration number.

    1 0 0 1 0 0 1 1 0 0 1 1 1 0 0 0 1 1 0 0 1 1 1 1 0
    1 0 0 1 0 1 0 0 1 0 1 0 0 1 0 1 0 0 1 0 1 0 0 0 0
    1 1 1 1 0 1 0 0 0 0 1 1 1 0 0 1 0 0 0 0 1 1 1 0 0
    1 0 0 1 0 1 0 1 1 0 1 0 0 1 0 1 0 0 0 0 1 0 0 0 0
    1 0 0 1 0 1 0 0 1 0 1 0 0 1 0 1 0 0 1 0 1 0 0 0 0
    1 0 0 1 0 0 1 1 1 0 1 1 1 0 0 0 1 1 0 0 1 0 0 0 0
    
After I realised what my output was, I parsed the black pixels as octothorps, and the transparent as spaces and got the
below output. This was interesting to me as it meant you couldn't programmatically test your solution and therefore
required human interpretation. I suppose you could use an OCR (Optical Character Recognition) software.


    #     #     # #     # # #       # #     # # # #  
    #     #   #     #   #     #   #     #   #        
    # # # #   #         # # #     #         # # #    
    #     #   #   # #   #     #   #         #        
    #     #   #     #   #     #   #     #   #        
    #     #     # # #   # # #       # #     #        
    
The moment it clicked, and I saw the ASCII style text printed out, it put a huge grin on my face as this was the first
time I'd seen such a niche puzzle in Advent of Code.

## What I Learned
### Kotlin & Kotlin Gradle DSL
A friend of mine had been raving about Kotlin for months at work, and I finally decided to start learning it when I
started the Advent of Code. Having written in nothing but Java 10 hours a day for 2 years, I was amazed by how easy it
was to pick the language up. The first thing I noticed was how succinct the language was syntactically. Most simple
functions could be one-liners; return types were mostly implicit; and features such as data classes removed a lot of the
boilerplate code that is prevalent in enterprise Java code.

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

| Day 	| Part 1 	| Part 2 	        | Name                                      | Documentation          |
|-------|-----------|-------------------|-------------------------------------------|------------------------|
| 01   	| 3184233  	| 4773483           | The Tyranny of the Rocket Equation        | [Link](docs/DAY1.MD)   |
| 02   	| 10566835 	| 2347              | 1202 Program Alarm                        | [Link](docs/DAY2.MD)   |
| 03   	| 529      	| 20386         	| Crossed Wires                             | [Link](docs/DAY3.MD)   |                   
| 04   	| 466      	| 292     	        | Secure Container                          | [Link](docs/DAY4.MD)   |                                 
| 05   	| 5044655  	| 7408802 	        | Sunny with a Chance of Asteroids          | [Link](docs/DAY5.MD)   |                                         
| 06   	| 314702   	| 439            	| Universal Orbit Map                       | [Link](docs/DAY6.MD)   |                                 
| 07   	| 21860    	| 2645740  	        | Amplification Circuit                     | [Link](docs/DAY7.MD)   |
| 08   	| 2904     	| HGBCF         	| Space Image Format                        | [Link](docs/DAY8.MD)   |
| 09   	| 3100786347| 87023    	        | Sensor Boost                              | [Link](docs/DAY9.MD)   |
| 10  	| 263      	| 1110     	        | Monitoring Station                        | [Link](docs/DAY10.MD)  |
| 11  	| 1564     	| RFEPCFEB 	        | Space Police                              | [Link](docs/DAY11.MD)  |
| 12  	| 7722     	| 292653556339368  	| The N-Body Problem                        | [Link](docs/DAY12.MD)  |
| 13  	| 247     	| 12954    	        | Care Package                              | [Link](docs/DAY13.MD)  |
| 14  	| 1037742  	| 1572358  	        | Space Stoichiometry                       | [Link](docs/DAY14.MD)  |
| 15  	| 212      	| 358             	| Oxygen System                             | [Link](docs/DAY15.MD)  |
| 16  	| 77038830 	| 28135104         	| Flawed Frequency Transmission             | [Link](docs/DAY16.MD)  |
| 17  	| 7404    	| 929045           	| Set and Forget                            | [Link](docs/DAY17.MD)  |
| 18  	| -       	| -             	| Many-Worlds Interpretation                | [Link](docs/DAY18.MD)  |
| 19  	| 181      	| 4240964          	| Tractor Beam                              | [Link](docs/DAY19.MD)  |
| 20  	| 526     	| 6292             	| Donut Maze                                | [Link](docs/DAY20.MD)  |
| 21  	| 19350258 	| 1142627861       	| Springdroid Adventure                     | [Link](docs/DAY21.MD)  |
| 22  	| 2604    	| -             	| Slam Shuffle                              | [Link](docs/DAY22.MD)  |
| 23  	| 23815    	| 16666           	| Category Six                              | [Link](docs/DAY23.MD)  |
| 24  	| -       	| -             	| Planet of Discord                         | [Link](docs/DAY24.MD)  |
| 25  	| -       	| -             	| Cryostasis                                | [Link](docs/DAY25.MD)  |