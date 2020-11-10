# :christmas_tree: Advent of Code (2019)

![GitHub](https://img.shields.io/github/license/TomPlum/advent-of-code-2019?color=informational)
[![GitHub Issues](https://img.shields.io/github/issues/TomPlum/advent-of-code-2019.svg)](https://github.com/TomPlum/advent-of-code-2019/issues)
![GitHub closed issues](https://img.shields.io/github/issues-closed/TomPlum/advent-of-code-2019?color=brightgreen)
![GitHub](https://img.shields.io/badge/tests-1074-informational)
![GitHub](https://img.shields.io/badge/instructions-96%25-success)
![GitHub](https://img.shields.io/badge/branches-88%25-orange)

## What is Advent of Code?

_Excerpt from the Advent of Code [website](https://adventofcode.com/2019/about);_

    "Advent of Code is an Advent calendar of small programming puzzles for a variety of skill sets
    and skill levels that can be solved in any programming language you like. People use them as a
    speed contest, interview prep, company training, university coursework, practice problems, or
    to challenge each other."

## About
Working through the advent of code while learning [Kotlin](https://kotlinlang.org/). Acts as a nice format for personal development. 
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
  * [Static Code Analysis & Linting](#static-code-analysis--linting)
  * [Testing](#testing)
    * [Java Micro-Benchmarking Harness](#jmh-java-micro-benchmarking-harness)
    * [JUnit5 & AssertK](#junit5--assertk)
    * [Test Driven Development](#test-driven-development)
    * [VisualVM Sampling & Profiling](#visualvm-sampling--profiling)
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

I created two Gradle root projects, `implementation` and `solutions`. With the former having sub-projects, `common`, for
behaviour that is commonly used across multiple days and `test-support` for unit test utility classes.

    -implementation
        -src
        -common
        -test-support
    -solutions

### Design
-Design Patterns

### Static Code Analysis & Linting
I used the [DeteKT](https://detekt.github.io/detekt/index.html) Gradle plugin to perform static code analysis on the
codebase. It produces a report containing all the code-smells that it found based on the set configuration.

### Testing

#### JMH (Java Micro-Benchmarking Harness)
Due to the nature of the puzzle inputs, lots of the second parts scaled the input size significantly.
The shear size of these inputs made the solutions impossible to brute-force or to even wait for with a runtime
complexity of `O(n)`. I used [VisualVM](#visualvm-sampling--profiling) to manually investigate crippling performance
issues, but I wanted an automated solution to run across the whole codebase.

From the [OpenJDK](https://openjdk.java.net/projects/code-tools/jmh/) website;

    "JMH is a Java harness for building, running, and analysing nano/micro/milli/macro benchmarks
    written in Java and other languages targeting the JVM."

#### Junit5 & AssertK
The JUnit5 Jupiter API exposes some really nice functionality in the form of annotations and offers callback 
interfaces for each of the life-cycle events. Utilising the `@ParameterizedTest` can significantly reduce the
quantity of test-methods in your unit tests suites while `@Nested` allows you to organise your tests in nested
classes.

[AssertK](https://git.io/JJd1g) is an assertion library inspired by [AssertJ](https://git.io/JJd1a) but for Kotlin.
I'm familiar with AssertJ and prefer the naming conventions and variety of assertions over the default JUnit offering.

#### Test-Driven Development


#### VisualVM Sampling & Profiling
During the first iterations of the graphing algorithms, runtime performance was an issue. There were several cases where
the first rough implementation passed all the example tests, but didn't suffice for the scaled puzzle input.

This is where [VisualVM](https://visualvm.github.io/) came in handy. The aforementioned desktop-client in tandem with
the [IntelliJ Plugin](https://plugins.jetbrains.com/plugin/7115-visualvm-launcher) made it easy to run a given unit test
with a VisualVM instance attached the JVM process. This allowed me to sample and profile classes of my choice to find
the source of the performance issues.

One problem I ran into (and seemingly many others online too) was that JUnit5 bootstrapped and ran my test so quickly
that it finished before VisualVM even had a change to boot-up. A common solution I'd seen was to simply add a
`Thread.sleep(x)` line at the start of the test method. Although this is the solution I technically went with, I
abstracted it into a [`@WaitForVisualVM`](https://git.io/JJdg1) annotation and created a custom implementation
of the Jupiter APIs [`BeforeTestExecutionCallback`](https://junit.org/junit5/docs/5.0.1/api/org/junit/jupiter/api/extension/BeforeTestExecutionCallback.html)
interface called [`SupportsVisualVM.kt`](https://git.io/JJd2e) which can be added to a test-suite class using the
[`@ExtendWith`](https://junit.org/junit5/docs/5.0.1/api/org/junit/jupiter/api/extension/ExtendWith.html) annotation.

This kept things inline with the 'enterprise-style' aspect of my codebase as it did the following;
- Wrapped the dubious `Thread.sleep()` call with a self-explanatory annotation (and is also documented).
- Removed noise from the test method and ensured that it always runs before test-execution.
- Allows developers to easily disable all waiting for tests in a suite by simply removing the support extension.
- Makes it easier to refactor in the future the as implementation specifics are encapsulated in the annotation.

Although it might seem over-engineered, it's really just a trivial example to demonstrate the concept and its benefits.

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
Day 22, Slam Shuffle, started off in part 1 as nice and easy puzzle with some interesting card shuffling strategies
that work nicely with behavioural design patterns. However, part 2 threw a spanner in the works with what some have
called 'Advent of Math'. The second part increased the deck size from `1x10^5` -> `1x10^14` and the number of shuffles
from 1 to an order of magnitude similar to that of the deck size. The solution in short was to;

1. Represent the shuffling algorithms as linear functions in the form `f(x) = ax + b` (See table below).
2. Create a single aggregate function by composing all the converted linear functions together.
3. Use exponentiation by squaring with modular arithmetic in order to reduce the number of shuffles required.

| Shuffling Technique | Linear Transformation Function |
|---------------------|--------------------------------|
| Deal Into New Stack | `f(x) = m - x - 1`             |
| Cut N               | `f(x) = x - n mod(m)`          |
| Deal Increment N    | `f(x) = n.x mod(m)`            |

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
| 22  	| 2604    	| 79608410258462   	| Slam Shuffle                              | [Link](docs/DAY22.MD)  |
| 23  	| 23815    	| 16666           	| Category Six                              | [Link](docs/DAY23.MD)  |
| 24  	| 32506764 	| 1963             	| Planet of Discord                         | [Link](docs/DAY24.MD)  |
| 25  	| 196872   	| -             	| Cryostasis                                | [Link](docs/DAY25.MD)  |