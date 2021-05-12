# TLDR

[F# Fisher-Yates Solution](https://github.com/ppeg34/locai-challenge/blob/main/fsharp/fisheryates.fsx)

[Scala Solutions](https://github.com/ppeg34/locai-challenge/tree/main/src/main/scala/solvers)

[Scala Tests](https://github.com/ppeg34/locai-challenge/blob/main/src/test/scala/solvers/SolverSpec.scala)

### Setup
[Install java/sbt](https://www.scala-sbt.org/1.x/docs/Setup.html)

Clone this repo:
`git clone https://github.com/ppeg34/locai-challenge.git`
`cd locai-challenge`

Run scala tests:
`sbt test`

Test F# implementation with output
`dotnet fsi fsharp/fisheryates.fsx`

# Project Structure

All of the scala implementations of the algorithms required are in src/main/scala/solvers folder.
There is a scala trait, SolverInterface, that is an interface that the Solvers implement.
the function, runCalc, is a non-outputting core function that simply executes core functionality of a class's 
implemented algorithm.  This exists simply to exclude type translations from being considered as i profiled different
implementations.

Tests for each implementation exist in src/test/scala/solvers folder.
There is a SolverSpec trait used as an interface which contains the majority of the testing logic for solvers.
Inheriting classes simply extend this trait and define the corresponding solver to get profiled.
Optionally, they can override iterations.  This is the number of times a solver is executed for a profile run.
I default it to 10000, but this was too much for some of the solvers and I override them so that they execute in a
reasonable amount of time.

# Overall Approach

I initially sought to meet the requirements of the problem in the most naive
way possible.  The result was aptly-named NaiveSolver. This gave me a very basic implementation to write 
simple tests against as well as a benchmark to meet (or improve upon) as I aimed to optimize my less naive solutions.

After a quick naive solution and some quick sanity-checking tests, I began implementing some additional solvers.

I fairly quickly realized that shuffling a range of numbers 1 to 10000 would likely be the most effective means of 
ensuring unique numbers that stayed in the range of 1 to 10000.  

## Naive Solver

This simply generates a range of numbers from 1 to 10000 and randomizes them using scala's built-in shuffle 
implementation. In the wild, this is the solution I'd default to as I can think of it on the spot, its fast, and other
people can easily see how it works.

## Short Solver

This was a quick attempt at optimizing the naive solver.  I simply tried to use Short data types instead of Ints, however
the benefits were negligible if any.  This wasn't surprising to me as the costly operation of this algorithm is
in changing the pointers to the data in the arraybuffer, not in changing the data itself.

## Fisher-Yates

I quickly looked into scala's source code to see how shuffle was implemented.  By eliminating alternative type handling,
and streamlining the implementation I was able to shave off a significant amount of time from the algorithm.  This ended 
up being the fastest option.

I implemented this same algorithm in F# fsharp/fisheryates.fsx

## Fisher-Yates Recursive

This was a recursive implementation of the fisher-yates algorithm.  When I set out to make this implementation I was hoping
I could implement by only using head, tail, reverse, and the cons operator as these are highly optimized when dealing with 
immutable list structures in scala, but splitting the list ultimately had to be done which hurt performance.

Ultimately, this highlighted that this algorithm was a good use case for a mutable indexed list due to constant time 
updating and accessing shown in the Fisher-Yates solution above.

## Overhand Solver

This is an implementation of an overhand card shuffling technique. This is DOES NOT generate a random list as implemented.
It need to execute an additional number of iterations in order to ensure that the list is shuffled sufficiently, but I 
didn't have the time to determine exactly where to set shuffling iterations and average subset size to ensure random (plus
the challenge didn't specify exactly what constitutes "random" for the sake fo the exercise).  Recursion was a very
natural way of representing how this style of card shuffling happens in real life, so I found it interesting for that
purpose at the very least. 

Additionally this algorithm is very tweakable and is capable of streaming and scaling the shuffle operation.  You can 
think of it as two stacks (represented by a card shuffler's right and left hands) where a subset of random size (I used
up to 100 for this implementation) is taken off the top of one stack and placed into the other stack repetitively until
this stack is depleted.  The process is then repeated until randomness parameters are met.

## Sort Solver

This was a simple solver with admirable performance, that operated differently from fisher-yates.  
I mapped a random int to each number in a range from 1 to 10000.
Then I sorted the list by the random value.

# Abbreviated Results

```
FisherYatesSolver:              1403505ns per iteration 
NaiveSolver:                    1773407ns per iteration 
ShortSolver:                    1731974ns per iteration 
SortSolver:                     3805183ns per iteration 
OverhandSolver:                27839818ns per iteration 
FisherYatesRecursiveSolver:   426600687ns per iteration
```
